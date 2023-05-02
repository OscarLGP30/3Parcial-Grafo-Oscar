package org.example;

public class Ruta {
    private Puerto origen;
    private Puerto destino;
    private int distancia;

    public Ruta(Puerto origen, Puerto destino, int distancia) {
        this.origen = origen;
        this.destino = destino;
        this.distancia = distancia;
    }

    public Puerto getOrigen() {
        return origen;
    }

    public Puerto getDestino() {
        return destino;
    }

    public int getDistancia() {
        return distancia;
    }
}

