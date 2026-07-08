package com.radino.practicando.controller;

import com.radino.practicando.model.Venta;
import com.radino.practicando.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/ventas")
public class VentaController {

    @Autowired
    VentaService ventaService;

    @PostMapping("/crearVenta")
    public void crearVenta(@RequestBody Venta v){

        ventaService.crearVenta(v);
    }

    @GetMapping("/listar")
    public List<Venta> listarVentas(){

        return ventaService.listarVentas();
    }

    @GetMapping("/obtenerVenta/{id}")
    public Venta devolverVentaPorId(@PathVariable int id){

        return ventaService.devolverVentaPorId(id);
    }


    @DeleteMapping("/eliminar/{id}")
    public void eliminarVenta(@PathVariable int id){

        ventaService.eliminarVenta(id);
    }


    @GetMapping("listarVentasPorProducto/{idProducto}")
    public List<Venta> listarVentasDeUnProducto(@PathVariable int idProducto){

        return ventaService.listarVentasDeUnProducto(idProducto);
    }





}

