package com.radino.practicando.controller;

import com.radino.practicando.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    //POST /ventas/realizarVenta?productoId=1&cantidad=2
    @PostMapping("/realizarVenta")
    public void vender(@RequestParam int productoId,
                       @RequestParam int cantidad) {

        ventaService.vender(productoId, cantidad);
    }
}