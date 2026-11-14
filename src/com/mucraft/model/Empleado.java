package com.mucraft.model;

public record Empleado(String usuario, String nombre1, String nombre2, String apellido1,
        String apellido2, String correo, String codigoRol, String codigoEstado) {
    public String nombreCompleto() {
        return String.join(" ", java.util.stream.Stream.of(nombre1, nombre2, apellido1, apellido2)
                .filter(s -> s != null && !s.isBlank()).toList());
    }
}
