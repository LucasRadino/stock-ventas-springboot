package com.radino.practicando.controller;

import com.radino.practicando.service.ProductoService;
import com.radino.practicando.model.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService service;

    // POST /productos
    // Crea un nuevo producto recibiendo un JSON en el body
    @PostMapping
    public void crear(@RequestBody Producto p) {
        service.crear(p);
    }

    // GET /productos
    // Devuelve la lista completa de productos
    @GetMapping
    public List<Producto> listar() {
        return service.listar();
    }

    // GET /productos/{id}/stock
    // PathVariable toma el valor del {id} en la URL
    // Ej: /productos/5/stock → id = 5
    @GetMapping("/{id}/stock")
    public int stock(@PathVariable int id) {
        return service.stock(id);
    }

    // DELETE /productos/{id}
    // Elimina un producto según su id tomado desde la URL
    @DeleteMapping("/{id}/eliminar")
    public void eliminar(@PathVariable int id) {
        service.eliminarP(id);
    }

    // PUT /productos/{id}/precio?precio=XXXX
    // PathVariable → toma el id desde la URL
    // RequestParam → toma el valor desde el query param (?precio=)
    @PutMapping("/{id}/precio")
    public void modificarPrecio(@PathVariable int id, @RequestParam double precio) {
        service.precioModificado(id, precio);
    }

}