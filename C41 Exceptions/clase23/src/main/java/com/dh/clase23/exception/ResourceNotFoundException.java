package com.dh.clase23.controller;

public class ResourceNotFoundException extends Exception {
    public ResourceNotFoundException(String mensaje) {
        super(mensaje);
    }
}
