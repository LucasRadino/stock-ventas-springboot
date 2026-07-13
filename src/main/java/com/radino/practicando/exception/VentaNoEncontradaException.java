package com.radino.practicando.exception;

public class VentaNoEncontradaException extends RuntimeException {

    public VentaNoEncontradaException(String mensaje) {
        super(mensaje);
    }

}