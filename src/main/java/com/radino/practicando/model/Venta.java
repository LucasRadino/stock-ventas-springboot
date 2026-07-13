package com.radino.practicando.model;

import java.time.LocalDateTime;

public class Venta {

    //ATRIBUTOS
    private int id;
    private int productoId;
    private int cantidad;
    private LocalDateTime fecha;
    //CONSTRUCTORES
    public Venta() {
    }
    public Venta(int id, int productoId, int cantidad, LocalDateTime fecha) {
        this.id = id;
        this.productoId = productoId;
        this.cantidad = cantidad;
        this.fecha = fecha;
    }
    //GETTERS Y SETTERS
    public int getCantidad() {
        return cantidad;
    }
    public LocalDateTime getFecha() {
        return fecha;
    }
    public int getId() {
        return id;
    }
    public int getProductoId() {
        return productoId;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setProductoId(int productoId) {
        this.productoId = productoId;
    }

}