package com.morpheus.cursomc2.services;

import com.morpheus.cursomc2.domain.*;
import com.morpheus.cursomc2.domain.enums.EstadoPagamento;
import com.morpheus.cursomc2.domain.enums.TipoCliente;
import com.morpheus.cursomc2.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;

import static java.util.Arrays.asList;

@Service
@RequiredArgsConstructor
public class DBService {

    private final CategoriaRepository categoriaRepository;
    private final ProdutoRepository produtoRepository;
    private final CidadeRepository cidadeRepository;
    private final EstadoRepository estadoRepository;
    private final ClienteRepository clienteRepository;
    private final EnderecoRepository enderecoRepository;
    private final PedidoRepository pedidoRepository;
    private final PagamentoRepository pagamentoRepository;
    private final ItemPedidoRepository itemPedidoRepository;


    public void instatiateTestDatabase() throws ParseException {
        Categoria cat1 = new Categoria(null, "Informática");
        Categoria cat2 = new Categoria(null, "Escritório");
        Categoria cat3 = new Categoria(null, "Cama mesa e banho");
        Categoria cat4 = new Categoria(null, "Eletrônicos");
        Categoria cat5 = new Categoria(null, "Jardinagem");
        Categoria cat6 = new Categoria(null, "Decoração");
        Categoria cat7 = new Categoria(null, "Perfumaria");

        Produto p1 = new Produto(null, "Computador", 2000.00);
        Produto p2 = new Produto(null, "Impressora", 800.00);
        Produto p3 = new Produto(null, "Mouse", 80.00);
        Produto p4 = new Produto(null, "Mesa de escritório", 300.00);
        Produto p5 = new Produto(null, "Toalha", 50.00);
        Produto p6 = new Produto(null, "Colcha", 200.00);
        Produto p7 = new Produto(null, "TV true color", 1200.00);
        Produto p8 = new Produto(null, "Roçadeira", 800.00);
        Produto p9 = new Produto(null, "Abajour", 100.00);
        Produto p10 = new Produto(null, "Pendente", 180.00);
        Produto p11 = new Produto(null, "Shampoo", 90.00);

        cat1.getProdutos().addAll(asList(p1, p2, p3));
        cat2.getProdutos().addAll(asList(p2, p4));
        cat3.getProdutos().addAll(asList(p5, p6));
        cat4.getProdutos().addAll(asList(p1, p2, p3, p7));
        cat5.getProdutos().addAll(Collections.singletonList(p8));
        cat6.getProdutos().addAll(asList(p9, p10));
        cat7.getProdutos().addAll(Collections.singletonList(p11));

        p1.getCategorias().addAll(asList(cat1, cat4));
        p2.getCategorias().addAll(asList(cat1, cat2, cat4));
        p3.getCategorias().addAll(asList(cat1, cat4));
        p4.getCategorias().addAll(Collections.singletonList(cat2));
        p5.getCategorias().addAll(Collections.singletonList(cat3));
        p6.getCategorias().addAll(Collections.singletonList(cat3));
        p7.getCategorias().addAll(Collections.singletonList(cat4));
        p8.getCategorias().addAll(Collections.singletonList(cat5));
        p9.getCategorias().addAll(Collections.singletonList(cat6));
        p10.getCategorias().addAll(Collections.singletonList(cat6));
        p11.getCategorias().addAll(Collections.singletonList(cat7));

        categoriaRepository.saveAll(asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
        produtoRepository.saveAll(asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));

        Estado est1 = new Estado(null, "Minas Gerais");
        Estado est2 = new Estado(null, "São Paulo");

        Cidade c1 = new Cidade(null, "Uberlandia", est1);
        Cidade c2 = new Cidade(null, "São Paulo", est2);
        Cidade c3 = new Cidade(null, "Campinas", est2);

        est1.getCidades().add(c1);
        est2.getCidades().addAll(asList(c2, c3));

        estadoRepository.saveAll(asList(est1, est2));
        cidadeRepository.saveAll(asList(c1, c2, c3));

        Cliente cli1 = new Cliente(null, "Maria Silva", "nelio.cursos@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
        cli1.getTelefones().addAll(asList("27363323", "93838393"));

        Cliente cli2 = new Cliente(null, "Ana Costa", "nelio.iftm@gmail.com", "31628382740", TipoCliente.PESSOAFISICA);
        cli2.getTelefones().addAll(asList("93883321", "34252625"));

        Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli1, c1);
        Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);
        Endereco e3 = new Endereco(null, "Avenida Floriano", "2106", null, "Centro", "281777012", cli2, c2);

        cli1.getEnderecos().addAll(asList(e1, e2));
        cli2.getEnderecos().addAll(Collections.singletonList(e3));

        clienteRepository.saveAll(asList(cli1, cli2));
        enderecoRepository.saveAll(asList(e1, e2));

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
        Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);

        Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
        ped1.setPagamento(pagto1);

        Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2,
                sdf.parse("20/10/2017 00:00"), null);
        ped2.setPagamento(pagto2);

        cli1.getPedidos().addAll(asList(ped1, ped2));

        pedidoRepository.saveAll(asList(ped1, ped2));
        pagamentoRepository.saveAll(asList(pagto1, pagto2));

        ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
        ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
        ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);

        ped1.getItens().addAll(asList(ip1, ip2));
        ped2.getItens().add(ip3);

        p1.getItens().add(ip1);
        p2.getItens().add(ip3);
        p3.getItens().add(ip2);

        itemPedidoRepository.saveAll(asList(ip1, ip2, ip3));
    }
}
