package com.batallaNaval.controlador;

import com.batallaNaval.modelo.Jugador;
import com.batallaNaval.modelo.Tablero;

import java.util.Scanner;

/**
 * Controla el flujo principal del juego.
 */
public class Juego {
    private Jugador jugador1;
    private Jugador jugador2;
    private Scanner sc;

    public Juego() {
        sc = new Scanner(System.in);
        jugador1 = new Jugador("Jugador 1");
        jugador2 = new Jugador("Jugador 2");
    }

    public void iniciar() {
        System.out.println("=== Batalla Naval ===\n");

        // Fase de posicionamiento
        jugador1.posicionarFlota();
        jugador2.posicionarFlota();

        boolean juegoTerminado = false;
        Jugador turnoActual = jugador1;
        Jugador oponente = jugador2;

        while (!juegoTerminado) {
            System.out.println("\nTurno de " + turnoActual.getNombre());
            turnoActual.mostrarTableroAtaque();

            int fila, columna;
            while (true) {
                System.out.print("Ingrese fila para disparar (0-9): ");
                fila = sc.nextInt();
                System.out.print("Ingrese columna para disparar (0-9): ");
                columna = sc.nextInt();

                if (fila < 0 || fila > 9 || columna < 0 || columna > 9) {
                    System.out.println("Coordenadas inválidas, intenta de nuevo.");
                    continue;
                }
                break;
            }

            String resultado = oponente.getTablero().recibirDisparo(fila, columna);
            System.out.println("Resultado: " + resultado);

            // Actualizar tablero de ataque del jugador actual
            char[][] tableroAtaque = turnoActual.getTablero().getTableroAtaque();
            if (resultado.equals("Agua")) {
                tableroAtaque[fila][columna] = 'A';
            } else if (resultado.equals("Tocado") || resultado.equals("Hundido")) {
                tableroAtaque[fila][columna] = 'T';
            }

            if (oponente.getTablero().flotaHundida()) {
                System.out.println("\n¡Felicidades " + turnoActual.getNombre() + "! Has hundido toda la flota enemiga y ganado el juego.");
                juegoTerminado = true;
            } else {
                // Cambiar turno
                Jugador temp = turnoActual;
                turnoActual = oponente;
                oponente = temp;
            }
        }
    }

    public static void main(String[] args) {
        new Juego().iniciar();
    }
}
