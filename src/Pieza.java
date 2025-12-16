/**
 * Clase abstracta que representa una pieza genérica del tablero.
 *
 * Cada pieza tiene una posición y un color (blancas o negras) y
 * define un comportamiento común para el movimiento, que será
 * implementado por las piezas concretas.
 */


public abstract class Pieza {

    /** Posición actual de la pieza en el tablero */
    protected Posicion p;

    /** Indica si la pieza pertenece al bando blanco (true) o negro (false) */
    protected boolean blancas;

    /**
     * Constructor de la clase Pieza.
     *
     * @param p posición inicial de la pieza
     * @param blancas true si la pieza es blanca, false si es negra
     */
    public Pieza(Posicion p, boolean blancas) {
        this.p = p;
        this.blancas = blancas;
    }

    /**
     * Indica el color de la pieza.
     *
     * @return true si la pieza es blanca, false si es negra
     */
    public boolean getBlancas(){
        return this.blancas;
    }

    /**
     * Intenta mover la pieza a una nueva posición en el tablero.
     *
     * @param t tablero sobre el que se realiza el movimiento
     * @param p posición destino
     * @return true si el movimiento es válido y se realiza,
     *         false en caso contrario
     */
    public abstract boolean mover(Tablero t, Posicion p);
}
