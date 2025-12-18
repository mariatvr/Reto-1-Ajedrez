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
     * Comprueba y realiza el movimiento del peón según las reglas básicas:
     *   ·Avance de una casilla hacia delante si está libre.</li>
     *   ·Captura en diagonal de una pieza enemiga.</li>
     *
     * @param t tablero donde se efectúa el movimiento
     * @param p posición destino
     * @return true si el movimiento es válido y se realiza,
     * false en caso contrario
     */
    public boolean mover(Tablero t, Posicion p) {
        boolean valido=false;

        // Comprobación de avance una fila hacia delante
        if(p.comprobarFila(this.p.getFila()+1)){

            // Movimiento recto sin captura
            if(p.comprobarColumna(this.p.getColumna())&& t.caminoLibre(this.p,p,0)) {

                //Mueve a la posición
                valido = true;
                this.p=p; //Actualiza posición
                t.setPieza(null,this.p); //Elimina la ficha de su posición inicial
                t.setPieza(this, p); //Coloca la ficha en el tablero
            }

            // Captura en diagonal
            else if(p.comprobarColumna(this.p.getColumna()+1)||p.comprobarColumna(this.p.getColumna()-1)){

                //Hay una pieza en la casilla a la que se mueve
                if(t.getPosicion(p)!=null)

                    //La ficha es del otro bando
                    if(t.getPosicion(p).getBlancas()!=this.blancas) {

                        //Mueve a la posición
                        valido = true;
                        this.p = p;
                        t.setPieza(null,this.p); //Elimina la ficha de su posición inicial
                        t.setPieza(this, p); //Coloca la ficha en el tablero
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
