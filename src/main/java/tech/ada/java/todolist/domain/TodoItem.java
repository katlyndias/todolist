package tech.ada.java.todolist.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Entity // Anotação do JPA que mostra que a classe é uma entidade do banco de dados
@Setter // Anotação do Lombok que realiza os gets dos campos
@Getter // Anotação do Lombok que realiza os sets dos campos
@NoArgsConstructor //Anotação que faz um construtor sem os campos da classe
@AllArgsConstructor // Anotação que faz um construtor com todos os campos da classe
@EqualsAndHashCode // Anotação que realiza o Equals e HashCode da classe

public class TodoItem {
    // Declaração dos atributos importantes para o TodoItem existir
    // Atributos privados para não serem acessados/alterados diretamente, precisa colocar getter e setter
    @Id // Anotação que mostra que o id é a chave primária da tabela
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Anotação que faz o autoincremento do id
    private Long id;
    private String titulo;
    private String descricao;
    private boolean concluida;
    private LocalDateTime dataHora;

}
