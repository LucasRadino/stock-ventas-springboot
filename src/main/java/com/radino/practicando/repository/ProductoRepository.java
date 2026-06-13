package com.radino.practicando.repository;

import com.radino.practicando.model.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import org.springframework.jdbc.core.JdbcTemplate;


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

        String sql = "SELECT * FROM producto";

        //query() Se usa cuando esperás: MUCHAS filas  o una lista de objetos
        return jdbcTemplate.query(sql, new RowMapper<Producto>() {

            public Producto mapRow(ResultSet rs, int rowNum) throws SQLException {

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

        String sql = "SELECT stock FROM producto WHERE id = ?";

        //queryForObject() Se usa cuando esperás: UN solo valor o UNA sola fila
        return jdbcTemplate.queryForObject(sql, Integer.class, id);
        //"Integer.class" le dice a Spring: “el resultado que viene de SQL
        //convertímelo a Integer” Porque el stock es un entero.
        //Es parecido a decir: quiero que el resultado sea tipo Integer
    }

    public void eliminarProducto(int id){
        String sql = "DELETE FROM producto WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public void modificarPrecio(int id, double precio){

        String sql = "UPDATE producto SET precio = ? WHERE id = ?";
        jdbcTemplate.update(sql, precio, id);
    }

    public void actualizarStock(int id, int stock) {
        String sql = "UPDATE producto SET stock = ? WHERE id = ?";
        jdbcTemplate.update(sql, stock, id);
    }


}
