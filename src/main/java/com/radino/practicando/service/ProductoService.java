package com.radino.practicando.service;

import com.radino.practicando.model.Producto;
import com.radino.practicando.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service le dice a Spring, esta clase es un Service. Quiero que la administres vos.
@Service
public class ProductoService {


    //Le estás diciendo a Spring: "Necesito un ProductoRepository. Buscalo vos y colocámelo acá."
    @Autowired
    private ProductoRepository repo;

    public void crearProducto(Producto p) {
        repo.crearProducto(p);
    }

    public List<Producto> listarProductos() {
        return repo.listarProductos();
    }

    public int obtenerStock(int id) {
        return repo.obtenerStock(id);
    }

    public void eliminarProducto(int id){
        repo.eliminarProducto(id);
    }

    public void modificarPrecio(int id, double precioNuevo){
        repo.modificarPrecio(id, precioNuevo);
    }

    public Producto devolverProductoPorId(int id){
        return repo.devolverProductoPorId(id);
    }


}
