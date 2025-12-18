public class Main {

    /*
    Pedir entrada Blancas/Negras y guardarlo como strings.
    Generar tablero y pasarle los strings.
    Comprobar si tablero válido.
    Comprobar si jaque.
    Imprimir si válido.
    Si no jaque, pedir quien mueve.
    Pedir movimiento.
    Comprobar movimiento válido.
    Imprimir nuevo tablero si válido.
    */

    public static void main(String[] args) {

        Tablero tablero = new Tablero();

        // Colocar peones negros y blancos
        tablero.colocarPiezas("peon", false); // fila 1
        tablero.colocarPiezas("peon", true);  // fila 6

        // Imprimir tablero
        System.out.println("Tablero con peones:");
        System.out.println(tablero);
    }
}

