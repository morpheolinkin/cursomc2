package com.morpheus.cursomc2.services.validation;

import com.morpheus.cursomc2.domain.Cliente;
import com.morpheus.cursomc2.dto.ClienteDTO;
import com.morpheus.cursomc2.repository.ClienteRepository;
import com.morpheus.cursomc2.resources.exception.FieldMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public void initialize(ClienteUpdate ann) {
    }

    @Override
    public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {
        Map<String, String> map = (Map<String, String>)
                httpServletRequest.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Integer uriId = Integer.parseInt(map.get("id"));

        List<FieldMessage> list = new ArrayList<>();

        Cliente aux = clienteRepository.findByEmail(objDto.getEmail());
        if (aux != null & !Objects.requireNonNull(aux).getId().equals(uriId)) {
            list.add(new FieldMessage("email", "Email já existe"));
        }
        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}
