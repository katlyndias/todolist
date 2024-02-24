package tech.ada.java.todolist.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // Autodeclarar isso como repositório, é um padrão, boa prática
public interface TodoItemRepository extends JpaRepository<TodoItem, Long> {
    // Repositorio é uma camada de abstração para fazer conversas com e transformações no banco de dados
    // JPA Repositery é um Bean controlado pelo Spring, herda de outras interfaces com várias funcionalidades (inserir, atualizar, deletar, buscar, etc)
    // quem implementar vai definir como vai ser
    // repositórios só são atrelados a uma única entidade

    //JPA vai implementar essa função (Spring Data, que usa a JPA)
    List<TodoItem> findByTitulo(String titulo); // lista pois posso ter mais de um TodoItem com o mesmo titulo

    // Query - genérico pra atender todos os bancos (serve pra qualquer um: MySql, PostgreSQL, etc)
    @Query("SELECT t FROM TodoItem t WHERE t.titulo = ?1") //?1 é o primeiro dentro do parenteses abaixo
    List<TodoItem> findByTituloQuery(String titulo);

    // Query nativa - nem todo banco aceita, todo_item é sintaxe específica do H2
    @Query(value = "SELECT * FROM todo_item WHERE titulo = ?1", nativeQuery = true) //?1 é o primeiro dentro do parenteses abaixo
    List<TodoItem> findByTituloQueryNativa(String titulo);


}
