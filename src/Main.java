import java.util.Scanner;

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
            System.out.println("Mueven blancas."); //Podría igualarse inicia a blancas o negras según quien salga?
        } else if (jaqueNegro){
            System.out.println("Mueven negras.");
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
        boolean valido = true;
        for (int i = 0; i < piezas.length(); i++) {
            char c = piezas.charAt(i);
            /*
            Si el caracter actual que recorre el string es una letra o un número es válido.
            Lo mismo pasa si contiene un espacio, que sería el separador de cada movimiento.
             */
            if (!Character.isLetterOrDigit(c) && c != ' ') {
                valido = false;
                break;
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
                /*
                Una vez que sea inválido, se acaba la comprobación y finaliza la función.
                 */
                valido = false;
                break;
            }
            trozo = "";
        }

        return valido;
    }

    public static String pidoMovimiento(){
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

        return movimiento;
    }

    //Función que comprueba que solo hay 1 rey y que hay 8 peones o menos, y que ninguno está en las filas 1 ni 8
    public static boolean piezasCorrectas(String entrada) {
        boolean valido = true;

        // 1. Comprobación de que haya EXACTAMENTE 1 Rey
        int reyes = entrada.length() - entrada.replace("R", "").length();
        if (reyes != 1) {
            System.out.println("Error: Debe haber exactamente 1 rey (encontrados: " + reyes + ").");
            valido = false;
        }

        // 2. Comprobación de Peones (Solo si el rey está bien)
        if (valido) {
            valido = validarPeones(entrada, 8);
        }

        return valido;
    }

    private static boolean validarPeones(String entrada, int max) {
        // Patrón para detectar peones en filas prohibidas (1 y 8)
        Pattern filaProhibida = Pattern.compile("(?<![A-Z])[a-h][18]");
        if (filaProhibida.matcher(entrada).find()) {
            System.out.println("Error: Hay peones en la fila 1 u 8, lo cual es ilegal.");
            return false;
        }

        // Patrón para contar peones válidos (filas 2 a 7)
        Pattern p = Pattern.compile("(?<![A-Z])[a-h][2-7]");
        Matcher m = p.matcher(entrada);

        int contador = 0;
        while (m.find()) {
            contador++;
        }

        if (contador > max) {
            System.out.println("Error: No puede haber más de " + max + " peones. Detectados: " + contador);
            return false;
        }

        return true;
    }


    public static void main (String[]args){

        Tablero tablero = new Tablero();

        Scanner scan = new Scanner(System.in);
        String mueven;
        boolean entradaCorrecta = true;
        String piezasB;
        String piezasN;
        String movimiento;

        // Imprimir tablero
        System.out.println("Tablero Vacio:");
        System.out.println(tablero);
       /*
       Se pide la entrada del tablero inicial
        */
            do {
                System.out.println("Introduce la posición inicial de piezas de color blanco:");
                piezasB = scan.nextLine();
                entradaCorrecta=posicionIni(piezasB)

            if (!entradaCorrecta) {
                    System.out.println("Entrada no válida. El formato...");
                }
            }while (!entradaCorrecta);

        do {
            System.out.println("Introduce las posición inicial de piezas de color negro:");
            piezasN = scan.nextLine();
            entradaCorrecta=posicionIni(piezasB)

            if (!entradaCorrecta) {
                System.out.println("Entrada no válida. El formato...");
            }
        }while (!entradaCorrecta);

        //comprobar tablero inicial correcto

        /*inicializamos tablero*/
        tablero.colocarPiezasDesdeNotacion(piezasB);
        tablero.colocarPiezasDesdeNotacion(piezasN);

        // Imprimir tablero
        System.out.println("Tablero Vacio:");
        System.out.println(tablero);

        //Se establece quien mueve
        mueven=jugadorInicial(tablero);

        //Pedimos movimiento
        movimiento=pidoMovimiento();

        //Realizamos movimiento

        // Imprimir tablero final
        System.out.println(tablero);
    }
}

