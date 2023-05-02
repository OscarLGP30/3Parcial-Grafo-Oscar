package org.example;

public class Battleship {
    private final int size = 5;
    private int hits = 0;
    private boolean[] isIsolated = { true, false, false, false, true }; // Indica si una posición está aislada

    // Constructor por defecto
    public Battleship() {}

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
}

