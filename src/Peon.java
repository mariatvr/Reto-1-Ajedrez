
public class Peon extends Pieza{


    public Peon(Posicion p, boolean blancas) {
        super(p, blancas);
    }


    @Override
    public boolean compMov(Tablero t, Posicion p) {
        boolean valido=false;
        int dir;
        if (this.blancas){

        }
        // Comprobación de avance una fila hacia delante
        if(p.comprobarFila(this.p.getFila()+1)){

            // Movimiento recto sin captura
            if(p.comprobarColumna(this.p.getColumna())&& t.caminoLibre(this.p,p,0)) {

                //Movimiento permitido
                valido = true;
            }

            // Captura en diagonal
            else if(p.comprobarColumna(this.p.getColumna()+1)||p.comprobarColumna(this.p.getColumna()-1)){

                //Hay una pieza en la casilla a la que se mueve
                if(t.getPosicion(p)!=null)

                    //La ficha es del otro bando
                    if(t.getPosicion(p).getBlancas()!=this.blancas) {

                        //Movimiento permitido
                        valido = true;
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
