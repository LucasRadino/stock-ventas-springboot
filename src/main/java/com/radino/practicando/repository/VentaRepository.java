package com.radino.practicando.repository;

import com.radino.practicando.model.Venta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class VentaRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public void crearVenta(Venta v){

        String sql = "INSERT INTO venta (producto_id, cantidad, fecha) VALUES (?, ?, ?)";

        jdbcTemplate.update(sql, v.getProductoId(), v.getCantidad(), v.getFecha());
    }

    public List<Venta> listarVentas(){

        String sql = "SELECT * FROM venta";

        return jdbcTemplate.query(sql, new RowMapper<Venta>() {

            public Venta mapRow(ResultSet rs, int rowNum) throws SQLException{

                return new Venta(

                        rs.getInt("id"),
                        rs.getInt("producto_id"),
                        rs.getInt("cantidad"),
                        rs.getTimestamp("fecha").toLocalDateTime()
                );
            }
        });

    }


    public Venta devolverVentaPorId(int id){

        String sql = "SELECT * FROM venta WHERE id = ?";

        return jdbcTemplate.queryForObject(sql, new RowMapper<Venta>() {
            @Override
            public Venta mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Venta(

                        rs.getInt("id"),
                        rs.getInt("producto_id"),
                        rs.getInt("cantidad"),
                        rs.getTimestamp("fecha").toLocalDateTime()


                );
            }
        }, id);
    }

    public void eliminarVenta(int id){

        String sql = "DELETE FROM venta WHERE id = ?";

        jdbcTemplate.update(sql, id);
    }


    public List<Venta> listarVentasDeUnProducto(int idProducto){

        String sql = "SELECT * FROM venta WHERE producto_id = ?";

        return jdbcTemplate.query(sql, new RowMapper<Venta>() {
            @Override
            public Venta mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Venta(
                        rs.getInt("id"),
                        rs.getInt("producto_id"),
                        rs.getInt("cantidad"),
                        rs.getTimestamp("fecha").toLocalDateTime()
                );
            }
        }, idProducto);
    }

}
