package com.radino.practicando.service;

import com.radino.practicando.model.Producto;

import com.radino.practicando.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service es una anotación de Spring que le dice al framework: “esta clase es
//un componente de la capa de servicio, creala y manejala vos automáticamente”
@Service
public class ProductoService {

    @Autowired //le estás diciendo a Spring: “no crees este objeto con new, dámelo vos ya creado”
    private ProductoRepository repo;

    public void crear(Producto p) {
        repo.crearProducto(p);
    }

    public List<Producto> listar() {
        return repo.listarProductos();
    }

    public int stock(int id) {
        return repo.obtenerStock(id);
    }

    public void eliminarP(int id) {
        repo.eliminarProducto(id);
    }

    public void precioModificado(int id, double precio) {
        repo.modificarPrecio(id, precio);
    }


}