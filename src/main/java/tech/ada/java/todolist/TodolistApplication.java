package tech.ada.java.todolist;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TodolistApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodolistApplication.class, args);
    }
    // tinhamos feito o toEntity na mão, para transformar em entidade, mas existe outra forma de fazer isso, através de uma ferramenta, que foi importada na biblioteca: modelmapper, a classe que eu preciso
    @Bean // Bean são objetos controlados pela Spring, com isso o Spring sabe que vai cuidar em instanciar pra mim, sem eu precisar ficar criando toda vez
    public ModelMapper modelMapper(){ // modelMapper é uma classe, aqui digo como deve ser criao
        return new ModelMapper();
    }

}
