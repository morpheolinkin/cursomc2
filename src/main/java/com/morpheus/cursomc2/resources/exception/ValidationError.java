package com.morpheus.cursomc2.resources.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StadardError{
    private List<FieldMessage> errors = new ArrayList<>();

    public ValidationError(Integer status, String msg, Long timeStamp) {
        super(status, msg, timeStamp);
    }

    public List<FieldMessage> getErrors() {
        return errors;
    }

    public void addError(String fildName, String message) {
        errors.add(new FieldMessage(fildName, message));
    }
}
