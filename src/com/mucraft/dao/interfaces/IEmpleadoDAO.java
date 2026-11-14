package com.mucraft.dao.interfaces;

import java.sql.SQLException;
import java.util.Optional;
import com.mucraft.model.Empleado;

public interface IEmpleadoDAO {
    Optional<Empleado> buscarPorUsuario(String usuario) throws SQLException;
    String obtenerContrasena(String usuario) throws SQLException;
}
