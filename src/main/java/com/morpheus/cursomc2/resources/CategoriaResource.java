package com.morpheus.cursomc2.resources;

import com.morpheus.cursomc2.domain.Categoria;
import com.morpheus.cursomc2.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> find(@PathVariable Integer id){
        Optional<Categoria> encontrar = categoriaService.find(id);

       return ResponseEntity.ok().body(encontrar);
    }

    @GetMapping
    public ResponseEntity<List<Categoria>> findAll(){
        List<Categoria> categoriaList = categoriaService.findAll();
        return ResponseEntity.ok().body(categoriaList);
    }
}

/*Voltar para a aula 18*/
