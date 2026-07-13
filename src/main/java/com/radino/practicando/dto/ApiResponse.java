package com.radino.practicando.dto;

import java.time.LocalDateTime;


//DTO utilizado para enviar respuestas estándar desde la API
//Permite que todos los endpoints devuelvan la misma estructura de respuesta:
//{
//    "mensaje": "Producto creado correctamente",
//    "fecha": "2026-07-13T15:30:00"
//}
public class ApiResponse {


    //Mensaje descriptivo de la operación realizada
    //Ejemplo: "Producto creado correctamente"
    private String mensaje;

    //Fecha y hora en la que se generó la respuesta
    private LocalDateTime fecha;


    //Constructor vacío necesario para que Spring pueda crear el objeto automáticamente
    public ApiResponse() {
    }


    //Constructor utilizado para crear respuestas rápidamente enviando mensaje y fecha
    public ApiResponse(String mensaje, LocalDateTime fecha) {
        this.mensaje = mensaje;
        this.fecha = fecha;
    }


    //GETTER → permite obtener el mensaje de la respuesta
    public String getMensaje() {
        return mensaje;
    }


    //GETTER → permite obtener la fecha de la respuesta
    public LocalDateTime getFecha() {
        return fecha;
    }


    //SETTER → permite modificar el mensaje de la respuesta
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }


    //SETTER → permite modificar la fecha de la respuesta
    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

}