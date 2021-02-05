package com.morpheus.cursomc2.resources;

import com.morpheus.cursomc2.domain.Categoria;
import com.morpheus.cursomc2.dto.CategoriaDTO;
import com.morpheus.cursomc2.services.CategoriaService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    private final CategoriaService categoriaService;

    public CategoriaResource(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Categoria> find(@PathVariable Integer id){
        Categoria encontrar = categoriaService.find(id);

       return ResponseEntity.ok().body(encontrar);
    }

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> findAll(){
        List<Categoria> categoriaList = categoriaService.findAll();
        List<CategoriaDTO> dtoList = categoriaList.stream().map(CategoriaDTO::new).collect(Collectors.toList());

        return ResponseEntity.ok().body(dtoList);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDTO objDto){
        Categoria obj = categoriaService.fromDTO(objDto);
        categoriaService.insert(obj);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(objDto.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody CategoriaDTO objDto, @PathVariable Integer id){
        Categoria obj = categoriaService.fromDTO(objDto);
        obj.setId(id);
        categoriaService.update(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Categoria id){
        categoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/page")
    public ResponseEntity<Page<CategoriaDTO>> findPage(
            @RequestParam(value="page", defaultValue="0") Integer page,
            @RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
            @RequestParam(value="direction", defaultValue="ASC") String direction,
            @RequestParam(value="orderBy", defaultValue="nome") String orderBy) {

        Page<Categoria> list = categoriaService.findPage(page, linesPerPage, direction, orderBy);
        Page<CategoriaDTO> listDto = list.map(CategoriaDTO::new);
        return ResponseEntity.ok().body(listDto);

    }
}

