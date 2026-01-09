public class Alfil extends Pieza {

    public Alfil(Posicion posicion) {
        super(posicion);
    }


    @Override
    public boolean movimientoValido(Posicion destino) {

        int filaOrigen = posicion.getFila();      // Fila actual del alfil
        int colOrigen = posicion.getColumna();    // Columna actual del alfil
        int filaDestino = destino.getFila();      // Fila destino
        int colDestino = destino.getColumna();    // Columna destino

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
}
