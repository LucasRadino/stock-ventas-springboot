package com.radino.practicando.controller;

import com.radino.practicando.model.Producto;
import com.radino.practicando.repository.ProductoRepository;
import com.radino.practicando.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController → define una API REST
//@RequestMapping → ruta base del controller
//@Autowired → Spring inyecta dependencias automáticamente
//@PostMapping → endpoint POST (crear datos)
//@RequestBody → convierte JSON en objeto Java


//Sirve para decirle a Spring: “Esta clase va a recibir y responder peticiones HTTP (API REST)”
@RestController
//Sirve para definir la ruta base del controller, lo que esté dentro de este controller empieza con /productos.
@RequestMapping("/productos")
public class ProductoController {

    //Sirve para que Spring haga esto automáticamente: “Yo te creo y te conecto el ProductoService”
    @Autowired
    private ProductoService service;


    //Sirve para decir: “Este méodo responde a peticiones HTTP POST”
    @PostMapping("/crear")
    public void crearProducto(@RequestBody Producto p){ //Sirve para decir: “Tomá el JSON que llega en el body y convertílo a un objeto Producto”

        service.crearProducto(p);
    }
    //se encarga de convertir el JSON que llega en el cuerpo (body) de la petición HTTP en un objeto Java.


    //le dice a Spring: "Cuando llegue una petición HTTP GET a la ruta /listar, ejecutá este méodo."
    @GetMapping("/listar")
    public List<Producto> listarProductos(){
        return service.listarProductos();
    }


    @GetMapping("/{id}/obtenerStock")
    public int obtenerStock(@PathVariable int id){ //Tomá el valor que viene en la URL y guardalo en esta variable.
        return service.obtenerStock(id);
    }

    @DeleteMapping("/{id}/eliminar")
    public void eliminarProducto(@PathVariable int id){
        service.eliminarProducto(id);
    }

    // PUT /productos/{id}/modificarPrecio?precioNuevo=XXXX
    // PathVariable → toma el id desde la URL
    // RequestParam → toma el valor desde el query param (?precioNuevo=)
    @PutMapping("/{id}/modificarPrecio")
    public void modificarPrecio(@PathVariable int id, @RequestParam double precioNuevo){
        service.modificarPrecio(id, precioNuevo);
    }


}
