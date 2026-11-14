package com.mucraft.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Animal(Integer codigoAnimal, Integer codigoLote, int codigoTipoAnimal,
        int codigoEspecie, Integer codigoRaza, String codigoGenero, String codigoOrigen,
        String observaciones, String codigoEstadoAnimal, BigDecimal precioCompra,
        String empleadoRegistro, Integer codigoHistoriaClinica, int codigoFinca,
        LocalDateTime fechaRegistro, String usuarioModifico) { }
