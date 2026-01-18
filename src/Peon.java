import java.util.Scanner;

/**
 * Representa la pieza Peón del juego de ajedrez.
 * El peón puede avanzar una casilla hacia delante si está libre
 * o capturar en diagonal una pieza del bando contrario.
 */
public class Peon extends Pieza{

    /**
     * Constructor de la clase Peon.
     *
     * @param p posición inicial del peón
     * @param blancas true si el peón es blanco, false si es negro
     */
    public Peon(Posicion p, boolean blancas) {
        super(p, blancas, 'p');
    }

    /**
     * Comprueba el movimiento del peón según las reglas básicas:
     *   ·Avance de una o dos casillas (en caso de no haber movido aún)
     *   hacia delante si está libre.
     *   ·Captura en diagonal de una pieza enemiga.
     *
     * @param t tablero donde se efectúa el movimiento
     * @param destinoPos posición de destino
     * @return true si el movimiento es válido,
     *      * false en caso contrario
     */
    @Override
    public boolean compMov(Tablero t, Posicion destinoPos) {
        boolean valido=false;

        if (destinoPos.dentroTablero()) {

        Pieza destino = t.getPosicion(destinoPos);

        int df = destinoPos.getFila() - this.p.getFila();
        int dc = destinoPos.getColumna() - this.p.getColumna();
        int filaInicial = this.blancas ? 6 : 1;

        // Ajusta este dir según tu tablero:
        // Si a1 = fila 7 y a8 = fila 0, blancas avanzan con -1
        int dir = this.blancas ? -1 : 1;

        // Recto
        if (dc == 0 && df == dir && destino == null) valido=true;

        if (dc == 0 && this.p.getFila() == filaInicial && df == 2*dir && destino == null) {
            Posicion intermedia = new Posicion(this.p.getFila() + dir, this.p.getColumna());
            if (t.getPosicion(intermedia) == null) valido=true;
        }

        // Captura diagonal
        if (Math.abs(dc) == 1 && df == dir && destino != null && destino.getBlancas() != this.blancas) valido=true;
        }

        return valido;
    }

    public boolean estaEnFilaFinal() {
        int fila = this.p.getFila();
        return (this.blancas && fila == 0) || (!this.blancas && fila == 7);
    }

    public Pieza promocionar() {
        Scanner sc = new Scanner(System.in);
        System.out.println("¡Promoción del peón!");
        System.out.println("¿En qué quieres convertirlo?");
        System.out.println("1 - Reina");
        System.out.println("2 - Torre");
        System.out.println("3 - Alfil");
        System.out.println("4 - Caballo");

        int opcion = sc.nextInt();

        return switch (opcion) {
            case 2 -> new Torre(this.p, this.blancas);
            case 3 -> new Alfil(this.p, this.blancas);
            case 4 -> new Caballo(this.p, this.blancas);
            default -> new Reina(this.p, this.blancas);
        };
    }


    @Override
    public String toString() {
        return blancas ? "♙" : "♟";
    }
}
