package com.mucraft.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.mucraft.config.DatabaseConnection;
import com.mucraft.dao.interfaces.ICatalogoDAO;
import com.mucraft.model.CatalogoItem;

public final class CatalogoDAOImpl implements ICatalogoDAO {
    @Override public List<CatalogoItem> continentes() throws SQLException {
        return query("SELECT CodigoContinente, NombreContinente FROM Continentes ORDER BY NombreContinente");
    }
    @Override public List<CatalogoItem> paises(String id) throws SQLException {
        return filtered("SELECT CodigoPais, NombrePais FROM Paises WHERE CodigoContinente=? ORDER BY NombrePais", id);
    }
    @Override public List<CatalogoItem> ciudades(String id) throws SQLException {
        return filtered("SELECT CodigoCiudad, NombreCiudad FROM Ciudades WHERE CodigoPais=? ORDER BY NombreCiudad", id);
    }
    @Override public List<CatalogoItem> tiposAnimal() throws SQLException {
        return query("SELECT CodigoTipoAnimal, NombreTipo FROM TiposAnimal ORDER BY NombreTipo");
    }
    @Override public List<CatalogoItem> especies(int id) throws SQLException {
        return filtered("SELECT CodigoEspecie, NombreEspecie FROM Especies WHERE CodigoTipoAnimal=? ORDER BY NombreEspecie", id);
    }
    @Override public List<CatalogoItem> razas(int id) throws SQLException {
        return filtered("SELECT CodigoRaza, NombreRaza FROM Razas WHERE CodigoEspecie=? ORDER BY NombreRaza", id);
    }
    @Override public List<CatalogoItem> catalogo(String tabla, String codigo, String nombre) throws SQLException {
        // Identificadores SQL no se aceptan como parámetros; validar por allowlist.
        String[][] allow = {{"TipoDocumento", "CodigoTipoDoc", "Descripcion"}, {"Roles", "CodigoRol", "Descripcion"},
            {"EstadoEmpleado", "CodigoEstado", "Descripcion"}, {"Genero", "CodigoGenero", "Descripcion"},
            {"OrigenAnimal", "CodigoOrigen", "Descripcion"}, {"EstadoAnimal", "CodigoEstado", "Descripcion"},
            {"EstadosTarea", "CodigoEstado", "Descripcion"}, {"PrioridadTarea", "CodigoPrioridad", "Descripcion"},
            {"TiposTarea", "CodigoTipoTarea", "Descripcion"}, {"ProcedimientosMedicos", "CodigoProcedimiento", "NombreProcedimiento"}};
        for (String[] item : allow) if (item[0].equals(tabla) && item[1].equals(codigo) && item[2].equals(nombre))
            return query("SELECT " + codigo + ", " + nombre + " FROM " + tabla + " ORDER BY " + nombre);
        throw new IllegalArgumentException("Catálogo no permitido: " + tabla);
    }
    private List<CatalogoItem> filtered(String sql, Object id) throws SQLException {
        try (Connection c = DatabaseConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setObject(1, id);
            return read(ps);
        }
    }
    private List<CatalogoItem> query(String sql) throws SQLException {
        try (Connection c = DatabaseConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            return read(ps);
        }
    }
    private List<CatalogoItem> read(PreparedStatement ps) throws SQLException {
        List<CatalogoItem> items = new ArrayList<>();
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) items.add(new CatalogoItem(rs.getString(1), rs.getString(2)));
        }
        return items;
    }
}
