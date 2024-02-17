package tech.ada.java.todolist.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

// o @Controller é gerenciado pelo Spring, então se uso ele não preciso dar new TodoController na main
@RestController("/todo") // ResController tem o Controller dentro dele, mas por ser rest, vou poder usar métodos http, nível acima do controler
// o controlador vai ser acessado através desse caminho /todo (na Rest)

public class TodoController {
    // Repository é um atributo de Controller, pois para Controller ser construída tem que receber o repositório
    private TodoItemRepository todoItemRepository; // para respeitar o SOLID e possibilitar inversão de dependência

    public TodoController(TodoItemRepository todoItemRepository){
        this.todoItemRepository = todoItemRepository; // só é instanciado aqui no construtor, por isso é final lá em cima, aqui foi colocado apenas pra ficar visual,pra mostrar que o spring está cuidando
    }


    // quero que esse método seja acessado quando a porta /todo for acessada através do método GetMapping
    // método do Controller, que nesse caso vai inserir o todoItem
    @PostMapping("/todo-item")
    // GetMapping não insere informações, é só pra leitura, devemos usar Post, mas fizemos para estudo/teste
    // sem o /todo-item quando digito localhost:8080 ele acessa direto esse metodo
    // método para cadastrar abaixo:
    public ResponseEntity<TodoItem> cadastrarItem(@RequestBody TodoItemRequest request) { // estamos chamando repositório para salvar o todoItemRequest que chegar pela requisição (body), com os atributos: id, etc
        // request é um objeto TodoItemRequest
        // o TodoItem é a entidade e o TodoItemRequest é uma classe para receber a informação de fora sem expor a entidade TodoItem porque não é o mesmo objeto e dentro da classe TodoItemRepository falamos que ele é de TodoItem (só sabe acessar essa entidade/tabela e não uma classe!)
        // o spring vai criar o TodoItemRequest
        // o repositório só lida com a entidade TodoItem, ele não lida com a entidade TodoItemRequest
        // nao posso usar casting para converter o request para TodoItem, IntelliJ nem permite, pois não é uma entidade
        // casting é perigoso, cuidado ao usar, é enganar a própria JVM, são raros os momentos em que precisamos fazer isso
        TodoItem todoItemConvertido = request.toEntity();

        // aí posso passar o todoItemConvertido dentro do save porque ele é um todoItem!
        //id vai ser automatico, concluido vai começar como nao concluido e dataHora vai ser automatico também
        TodoItem novoTodoItem = todoItemRepository.save(todoItemConvertido); // save recebe uma entidade genérica (S), pode ser qualquer classe, e retorna a mesma entidade que ele salvou
        return ResponseEntity.status(HttpStatus.CREATED).body(novoTodoItem);
    }

    // Get não tem corpo, é tudo na URL
    // método para buscar todos abaixo:
    @GetMapping("/todo-item")
    public List<TodoItem> buscarTodos() {
        List<TodoItem> listaComTodos = todoItemRepository.findAll(); //o findAll() não recebe parámetro e retorna uma lista List<TodoItem>, por isso poderia retornar direto, por retornar uma lista, mas criando a variável fica mais didático
        return listaComTodos;
    }
}

// obs: se derrubar o servidor e colocar só o get ele não busca nada pois o banco estaria vazio, teria que fazer POST de novo antes do GET
// lista no JSON é representada por cochetes [] e cada chave é um objeto {}

// GET -> usado para leitura passando informações por URL, rápido, buscando informação
// POST -> toda vez que uso POST estou modificando o servidor, inserindo algo novo, por dentro (body), geralmente o JSON, e não pela url, se executar n vezes o mesmo, vai acontecer n vezes
// PUT -> parecido com o POST mas nem sempre altera o servidor, usado para atualizar algo que já existe, UP-search, se executar n vezes o mesmo vai atualizar uma vez só, mas o código precisa estar configurado para isso, o nosso código acima na verdade está sempre isnerindo, usado para atualizar tudo
// PATCH -> para alterar/atualizar informações/campos específicos, não cria nada novo
// DELETE -> muitas empresas não usam delete, usam o post e no lugar do save coloca .delete ali em cima, mas não é boa prática


// TodoItemRequest item1 = new TodoItemRequest(
//        "Acordar",
//        "Estou acordando",
//        LocalDate.now());
//
// depois que deu new não pode ser modificada, mas posso dar new várias vezes com vários valores diferentes, porque vai ser um outro objeto, um novo por cima foi criado, com o mesmo nome de variável
// por exemplo:
// item1 = new TodoItemRequest(
//        "Acordar2",
//        "Estou acordando2",
//        LocalDate.now());
// dar new não é alterar, é recriar do zero
// quando dou o new ele cria o objeto na memoria, se dou new de novo ele joga fora o q tinha antes e cria outro