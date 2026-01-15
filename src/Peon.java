/**
 * Representa la pieza Peón del juego de ajedrez.
 * El peón puede avanzar una casilla hacia delante si está libre
 * o capturar en diagonal una pieza del bando contrario.
 */
public class Peon extends Pieza{

    /**
     * Constructor de la clase Peon.
     *
     * @param p posición inicial del peón
     * @param blancas true si el peón es blanco, false si es negro
     */
    public Peon(Posicion p, boolean blancas) {
        super(p, blancas, 'p');
    }

    /**
     * Comprueba el movimiento del peón según las reglas básicas:
     *   ·Avance de una o dos casillas (en caso de no haber movido aun)
     *   hacia delante si está libre.
     *   ·Captura en diagonal de una pieza enemiga.
     *
     * @param t tablero donde se efectúa el movimiento
     * @param p posición destino
     * @return true si el movimiento es válido,
     *      * false en caso contrario
     */
    @Override
    public boolean compMov(Tablero t, Posicion destinoPos) {
        boolean valido=false;

        if (destinoPos.dentroTablero()) {

        Pieza destino = t.getPosicion(destinoPos);

        int df = destinoPos.getFila() - this.p.getFila();
        int dc = destinoPos.getColumna() - this.p.getColumna();
        int filaInicial = this.blancas ? 6 : 1;

        // Ajusta este dir según tu tablero:
        // Si a1 = fila 7 y a8 = fila 0, blancas avanzan con -1
        int dir = this.blancas ? -1 : 1;

        // Recto
        if (dc == 0 && df == dir && destino == null) valido=true;

        if (dc == 0 && this.p.getFila() == filaInicial && df == 2*dir && destino == null) {
            Posicion intermedia = new Posicion(this.p.getFila() + dir, this.p.getColumna());
            if (t.getPosicion(intermedia) == null) valido=true;
        }

        // Captura diagonal
        if (Math.abs(dc) == 1 && df == dir && destino != null && destino.getBlancas() != this.blancas) valido=true;
        }

        return valido;
    }

    @Override
    public String toString() {
        return blancas ? "♙" : "♟";
    }
}
