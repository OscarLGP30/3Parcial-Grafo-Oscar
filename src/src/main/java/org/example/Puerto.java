package org.example;

import org.jgrapht.alg.util.Pair;
import org.jheaps.AddressableHeap;
import org.jheaps.tree.PairingHeap;

import java.util.function.Supplier;

public class Puerto implements Supplier<AddressableHeap<Double, Pair<Puerto, Arista>>> {
    private String nombre;
    private int distancia;
    private AddressableHeap<Double, Pair<Puerto, Arista>> heap;

    public Puerto(String nombre, int distancia) {
        this.nombre = nombre;
        this.distancia = distancia;
        this.heap = new PairingHeap<>();
    }

    public String getNombre() {
        return nombre;
    }

    public int getDistancia() {
        return distancia;
    }

    @Override
    public String toString() {
        return nombre;
    }

    @Override
    public AddressableHeap<Double, Pair<Puerto, Arista>> get() {
        return heap;
    }
}

