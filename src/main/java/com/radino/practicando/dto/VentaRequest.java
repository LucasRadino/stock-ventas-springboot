package com.radino.practicando.dto;

import jakarta.validation.constraints.Positive;


//DTO utilizado para recibir los datos necesarios para crear una venta.
//
//Representa la información que el cliente envía al backend.
//No contiene datos que el cliente no debería modificar como:
// - id de la venta
// - fecha de creación
//
//Ejemplo JSON recibido:
//
//{
//    "productoId": 4,
//    "cantidad": 2
//}
public class VentaRequest {


    //ID del producto que se quiere vender.
    //
    //@Positive valida que el valor sea mayor que 0.
    //Evita recibir:
    //0
    //-1
    //-20
    //
    //Ejemplo válido:
    //"productoId":4
    @Positive(message = "El producto debe ser válido")
    private int productoId;


    //Cantidad de unidades que se quieren vender.
    //
    //@Positive obliga a que sea mayor que 0.
    //Una venta de 0 unidades o negativa no tiene sentido.
    //
    //Ejemplo válido:
    //"cantidad":2
    @Positive(message = "La cantidad debe ser mayor que 0")
    private int cantidad;



    //GETTERS Y SETTERS


    //Permite obtener el id del producto desde el Service.
    public int getProductoId() {
        return productoId;
    }


    //Permite obtener la cantidad vendida desde el Service.
    public int getCantidad() {
        return cantidad;
    }


    //Permite que Spring cargue el valor recibido desde el JSON.
    //
    //Ejemplo:
    //{
    // "productoId":4
    //}
    //
    //Spring ejecuta automáticamente:
    //setProductoId(4)
    public void setProductoId(int productoId) {
        this.productoId = productoId;
    }


    //Permite que Spring cargue la cantidad recibida desde el JSON.
    //
    //Ejemplo:
    //{
    // "cantidad":2
    //}
    //
    //Spring ejecuta automáticamente:
    //setCantidad(2)
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

}