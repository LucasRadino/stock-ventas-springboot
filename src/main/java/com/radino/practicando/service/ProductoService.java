package com.radino.practicando.service;

import com.radino.practicando.dto.ProductoRequest;
import com.radino.practicando.dto.ProductoResponse;
import com.radino.practicando.exception.ProductoNoEncontradoException;
import com.radino.practicando.model.Producto;
import com.radino.practicando.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service → indica que esta clase contiene lógica de negocio.
//Spring crea y administra automáticamente este objeto.
//El Controller utiliza este Service para comunicarse con el Repository.
@Service
public class ProductoService {


    //Spring inyecta automáticamente el ProductoRepository.
    //El Service utiliza el Repository para acceder a la base de datos.
    @Autowired
    private ProductoRepository repo;



    //Convierte el ProductoRequest recibido desde el Controller
    //en una entidad Producto para guardarla en la base de datos.
    public void crearProducto(ProductoRequest request) {

        Producto producto = new Producto();

        producto.setNombre(request.getNombre());
        producto.setPrecio(request.getPrecio());
        producto.setStock(request.getStock());

        repo.crearProducto(producto);
    }

    public List<ProductoResponse> listarProductos(){

        return repo.listarProductos()
                .stream()
                .map(this::convertirAResponse)
                .toList();
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

    public ProductoResponse devolverProductoPorId(int id){

        try{

            Producto producto = repo.devolverProductoPorId(id);

            return convertirAResponse(producto);

        }catch (EmptyResultDataAccessException e){

            throw new ProductoNoEncontradoException(
                    "No existe un producto con id " + id
            );
        }
    }

    public List<ProductoResponse> listarProductosPorNombre(String nombre){

        return repo.listarProductosPorNombre(nombre)
                .stream()
                .map(this::convertirAResponse)
                .toList();
    }

    private ProductoResponse convertirAResponse(Producto producto){

        return new ProductoResponse(
                producto.getId(),
                producto.getNombre(),
                producto.getPrecio(),
                producto.getStock()
        );
    }


}
