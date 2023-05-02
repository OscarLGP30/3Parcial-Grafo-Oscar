package org.example;

import java.util.*;

public class GrafoNoDirigido {
    private HashMap<String, ArrayList<Arista>> grafo;

    public GrafoNoDirigido() {
        grafo = new HashMap<>();
    }

    public void agregarPuerto(String puerto) {
        grafo.put(puerto, new ArrayList<>());
    }

    public void agregarArista(String puerto1, String puerto2, int distancia) {
        ArrayList<Arista> aristas1 = grafo.get(puerto1);
        ArrayList<Arista> aristas2 = grafo.get(puerto2);

        aristas1.add(new Arista(puerto2, distancia));
        aristas2.add(new Arista(puerto1, distancia));
    }

    public void eliminarPuerto(String puerto) {
        ArrayList<Arista> aristas = grafo.get(puerto);

        for (String p : grafo.keySet()) {
            ArrayList<Arista> aristasVecino = grafo.get(p);
            for (int i = 0; i < aristasVecino.size(); i++) {
                Arista arista = aristasVecino.get(i);
                if (arista.getPuerto().equals(puerto)) {
                    aristasVecino.remove(i);
                    break;
                }
            }
        }

        grafo.remove(puerto);
    }

    public void barridoProfundidad(String inicio) {
        HashSet<String> visitados = new HashSet<>();
        barridoProfundidadAux(inicio, visitados);
    }

    private void barridoProfundidadAux(String puerto, HashSet<String> visitados) {
        visitados.add(puerto);
        System.out.print(puerto + " ");

        ArrayList<Arista> aristas = grafo.get(puerto);
        for (Arista arista : aristas) {
            String vecino = arista.getPuerto();
            if (!visitados.contains(vecino)) {
                barridoProfundidadAux(vecino, visitados);
            }
        }
    }

    public int caminoMasCorto(String origen, String destino) {
        HashMap<String, Integer> distancia = new HashMap<>();
        HashSet<String> visitados = new HashSet<>();
        PriorityQueue<String> cola = new PriorityQueue<>(Comparator.comparingInt(distancia::get));

        for (String puerto : grafo.keySet()) {
            distancia.put(puerto, Integer.MAX_VALUE);
        }

        distancia.put(origen, 0);
        cola.add(origen);

        while (!cola.isEmpty()) {
            String puerto = cola.poll();
            visitados.add(puerto);

            ArrayList<Arista> aristas = grafo.get(puerto);
            for (Arista arista : aristas) {
                String vecino = arista.getPuerto();
                int peso = arista.getPeso();
                if (!visitados.contains(vecino) && distancia.get(puerto) + peso < distancia.get(vecino)) {
                    distancia.put(vecino, distancia.get(puerto) + peso);
                    cola.add(vecino);
                }
            }
        }

        return distancia.get(destino);
    }

    public String eliminarPuertoConMasAristas() {
        int maxAristas = Integer.MIN_VALUE;
        String puertoMaxAristas = "";

        for (String puerto : grafo.keySet()) {
            ArrayList<Arista> aristas = grafo.get(puerto);
            int numAristas = aristas.size();
