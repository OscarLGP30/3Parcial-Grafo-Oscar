package org.example;

public class Battleship {
    private final int size = 5;
    private int hits = 0;
    private boolean[] isIsolated = { true, false, false, false, true }; // Indica si una posición está aislada
    private Puerto puertoActual; // Puerto actual del barco

    // Constructor por defecto
    public Battleship() {}

    // Constructor con el puerto inicial del barco
    public Battleship(Puerto puerto) {
        this.puertoActual = puerto;
    }

    // Método para obtener el puerto actual del barco
    public Puerto getPuertoActual() {
        return puertoActual;
    }

    // Método para establecer el puerto actual del barco
    public void setPuertoActual(Puerto puerto) {
        this.puertoActual = puerto;
    }

    // Método para comprobar si el barco está hundido
    public boolean isSunk() {
        return hits == size;
    }

    // Método para comprobar si una posición del barco ha sido impactada
    public boolean isHit(int position) {
        if (position < 0 || position >= size) {
            throw new IllegalArgumentException("Posición inválida");
        }
        if (isIsolated[position]) {
            // Si la posición está aislada incrementamos los hits
            hits++;
            isIsolated[position] = false; // Ya no está aislada
            return true;
        }
        return false;
    }

    public Puerto getPuerto() {
        return puertoActual;
    }

    public void setPuerto(Puerto puerto2) {
        this.puertoActual = puerto2;
    }
}


