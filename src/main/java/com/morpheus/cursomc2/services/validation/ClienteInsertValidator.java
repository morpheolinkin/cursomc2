package com.morpheus.cursomc2.services.validation;

import com.morpheus.cursomc2.domain.enums.TipoCliente;
import com.morpheus.cursomc2.dto.ClienteNewDTO;
import com.morpheus.cursomc2.resources.exception.FieldMessage;
import com.morpheus.cursomc2.services.validation.utils.BRCpf;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
    @Override
    public void initialize(ClienteInsert ann) {
    }

    @Override
    public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        if (objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BRCpf.isValidCPF(objDto.getCpfOuCnpj())) {
            list.add(new FieldMessage("CpfOuCnpj", "CPF inválido"));
        }
        if (objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BRCpf.isValidCNPJ(objDto.getCpfOuCnpj())) {
            list.add(new FieldMessage("CpfOuCnpj", "CNPJ inválido"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}
