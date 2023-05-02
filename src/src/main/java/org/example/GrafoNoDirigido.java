package org.example;

import java.util.*;

public class GrafoNoDirigido {

    private final Map<String, ArrayList<Object>> grafo = new HashMap<>();

    // Clase interna que representa una arista
    private static class Arista {
        String destino;
        int distancia;

        Arista(String destino, int distancia) {
            this.destino = destino;
            this.distancia = distancia;
        }
    }

    // Método para agregar un puerto al grafo
    public void agregarPuerto(String puerto) {
        grafo.put(puerto, new ArrayList<>());
    }

    // Método para agregar una arista que conecta dos puertos, con su respectiva distancia
    public void agregarArista(String puertoOrigen, String puertoDestino, int distancia) {
        if (!grafo.containsKey(puertoOrigen)) {
            throw new IllegalArgumentException("El puerto origen no existe en el grafo");
        }
        if (!grafo.containsKey(puertoDestino)) {
            throw new IllegalArgumentException("El puerto destino no existe en el grafo");
        }
        grafo.get(puertoOrigen).add(new Arista(puertoDestino, distancia));
        grafo.get(puertoDestino).add(new Arista(puertoOrigen, distancia));
    }

    // Método para realizar un barrido en profundidad desde el primer puerto en el grafo
    public void barridoEnProfundidad() {
        Set<String> visitados = new HashSet<>();
        for (String puerto : grafo.keySet()) {
            if (!visitados.contains(puerto)) {
                barridoEnProfundidadAux(puerto, visitados);
            }
        }
    }

    // Método auxiliar para realizar el barrido en profundidad recursivamente
    private void barridoEnProfundidadAux(String puerto, Set<String> visitados) {
        System.out.print(puerto + " ");
        visitados.add(puerto);
        for (Object arista : grafo.get(puerto)) {
            if (!visitados.contains(arista.destino)) {
                barridoEnProfundidadAux(arista.destino, visitados);
            }
        }
    }

    // Método para determinar el camino más corto desde puertoOrigen al puertoDestino
    public List<String> caminoMasCorto(String puertoOrigen, String puertoDestino) {
        if (!grafo.containsKey(puertoOrigen)) {
            throw new IllegalArgumentException("El puerto origen no existe en el grafo");
        }
        if (!grafo.containsKey(puertoDestino)) {
            throw new IllegalArgumentException("El puerto destino no existe en el grafo");
        }

        // Inicializamos las distancias a cada puerto como "infinito" excepto al puerto de origen
        Map<String, Integer> distancias = new HashMap<>();
        for (String puerto : grafo.keySet()) {
            distancias.put(puerto, Integer.MAX_VALUE);
        }
        distancias.put(puertoOrigen, 0);

        // Usamos una cola de prioridad para manejar los nodos no visitados y sus distancias
        Queue<String> cola = new PriorityQueue<>(Comparator.comparingInt(distancias::get));
        cola.add(puertoOrigen);

        // Mantenemos un mapa de padres para poder reconstruir el camino más corto al final
        Map<String, String> padres = new HashMap<>();

        // Algoritmo de Dijkstra para calcular las distancias mínimas
        while (!cola.isEmpty()) {
            String actual = cola.poll();
            if (actual.equals(puertoDestino)) {
                break; // Ya llegamos al puerto destino, podemos salir del bucle
            }
            for (Object arista : grafo.get(actual)) {
                int distanciaNueva = distancias.get(actual) + arista.distancia;
                if (distanciaNueva < distancias.get(arista.destino)) {
                    distancias.put(arista.destino, distanciaNueva);
                    cola.add(arista.destino);
                    padres.put(arista.destino, actual);
                }
            }
        }

        // Reconstruimos el camino más corto
        List<String> camino = new ArrayList<>();
        String actual = puertoDestino;
        while (actual != null) {
            camino.add(actual);
            actual = padres.get(actual);
        }
        Collections.reverse(camino);
        return camino;
    }

    // Método para determinar el puerto con mayor número de aristas y eliminarlo
    public String eliminarPuertoConMasAristas() {
        String puertoConMasAristas = null;
        int maxAristas = 0;
        for (String puerto : grafo.keySet()) {
            int numAristas = grafo.get(puerto).size();
            if (numAristas > maxAristas) {
                maxAristas = numAristas;
                puertoConMasAristas = puerto;
            }
        }
        grafo.remove(puertoConMasAristas);
        for (String puerto : grafo.keySet()) {
            List<Object> aristas = grafo.get(puerto);
            String finalPuertoConMasAristas = puertoConMasAristas;
            aristas.removeIf(arista -> arista.destino.equals(finalPuertoConMasAristas));
        }
        return puertoConMasAristas;
    }

    // Método para obtener la representación del grafo como un String
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String puerto : grafo.keySet()) {
            sb.append(puerto).append(": ");
            for (Object arista : grafo.get(puerto)) {
                sb.append("(").append(arista.destino).append(", ").append(arista.distancia).append(") ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    // Método para probar el grafo
    public static void main(String[] args) {
        GrafoNoDirigido grafo = new GrafoNoDirigido();
        grafo.agregarPuerto("Puerto Madero");
        grafo.agregarPuerto("Puerto de Rodas");
        grafo.agregarPuerto("Puerto de Sydney");
        grafo.agregarPuerto("Puerto de Singapur");
        grafo.agregarArista("Puerto Madero", "Puerto de Rodas", 10);
        grafo.agregarArista("Puerto Madero", "Puerto de Sydney", 15);
        grafo.agregarArista("Puerto de Rodas", "Puerto de Singapur", 20);
        grafo.agregarArista("Puerto de Sydney", "Puerto de Singapur", 25);
        System.out.println(grafo);
        grafo.barridoEnProfundidad();
        System.out.println();
        System.out.println(grafo.caminoMasCorto("Puerto Madero", "Puerto de Rodas"));
        System.out.println();
        System.out.println(grafo.eliminarPuertoConMasAristas());
        System.out.println(grafo);
    }
}


