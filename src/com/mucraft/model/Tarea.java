package com.mucraft.model;

import java.time.LocalDateTime;

public record Tarea(Integer codigoTarea, LocalDateTime fechaAlta, LocalDateTime fechaFinalizacion,
        LocalDateTime fechaActualizacion, String codigoEstadoTarea, String codigoPrioridad,
        String empleadoCreador, String empleadoAsignado, String codigoTipoTarea,
        Integer codigoLote, Integer codigoAnimal, Integer codigoProcedimiento) { }
