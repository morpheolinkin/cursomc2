package com.morpheus.cursomc2.services;

import com.morpheus.cursomc2.domain.Cliente;
import com.morpheus.cursomc2.repository.ClienteRepository;
import com.morpheus.cursomc2.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente find(Integer id) {
        Optional<Cliente> byId = clienteRepository.findById(id);
        return byId.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
    }

    public List<Cliente> findAll(){
        return clienteRepository.findAll();
    }

}
