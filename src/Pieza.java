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

    /** Indica que tipo de pieza es **/
    protected char tipo;
    /**
     * Constructor de la clase Pieza.
     *
     * @param p posición inicial de la pieza
     * @param blancas true si la pieza es blanca, false si es negra
     */
    public Pieza(Posicion p, boolean blancas, char tipo) {
        this.p = p;
        this.blancas = blancas;
        this.tipo=tipo;
    }

    /**
     * Indica el color de la pieza.
     *
     * @return true si la pieza es blanca, false si es negra
     */
    public boolean getBlancas(){
        return this.blancas;
    }

    public char getTipo(){return this.tipo;}

    public Posicion getPosicion(){return this.p;}

    /**
     * Comprueba el movimiento de la pieza según las reglas básicas:
     *
     * @param t tablero donde se efectúa el movimiento
     * @param p posición destino
     * @return true si el movimiento es válido,
     * false en caso contrario
     */
    public abstract boolean compMov(Tablero t, Posicion p);

    /**
     * Intenta mover la pieza a una nueva posición en el tablero.
     *
     * @param t tablero sobre el que se realiza el movimiento
     * @param p posición destino
     * @return si el movimiento es valido lo realiza.
     */

}
