/*
La pieza Reina del ajedrez.
 */
public class Reina extends Pieza{

    public Reina(Posicion p, boolean blancas) {
        super(p, blancas);
    }

    /*
    La función de movimiento y comprobación de la pieza Reina.
     */
public boolean compMov(Tablero t, Posicion p) {
    boolean valido = false;

        /*
        Si se puede mover en horizontal.
         */

    if (p.comprobarFila(this.p.getFila()) && t.caminoLibre(this.p, p, 0)) {
        valido = true;

            /*
            Si se puede mover en vertical.
             */

        if (p.comprobarColumna(this.p.getColumna()) && t.caminoLibre(this.p, p, 0)) {
            valido = true;

                /*
            Si se puede mover en diagonales.
                */

            if (comprobarDiagonal(p) && t.caminoLibre(this.p, p, 0)) {
                valido = true
            }
        }
    }

    /*
    Si la comprobación del movimiento es válido, se comprueba que se puede mover.
     */
    if (valido) {
        /*
        Si no hay pieza en la posición de destino o la pieza es de otro color, entonces la Reina puede moverse
        */
        if (t.getPosicion(p) == null || p.getBlancas() != this.blancas) {
            t.setPieza(this.pieza, p);
        }
    }

    }

    return valido;
}

    /*
    Para comprobar la diferencia entre la posición inicial y una final,
    si la posición final coinciden los 2 números, se mueve en diagonal.
     */

private boolean comprobarDiagonal(Posicion p) {
    boolean valido = false;

    int difx = Math.abs(p.getFila - this.p.getFila);
    int dify = Math.abs(p.getColumna - this.p.getColumna);

    if (difx == dify) {
        valido = true;
    }
}

}