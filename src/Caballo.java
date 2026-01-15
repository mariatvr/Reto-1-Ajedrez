public class Caballo extends Pieza {

        public Caballo(Posicion p, boolean blancas) {
            super(p, blancas,'C');
        }

        @Override
        public boolean compMov(Tablero t, Posicion destino) {
            int filaDiff = Math.abs(destino.getFila() - p.getFila());
            int colDiff = Math.abs(destino.getColumna() - p.getColumna());

            // Movimiento en L
            boolean esMovimientoValido = (filaDiff == 2 && colDiff == 1) || (filaDiff == 1 && colDiff == 2);

            // Comprobar si la casilla destino está ocupada por pieza del mismo color
            Pieza destinoPieza = t.getPosicion(destino);
            if (destinoPieza != null && destinoPieza.getBlancas() == this.blancas) {
                esMovimientoValido=false; // no puede capturar pieza propia
            }

            // Movimiento válido
            return esMovimientoValido;
        }

        @Override
        public String toString() {
            return blancas ? "♘" : "♞";
        }
    }


