package com.batallaNaval.modelo;

import com.batallaNaval.util.ListaEnlazadaBarcos;
import java.util.Arrays;

/**
 * Representa el tablero de 10x10 para un jugador.
 * Contiene dos matrices: posicionamiento y ataque.
 */
public class Tablero {
    private char[][] tableroPosicionamiento;
    private char[][] tableroAtaque;
    private ListaEnlazadaBarcos flota;

    public Tablero() {
        tableroPosicionamiento = new char[10][10];
        tableroAtaque = new char[10][10];
        flota = new ListaEnlazadaBarcos();
        inicializarTableros();
    }

    private void inicializarTableros() {
        for (int i = 0; i < 10; i++) {
            Arrays.fill(tableroPosicionamiento[i], '~');
            Arrays.fill(tableroAtaque[i], '~');
        }
    }

    public char[][] getTableroPosicionamiento() {
        return tableroPosicionamiento;
    }

    public char[][] getTableroAtaque() {
        return tableroAtaque;
    }

    public ListaEnlazadaBarcos getFlota() {
        return flota;
    }

    /**
     * Intenta posicionar un barco en el tablero.
     * @param barco Barco a posicionar
     * @param fila fila inicial (0-9)
     * @param columna columna inicial (0-9)
     * @param vertical true si el barco se coloca vertical, false horizontal
     * @return true si se pudo posicionar, false si hay error (fuera de rango o superposición)
     */
    public boolean posicionarBarco(Barco barco, int fila, int columna, boolean vertical) {
        int tamaño = barco.getTamaño();

        // Validar límites
        if (vertical) {
            if (fila + tamaño > 10) return false;
        } else {
            if (columna + tamaño > 10) return false;
        }

        // Validar superposición
        for (int i = 0; i < tamaño; i++) {
            int f = vertical ? fila + i : fila;
            int c = vertical ? columna : columna + i;
            if (tableroPosicionamiento[f][c] != '~') {
                return false; // ya hay un barco
            }
        }

        // Posicionar barco
        for (int i = 0; i < tamaño; i++) {
            int f = vertical ? fila + i : fila;
            int c = vertical ? columna : columna + i;
            tableroPosicionamiento[f][c] = 'B'; // 'B' indica barco
        }

        // Agregar barco a la flota
        flota.agregarBarco(barco);
        return true;
    }

    /**
     * Procesa un disparo en las coordenadas dadas.
     * @param fila fila disparo
     * @param columna columna disparo
     * @return resultado: "Agua", "Tocado", o "Hundido"
     */
    public String recibirDisparo(int fila, int columna) {
        char celda = tableroPosicionamiento[fila][columna];
        if (celda == '~') {
            tableroPosicionamiento[fila][columna] = 'A'; // Agua
            return "Agua";
        } else if (celda == 'B') {
            tableroPosicionamiento[fila][columna] = 'T'; // Tocado
            // Aquí se debería actualizar el estado del barco (simplificado)
            // Para esta fase, retornamos "Tocado"
            return "Tocado";
        } else if (celda == 'T' || celda == 'A') {
            return "Ya disparado aquí";
        }
        return "Error";
    }

    /**
     * Verifica si toda la flota está hundida.
     * @return true si todos los barcos están hundidos
     */
    public boolean flotaHundida() {
        // Para esta fase, simplificamos: si no hay 'B' en tableroPosicionamiento, está hundida
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (tableroPosicionamiento[i][j] == 'B') {
                    return false;
                }
            }
        }
        return true;
    }
}
