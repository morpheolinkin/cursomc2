package com.morpheus.cursomc2.services;

import com.morpheus.cursomc2.domain.Categoria;
import com.morpheus.cursomc2.dto.CategoriaDTO;
import com.morpheus.cursomc2.repository.CategoriaRepository;
import com.morpheus.cursomc2.services.exceptions.DataIntegrityException;
import com.morpheus.cursomc2.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria find(Integer id) {
        Optional<Categoria> byId = categoriaRepository.findById(id);
        return byId.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
    }

    public List<Categoria> findAll(){
        return categoriaRepository.findAll();
    }

    public Categoria insert(Categoria obj) {
        //No método SAVE, quando o ID vale nulo ele insere, quando não vale nulo ele atualiza o objeto
        obj.setId(null);
        return categoriaRepository.save(obj);
    }

    public Categoria update(Categoria obj) {
        //No método SAVE, quando o ID vale nulo ele insere, quando não vale nulo ele atualiza o objeto
        find(obj.getId()); //Verifica se o id existe
        return categoriaRepository.save(obj);
    }

    public void delete(Categoria id) {
        find(id.getId());
        try {
            categoriaRepository.deleteById(id.getId());
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("" +
                    "Não é possível excluir uma categoria que possui um produto");
        }
    }

    public Page<Categoria> findPage(Integer page, Integer linePerPage, String direction, String orderBy){
        PageRequest pageRequest = PageRequest.of(page, linePerPage, Sort.Direction.valueOf(direction), orderBy);
        return categoriaRepository.findAll(pageRequest);
    }

    public Categoria fromDTO(CategoriaDTO objDto){
        return new Categoria(objDto.getId(), objDto.getNome());
    }
}
