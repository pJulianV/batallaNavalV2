package com.batallaNaval.util;


import com.batallaNaval.modelo.Barco;

/**
 * Lista enlazada simple para gestionar la flota de barcos de un jugador.
 */
public class ListaEnlazadaBarcos {
    private Barco cabeza;

    public void agregarBarco(Barco barco) {
        if (cabeza == null) {
            cabeza = barco;
        } else {
            Barco actual = cabeza;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(barco);
        }
    }

    public Barco getCabeza() {
        return cabeza;
    }
}
