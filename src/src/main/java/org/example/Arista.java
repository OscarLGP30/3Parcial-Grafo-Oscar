package org.example;

public class Arista implements Comparable<Arista> {
    private Puerto origen;
    private Puerto destino;
    private int peso;

    public Arista(Puerto origen, Puerto destino, int peso) {
        this.origen = origen;
        this.destino = destino;
        this.peso = peso;
    }

    public Arista(double edgeWeight) {
        this.peso = (int) edgeWeight;
    }

    public Puerto getOrigen() {
        return origen;
    }

    public void setOrigen(Puerto origen) {
        this.origen = origen;
    }

    public Puerto getDestino() {
        return destino;
    }

    public void setDestino(Puerto destino) {
        this.destino = destino;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    @Override
    public int compareTo(Arista otra) {
        return Integer.compare(this.peso, otra.peso);
    }

    @Override
    public String toString() {
        return "Arista{" +
                "origen=" + origen.getNombre() +
                ", destino=" + destino.getNombre() +
                ", peso=" + peso +
                '}';
    }
}
