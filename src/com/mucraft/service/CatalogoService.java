package com.mucraft.service;

import java.sql.SQLException;
import java.util.List;
import com.mucraft.dao.interfaces.ICatalogoDAO;
import com.mucraft.model.CatalogoItem;

/** Validaciones ligeras y acceso coordinado a los catálogos relacionales. */
public final class CatalogoService {
    private final ICatalogoDAO dao;
    public CatalogoService(ICatalogoDAO dao) { this.dao = dao; }
    public List<CatalogoItem> continentes() throws SQLException { return dao.continentes(); }
    public List<CatalogoItem> paises(String continente) throws SQLException {
        if (continente == null || continente.isBlank()) return List.of();
        return dao.paises(continente);
    }
    public List<CatalogoItem> ciudades(String pais) throws SQLException {
        if (pais == null || pais.isBlank()) return List.of();
        return dao.ciudades(pais);
    }
    public List<CatalogoItem> tiposAnimal() throws SQLException { return dao.tiposAnimal(); }
    public List<CatalogoItem> especies(int tipo) throws SQLException { return dao.especies(tipo); }
    public List<CatalogoItem> razas(int especie) throws SQLException { return dao.razas(especie); }
    public List<CatalogoItem> catalogo(String tabla, String codigo, String descripcion) throws SQLException {
        return dao.catalogo(tabla, codigo, descripcion);
    }
}
