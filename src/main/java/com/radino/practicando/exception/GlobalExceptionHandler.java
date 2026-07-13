package com.radino.practicando.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;


//@RestControllerAdvice → clase global que intercepta excepciones de todos los Controllers
//Permite manejar errores en un solo lugar y devolver respuestas HTTP personalizadas
@RestControllerAdvice
public class GlobalExceptionHandler {


    //@ExceptionHandler → indica qué excepción debe manejar este método
    //En este caso captura errores de validación generados por @Valid
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> manejarValidaciones(MethodArgumentNotValidException e){


        //Map donde se guardan los errores encontrados
        //Ejemplo: "nombre" → "El nombre no puede estar vacío"
        Map<String, String> errores = new HashMap<>();


        //getBindingResult() obtiene el resultado de la validación realizada por @Valid
        //getFieldErrors() devuelve la lista de campos que no cumplieron las reglas
        //forEach recorre cada error encontrado
        e.getBindingResult().getFieldErrors().forEach(error ->

                //Guarda en el Map:
                //Clave → nombre del campo con error
                //Valor → mensaje definido en la anotación de validación
                errores.put(error.getField(), error.getDefaultMessage())
        );


        //badRequest() devuelve código HTTP 400
        //Indica que el cliente envió datos inválidos
        //body(errores) envía el detalle de los errores en formato JSON
        return ResponseEntity.badRequest().body(errores);
    }

    //@ExceptionHandler → captura la excepción StockInsuficienteException
    //Cuando se lance esta excepción, Spring ejecutará este método automáticamente
    //Devuelve un código HTTP 400 junto con el mensaje del error
    @ExceptionHandler(StockInsuficienteException.class)
    public ResponseEntity<String> manejarStockInsuficiente(StockInsuficienteException e){

        return ResponseEntity.badRequest().body(e.getMessage());
    }


    //@ExceptionHandler → captura la excepción ProductoNoEncontradoException
    //Devuelve código HTTP 404 cuando el producto no existe
    @ExceptionHandler(ProductoNoEncontradoException.class)
    public ResponseEntity<String> manejarProductoNoEncontrado(ProductoNoEncontradoException e){

        return ResponseEntity.status(404).body(e.getMessage());
    }

    @ExceptionHandler(VentaNoEncontradaException.class)
    public ResponseEntity<String> manejarVentaNoEncontrada(VentaNoEncontradaException e){

        return ResponseEntity.status(404).body(e.getMessage());

    }

}