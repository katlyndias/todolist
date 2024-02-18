package tech.ada.java.todolist.domain;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

// public class TodoItemRequest {
//    // criado para informações que eu realmente preciso receber de fora
//    // na minha regra de negocio é o próprio banco/entidade que gera id, não preciso
//    private String titulo; // preciso receber
//    private String descricao; // preciso receber
//    // concluido nao preciso, vamos colocar que toda tarefa comeca como nao concluida como regra de negocio
//    // dataHoraCriacao nao preciso receber
//    private LocalDate prazoFinal; // preciso receber
//
//}

//
@Getter
public class TodoItemRequest {
        private String titulo;
        private String descricao;
        private LocalDate prazoFinal;


    // método que está convertendo uma request em entidade
    public TodoItem toEntity(){
        return TodoItem.createTodoItem(titulo, descricao, prazoFinal);
    }
}

//     o que o Lombok faria:
//    public getTitulo(){
//        return titulo();
//    }

//    o que o record faz:
//    public Titulo(){
//        return titulo;
//    }



// record é classe Java que já cria os getters e setters e é recomendado para registros, como DTO, pra pegar o que de fato o usuario falou, e evitar que fique vulneravel pra alguem editar
// embora record seja final, toda vez que faço um post ele cria um objeto novo
// podemos usar uma classe normal se quisermos com os @Getter e @Setter