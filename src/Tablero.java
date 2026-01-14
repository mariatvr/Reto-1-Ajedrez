public class Tablero {

   // COLORINESSSSSS
    private static final String RESET = "\u001B[0m";

    // Fondo general verde
    private static final String BG_VERDE = "\u001B[48;5;22m";
    // Casillas
    private static final String BG_BLANCA = "\u001B[48;5;230m"; // casillas blancas
    private static final String BG_MARRON = "\u001B[48;5;94m"; // casillas marrones

    // Piezas
    private static final String FG_BLANCA = "\u001B[38;5;231m"; // piezas blancas
    private static final String FG_NEGRA  = "\u001B[38;5;16m"; // piezas marrones


    private Pieza[][] tablero;

    public Tablero() {
        this.tablero = new Pieza[8][8];
    }

    public Pieza getPosicion(Posicion p) {
        return tablero[p.getFila()][p.getColumna()];
    }

    public void setPieza(Pieza pieza, Posicion p) {
        tablero[p.getFila()][p.getColumna()] = pieza;
    }

    public boolean jaque(Posicion p, boolean blanca) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                Pieza pieza = tablero[i][j];

                if (pieza != null && pieza.getBlancas() != blanca) {
                    if (pieza.compMov(this, p)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

 // DISEÑO DEL TABLERO

 @Override
 public String toString() {
     StringBuilder sb = new StringBuilder(); // "StringBuilder" para construir el tablero línea por línea

     // Recorremos las filas del tablero
     for (int fila = 0; fila < 8; fila++) {

         for (int col = 0; col < 8; col++) {

         }
             boolean clara = (fila + col) % 2 == 0; // Si fila y columna son pares → clara, si es impar → marrón
             String bg = clara ? BG_BLANCA : BG_MARRON;
             sb.append(bg);

             // Comprobamos si hay una pieza ya en la casilla para saber que deberia de haber ahi
             if (tablero[fila][col] == null) {
                 sb.append("   "); Si no hay pieza, ponemos un espacio vacío de 3 caracteres
             } else {
                 Pieza p = tablero[fila][col];
                 sb.append(" ").append(p).append(" "); Si hay pieza, ponemos su simbolo de ficha centrado
             }
             sb.append(RESET);
         }
         sb.append("\n");
     }

     // Devolvemos tablero como un String
     return sb.toString();
 }
