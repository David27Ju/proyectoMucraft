package com.mucraft.model;

public record CatalogoItem(String id, String descripcion) {
    @Override public String toString() { return descripcion; }
}
