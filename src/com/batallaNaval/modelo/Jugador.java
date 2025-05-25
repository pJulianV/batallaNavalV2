package com.batallaNaval.modelo;

import java.util.Scanner;

/**
 * Representa un jugador con su tablero y nombre.
 */
public class Jugador {
    private String nombre;
    private Tablero tablero;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.tablero = new Tablero();
    }

    public String getNombre() {
        return nombre;
    }

    public Tablero getTablero() {
        return tablero;
    }

    /**
     * Permite posicionar la flota completa (3 barcos) con interacción por consola.
     */
    public void posicionarFlota() {
        Scanner scanner = new Scanner(System.in);
        int[] tamaños = {4, 3, 2};
        String[] nombresBarcos = {"Acorazado (4)", "Crucero (3)", "Destructor (2)"};

        System.out.println(nombre + ", posiciona tus barcos:");

        for (int i = 0; i < tamaños.length; i++) {
            boolean colocado = false;
            while (!colocado) {
                System.out.printf("Posiciona tu %s\n", nombresBarcos[i]);
                System.out.print("Fila (0-9): ");
                int fila = scanner.nextInt();
                System.out.print("Columna (0-9): ");
                int columna = scanner.nextInt();
                System.out.print("Vertical? (true/false): ");
                boolean vertical = scanner.nextBoolean();

                Barco barco = new Barco(tamaños[i]);
                if (tablero.posicionarBarco(barco, fila, columna, vertical)) {
                    colocado = true;
                    System.out.println("Barco colocado correctamente.\n");
                    mostrarTableroPosicionamiento();
                } else {
                    System.out.println("Posición inválida, intenta de nuevo.\n");
                }
            }
        }
     
    }

    /**
     * Muestra el tablero de posicionamiento.
     */
    public void mostrarTableroPosicionamiento() {
        char[][] t = tablero.getTableroPosicionamiento();
        System.out.println("  0 1 2 3 4 5 6 7 8 9");
        for (int i = 0; i < 10; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < 10; j++) {
                System.out.print(t[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Muestra el tablero de ataque.
     */
    public void mostrarTableroAtaque() {
        char[][] t = tablero.getTableroAtaque();
        System.out.println("  0 1 2 3 4 5 6 7 8 9");
        for (int i = 0; i < 10; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < 10; j++) {
                System.out.print(t[i][j] + " ");
            }
            System.out.println();
        }
    }
}

