package tech.ada.java.todolist.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record AlteraTodoItemCompletoRequest(String titulo, String descricao, Boolean concluida, LocalDateTime dataHora, LocalDate prazoFinal) {


}
