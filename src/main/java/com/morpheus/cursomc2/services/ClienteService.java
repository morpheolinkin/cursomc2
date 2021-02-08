package com.morpheus.cursomc2.services;

import com.morpheus.cursomc2.domain.Cidade;
import com.morpheus.cursomc2.domain.Cliente;
import com.morpheus.cursomc2.domain.Endereco;
import com.morpheus.cursomc2.domain.enums.TipoCliente;
import com.morpheus.cursomc2.dto.ClienteDTO;
import com.morpheus.cursomc2.dto.ClienteNewDTO;
import com.morpheus.cursomc2.repository.ClienteRepository;
import com.morpheus.cursomc2.repository.EnderecoRepository;
import com.morpheus.cursomc2.services.exceptions.DataIntegrityException;
import com.morpheus.cursomc2.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;

    public Cliente find(Integer id) {
        Optional<Cliente> byId = clienteRepository.findById(id);
        return byId.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
    }

    public List<Cliente> findAll(){
        return clienteRepository.findAll();
    }

    @Transactional
    public Cliente insert(Cliente obj) {
        //No método SAVE, quando o ID vale nulo ele insere, quando não vale nulo ele atualiza o objeto
        obj.setId(null);
        obj = clienteRepository.save(obj);
        enderecoRepository.saveAll(obj.getEnderecos());
        return obj;
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

    public Cliente fromDTO(ClienteNewDTO objDto){

        Cliente cli = new Cliente(null, objDto.getNome(),
                objDto.getEmail(), objDto.getCpfOuCnpj(),
                TipoCliente.toEnum(objDto.getTipo()));

        Cidade cid = new Cidade(objDto.getCidadeId(), null, null);

        Endereco endereco = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(),
                objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cli, cid);

        cli.getEnderecos().add(endereco);
        cli.getTelefones().add(objDto.getTelefone1());

        if (objDto.getTelefone2() != null) {
            cli.getTelefones().add(objDto.getTelefone2());
        }
        if (objDto.getTelefone3() != null) {
            cli.getTelefones().add(objDto.getTelefone3());
        }

        return cli;
    }
}
