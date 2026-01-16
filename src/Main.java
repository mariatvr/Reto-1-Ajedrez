import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public static boolean jugadorInicial(Tablero miTablero) {
        String inicia = "";
        boolean blancas;
        Scanner sc = new Scanner(System.in);

        //Buscamos al Rey blanco y comprobamos jaque
        Posicion posReyBlanco = localizarRey(miTablero, true);
        boolean jaqueBlanco = miTablero.jaque(posReyBlanco, true);

        //Buscamos al Rey negro y comprobamos jaque
        Posicion posReyNegro = localizarRey(miTablero, false);
        boolean jaqueNegro = miTablero.jaque(posReyNegro, false);


        if (jaqueBlanco) {
            System.out.println("Mueven blancas.");
            blancas=true;
        } else if (jaqueNegro) {
            System.out.println("Mueven negras.");
            blancas=false;
        } else {
            System.out.print("Introduce quién quieres que mueva (blancas/negras): ");
            boolean valido = false;

            do {
                inicia = sc.nextLine();

                if (inicia.equalsIgnoreCase("blancas") || inicia.equalsIgnoreCase("negras")) {
                    valido = true;
                } else {
                    System.out.println("Debe introducir una opción válida.");
                }
                //hacer bucle para que la entrada sea correcta e inicializar variable "blancas" a true o false.
            } while (!valido);

            blancas = inicia.equalsIgnoreCase("blancas");
        }
        return blancas;
    }

    public static boolean posicionIni(String piezas) {
        /*
        Primero de todo, valida que no contiene símbolos antes de comprobar el String por trozos.
         */
        piezas = piezas.replace(" ", "");
        boolean valido = false;
        for (int i = 0; i < piezas.length(); i++) {
            char c = piezas.charAt(i);
            /*
            Si el caracter actual que recorre el string es una letra o un número es válido.
            Lo mismo pasa si contiene un espacio, que sería el separador de cada movimiento.
             */
            if (Character.isLetterOrDigit(c) || c == ',') {
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
            for (; ultChar < piezas.length(); ultChar++) {
                char c = piezas.charAt(ultChar);
                /*
                Si contiene un espacio el caracter actual, se almacena el trozo de una pieza y del movimiento.
                 */
                if (c == ',') {
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

    public static String pidoMovimiento() {
        String movimiento;
        String patron = "[TCARD]?[1-8]?[a-h][1-8]";
        //Hay un posible numero seguido de la mayúscula que indica la fila
        // de la figura que queremos mover en caso de que haya otra de
        // su mismo tipo en esa misma columna.

        boolean valido = true;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print("Introduce tu próximo movimiento: ");
            movimiento = sc.nextLine();

            if (movimiento.matches(patron)) {
                valido = false;
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
            valido = validarPeones(entrada);
        }

        return valido;
    }

    private static boolean validarPeones(String entrada) {
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

        if (contador > 8) {
            System.out.println("Error: No puede haber más de " + 8 + " peones. Detectados: " + contador);
            return false;
        }

        return true;
    }

    public static Posicion convertirPosicion (char columna, char fila){
        int c = columna - 'a';           // a-h → 0-7
        int f = 8 - (fila - '0');        // 1-8 → 7-0
        return new Posicion(f, c);
    }

    public static void muevePieza (Tablero tablero, char tipo, Posicion destino, boolean blancas, boolean fila, boolean columna, int fijo) {
        boolean movRealizado = false;

        for (int row = 0; row <= 7; row++) {
            for (int col = 0; col <= 7; col++) {

                if(columna){
                    Posicion p=new Posicion(row,fijo);
                    if(tablero.getPosicion(p)!=null) {
                        if(tablero.getPosicion(p).getTipo() == tipo && tablero.getPosicion(p).getBlancas() == blancas) {
                            if(tablero.mover(p, destino)){
                                movRealizado=true;
                            }
                        }
                    }
                }
                else if(fila){
                    Posicion p=new Posicion(fijo,col);
                    if(tablero.getPosicion(p)!=null) {
                        if(tablero.getPosicion(p).getTipo() == tipo && tablero.getPosicion(p).getBlancas() == blancas){
                            if(tablero.mover(p, destino)){
                                movRealizado=true;
                            }
                        }
                    }
                }
                else{
                    Posicion p=new Posicion(row,col);
                    if(tablero.getPosicion(p)!=null) {
                        if(tablero.getPosicion(p).getTipo() == tipo && tablero.getPosicion(p).getBlancas() == blancas){
                            if(tablero.mover(p, destino)) {
                                movRealizado = true;
                            }
                        }
                    }
                }
            }
        }

        if(!movRealizado) {
            if (blancas){
                System.out.println("Movimiento no válido, blancas pierden");
            }else{
                System.out.println("Movimiento no válido, negras pierden");
            }
        }
        else{
            Posicion posRey = localizarRey(tablero, !blancas);
            boolean jaque = tablero.jaque(posRey, !blancas);

            if(jaque){
                if (!blancas){
                    System.out.println("El Rey blanco está en Jaque, blancas pierden");
                }else{
                    System.out.println("El Rey negro está en Jaque, negras pierden");
                }
            }
        }
    }
    public static void mover (Tablero t, String movimiento, boolean blancas) {
        boolean movRealizado = false;
        char tipo='p';
        if(Character.isUpperCase(movimiento.charAt(0))) {
            tipo=movimiento.charAt(0);
        }

        if (movimiento.length() == 2) {
            Posicion posDestino = convertirPosicion(movimiento.charAt(0), movimiento.charAt(1));
            muevePieza(t,tipo,posDestino, blancas,false,false,0);
        } //mover peon

        else if (movimiento.length() == 3) {
            //pieza normal
            if (tipo != 'p') {
                    Posicion posDestino = convertirPosicion(movimiento.charAt(1), movimiento.charAt(2));
                    muevePieza(t,tipo,posDestino, blancas,false,false,0);
            }
            //peon con conflicto
            else {
                Posicion posDestino = convertirPosicion(movimiento.charAt(1), movimiento.charAt(2));
                int columna = convertirPosicion(movimiento.charAt(0), '1').getColumna();
                muevePieza(t,tipo,posDestino, blancas,false,true,columna);

            }
        } else {
            Posicion posDestino = convertirPosicion(movimiento.charAt(2), movimiento.charAt(3));

            if (Character.isDigit(movimiento.charAt(1))) {
                int fila = movimiento.charAt(1);
                muevePieza(t,tipo,posDestino, blancas,true,false,fila);
            } else {
                int columna = convertirPosicion(movimiento.charAt(1), '1').getColumna();
                muevePieza(t,tipo,posDestino, blancas,false,true,columna);
            }
        }
    }

    public static void main(String[] args) {

        Tablero tablero = new Tablero();

        Scanner scan = new Scanner(System.in);
        boolean mueven;
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
            entradaCorrecta = posicionIni(piezasB)&piezasCorrectas(piezasB);

            if (!entradaCorrecta) {
                System.out.println("Entrada no válida. El formato...");
            }
        } while (!entradaCorrecta);

        do {
            System.out.println("Introduce las posición inicial de piezas de color negro:");
            piezasN = scan.nextLine();
            entradaCorrecta = posicionIni(piezasN)&piezasCorrectas(piezasN);

            if (!entradaCorrecta) {
                System.out.println("Entrada no válida. El formato...");
            }
        } while (!entradaCorrecta);

        /*inicializamos tablero*/
        tablero.colocarPiezasDesdeNotacion(piezasB, true);
        tablero.colocarPiezasDesdeNotacion(piezasN, false);

        // Imprimir tablero
        System.out.println("Tablero Inicial:");
        System.out.println(tablero);

        //Se establece quien mueve: "Mueven blancas."/"Mueven negras."
        mueven = jugadorInicial(tablero);

        if(mueven){
            System.out.println("Mueven balncas");
        }
        else{
            System.out.println("Mueven negras");
        }

        //Pedimos movimiento "[TCARD]?[1-8]?[a-h][1-8]"
        movimiento = pidoMovimiento();

        System.out.println("Movimiento:"+ movimiento + " "+ mueven);

        mover(tablero,movimiento, mueven);

        // Imprimir tablero final
        System.out.println("Tablero Final:");
        System.out.println(tablero);
    }
}
