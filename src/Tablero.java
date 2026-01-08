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

    public boolean jaque(Posicion p, boolean blanca){
        boolean jaque=false;
        int cont=0;
        Pieza amenazas[]=new Pieza[15];

            for (int i = 0; i < 7; i++) {
                for (int j = 0; j < 7; j++) {
                    if (this.tablero[i][j].getBlancas()!=blanca){
                        if(this.tablero[i][j].compMov(this,p)){
                            jaque= true;
                            amenazas[cont]=this.tablero[i][j];
                            cont++;
                        }
                    }
                }
            }

        return jaque;
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



    //Comprueba la posicion inicial
    /**
     * Coloca piezas en el tablero a partir de una cadena en notación algebraica.
     * Se usa tanto para blancas como para negras.
     *
     * Ejemplo:
     * "Rg1, Tf1, h2, Ce5, Ta1"
     */
    public void colocarPiezasDesdeNotacion(String entrada) {


        entrada = entrada.replace(" ", "");


        String[] piezas = entrada.split(",");


        for (String p : piezas) {

            char tipo;
            char columnaChar;
            char filaChar;

            // Si empieza por mayúscula, es una pieza distinta de peón
            if (Character.isUpperCase(p.charAt(0))) {
                tipo = p.charAt(0);         // Tipo de pieza (R, T, C, A)
                columnaChar = p.charAt(1);  // Columna (a-h)
                filaChar = p.charAt(2);     // Fila (1-8)
            } else {
                tipo = 'P';                 // Si no hay letra, es un peón
                columnaChar = p.charAt(0);
                filaChar = p.charAt(1);
            }

            // Convierte la columna de 'a'-'h' a 0-7
            int columna = columnaChar - 'a';

            // Convierte la fila de '1'-'8' a 0-7 (orientación estándar)
            int fila = 8 - (filaChar - '0');

            // Comprueba que la posición esté dentro del tablero
            if (fila < 0 || fila > 7 || columna < 0 || columna > 7) {
                throw new IllegalArgumentException("Posición inválida: " + p);
            }

            // Comprueba que la casilla esté libre
            if (tablero[fila][columna] != null) {
                throw new IllegalArgumentException("Casilla ocupada: " + p);
            }

            // Crea la posición
            Posicion pos = new Posicion(fila, columna);

            // Crea la pieza correspondiente
            Pieza pieza;
            switch (tipo) {
                case 'R':
                    pieza = new Rey(pos);
                    break;
                case 'T':
                    pieza = new Torre(pos);
                    break;
                case 'C':
                    pieza = new Caballo(pos);
                    break;
                case 'A':
                    pieza = new Alfil(pos);
                    break;
                case 'P':
                    pieza = new Peon(pos);
                    break;
                default:
                    throw new IllegalArgumentException("Tipo de pieza desconocido: " + tipo);
            }

            // Coloca la pieza en el tablero
            tablero[fila][columna] = pieza;
        }
    }




    //movimiento libre

    /**
     * Comprueba si el camino entre dos posiciones está libre de piezas.
     */
    public boolean caminoDespejado(Posicion inicio, Posicion fin, int dirFila, int dirCol) {

        int filaActual = inicio.getFila() + dirFila;
        int colActual = inicio.getColumna() + dirCol;

        while (filaActual != fin.getFila() || colActual != fin.getColumna()) {
            if (tablero[filaActual][colActual] != null) {
                return false;
            }

            filaActual += dirFila;
            colActual += dirCol;
        }

        return true; // Devuelve true si no se encontró ninguna pieza bloqueando
    }



}