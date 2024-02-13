package tech.ada.java.todolist.domain;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// o @Controller é gerenciado pelo Spring, então se uso ele não preciso dar new TodoController na main
@RestController("/todo") // ResController tem o Controller dentro dele, mas por ser rest, vou poder usar métodos http, nível acima do controler
// o controlador vai ser acessado através desse caminho /todo (na Rest)


public class TodoController {
    // Repository é um atributo de Controller, pois para Controller ser construída tem que receber o repositório
    private final TodoItemRepository todoItemRepository; // para respeitar o SOLID e possibilitar inversão de dependência

    public TodoController(TodoItemRepository todoItemRepository){
        this.todoItemRepository = todoItemRepository; // só é instanciado aqui no construtor, por isso é final lá em cima
    }

    // quero que esse método seja acessado quando a porta /todo for acessada através do método GetMapping
    // método do Controller, que nesse caso vai inserir o todoItem
    @GetMapping("/todo-item") // GetMapping não insere informações, normalmente é Post, mas fizemos para estudo/teste
    // sem o /todo-item quando digito localhost:8080 ele acessa direto esse metodo
    public void inserirTodoItem(){
        TodoItem todoItem = new TodoItem(); // criou o objeto do construtor vazio
        todoItem.setTitulo("Acordar"); // usou o set para colocar só o título

        todoItemRepository.save(todoItem);
    }
}
