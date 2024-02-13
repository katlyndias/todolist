package tech.ada.java.todolist.domain;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    @PostMapping("/todo-item") // GetMapping não insere informações, é só pra leitura, devemos usar Post, mas fizemos para estudo/teste
    // sem o /todo-item quando digito localhost:8080 ele acessa direto esse metodo
    public void inserirTodoItem(){
        TodoItem todoItem = new TodoItem(); // criou o objeto do construtor vazio
        todoItem.setTitulo("Acordar"); // usou o set para colocar só o título

        todoItemRepository.save(todoItem);
    }
}


// GET -> usado para leitura passando informações por URL, rápido, buscando informação
// POST -> toda vez que uso POST estou modificando o servidor, inserindo algo novo, por dentro (body), geralmente o JSON, e não pela url, se executar n vezes o mesmo, vai acontecer n vezes
// PUT -> parecido com o POST mas nem sempre altera o servidor, usado para atualizar algo que já existe, UP-search, se executar n vezes o mesmo vai atualizar uma vez só, mas o código precisa estar configurado para isso, o nosso código acima na verdade está sempre isnerindo, usado para atualizar tudo
// PATCH -> para alterar/atualizar informações/campos específicos, não cria nada novo
// DELETE -> muitas empresas não usam delete, usam o post e no lugar do save coloca .delete ali em cima, mas não é boa prática
