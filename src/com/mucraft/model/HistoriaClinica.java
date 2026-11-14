package com.mucraft.model;

import java.time.LocalDateTime;

public record HistoriaClinica(Integer codigoHistoria, LocalDateTime fechaCreacion,
        String observacionesGenerales) { }
