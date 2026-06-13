package com.radino.practicando.service;

import com.radino.practicando.repository.ProductoRepository;
import com.radino.practicando.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VentaService {

    @Autowired
    private ProductoRepository productoRepo;

    @Autowired
    private VentaRepository ventaRepo;

    public void vender(int productoId, int cantidad) {

        int stockActual = productoRepo.obtenerStock(productoId);

        if (stockActual < cantidad) {
            throw new RuntimeException("Stock insuficiente");
        }

        int nuevoStock = stockActual - cantidad;

        // 🔥 AHORA SÍ actualizás stock correctamente
        productoRepo.actualizarStock(productoId, nuevoStock);

        // registrás la venta
        ventaRepo.registrarVenta(productoId, cantidad);
    }
}