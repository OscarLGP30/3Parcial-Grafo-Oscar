package org.example;

import java.util.List;

public class Main {

        public static void main(String[] args) {
                // Crear grafo
                Grafo grafo = new Grafo();

                // Crear puertos
                Puerto puertoMadero = new Puerto("Puerto Madero", 0);
                Puerto buenosAires = new Puerto("Buenos Aires", 10);
                Puerto montevideo = new Puerto("Montevideo", 15);
                Puerto rioDeJaneiro = new Puerto("Río de Janeiro", 20);
                Puerto rodas = new Puerto("Rodas", 25);

                // Agregar puertos al grafo
                grafo.agregarPuerto(puertoMadero);
                grafo.agregarPuerto(buenosAires);
                grafo.agregarPuerto(montevideo);
                grafo.agregarPuerto(rioDeJaneiro);
                grafo.agregarPuerto(rodas);

                // Agregar aristas al grafo
                grafo.agregarArista(puertoMadero, buenosAires, 100);
                grafo.agregarArista(buenosAires, montevideo, 200);
                grafo.agregarArista(montevideo, rioDeJaneiro, 300);
                grafo.agregarArista(rioDeJaneiro, rodas, 400);
                grafo.agregarArista(montevideo, rodas, 500);
                grafo.agregarArista(puertoMadero, rodas, 600);

                // Mostrar información del grafo
                System.out.println("Información del grafo:");
                System.out.println(grafo);

                // Realizar un barrido en profundidad desde el primer puerto
                System.out.println("Recorrido en profundidad:");
                List<Puerto> recorrido = grafo.barridoProfundidad(puertoMadero);
                if (recorrido != null) {
                        for (Puerto puerto : recorrido) {
                                System.out.println(puerto);
                        }
                } else {
                        System.out.println("No se encontró recorrido.");
                }

                // Determinar el camino más corto desde Puerto Madero a Rodas
                List<Arista> caminoMasCorto = grafo.caminoMasCorto(puertoMadero, rodas);
                System.out.println("Camino más corto desde Puerto Madero a Rodas:");
                if (caminoMasCorto != null) {
                        for (Arista arista : caminoMasCorto) {
                                if (arista.getOrigen() != null && arista.getDestino() != null) {
                                        System.out.println(arista.getOrigen().getNombre() + " -> " + arista.getDestino().getNombre() + ": " + arista.getPeso());
                                }

                                // Determinar el puerto con mayor número de aristas y eliminarlo
                                Puerto puertoConMasAristas = grafo.puertoConMasAristas();
                                if (puertoConMasAristas != null) {
                                        System.out.println("Puerto con más aristas: " + puertoConMasAristas);
                                        grafo.eliminarPuertoConMasAristas();
                                        System.out.println("Información del grafo después de eliminar el puerto:");
                                        System.out.println(grafo);
                                } else {
                                        System.out.println("No se encontró puerto con aristas.");
                                }
                        }
                }


        }
}




