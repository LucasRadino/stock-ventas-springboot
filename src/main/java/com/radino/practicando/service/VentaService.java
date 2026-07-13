package com.radino.practicando.service;

import com.radino.practicando.dto.ProductoResponse;
import com.radino.practicando.dto.VentaRequest;
import com.radino.practicando.dto.VentaResponse;
import com.radino.practicando.exception.StockInsuficienteException;
import com.radino.practicando.exception.VentaNoEncontradaException;
import com.radino.practicando.model.Producto;
import com.radino.practicando.model.Venta;
import com.radino.practicando.repository.ProductoRepository;
import com.radino.practicando.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VentaService {

    @Autowired
    VentaRepository ventaRepo;

    @Autowired
    ProductoRepository productoRepo;

    public void crearVenta(VentaRequest request){

        // Crear una nueva venta con los datos recibidos del cliente
        Venta venta = new Venta();

        // Pasar los datos del DTO Request hacia la entidad Venta
        venta.setProductoId(request.getProductoId());
        venta.setCantidad(request.getCantidad());
        venta.setFecha(LocalDateTime.now());

        // Obtener stock actual del producto
        int stockActual = productoRepo.obtenerStock(venta.getProductoId());

        // Validar que haya stock suficiente
        if(venta.getCantidad() > stockActual){

            throw new StockInsuficienteException(
                    "Stock insuficiente"
            );
        }

        // Calcular nuevo stock después de la venta
        int nuevoStock = stockActual - venta.getCantidad();

        // Actualizar stock del producto
        productoRepo.actualizarStock(
                venta.getProductoId(),
                nuevoStock
        );

        // Guardar venta en base de datos
        ventaRepo.crearVenta(venta);
    }


    public List<VentaResponse> listarVentas(){

        return ventaRepo.listarVentas()
                .stream()
                .map(this::convertirAResponse)
                .toList();
    }

    public VentaResponse devolverVentaPorId(int id){

        try{

            Venta venta = ventaRepo.devolverVentaPorId(id);

            return convertirAResponse(venta);


        }catch (EmptyResultDataAccessException e){

            throw new VentaNoEncontradaException(
                    "No existe una venta con id " + id
            );
        }
    }

    public void eliminarVenta(int id){

        // Buscar la venta real desde el Repository
        Venta venta = ventaRepo.devolverVentaPorId(id);

        // Buscar el producto asociado a esa venta
        Producto producto = productoRepo.devolverProductoPorId(
                venta.getProductoId()
        );

        // Devolver nuevamente el stock vendido
        int nuevoStock = producto.getStock() + venta.getCantidad();

        // Actualizar stock del producto
        productoRepo.actualizarStock(
                producto.getId(),
                nuevoStock
        );

        // Eliminar venta
        ventaRepo.eliminarVenta(id);
    }


    public List<VentaResponse> listarVentasDeUnProducto(int idProducto){

        return ventaRepo.listarVentasDeUnProducto(idProducto)
                .stream()
                .map(this::convertirAResponse)
                .toList();
    }

    private VentaResponse convertirAResponse(Venta venta){

        return new VentaResponse(
                venta.getId(),
                venta.getProductoId(),
                venta.getCantidad(),
                venta.getFecha()
        );
    }

}
