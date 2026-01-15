/*
La pieza Reina del ajedrez.
 */
public class Reina extends Pieza{

    public Reina(Posicion p, boolean blancas) {
        super(p, blancas,'D');
    }

    /*
    La función de movimiento y comprobación de la pieza Reina.
     */
public boolean compMov(Tablero t, Posicion p) {
    boolean valido = false;

    int df = p.getFila() - this.p.getFila();
    int dc = p.getColumna() - this.p.getColumna();

    int dirFila = Integer.compare(df, 0);   // -1, 0 o +1
    int dirCol  = Integer.compare(dc, 0);
        /*
        Si se puede mover en horizontal.
         */

    if (t.caminoLibre(this.p, p, dirFila,dirCol)) {
        if(dirFila == 0 || dirCol == 0 || Math.abs(df)==Math.abs(dc)) {
            if (t.getPosicion(p) == null || t.getPosicion(p).getBlancas() != this.blancas) {
                valido=true;
            }
        }
    }

    return valido;
}


@Override
public String toString() {
    return blancas ? "♕" : "♛";
}
}