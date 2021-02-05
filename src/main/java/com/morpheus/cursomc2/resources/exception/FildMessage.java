package com.morpheus.cursomc2.resources.exception;

import java.io.Serializable;

public class FildMessage implements Serializable {

    private String fildName;
    private String message;

    public FildMessage() {
    }

    public FildMessage(String fildName, String message) {
        this.fildName = fildName;
        this.message = message;
    }

    public String getFildName() {
        return fildName;
    }

    public void setFildName(String fildName) {
        this.fildName = fildName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
