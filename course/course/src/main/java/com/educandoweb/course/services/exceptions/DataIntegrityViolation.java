package com.educandoweb.course.services.exceptions;

public class DataIntegrityViolation extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DataIntegrityViolation(Object id){
        super("Produto inativado" + id);
    }
}
