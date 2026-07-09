package com.radino.practicando.controller;

import com.radino.practicando.model.Venta;
import com.radino.practicando.service.VentaService;
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
//@GetMapping → endpoint GET (obtener datos)
//@DeleteMapping → endpoint DELETE (eliminar datos)
//@RequestBody → convierte el JSON recibido en un objeto Java
//@PathVariable → toma un valor de la URL y lo guarda en una variable
//@Valid → activa las validaciones de la clase (ej: @Positive, @NotBlank)
@RestController
@RequestMapping("/ventas")
public class VentaController {


    //Spring crea y conecta automáticamente el VentaService
    @Autowired
    VentaService ventaService;


    //POST → recibe los datos necesarios para crear una venta
    //@Valid → valida los datos de la venta antes de ejecutar el método
    //@ResponseEntity → permite controlar la respuesta HTTP (código + mensaje/datos)
    //@HttpStatus.CREATED → devuelve 201 indicando que se creó correctamente
    @PostMapping("/crear")
    public ResponseEntity<String> crearVenta(@Valid @RequestBody Venta v){

        ventaService.crearVenta(v);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("La venta se ha generado correctamente.");
    }


    //GET → obtiene todas las ventas registradas
    //@ResponseEntity<List<Venta>> → devuelve una lista de ventas junto con el código HTTP
    //@ok() → devuelve código 200 OK
    @GetMapping("/listar")
    public ResponseEntity<List<Venta>> listarVentas(){

        return ResponseEntity.ok(ventaService.listarVentas());
    }


    //GET → obtiene una venta específica por su id
    //@PathVariable → obtiene el id desde la URL (/obtenerVenta/{id})
    @GetMapping("/obtenerVenta/{id}")
    public ResponseEntity<Venta> devolverVentaPorId(@PathVariable int id){

        return ResponseEntity.ok(ventaService.devolverVentaPorId(id));
    }


    //DELETE → elimina una venta por su id
    //@ResponseEntity<Void> → indica que no se devuelve contenido en la respuesta
    //@noContent() → devuelve código 204 indicando que se eliminó correctamente
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarVenta(@PathVariable int id){

        ventaService.eliminarVenta(id);

        return ResponseEntity.noContent().build();
    }


    //GET → busca todas las ventas asociadas a un producto específico
    //@PathVariable → toma el id del producto desde la URL
    //@ResponseEntity<List<Venta>> → devuelve una lista de ventas con código HTTP
    @GetMapping("/listarVentasPorProducto/{idProducto}")
    public ResponseEntity<List<Venta>> listarVentasDeUnProducto(@PathVariable int idProducto){

        return ResponseEntity.ok(ventaService.listarVentasDeUnProducto(idProducto));
    }

}