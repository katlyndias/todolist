package tech.ada.java.todolist.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // Autodeclarar isso como repositório, é um padrão, boa prática
public interface TodoItemRepository extends JpaRepository<TodoItem, Long> {
    // Repositorio é uma camada de abstração para fazer conversas com e transformações no banco de dados
    // JPA Repositery é um Bean controlado pelo Spring, herda de outras interfaces com várias funcionalidades (inserir, atualizar, deletar, buscar, etc)
    // quem implementar vai definir como vai ser
    // repositórios só são atrelados a uma única entidade

    //JPA vai implementar essa função (Spring Data, que usa a JPA)
    public List<TodoItem> findByTitulo(String titulo); // lista pois posso ter mais de um TodoItem com o mesmo titulo
}
