package com.mucraft.dao.interfaces;

import java.sql.SQLException;
import java.util.List;
import com.mucraft.model.CatalogoItem;

public interface ICatalogoDAO {
    List<CatalogoItem> continentes() throws SQLException;
    List<CatalogoItem> paises(String codigoContinente) throws SQLException;
    List<CatalogoItem> ciudades(String codigoPais) throws SQLException;
    List<CatalogoItem> tiposAnimal() throws SQLException;
    List<CatalogoItem> especies(int codigoTipoAnimal) throws SQLException;
    List<CatalogoItem> razas(int codigoEspecie) throws SQLException;
    List<CatalogoItem> catalogo(String tabla, String columnaCodigo, String columnaNombre) throws SQLException;
}
