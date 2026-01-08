/**
 * Representa la pieza Rey del juego de ajedrez.
 * El Rey puede avanzar una casilla hacia cualquier dirección siempre que no haya
 * una pieza aliada o no se coloque en jaque.
 */

public class Rey extends Pieza{

    /**
     * Variable que determina si el Rey se encuentra en disposición de realizar un enroque.
     */
    private boolean enroque;

    /**
     * Constructor de la clase Peon.
     *
     * @param p posición inicial del Rey
     * @param blancas true si el Rey es blanco, false si es negro
     */
    public Rey(Posicion p, boolean blancas, boolean enroque) {
        super(p, blancas);
        this.enroque=enroque;
    }

    /**
     * Comprueba el movimiento del Rey según las reglas básicas:
     *   ·Se puede mover una casilla en cualquier dirección si está vacia o ocupada por una ficha enemiga
     *   siempre y cuando no se ponga en jaque.     *
     * @param t tablero donde se efectúa el movimiento
     * @param p posición destino
     * @return true si el movimiento es válido,
     * false en caso contrario
     */
    @Override
    public boolean compMov(Tablero t, Posicion p) {
        boolean valido=false;
        int destino = t.getPosicion(p);

        if(p.dentroTablero()){
            if(destino!=null){
                if(destino.getBlancas()!=this.getBlancas()){
                    if(!t.jaque(p,this.blancas)){
                       valido=true;
                    }
                }
            }
            else{
                if(!t.jaque(p,this.blancas)){
                    valido=true;
                }
            }
        }

        return valido;
    }

    @Override
    public String toString() {
        return blancas ? "♔" : "♚";
    }
}
