package com.radino.practicando.repository;

import com.radino.practicando.model.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.dao.EmptyResultDataAccessException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ProductoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void crearProducto(Producto p){

        String sql = "insert into producto (nombre, precio, stock) values (?, ?, ?)";
        jdbcTemplate.update(sql, p.getNombre(), p.getPrecio(), p.getStock());
    }

    public List<Producto> listarProductos(){

        String sql = "select * from producto";

        //Un RowMapper es simplemente una clase que sabe responder:
        //"Tengo una fila del ResultSet. ¿Cómo la convierto en un objeto Java?"
        //ResultSet es el objeto que contiene los resultados de una consulta SQL.
        return jdbcTemplate.query(sql, new RowMapper<Producto>() {

            //Spring llama automáticamente este metodo por cada fila encontrada
            public Producto mapRow(ResultSet rs, int rowNum) throws SQLException{

                //Crear el objeto Producto
                return new Producto(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getDouble("precio"),
                        rs.getInt("stock")
                );
            }
        });
    }

    public int obtenerStock(int id){

        // Consulta SQL para obtener el stock de un producto según su id
        String sql = "SELECT stock FROM producto WHERE id = ?";

        // queryForObject: 1) Ejecuta la consulta SQL; 2) Reemplaza el ? por el valor de id;
        // 3) Obtiene un único resultado; 4) Lo convierte a Integer; 5) Lo retorna

        return jdbcTemplate.queryForObject(sql, Integer.class, id);
    }

    public void eliminarProducto(int id){

        String sql = "DELETE FROM producto WHERE id = ?";

        jdbcTemplate.update(sql, id);
    }

    public void modificarPrecio(int id, double precioNuevo){

        String sql = "UPDATE producto SET precio = ? WHERE id = ?";

        jdbcTemplate.update(sql, precioNuevo, id);
    }

    public void actualizarStock(int id, int nuevoStock){

        String sql = "UPDATE producto SET stock = ? WHERE id = ?";

        jdbcTemplate.update(sql, nuevoStock, id);
    }

    public Producto devolverProductoPorId(int id){

        String sql = "SELECT * FROM producto WHERE id = ?";

        return jdbcTemplate.queryForObject(sql, new RowMapper<Producto>() {

            @Override
            public Producto mapRow(ResultSet rs, int rowNum) throws SQLException{

                return new Producto(

                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getDouble("precio"),
                        rs.getInt("stock")

                );
            }
        }, id);
    }

    public List<Producto> listarProductosPorNombre(String nombre){

        String sql = "SELECT * FROM producto WHERE nombre LIKE ?";

        return jdbcTemplate.query(sql, new RowMapper<Producto>() {
            @Override
            public Producto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Producto(

                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getDouble("precio"),
                        rs.getInt("stock")
                );
            }
        }, "%" + nombre + "%");
    }
}
