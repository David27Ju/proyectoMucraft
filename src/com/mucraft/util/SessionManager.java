package com.mucraft.util;

import java.util.Optional;
import com.mucraft.model.Empleado;

/** Sesión en memoria del usuario autenticado. */
public final class SessionManager {
    private static final SessionManager INSTANCE = new SessionManager();
    private volatile Empleado empleado;
    private SessionManager() { }
    public static SessionManager getInstance() { return INSTANCE; }
    public synchronized void iniciar(Empleado user) {
        if (user == null) throw new IllegalArgumentException("El empleado es obligatorio");
        empleado = user;
    }
    public synchronized void cerrar() { empleado = null; }
    public Optional<Empleado> empleadoActual() { return Optional.ofNullable(empleado); }
    public String usuarioActual() {
        return empleadoActual().orElseThrow(() -> new IllegalStateException("No hay sesión iniciada")).usuario();
    }
    public String rolActual() {
        return empleadoActual().orElseThrow(() -> new IllegalStateException("No hay sesión iniciada")).codigoRol();
    }
}
