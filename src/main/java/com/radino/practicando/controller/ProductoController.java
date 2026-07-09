package com.radino.practicando.controller;

import com.radino.practicando.model.Producto;
import com.radino.practicando.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//@RestController → define una API REST y permite responder datos en formato JSON
//@RequestMapping → define la ruta base del controller
//@Autowired → Spring inyecta dependencias automáticamente
//@PostMapping → endpoint POST (crear datos)
//@RequestBody → convierte el JSON recibido en un objeto Java
//@Valid → activa las validaciones de la clase (ej: @NotBlank, @Positive)
@RestController
@RequestMapping("/productos")
public class ProductoController {


    //Spring crea y conecta automáticamente el ProductoService
    @Autowired
    private ProductoService service;


    //POST → recibe datos para crear un producto
    //@ResponseEntity → permite controlar la respuesta HTTP (código + mensaje/datos)
    //@HttpStatus.CREATED → devuelve código 201 indicando que se creó correctamente
    @PostMapping("/crear")
    public ResponseEntity<String> crearProducto(@Valid @RequestBody Producto p) {

        service.crearProducto(p);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Producto creado correctamente.");
    }


    //GET → obtiene una lista de productos
    //@ResponseEntity<List<Producto>> → devuelve una lista junto con el código HTTP de respuesta
    //@ok() → devuelve código 200 OK
    @GetMapping("/listar")
    public ResponseEntity<List<Producto>> listarProductos() {

        return ResponseEntity.ok(service.listarProductos());
    }


    //GET → busca el stock de un producto por su id
    //@PathVariable → toma el valor que viene en la URL y lo guarda en la variable id
    @GetMapping("/obtenerStock/{id}")
    public ResponseEntity<Integer> obtenerStock(@PathVariable int id) {

        return ResponseEntity.ok(service.obtenerStock(id));
    }


    //DELETE → elimina un producto por su id
    //@ResponseEntity<Void> → indica que la respuesta no devuelve contenido
    //@noContent() → devuelve código 204 indicando que se eliminó correctamente
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable int id) {

        service.eliminarProducto(id);

        return ResponseEntity.noContent().build();
    }


    //PUT → modifica un dato existente de un producto
    //@PathVariable → obtiene el id desde la URL
    //@RequestParam → obtiene datos desde parámetros de la URL (?precioNuevo=100)
    @PutMapping("/{id}/modificarPrecio")
    public ResponseEntity<String> modificarPrecio(
            @PathVariable int id,
            @RequestParam double precioNuevo) {

        service.modificarPrecio(id, precioNuevo);

        return ResponseEntity.ok("Precio modificado correctamente.");
    }


    //GET → obtiene un producto específico por id
    //@ResponseEntity<Producto> → devuelve un producto junto con su código HTTP
    @GetMapping("/obtenerProducto/{id}")
    public ResponseEntity<Producto> devolverProductoPorId(@PathVariable int id){

        return ResponseEntity.ok(service.devolverProductoPorId(id));
    }


    //GET → busca productos filtrando por nombre
    //@RequestParam → recibe valores desde la URL (?nombre=mouse)
    @GetMapping("/listarProductosPorNombre")
    public ResponseEntity<List<Producto>> listarProductosPorNombre(
            @RequestParam String nombre){

        return ResponseEntity.ok(service.listarProductosPorNombre(nombre));
    }

}