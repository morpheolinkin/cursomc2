package com.morpheus.cursomc2.services;

import com.morpheus.cursomc2.domain.Categoria;
import com.morpheus.cursomc2.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    public Optional<Categoria> find(Integer id) {
        Optional<Categoria> byId = categoriaRepository.findById(id);

        return byId;
    }

    public List<Categoria> findAll(){
        return categoriaRepository.findAll();
    }

}
