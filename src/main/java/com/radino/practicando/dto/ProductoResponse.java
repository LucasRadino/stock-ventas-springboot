package com.radino.practicando.dto;


//DTO utilizado para enviar información del producto desde la API hacia el cliente.
//
//Response significa "respuesta".
//Su objetivo es controlar qué datos exponemos públicamente,
//evitando devolver directamente la entidad Producto.
//
//Ejemplo de respuesta JSON:
//{
//    "id": 1,
//    "nombre": "Mouse Gamer",
//    "precio": 15000,
//    "stock": 20
//}
public class ProductoResponse {


    //Identificador del producto
    private int id;


    //Nombre del producto
    private String nombre;


    //Precio actual del producto
    private double precio;


    //Cantidad disponible en stock
    private int stock;



    //Constructor utilizado para convertir una entidad Producto
    //en un objeto ProductoResponse.
    //
    //Flujo:
    //Producto (modelo interno)
    //        ↓
    //ProductoResponse (datos enviados al cliente)
    public ProductoResponse(int id, String nombre, double precio, int stock) {

        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }



    //GETTERS
    //
    //Permiten acceder a los datos del DTO cuando Spring
    //convierte el objeto en una respuesta JSON.
    //
    //No se utilizan setters porque este objeto solamente
    //representa información que se devuelve al cliente.
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

}