public class Tablero {
    Pieza tablero [][];


    Tablero (){
        this.tablero= new Pieza[8][8];
    }

    public void colocarPiezas (String posicion, boolean blancas){

        if (!posicion.equalsIgnoreCase("peon")) return;

        int fila = blancas ? 6 : 1; // fila 6 para blancos, 1 para negros

        for (int col = 0; col < 8; col++) {
            Posicion p = new Posicion(fila, col);
            setPieza(new Peon(p, blancas), p);
        }
    }

    /**
     Esta función determina si el camino está despejado desde una posición inicial p1 hasta
     **/
    public boolean caminoLibre (Posicion p1, Posicion p2, int dir){
        boolean libre = true;

        return libre;
    }

    public Pieza getPosicion(Posicion p) {
        return tablero[p.getFila()][p.getColumna()];
    }

    public void setPieza(Pieza pieza, Posicion p) {
        this.tablero[p.getFila()][p.getColumna()] = pieza;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        // Recorremos todas las filas del tablero (0 a 7)
        for (int fila = 0; fila < 8; fila++) {

            // Recorremos todas las columnas de la fila actual
            for (int col = 0; col < 8; col++) {

                // Si no hay pieza en la posición, imprimimos un símbolo alternado para casillas
                if (tablero[fila][col] == null) {

                    if ((fila + col) % 2 == 0) {
                        sb.append("□ "); // Casilla clara
                    } else {
                        sb.append("■ "); // Casilla oscura
                    }
                } else {
                    // Si hay una pieza, llamamos a su toString() para mostrarla
                    sb.append(tablero[fila][col].toString() + " ");
                }
            }

            // Después de cada fila, agregamos un salto de línea para pasar a la siguiente
            sb.append("\n");
        }
        return sb.toString();
    }



}