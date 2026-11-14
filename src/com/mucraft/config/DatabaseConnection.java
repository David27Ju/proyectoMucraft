package com.mucraft.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/** Proveedor de conexiones JDBC. Obtiene una conexión por operación; no comparte
 * una Connection entre hilos Swing. Configuración mediante variables de entorno. */
public final class DatabaseConnection {
    private static final String DEFAULT_URL = "jdbc:mysql://localhost:3306/Mucraft_Proyecto_Sena";

    private DatabaseConnection() { }

    public static Connection getConnection() throws SQLException {
        String url = setting("MUCRAFT_DB_URL", DEFAULT_URL);
        String user = setting("MUCRAFT_DB_USER", "admin_mucraft");
        String password = setting("MUCRAFT_DB_PASSWORD", "1095933861");
        return DriverManager.getConnection(url, user, password);
    }

    /** Ejecuta una consulta mínima para confirmar autenticación y selección de base. */
    public static String testConnection() throws SQLException {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery("SELECT CURRENT_USER(), DATABASE()")) {
            if (!result.next()) throw new SQLException("MySQL no devolvió información de conexión.");
            return "Conexión exitosa: " + result.getString(1) + " · " + result.getString(2);
        }
    }

    private static String setting(String key, String fallback) {
        String value = System.getenv(key);
        return value == null || value.isBlank() ? fallback : value;
    }
}
