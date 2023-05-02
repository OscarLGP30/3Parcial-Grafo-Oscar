package org.example;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm.SingleSourcePaths;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.jgrapht.traverse.DepthFirstIterator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Grafo {
    private Graph<Puerto, DefaultWeightedEdge> grafo;

    public Grafo() {
        grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
    }

    public void agregarPuerto(Puerto puerto) {
        grafo.addVertex(puerto);
    }

    public void agregarArista(Puerto origen, Puerto destino, int distancia) {
        DefaultWeightedEdge arista = grafo.addEdge(origen, destino);
        grafo.setEdgeWeight(arista, distancia);
    }

    public List<Puerto> barridoProfundidad(Puerto inicio) {
        DepthFirstIterator<Puerto, DefaultWeightedEdge> iterador = new DepthFirstIterator<>(grafo, inicio);
        while (iterador.hasNext()) {
            Puerto puerto = iterador.next();
            System.out.println(puerto);
        }
        return null;
    }

    public List<Arista> caminoMasCorto(Puerto origen, Puerto destino) {
        ShortestPathAlgorithm<Puerto, DefaultWeightedEdge> dijkstraAlg = new DijkstraShortestPath<>(this.grafo);
        SingleSourcePaths<Puerto, DefaultWeightedEdge> iPaths = dijkstraAlg.getPaths(origen);
        GraphPath<Puerto, DefaultWeightedEdge> path = iPaths.getPath(destino);
        List<Arista> aristas = new ArrayList<>();
        for (DefaultWeightedEdge edge : path.getEdgeList()) {
            aristas.add(new Arista(grafo.getEdgeWeight(edge)));
        }
        return aristas;
    }

    public Puerto eliminarPuertoConMasAristas() {
        Set<Puerto> puertos = grafo.vertexSet();
        Puerto puertoConMasAristas = null;
        int maxAristas = 0;
        for (Puerto puerto : puertos) {
            int numAristas = grafo.degreeOf(puerto);
            if (numAristas > maxAristas) {
                maxAristas = numAristas;
                puertoConMasAristas = puerto;
            }
        }
        if (puertoConMasAristas != null) {
            grafo.removeVertex(puertoConMasAristas);
        }
        return puertoConMasAristas;
    }

    public Puerto puertoConMasAristas() {
        Set<Puerto> puertos = grafo.vertexSet();
        Puerto puertoConMasAristas = null;
        int maxAristas = 0;
        for (Puerto puerto : puertos) {
            int numAristas = grafo.degreeOf(puerto);
            if (numAristas > maxAristas) {
                maxAristas = numAristas;
                puertoConMasAristas = puerto;
            }
        }
        return puertoConMasAristas;
    }

}




