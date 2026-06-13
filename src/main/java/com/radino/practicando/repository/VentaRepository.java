package com.radino.practicando.repository;

import com.radino.practicando.model.Venta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public class VentaRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void registrarVenta(int productoId, int cantidad) {

        String sql = "INSERT INTO venta (producto_id, cantidad, fecha) VALUES (?, ?, ?)";

        jdbcTemplate.update(sql, productoId, cantidad, LocalDateTime.now());
    }
}