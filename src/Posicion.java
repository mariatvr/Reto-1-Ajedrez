/**
 * Clase que representa una posición en una estructura bidimensional
 * mediante una fila y una columna.
 *
 * Se utiliza para comparar, actualizar y verificar coordenadas.
 */
public class Posicion {

    /** Fila de la posición */
    private int fila;

    /** Columna de la posición */
    private int columna;

    /**
     * Constructor de la clase Posicion.
     *
     * @param f fila inicial
     * @param c columna inicial
     */
    public Posicion(int f, int c) {
        this.fila = f;
        this.columna = c;
    }

    /**
     * Devuelve la columna de la posición.
     *
     * @return columna actual
     */
    public int getColumna() {
        return columna;
    }

    /**
     * Devuelve la fila de la posición.
     *
     * @return fila actual
     */
    public int getFila() {
        return fila;
    }

    /**
     * Modifica la posición actual asignando una nueva fila y columna.
     *
     * @param f nueva fila
     * @param c nueva columna
     */
    public void setPosicion(int f, int c) {
        this.fila = f;
        this.columna = c;
    }

    /**
     * Comprueba si la fila actual coincide con la indicada.
     *
     * @param f fila a comprobar
     * @return true si coinciden, false en caso contrario
     */
    public boolean comprobarFila(int f) {
        return this.fila == f;
    }

    /**
     * Comprueba si la columna actual coincide con la indicada.
     *
     * @param c columna a comprobar
     * @return true si coinciden, false en caso contrario
     */
    public boolean comprobarColumna(int c) {
        return this.columna == c;
    }

    /**
     * Comprueba si la posición actual coincide exactamente
     * con la fila y columna indicadas.
     *
     * @param f fila a comprobar
     * @param c columna a comprobar
     * @return true si ambas coinciden, false en caso contrario
     */
    public boolean comprobar(int f, int c) {
        return this.fila == f && this.columna == c;
    }
}

