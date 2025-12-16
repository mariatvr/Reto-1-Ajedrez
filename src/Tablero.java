public class Tablero {
    // Matriz que representa el tablero de ajedrez de 8x8
    // Cada celda puede contener una pieza o '.' si está vacía
    private char[][] tablero;

    // Crea un tablero vacío
    public Tablero() {
        tablero = new char[8][8]; // Crear la matriz de 8 filas x 8 columnas
        inicializar();            // Llenar el tablero con '.' haciendo referencias a las celdas vacías
    }

    // Esto hace que se inicialize el tablero entero con '.'
    public void inicializar() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                tablero[i][j] = '.';

            }
        }
    }
}