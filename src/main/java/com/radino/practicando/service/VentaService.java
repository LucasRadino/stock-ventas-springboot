package com.radino.practicando.service;

import com.radino.practicando.model.Producto;
import com.radino.practicando.model.Venta;
import com.radino.practicando.repository.ProductoRepository;
import com.radino.practicando.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VentaService {

    @Autowired
    VentaRepository ventaRepo;

    @Autowired
    ProductoRepository productoRepo;

    public void crearVenta(Venta v){

        v.setFecha(LocalDateTime.now());

        int stockActual = productoRepo.obtenerStock(v.getProductoId());

        if(v.getCantidad() > stockActual){
            throw new RuntimeException("Stock insuficiente");
        }

        int nuevoStock = stockActual - v.getCantidad();

        productoRepo.actualizarStock(v.getProductoId(), nuevoStock);

        ventaRepo.crearVenta(v);
    }


    public List<Venta> listarVentas(){

        return ventaRepo.listarVentas();
    }

    public Venta devolverVentaPorId(int id){

        return ventaRepo.devolverVentaPorId(id);
    }


    public void eliminarVenta(int id){

        // Buscar la venta
        Venta venta = ventaRepo.devolverVentaPorId(id);

        // Buscar el producto asociado a esa venta
        Producto producto = productoRepo.devolverProductoPorId(venta.getProductoId());

        // Calcular el nuevo stock
        int nuevoStock = producto.getStock() + venta.getCantidad();

        // Actualizar el stock
        productoRepo.actualizarStock(producto.getId(), nuevoStock);


        // Eliminar la venta
        ventaRepo.eliminarVenta(id);
    }

}
