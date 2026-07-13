package com.radino.practicando.dto;

import java.time.LocalDateTime;


//DTO utilizado para enviar información de una venta desde la API hacia el cliente.
//
//Response significa "respuesta".
//Permite definir qué información de una venta será visible,
//sin exponer directamente la clase interna Venta.
public class VentaResponse {


    //Identificador de la venta
    private int id;


    //Producto asociado a la venta
    private int productoId;


    //Cantidad vendida
    private int cantidad;


    //Fecha en la que fue realizada la venta
    private LocalDateTime fecha;



    //Constructor utilizado para transformar una entidad Venta
    //en un objeto VentaResponse antes de enviarlo al cliente.
    //
    //Flujo:
    //Venta (modelo interno)
    //        ↓
    //VentaResponse (respuesta JSON)
    public VentaResponse(int id, int productoId, int cantidad, LocalDateTime fecha){

        this.id = id;
        this.productoId = productoId;
        this.cantidad = cantidad;
        this.fecha = fecha;
    }



    //GETTERS
    //
    //Permiten que Spring pueda leer los valores del DTO
    //y convertirlos automáticamente en formato JSON.
    //
    //No se agregan setters porque este DTO es solamente
    //de lectura y no debe modificarse desde afuera.
    public int getId() {
        return id;
    }


    public int getProductoId() {
        return productoId;
    }


    public int getCantidad() {
        return cantidad;
    }


    public LocalDateTime getFecha() {
        return fecha;
    }

}