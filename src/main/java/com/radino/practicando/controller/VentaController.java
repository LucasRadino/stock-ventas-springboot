package com.radino.practicando.controller;

import com.radino.practicando.dto.ApiResponse;
import com.radino.practicando.dto.VentaRequest;
import com.radino.practicando.dto.VentaResponse;
import com.radino.practicando.service.VentaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


//@RestController → indica que esta clase es un controlador REST
//
//Permite recibir peticiones HTTP y devolver respuestas en formato JSON
//
//@RequestMapping → define la ruta base de todos los endpoints de este controller
//
//En este caso todas las rutas comienzan con /ventas
@RestController
@RequestMapping("/ventas")
public class VentaController {


    //Spring inyecta automáticamente la dependencia VentaService
    //
    //Permite utilizar la lógica de negocio sin crear el objeto manualmente
    @Autowired
    private VentaService ventaService;



    //POST → recibe datos para crear una nueva venta
    //
    //@RequestBody → convierte el JSON recibido en un objeto VentaRequest
    //
    //@Valid → ejecuta las validaciones definidas en VentaRequest antes de entrar al método
    //
    //Ejemplo: cantidad mayor a 0
    //
    //@ResponseEntity → permite controlar el código HTTP y el contenido de la respuesta
    //
    //@ApiResponse → devuelve una respuesta estándar con mensaje y fecha
    //
    //@HttpStatus.CREATED → devuelve código 201 indicando creación exitosa
    @PostMapping("/crear")
    public ResponseEntity<ApiResponse> crearVenta(@Valid @RequestBody VentaRequest request){

        ventaService.crearVenta(request);

        ApiResponse response = new ApiResponse(
                "La venta se ha generado correctamente.",
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }



    //GET → obtiene todas las ventas registradas
    //
    //@ResponseEntity<List<VentaResponse>> → devuelve una lista de DTOs con las ventas
    //
    //Se utiliza VentaResponse para no exponer directamente la entidad Venta
    //
    //@ok() → devuelve código HTTP 200 indicando respuesta correcta
    @GetMapping("/listar")
    public ResponseEntity<List<VentaResponse>> listarVentas(){

        return ResponseEntity.ok(ventaService.listarVentas());
    }



    //GET → obtiene una venta específica mediante su id
    //
    //@PathVariable → toma el valor enviado en la URL
    //
    //Ejemplo: /obtenerVenta/5 → id = 5
    //
    //@ResponseEntity<VentaResponse> → devuelve la venta en formato DTO
    @GetMapping("/obtenerVenta/{id}")
    public ResponseEntity<VentaResponse> devolverVentaPorId(@PathVariable int id){

        return ResponseEntity.ok(ventaService.devolverVentaPorId(id));
    }



    //DELETE → elimina una venta existente
    //
    //@PathVariable → obtiene el id desde la URL
    //
    //@ResponseEntity<ApiResponse> → devuelve mensaje y fecha de la operación
    //
    //@HttpStatus.OK → devuelve código 200 indicando eliminación correcta
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<ApiResponse> eliminarVenta(@PathVariable int id){


        ventaService.eliminarVenta(id);


        ApiResponse response = new ApiResponse(
                "Venta eliminada correctamente.",
                LocalDateTime.now()
        );


        return ResponseEntity.ok(response);
    }



    //GET → obtiene todas las ventas realizadas de un producto específico
    //
    //@PathVariable → obtiene el id del producto desde la URL
    //
    //Ejemplo: /listarVentasPorProducto/3 → idProducto = 3
    //
    //@ResponseEntity<List<VentaResponse>> → devuelve una lista de ventas como DTOs
    @GetMapping("/listarVentasPorProducto/{idProducto}")
    public ResponseEntity<List<VentaResponse>> listarVentasDeUnProducto(
            @PathVariable int idProducto){

        return ResponseEntity.ok(
                ventaService.listarVentasDeUnProducto(idProducto)
        );
    }

}