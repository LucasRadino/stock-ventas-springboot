package com.radino.practicando.controller;

import com.radino.practicando.dto.ApiResponse;
import com.radino.practicando.dto.ProductoRequest;
import com.radino.practicando.dto.ProductoResponse;
import com.radino.practicando.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


//@RestController → indica que esta clase es un Controller REST.
//Permite recibir peticiones HTTP y devolver respuestas en formato JSON.
//
//@RequestMapping → define la ruta base de todos los endpoints de esta clase.
//
//@Autowired → Spring inyecta automáticamente las dependencias necesarias.
//
//@PostMapping → recibe peticiones POST (crear datos).
//
//@GetMapping → recibe peticiones GET (consultar datos).
//
//@DeleteMapping → recibe peticiones DELETE (eliminar datos).
//
//@RequestBody → convierte el JSON recibido en un objeto Java.
//
//@Valid → activa las validaciones definidas en el DTO recibido
//(ejemplo: @NotBlank, @Positive).
@RestController
@RequestMapping("/productos")
public class ProductoController {


    //Spring crea e inyecta automáticamente el ProductoService.
    //El Controller utiliza el Service para ejecutar la lógica del negocio.
    @Autowired
    private ProductoService service;



    //POST → crea un nuevo producto.
    //
    //@Valid → valida los datos enviados antes de ejecutar el método.
    //
    //@RequestBody ProductoRequest → recibe solamente los datos necesarios
    //para crear un producto (nombre, precio y stock).
    //
    //@ResponseEntity<ApiResponse> → permite controlar:
    //1) Código HTTP de respuesta.
    //2) Objeto JSON que se devuelve.
    //
    //@HttpStatus.CREATED → devuelve código 201 indicando creación exitosa.
    @PostMapping("/crear")
    public ResponseEntity<ApiResponse> crearProducto(
            @Valid @RequestBody ProductoRequest request) {


        service.crearProducto(request);


        ApiResponse response = new ApiResponse(
                "Producto creado correctamente.",
                LocalDateTime.now()
        );


        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }



    //GET → obtiene todos los productos.
    //
    //@ResponseEntity<List<ProductoResponse>> →
    //devuelve una lista de DTOs de respuesta.
    //
    //ProductoResponse evita devolver directamente el modelo interno Producto.
    //
    //@ok() → devuelve código HTTP 200 OK.
    @GetMapping("/listar")
    public ResponseEntity<List<ProductoResponse>> listarProductos() {

        return ResponseEntity.ok(service.listarProductos());
    }



    //GET → obtiene solamente el stock de un producto.
    //
    //@PathVariable → obtiene el id enviado en la URL.
    //Ejemplo: /obtenerStock/5 → id = 5
    //
    //@ResponseEntity<Integer> → devuelve el valor del stock junto al código HTTP.
    @GetMapping("/obtenerStock/{id}")
    public ResponseEntity<Integer> obtenerStock(
            @PathVariable int id) {

        return ResponseEntity.ok(service.obtenerStock(id));
    }



    //DELETE → elimina un producto existente
    //
    // @PathVariable → obtiene el id desde la URL
    //
    // @ResponseEntity<ApiResponse> → devuelve una respuesta estándar
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<ApiResponse> eliminarProducto(@PathVariable int id) {


        service.eliminarProducto(id);


        ApiResponse response = new ApiResponse(
                "Producto eliminado correctamente.",
                LocalDateTime.now()
        );


        return ResponseEntity.ok(response);
    }


    //PUT → modifica el precio de un producto existente
    //
    //@PathVariable → obtiene el id del producto desde la URL
    //
    // @RequestParam → obtiene el nuevo precio desde el parámetro (?precioNuevo=100)
    //
    // @ResponseEntity<ApiResponse> → devuelve una respuesta estándar con mensaje y fecha
    @PutMapping("/modificarPrecio/{id}")
    public ResponseEntity<ApiResponse> modificarPrecio(@PathVariable int id, @RequestParam double precioNuevo) {


        service.modificarPrecio(id, precioNuevo);


        ApiResponse response = new ApiResponse(
                "Precio modificado correctamente.",
                LocalDateTime.now()
        );


        return ResponseEntity.ok(response);
    }



    //GET → obtiene un producto específico por id.
    //
    //@PathVariable → obtiene el id desde la URL.
    //
    //@ResponseEntity<ProductoResponse> →
    //devuelve un DTO con la información pública del producto.
    //
    //No devuelve directamente Producto porque es un objeto interno del sistema.
    @GetMapping("/obtenerProducto/{id}")
    public ResponseEntity<ProductoResponse> devolverProductoPorId(
            @PathVariable int id) {


        return ResponseEntity.ok(
                service.devolverProductoPorId(id)
        );
    }



    //GET → busca productos por nombre.
    //
    //@RequestParam → recibe valores desde la URL.
    //Ejemplo:
    // /productos/listarProductosPorNombre?nombre=mouse
    //
    //@ResponseEntity<List<ProductoResponse>> →
    //devuelve una lista de productos convertidos a DTO.
    @GetMapping("/listarProductosPorNombre")
    public ResponseEntity<List<ProductoResponse>> listarProductosPorNombre(
            @RequestParam String nombre) {


        return ResponseEntity.ok(
                service.listarProductosPorNombre(nombre)
        );
    }

}