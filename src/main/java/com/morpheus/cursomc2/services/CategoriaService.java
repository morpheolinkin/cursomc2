package com.morpheus.cursomc2.services;

import com.morpheus.cursomc2.domain.Categoria;
import com.morpheus.cursomc2.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository cr;

    public Optional<Categoria> find(Integer id) {

        return cr.findById(id);
    }

}
