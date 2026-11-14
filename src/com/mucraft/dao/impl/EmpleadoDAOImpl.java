package com.mucraft.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import com.mucraft.config.DatabaseConnection;
import com.mucraft.dao.interfaces.IEmpleadoDAO;
import com.mucraft.model.Empleado;

public final class EmpleadoDAOImpl implements IEmpleadoDAO {
    @Override public Optional<Empleado> buscarPorUsuario(String usuario) throws SQLException {
        String sql = "SELECT Usuario, Nombre1, Nombre2, Apellido1, Apellido2, Correo, CodigoRol, CodigoEstado FROM Empleados WHERE Usuario=?";
        try (Connection c = DatabaseConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, usuario);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return Optional.empty();
                return Optional.of(new Empleado(rs.getString("Usuario"), rs.getString("Nombre1"),
                    rs.getString("Nombre2"), rs.getString("Apellido1"), rs.getString("Apellido2"),
                    rs.getString("Correo"), rs.getString("CodigoRol"), rs.getString("CodigoEstado")));
            }
        }
    }
    @Override public String obtenerContrasena(String usuario) throws SQLException {
        try (Connection c = DatabaseConnection.getConnection(); PreparedStatement ps = c.prepareStatement("SELECT Contrasena FROM Empleados WHERE Usuario=?")) {
            ps.setString(1, usuario);
            try (ResultSet rs = ps.executeQuery()) { return rs.next() ? rs.getString(1) : null; }
        }
    }
}
