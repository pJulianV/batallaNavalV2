package com.batallaNaval.modelo;

/**
 * Representa un barco con tamaño y estado de impactos en cada parte.
 * Cada parte del barco está enlazada a la siguiente para formar la lista enlazada.
 */
public class Barco {
    private int tamaño;
    private boolean[] impactos; // true si la parte fue tocada
    private Barco siguiente; // referencia al siguiente nodo (parte del barco o siguiente barco)

    public Barco(int tamaño) {
        this.tamaño = tamaño;
        this.impactos = new boolean[tamaño];
    }

    public int getTamaño() {
        return tamaño;
    }

    public Barco getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Barco siguiente) {
        this.siguiente = siguiente;
    }

    /**
     * Marca la parte del barco en la posición dada como impactada.
     */
    public void impactar(int parte) {
        if (parte >= 0 && parte < tamaño) {
            impactos[parte] = true;
        }
    }

    /**
     * Verifica si el barco está hundido (todas las partes impactadas).
     */
    public boolean estaHundido() {
        for (boolean parteImpactada : impactos) {
            if (!parteImpactada) {
                return false;
            }
        }
        return true;
    }
}
