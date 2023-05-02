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

                // Crear los puertos
                Puerto puerto1 = new Puerto("Puerto 1",10);
                Puerto puerto2 = new Puerto("Puerto 2",20);
                Puerto puerto3 = new Puerto("Puerto 3",30);

                // Crear los barcos
                Battleship barco1 = new Battleship(puerto1);
                Battleship barco2 = new Battleship(puerto2);
                Battleship barco3 = new Battleship(puerto3);

                // Crear las rutas
                Ruta ruta1 = new Ruta(puerto1, puerto2, 500);
                Ruta ruta2 = new Ruta(puerto2, puerto3, 300);
                Ruta ruta3 = new Ruta(puerto1, puerto3, 700);

                // Crear el grafo y agregar los puertos, barcos y rutas
                Grafo grafo1 = new Grafo();
                grafo1.agregarVertice(puerto1);
                grafo1.agregarVertice(puerto2);
                grafo1.agregarVertice(puerto3);
                grafo1.agregarVertice(barco1.getPuertoActual());
                grafo1.agregarVertice(barco2.getPuertoActual());
                grafo1.agregarVertice(barco3.getPuertoActual());
                grafo1.agregarBarco(puerto1, puerto2, ruta1.getDistancia());
                grafo1.agregarBarco(puerto2, puerto3, ruta2.getDistancia());
                grafo1.agregarBarco(puerto1, puerto3, ruta3.getDistancia());
                grafo1.agregarBarco(puerto1, barco1.getPuertoActual(), 0);
                grafo1.agregarBarco(puerto2, barco2.getPuertoActual(), 0);
                grafo1.agregarBarco(puerto3, barco3.getPuertoActual(), 0);

                // Mover el barco 1 desde puerto 1 a puerto 2
                grafo1.mover(barco1, puerto2);

                // Comprobar si el barco 1 ha llegado a puerto 2
                if (barco1.getPuertoActual() == puerto2) {
                        System.out.println("El barco 1 ha llegado a " + puerto2.getNombre());
                }

                // Mover el barco 2 desde puerto 2 a puerto 3
                grafo1.mover(barco2, puerto3);

                // Comprobar si el barco 2 ha llegado a puerto 3
                if (barco2.getPuertoActual() == puerto3) {
                        System.out.println("El barco 2 ha llegado a " + puerto3.getNombre());
                }

                // Impactar la posición 1 del barco 3
                if (barco3.isHit(1)) {
                        System.out.println("Se ha impactado la posición 1 del barco 3");
                }

                // Comprobar si el barco 3 está hundido
                if (barco3.isSunk()) {
                        System.out.println("El barco 3 ha sido hundido");
                }


        }
}




