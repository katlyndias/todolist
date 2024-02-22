package tech.ada.java.todolist.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
public class AlteraTodoItemCompletoRequest {
        String titulo;
        String descricao;
        Boolean concluida;
        LocalDateTime dataHora;
        LocalDate prazoFinal;
    // construtor - reescrevendo o default
    public AlteraTodoItemCompletoRequest(String titulo, String descricao, Boolean concluida, LocalDateTime dataHora, LocalDate prazoFinal){
        this.titulo = Objects.requireNonNull(titulo, "Título é obrigatório"); // obrigando a não ser nulo, a partir do Java 16 essa classe Objects, tem versão sem mensagem e com sobrecarga com mensagem
        this.descricao = Objects.requireNonNull(descricao);
        this.concluida = Objects.requireNonNull(concluida);
        this.dataHora = Objects.requireNonNull(dataHora);
        this.prazoFinal = Objects.requireNonNull(prazoFinal);
    }

    //poderia fazer meu proprio NonNull e usar no lugar do Objects.requireNonNull()
//    public <T>T myNotNull (T objeto){ // usando generico
//        if (objeto == null) throw new NullPointerException();
//
//        return objeto; // fora do if
//    }
}

// OBS: record não é bom para validação, bom só para criar objeto cru.
// Se precisarmos usar o construtor completo que fizemos e tirar o default o Spring usa o completo, por isso conseguimos usar o Objects.
// Mais seguro usar o Objects.requireNonNull() em vez de @NotNull ou @NotBlank