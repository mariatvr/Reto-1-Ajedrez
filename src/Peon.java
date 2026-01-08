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
        super(p, blancas);
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
    public boolean compMov(Tablero t, Posicion p) {
        boolean valido=false;
        int dir;
        int destino = t.getPosicion(p);

        if(p.dentroTablero()){
            if (this.blancas) {
                // Comprobación de avance una fila hacia delante
                if (p.comprobarFila(this.p.getFila() + 1)) {

                    // Movimiento recto sin captura
                    if (p.comprobarColumna(this.p.getColumna()) && t.caminoLibre(this.p, p, 0)) {

                        //Movimiento permitido
                        valido = true;
                    }

                    // Captura en diagonal
                    else if (p.comprobarColumna(this.p.getColumna() + 1) || p.comprobarColumna(this.p.getColumna() - 1)) {

                        //Hay una pieza en la casilla a la que se mueve
                        if (destino != null)

                            //La ficha es del otro bando
                            if (destino.getBlancas() != this.blancas) {

                                //Movimiento permitido
                                valido = true;
                            }
                    }
                }
            }
            esle {
                // Comprobación de avance una fila hacia delante
                if (p.comprobarFila(this.p.getFila() - 1)) {

                    // Movimiento recto sin captura
                    if (p.comprobarColumna(this.p.getColumna()) && t.caminoLibre(this.p, p, 0)) {

                        //Movimiento permitido
                        valido = true;
                    }

                    // Captura en diagonal
                    else if (p.comprobarColumna(this.p.getColumna() + 1) || p.comprobarColumna(this.p.getColumna() - 1)) {

                        //Hay una pieza en la casilla a la que se mueve
                        if (t.getPosicion(p) != null)

                            //La ficha es del otro bando
                            if (t.getPosicion(p).getBlancas() != this.blancas) {

                                //Movimiento permitido
                                valido = true;
                            }
                    }
                }
            }
        }
        return valido;
    }

    @Override
    public String toString() {
        return blancas ? "♙" : "♟";
    }
}
