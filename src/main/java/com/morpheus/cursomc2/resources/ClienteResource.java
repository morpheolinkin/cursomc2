package com.morpheus.cursomc2.resources;

import com.morpheus.cursomc2.domain.Cliente;
import com.morpheus.cursomc2.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService clienteService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> find(@PathVariable Integer id){
        Cliente encontrar = clienteService.find(id);

       return ResponseEntity.ok().body(encontrar);
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> findAll(){
        List<Cliente> clienteList = clienteService.findAll();
        return ResponseEntity.ok().body(clienteList);
    }
}

/*Voltar para a aula 18*/
