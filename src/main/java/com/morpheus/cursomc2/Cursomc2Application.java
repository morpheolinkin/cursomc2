package com.morpheus.cursomc2;

import com.morpheus.cursomc2.domain.*;
import com.morpheus.cursomc2.domain.enums.TipoCliente;
import com.morpheus.cursomc2.repository.*;
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
    @Autowired
    private CidadeRepository cidadeRepository;
    @Autowired
    private EstadoRepository estadoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;

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

        Estado est1 = new Estado(null, "Minas Gerais");
        Estado est2 = new Estado(null, "São Paulo");

        Cidade c1 = new Cidade(null, "Uberlandia", est1);
        Cidade c2 = new Cidade(null, "São Paulo", est2);
        Cidade c3 = new Cidade(null, "Campinas", est2);

        est1.getCidades().add(c1);
        est2.getCidades().addAll(asList(c2, c3));

        estadoRepository.saveAll(asList(est1, est2));
        cidadeRepository.saveAll(asList(c1, c2, c3));

        Cliente cl1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
        cl1.getTelefone().addAll(asList("27363323", "93838393"));

        Endereco e1 = new Endereco(null, "Rua Flores", "300",
                "Apto 303", "Jardim", "38220834", cl1, c1);

        Endereco e2 = new Endereco(null, "Avenida Matos", "105",
                "Sala 800", "Centro", "38777012", cl1, c2);

        cl1.getEnderecos().addAll(asList(e1, e2));

        clienteRepository.save(cl1);
        enderecoRepository.saveAll(asList(e1, e2));



    }
}
