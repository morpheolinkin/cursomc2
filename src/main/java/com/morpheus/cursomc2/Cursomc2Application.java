package com.morpheus.cursomc2;

import com.morpheus.cursomc2.domain.Categoria;
import com.morpheus.cursomc2.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static java.util.Arrays.asList;

@SpringBootApplication
public class Cursomc2Application implements CommandLineRunner {
    @Autowired
    private CategoriaRepository categoriaRepository;

    public static void main(String[] args) {
        SpringApplication.run(Cursomc2Application.class, args);
    }

    @Override
    public void run(String... args) {
        Categoria cat1 = new Categoria(null, "Informática");
        Categoria cat2 = new Categoria(null, "Escritório");

        categoriaRepository.saveAll(asList(cat1, cat2));
    }
}
