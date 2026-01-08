import java.util.Scanner;

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
    public static boolean posicionIni(String piezas) {
        /*
        Primero de todo, valida que no contiene símbolos antes de comprobar el String por trozos.
         */
        boolean valido = false;
        for (int i = 0; i < piezas.length(); i++) {
            char c = piezas.charAt(i);
            /*
            Si el caracter actual que recorre el string es una letra o un número es válido.
            Lo mismo pasa si contiene un espacio, que sería el separador de cada movimiento.
             */
            if (Character.isLetterOrDigit(c) || c == ' ') {
                valido = true;
            } else {
                valido = false;
            }
        }
        String trozo = "";
        int ultChar = 0;
        for (int j = 0; j < piezas.length(); j++) {

            /*
            Se "trocea" el string para validar cada uno de los trozos.
             */
            for (;ultChar < piezas.length(); ultChar++) {
                char c = piezas.charAt(ultChar);
                /*
                Si contiene un espacio el caracter actual, se almacena el trozo de una pieza y del movimiento.
                 */
                if (c == ' ') {
                    break;
                }
                trozo = trozo + c;
            }

                /*
                Si el trozo es de 3 de longitud, es decir, que tiene una pieza y un movimiento, se comprueba que es correcto.
                 */
            if (trozo.length() == 3) {
                /*
                Se comprueba que la primera sea mayúscula (de acuerdo a la notación del ajedrez).
                 */
                if (trozo.charAt(0) == trozo.toUpperCase().charAt(0)) {
                    /*
                    Ahora se comprueba que el siguiente caracter sea una letra.
                     */
                    if (Character.isLetter(trozo.charAt(1))) {
                        /*
                        Por último, se comprueba que el siguiente caracter sea un número.
                         */
                        if (Character.isDigit(trozo.charAt(2))) {
                            /*
                            Si todo lo anterior se cumple en ese trozo en concreto, es válido (true).
                             */
                            valido = true;
                            /*
                            Pasa al siguiente trozo.
                             */
                            if (ultChar == piezas.length()) {
                                break;
                            } else {
                                ultChar = ultChar + 1;
                            }
                        }
                    }
                }
                /*
                Si no, es que es un peón (solo 2 caracteres para el movimiento) y se valida.
                 */
            } else if (trozo.length() == 2) {
                /*
                Comprobamos que el primer caracter sea una letra.
                 */
                if (Character.isLetter(trozo.charAt(0))) {
                    /*
                    Aquí lo mismo pero comprobamos el número.
                     */
                    if (Character.isDigit(trozo.charAt(1))) {
                        /*
                        Si es válido, se le asigna true al boolean valido.
                         */
                        valido = true;
                        /*
                        Aquí lo mismo que antes, pasa al siguiente trozo disponible.
                         */
                        if (ultChar == piezas.length()) {
                            break;
                        } else {
                            ultChar = ultChar + 1;
                        }
                    }
                }

                /*
                Si no, el formato introducido es inválido, por lo tanto el boolean valido es false.
                 */
            } else {
                valido = false;
                /*
                Una vez que sea inválido, se acaba la comprobación y finaliza la función.
                 */
                if (valido == false) {
                    break;
                }
            }
            trozo = "";
        }
        return valido;
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

        Scanner scan = new Scanner(System.in);
        String color;
        boolean blancas = false;
        String piezasB;
        String piezasN;
        /*
        Se le pregunta al usuario el color que empieza primero.
         */

        System.out.println("¿Qué color de piezas comienza a jugar? (Blancas/Negras)");

         /*
        El color para ver quién empieza a colocar las piezas.
         */
        color = scan.nextLine();

       /*
       Empiezan las blancas y luego las negras.
        */
        if (color.equalsIgnoreCase("Blancas")) {
            blancas = true;
            System.out.println("Introduce la posición inicial de piezas de color blanco:");
            piezasB = scan.nextLine();

            System.out.println("Introduce las posición inicial de piezas de color negro:");
            piezasN = scan.nextLine();

            /*
            Si no empiezan blancas, empiezan las negras.
             */
        } else if (color.equalsIgnoreCase("Negras")){
            blancas = false;
            System.out.println("Introduce las posición inicial de piezas de color negro:");
            piezasN = scan.nextLine();

            System.out.println("Introduce la posición inicial de piezas de color blanco:");
            piezasB = scan.nextLine();
            /*
            Si no coinciden los colores, muestra este mensaje.
             */
        } else {
            System.out.println("Color introducido incorrecto.");
        }
        pidoMovimiento();

        // Imprimir tablero
        System.out.println("Tablero Vacio:");
        System.out.println(tablero);
    }
}

