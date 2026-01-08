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

    private static Posicion localizarRey(Tablero miTablero, boolean esBlanca) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Pieza pieza = miTablero.getTablero()[i][j];

                if (pieza != null) {
                    boolean esRey = pieza.getClass().getSimpleName().equalsIgnoreCase("Rey");

                    if (esRey && pieza.getBlancas() == esBlanca) {
                        return new Posicion(i, j);
                    }
                }
            }
        }
        return null;
    }

    public static String jugadorInicial(Tablero miTablero){
        String inicia = "";
        Scanner sc = new Scanner(System.in);

        //Buscamos al Rey blanco y comprobamos jaque
        Posicion posReyBlanco = localizarRey(miTablero, true);
        boolean jaqueBlanco = miTablero.jaque(posReyBlanco, true);

        //Buscamos al Rey negro y comprobamos jaque
        Posicion posReyNegro = localizarRey(miTablero, false);
        boolean jaqueNegro = miTablero.jaque(posReyBlanco, false);


        if (jaqueBlanco){
            System.out.println("Inician blancas.");
        } else if (jaqueNegro){
            System.out.println("Inician negras.");
        } else {
            System.out.print("Introduce quién quieres que inicie (blancas/negras): ");
            inicia=sc.nextLine();
        }
        return inicia;
    }

    public static void pidoMovimiento(){
        String movimiento;
        String patron = "[TCARD]?[1-8]?[a-h][1-8]";
        //Hay un posible numero seguido de la mayúscula que indica la fila
        // de la figura que queremos mover en caso de que haya otra de
        // su mismo tipo en esa misma columna.

        boolean valido=true;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print("Introduce tu próximo movimiento: ");
            movimiento = sc.nextLine();

            if (movimiento.matches(patron)) {
                System.out.println("Formato válido");
                valido=false;
            } else {
                System.out.println("Formato incorrecto");
            }
        } while (valido);
    }

    public static void main (String[] args){
        Tablero tablero = new Tablero();

        pidoMovimiento();

        // Imprimir tablero
        System.out.println("Tablero Vacio:");
        System.out.println(tablero);
    }
}

