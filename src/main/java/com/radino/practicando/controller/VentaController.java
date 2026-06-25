package com.radino.practicando.controller;

import com.radino.practicando.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/venta")
public class VentaController {

    @Autowired
    private VentaService ventaService;


    //POST /venta/registrarVenta?productoId=1&cantidad=2
    @PostMapping("/registrarVenta")
    public void registrarVenta(@RequestParam int productoId, @RequestParam int cantidad){
        ventaService.registrarVenta(productoId, cantidad);
    }

}
