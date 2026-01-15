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
        boolean mueve=false;

        // 1. Comprobar si el movimiento es horizontal o vertical
        // (Misma fila o misma columna, pero no ambas a la vez)
        boolean mismaFila = this.p.getFila() == p.getFila();
        boolean mismaColumna = this.p.getColumna() == p.getColumna();
        int fila = this.p.getFila()-p.getFila();
        int columna = this.p.getColumna()-p.getColumna();

        if(fila!=0){
            fila=Math.abs(fila)/fila;
        } else fila=0;
        if(columna!=0){
            columna=Math.abs(columna)/columna;
        }  else columna=0;

        if (mismaFila != mismaColumna) {

            // 2. Verificar que no haya piezas en el camino
            if (t.caminoLibre(this.p, p, fila,columna)) {

                Pieza destino = t.getPosicion(p);

                // 3. Si el destino está vacío o hay un enemigo, el movimiento es válido
                if (destino == null || destino.getBlancas() != this.blancas) {
                    valido = true;
                     mueve=true;

                    // IMPORTANTE: Primero limpiamos la posición de ORIGEN en el tablero
                    t.setPieza(null, this.p);

                    // Actualizamos la posición de la pieza
                    this.p = p;

                    // Colocamos la pieza en el DESTINO
                    t.setPieza(this, p);
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
