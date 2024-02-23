package tech.ada.java.todolist.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice // Spring sabe que essa classe é o controlador de Exceções
public class RestControllerAdvice {
    // como lido com as exceções?

    // recebe um excpetionHandler e devolve um Response Entity
    // para qualquer exceção que possa acontecer (genérica) então não faz sentido a exceção trazer o status, que é da camada de Rest, aqui seriam só os erros
    // como não sei ainda o que é, vamos usar a família do 500, a mais genérica possível (erro interno do servidor), pois a dos 400 tá relacionado à regra de negócio e não sabemos ainda qual foi o erro de fato, então o 500 fala que foi um erro no servidor que não sabe lidar
    @ExceptionHandler(Exception.class) // Esse ExceptionHandler trata Exception
    public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex){ // Exception é exceção do nível mais alto, mais genérica possível, podemos depois fazer sobrecarga para exceções mais específicas que já sabemos serem passíveis de ocorrer
        // abaixo é o corpo, então esse http status é o do status que aparece na msg JSON do postman em cima do error e do trace (do corpo)
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());

            // abaixo, o primeiro parametro é o corpo da msg JSON no Postman (no trace e afins), e o segundo é o status pequenininho que aparece lá no Postman também, do ResponseEntity, do lado do tempo de execução
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // classe
    @AllArgsConstructor // construtor completo
    @Getter
    @Setter
    public class ErrorResponse {
         private HttpStatus status; // status do erro
         private String message; // mensagem do erro

    }
}
