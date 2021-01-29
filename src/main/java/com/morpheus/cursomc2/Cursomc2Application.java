package com.morpheus.cursomc2;

import com.morpheus.cursomc2.domain.Categoria;
import com.morpheus.cursomc2.domain.Produto;
import com.morpheus.cursomc2.repository.CategoriaRepository;
import com.morpheus.cursomc2.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static java.util.Arrays.asList;

@SpringBootApplication
public class Cursomc2Application implements CommandLineRunner {
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private ProdutoRepository produtoRepository;

    public static void main(String[] args) {
        SpringApplication.run(Cursomc2Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception{
        Categoria cat1 = new Categoria(null, "Informática");
        Categoria cat2 = new Categoria(null, "Escritório");

        Produto p1 = new Produto(null, "Computador", 2000.00);
        Produto p2 = new Produto(null, "Impressora", 800.00);
        Produto p3 = new Produto(null, "Mouse", 80.00);

        cat1.getProdutos().addAll(asList(p1, p2, p3));
        cat2.getProdutos().add(p2);

        p1.getCategorias().add(cat1);
        p2.getCategorias().addAll(asList(cat1, cat2));
        p3.getCategorias().add(cat1);

        categoriaRepository.saveAll(asList(cat1, cat2));
        produtoRepository.saveAll(asList(p1, p2, p3));


    }
}
