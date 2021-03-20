package com.morpheus.cursomc2.services;

import com.morpheus.cursomc2.domain.Pedido;
import com.morpheus.cursomc2.repository.CategoriaRepository;
import com.morpheus.cursomc2.repository.PedidoRepository;
import com.morpheus.cursomc2.services.exceptions.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    public Pedido find(Integer id) {
        Optional<Pedido> byId = pedidoRepository.findById(id);
        return byId.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
    }

    public List<Pedido> findAll(){
        return pedidoRepository.findAll();
    }
}
