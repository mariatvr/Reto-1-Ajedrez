/**
 * Representa la pieza Torre del juego de ajedrez.
 * Se mueve en línea recta (filas o columnas) tantas casillas como quiera,
 * siempre que el camino esté despejado.
 */
public class Torre extends Pieza {

    public Torre(Posicion p, boolean blancas) {
        super(p, blancas,'T');
    }

    @Override
    public boolean compMov(Tablero t, Posicion p) {
        boolean valido = false;

        // 1. Comprobar si el movimiento es horizontal o vertical
        // (Misma fila o misma columna, pero no ambas a la vez)
        boolean mismaFila = this.p.getFila() == p.getFila();
        boolean mismaColumna = this.p.getColumna() == p.getColumna();

        int df = p.getFila() - this.p.getFila();
        int dc = p.getColumna() - this.p.getColumna();

        int dirFila = Integer.compare(df, 0);   // -1, 0 o +1
        int dirCol  = Integer.compare(dc, 0);

        if (mismaFila != mismaColumna) {

            if (t.caminoLibre(this.p, p, dirFila,dirCol)) {

                Pieza destino = t.getPosicion(p);

                // 3. Si el destino está vacío o hay un enemigo, el movimiento es válido
                if (destino == null || destino.getBlancas() != this.blancas) {
                    valido = true;
                }
            }
        }

        return valido;
    }

    @Override
    public String toString() {
        return blancas ? "♖" : "♜";
    }

}
