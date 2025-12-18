public class Caballo extends Pieza {

    public Caballo(Posicion p, boolean blancas) {
        super(p, blancas);
    }

    @Override
    public boolean mover(Tablero t, Posicion destino) {
        int filaDiff = Math.abs(destino.getFila() - p.getFila());
        int colDiff = Math.abs(destino.getColumna() - p.getColumna());

        // Movimiento en L
        boolean movimientoValido = (filaDiff == 2 && colDiff == 1) || (filaDiff == 1 && colDiff == 2);

        if (!movimientoValido) return false;

        // Comprobar si la casilla destino está ocupada por pieza del mismo color
        Pieza destinoPieza = t.getPosicion(destino);
        if (destinoPieza != null && destinoPieza.getBlancas() == this.blancas) {
            return false; // no puede matar pieza del mismo color
        }

        // Movimiento válido
        t.setPieza(this, destino);
        t.setPieza(null, this.p); // vaciar la posición anterior
        this.p = destino;
        return true;
    }

    @Override
    public String toString() {
        return blancas ? "♘" : "♞";
    }
}
