public class Alfil extends Pieza {

    public Alfil(Posicion p, boolean blancas) {
        super(p, blancas,'A');
    }
    @Override
    public boolean compMov(Tablero t, Posicion p) {
        boolean valido = false;

        int df = p.getFila() - this.p.getFila();
        int dc = p.getColumna() - this.p.getColumna();

        int dirFila = Integer.compare(df, 0);   // -1, 0 o +1
        int dirCol  = Integer.compare(dc, 0);

        if (Math.abs(df)==Math.abs(dc)){
            if (t.caminoLibre(this.p, p, dirFila,dirCol)) {
            // Evita que el alfil se quede en la misma casilla
                valido=true;
            }
        }

        return valido;
    }

    @Override
    public String toString() {
        return blancas ? "♗" : "♝";
    }
}

