package com.radino.practicando.service;


import com.radino.practicando.repository.ProductoRepository;
import com.radino.practicando.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VentaService {

    @Autowired
    private ProductoRepository produRepo;

    @Autowired
    private VentaRepository ventaRepo;



    public void registrarVenta(int productoId, int cantidad) {

        int stockActual = produRepo.obtenerStock(productoId);

        if(stockActual < cantidad){
            throw new RuntimeException("Stock insuficiente");
        }

        int nuevoStock = stockActual - cantidad;

        produRepo.actualizarStock(productoId, nuevoStock);

        ventaRepo.registrarVenta(productoId, cantidad);
    }


}
