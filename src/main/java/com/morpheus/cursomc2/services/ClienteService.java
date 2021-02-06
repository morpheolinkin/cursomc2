package com.morpheus.cursomc2.services;

import com.morpheus.cursomc2.domain.Cliente;
import com.morpheus.cursomc2.domain.Cliente;
import com.morpheus.cursomc2.dto.ClienteDTO;
import com.morpheus.cursomc2.repository.ClienteRepository;
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

    public Cliente insert(Cliente obj) {
        //No método SAVE, quando o ID vale nulo ele insere, quando não vale nulo ele atualiza o objeto
        obj.setId(null);
        return clienteRepository.save(obj);
    }

    public Cliente update(Cliente obj) {
        //No método SAVE, quando o ID vale nulo ele insere, quando não vale nulo ele atualiza o objeto
        Cliente newObj = find(obj.getId()); //Verifica se o id existe
        updateData(newObj, obj);
        return clienteRepository.save(newObj);
    }

    private void updateData(Cliente newObj, Cliente obj) {
        newObj.setNome(obj.getNome());
        newObj.setEmail(obj.getEmail());
    }

    public void delete(Cliente id) {
        find(id.getId());
        try {
            clienteRepository.deleteById(id.getId());
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("" +
                    "Não é possível excluir porque há entidade relacionadas");
        }
    }

    public Page<Cliente> findPage(Integer page, Integer linePerPage, String direction, String orderBy){
        PageRequest pageRequest = PageRequest.of(page, linePerPage, Sort.Direction.valueOf(direction), orderBy);
        return clienteRepository.findAll(pageRequest);
    }

    public Cliente fromDTO(ClienteDTO objDto){
        return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
    }
}
