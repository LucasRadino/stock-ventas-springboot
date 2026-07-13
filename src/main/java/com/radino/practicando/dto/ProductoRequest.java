package com.radino.practicando.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;


//DTO utilizado para recibir información desde el cliente hacia la API
//Request significa "petición"
//El cliente envía este objeto cuando quiere crear un producto
//
//Ejemplo de JSON recibido:
//{
//    "nombre": "Mouse Gamer",
//    "precio": 15000,
//    "stock": 20
//}
public class ProductoRequest {



    //Valida que el nombre no sea null, vacío o solamente espacios
    //@Size limita la cantidad de caracteres permitidos
    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 3, max = 100,
            message = "El nombre debe tener entre 3 y 100 caracteres")
    private String nombre;


    //@Positive valida que el precio sea mayor que 0
    //Evita recibir valores inválidos como 0 o negativos
    @Positive(message = "El precio debe ser mayor que 0")
    private double precio;


    //@PositiveOrZero permite que el stock sea 0 o mayor
    //Evita recibir cantidades negativas
    @PositiveOrZero(message = "El stock no puede ser negativo")
    private int stock;



    //GETTER → permite obtener el nombre enviado en el request
    public String getNombre() {
        return nombre;
    }


    //GETTER → permite obtener el precio enviado en el request
    public double getPrecio() {
        return precio;
    }


    //GETTER → permite obtener el stock enviado en el request
    public int getStock() {
        return stock;
    }


    //SETTER → permite asignar el nombre recibido desde el JSON
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //SETTER → permite asignar el precio recibido desde el JSON
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    //SETTER → permite asignar el stock recibido desde el JSON
    public void setStock(int stock) {
        this.stock = stock;
    }

}