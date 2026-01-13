public class Alfil extends Pieza {

    public Alfil(Posicion p, boolean blancas) {
        super(p, blancas);
    }
    @Override
    public boolean compMov(Tablero t, Posicion p) {

        int filaOrigen = this.p.getFila();      // Fila actual del alfil
        int colOrigen = this.p.getColumna();    // Columna actual del alfil
        int filaDestino = p.getFila();      // Fila destino
        int colDestino = p.getColumna();    // Columna destino
        int difFila = filaDestino - filaOrigen;   // Diferencia de filas
        int difCol = colDestino - colOrigen;      // Diferencia de columnas

        // Evita que el alfil se quede en la misma casilla
        if (difFila == 0 && difCol == 0) {
            return false;
        }

        // El alfil solo se mueve en diagonal
        return Math.abs(difFila) == Math.abs(difCol);
    }

    @Override
    public String toString() {
        return blancas ? "♗" : "♝";
    }
}
