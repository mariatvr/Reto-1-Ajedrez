public class Tablero {
    Pieza[][] tablero;


    Tablero (){
        this.tablero= new Pieza[8][8];
    }

    //Comprueba la posicion inicial
    /**
     * Coloca piezas en el tablero a partir de una cadena en notación algebraica.
     * Se usa tanto para blancas como para negras.
     * Ejemplo:
     * "Rg1, Tf1, h2, Ce5, Ta1"
     */
    public void colocarPiezasDesdeNotacion(String entrada, boolean blancas) {


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
            Pieza pieza = switch (tipo) {
                case 'R' -> new Rey(pos, blancas);
                case 'T' -> new Torre(pos, blancas);
                case 'C' -> new Caballo(pos, blancas);
                case 'A' -> new Alfil(pos, blancas);
                case 'D' -> new Reina(pos, blancas);
                case 'P' -> new Peon(pos, blancas);
                default -> throw new IllegalArgumentException("Tipo de pieza desconocido: " + tipo);
            };

            // Coloca la pieza en el tablero
            tablero[fila][columna] = pieza;
        }
    }

    /**
     * Comprueba si el camino entre dos posiciones está libre de piezas.
     */
    public boolean caminoLibre(Posicion inicio, Posicion fin, int dirFila, int dirCol) {

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

    public Pieza[][] getTablero() {
        return this.tablero;
    }

    public Pieza getPosicion(Posicion p) {
        return tablero[p.getFila()][p.getColumna()];
    }

    public void setPieza(Pieza pieza, Posicion p) {
        this.tablero[p.getFila()][p.getColumna()] = pieza;
    }

    public boolean jaque(Posicion p, boolean blanca){

        boolean jaque=false;
        int cont=0;//no hace nada-------------------------------------------------------------------------------
        //Pieza amenazas[]=new Pieza[15]; Para futuras implementaciones
        Posicion posAtacante;

            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    posAtacante=new Posicion(i,j);
                    if(this.getPosicion(posAtacante)!=null) {
                        if (this.getPosicion(posAtacante).getBlancas() != blanca) {
                            if (this.getPosicion(posAtacante).compMov(this, p)) {
                                jaque = true;
                                break;
                                //amenazas[cont] = this.getPosicion(posAtacante);
                                //cont++;
                            }
                        }
                    }
                }
            }

        return jaque;
    }

    public boolean mover(Posicion pinicial, Posicion pfinal){
        boolean movimiento = false;

        Pieza pieza = getPosicion(pinicial);

        if (pieza != null && pieza.compMov(this, pfinal)) {

            // Mover la pieza
            setPieza(pieza, pfinal);
            setPieza(null, pinicial);
            pieza.setPosicion(pfinal); // importante actualizar la posición


            if (pieza instanceof Peon peon && peon.estaEnFilaFinal()) {
                Pieza nueva = peon.promocionar();
                setPieza(nueva, pfinal);
            }

            movimiento = true;
        }

        return movimiento;
    }


    // COLORINESSSSSS
    private static final String RESET = "\u001B[0m";

    // Casillas
    private static final String BG_BLANCA = "\u001B[48;5;230m"; // casillas blancas
    private static final String BG_MARRON = "\u001B[48;5;94m"; // casillas marrones

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(); // "StringBuilder" para construir el tablero línea por línea

        // Recorremos las filas del tablero
        for (int fila = 0; fila < 8; fila++) {
            int rank = 8 - fila;
            sb.append(" ").append(rank).append(" "); // ← guía izquierda

            for (int col = 0; col < 8; col++) {
                boolean clara = (fila + col) % 2 == 0; // Si fila y columna son pares → clara, si es impar → marrón
                String bg = clara ? BG_BLANCA : BG_MARRON;
                sb.append(bg);

                // Comprobamos si hay una pieza ya en la casilla para saber que deberia de haber ahi
                if (tablero[fila][col] == null) {
                    sb.append("     "); //Si no hay pieza, ponemos un espacio vacío de 3 caracteres
                } else {
                    Pieza p = tablero[fila][col];
                    sb.append(" ").append(p).append(" "); //Si hay pieza, ponemos su simbolo de ficha centrado
                }
                sb.append(RESET);
            }
            sb.append("\n");
        }

        // Guía inferior (alineada con las casillas)
        sb.append("   "); // mismo margen que los números de la izquierda
        for (char c = 'a'; c <= 'h'; c++) {
            sb.append("  ").append(c).append("  "); // ancho = 4
        }

        sb.append("\n");

        // Devolvemos tablero como un String
        return sb.toString();
    }
}

