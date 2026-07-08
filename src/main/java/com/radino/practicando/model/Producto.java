package com.radino.practicando.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public class Producto {

    //GETTERS Y SETTERS
    private int id;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 3,  max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    private String nombre;

    @Positive(message = "El precio debe ser mayor que 0")
    private double precio;

    @PositiveOrZero(message = "El stock no puede ser negativo")
    private int stock;
    //CONSTRUCTORES
    public Producto() {
    }
    public Producto(int id, String nombre, double precio, int stock) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }
    //GETTERS Y SETTERS
    public int getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public double getPrecio() {
        return precio;
    }
    public int getStock() {
        return stock;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }


    //HOLAAAA


}
