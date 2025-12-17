public class Tablero {
    Pieza tablero [][];


    Tablero (){
        this.tablero= new Pieza[8][8];
    }

    public void colocarPiezas (String posicion, boolean blancas){

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
        // StringBuilder se usa para construir el String
        StringBuilder sb = new StringBuilder();

        // Recorremos todas las filas del tablero (0 a 7)
        for (int fila = 0; fila < 8; fila++) {

            // Recorremos todas las columnas de la fila actual
            for (int col = 0; col < 8; col++) {

                // Si no hay pieza en la posición, imprimimos un punto para indicar casilla vacía
                if (tablero[fila][col] == null) {
                    sb.append(". "); // ". " representa una casilla vacía
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