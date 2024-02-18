package tech.ada.java.todolist.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.apache.tomcat.util.json.JSONFilter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity // Notação do JPA que mostra que a classe é uma entidade do banco de dados
@Setter // Notação do Lombok que realiza os gets dos campos
@Getter // Notação do Lombok que realiza os sets dos campos
//@NoArgsConstructor // Notação que faz um construtor sem os campos da classe (default)
//@AllArgsConstructor // Notação que faz um construtor com todos os campos da classe
@EqualsAndHashCode // Notação que realiza o Equals e HashCode da classe

public class TodoItem {
    // Declaração dos atributos importantes para o TodoItem existir
    // Atributos privados para não serem acessados/alterados diretamente, precisa colocar getter e setter
    @Id // Anotação que mostra que o id é a chave primária da tabela
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Anotação que faz o autoincremento do id
    private Long id;
    private String titulo;
    private String descricao;
    private Boolean concluida;
    private LocalDateTime dataHora;
    private LocalDate prazoFinal;

    // criei um construtor com os parâmetros que precisamos pra converter em entidade
    // a entidade precisa saber se construir da forma certa
    // o certo é ser privado pra ninguém que esteja fora dessa classe consiga usar
    public TodoItem(String titulo, String descricao, LocalDate prazoFinal){
        this.titulo = titulo;
        this.descricao = descricao;
        this.prazoFinal = prazoFinal;
        this.concluida = false; // regra de negócio na entidade
        this.dataHora = LocalDateTime.now(); // regra de negocio na entidade
    }

    // com modelMapper preciso usar o construtor default mas também quero colocar regras de negocio, então vou sobrescrever o construtor default porque sei que ele vai ser usado
    // o modelMapper é o que ele deveria ensinar pra gente, mas ele gosta de mostrar mais de uma opção e prefere o toEntity porque segue padrões de projeto, ele sempre tenta fugir de depender de ferramenta
    public TodoItem(){
        this.concluida = false; // e coloco a regra de negócio na entidade
        this.dataHora = LocalDateTime.now(); // e coloco regra de negocio na entidade
    }

//    // construtor default - > posso apagar e colocar o @NoArgsConstructor para a JPA conseguir fazer a leitura da volta
//    public TodoItem(){
//
//    }

    // Não precisamos do método abaixo, mas é uma boa prática, padrão de projeto chamado FACTORY
    // Método autodescritivo pelo nome createTodoItem
    // Podemos apagar pois a JPA usa o construtor vazio para fazer a leitura
//    public static TodoItem createTodoItem(String titulo, String descricao, LocalDate prazoFinal){
//        return new TodoItem(
//                titulo,
//                descricao,
//                prazoFinal,
//                false,
//                LocalDateTime.now());
//    }

}

//JSON dos itens acima para o POST:
//{
//    "titulo": "teste",
//        "descricao": "teste2222",
//        "concluida": true,
//        "dataHora": "2024-02-07T12:30:45"
//}

// acordo entre desenvolvedores: entidade não se trafega na internet, é algo interno da aplicação do back-end, só quem tem conhecimento é a aplicação interna e o banco de dados, por questões de boas práticas e de segurança
// pra isso existem os conceitos de request e DTO (data transfer object), request pode ser considerada um tipo de DTO, o objeto que está habilitado e só existe para trafegar informações, sei que estão expostas

// Anotações:
// Quando crio um projeto novo ele tem que ter um motivo pra existir, o que define o que ele é, quando olhamos pras classes Controller, Service, Repository e Entity qual dos quatro diz o que o software é/faz/motivo de existir?
// Controller é só uma porta de entrada, não diz o que o software é
// Repository é uma conexão com o banco de dados, não diz o que o software é
// Service? parcialmente, pode-se dizer que sim, tendo em vista que tem regras de negócio pode dizer o que o software faz
// Entidade? descrevem melhor, pois contém as classes, o que vai ser salvo no banco de dados, mas em arquitetura avança das regras de negocio ficam na propria entidade