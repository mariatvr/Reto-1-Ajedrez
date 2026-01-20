import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {


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
        String inicia;
        boolean blancas;
        boolean jaqueBlanco=false;
        boolean jaqueNegro=false;
        Scanner sc = new Scanner(System.in);

        //Buscamos al Rey blanco y comprobamos jaque
        Posicion posReyBlanco = localizarRey(miTablero, true);
        if(posReyBlanco != null){
            jaqueBlanco = miTablero.jaque(posReyBlanco, true);
        }

        //Buscamos al Rey negro y comprobamos jaque
        Posicion posReyNegro = localizarRey(miTablero, false);
        if(posReyNegro!=null){
            jaqueNegro = miTablero.jaque(posReyNegro, false);
        }

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
        Primero de, valida que no contiene símbolos antes de comprobar el String por trozos.
         */
        piezas = piezas.replace(" ", "");
        boolean valido = false;
        for (int i = 0; i < piezas.length(); i++) {
            char c = piezas.charAt(i);
            /*
            Si el carácter actual que recorre el string es una letra o un número es válido.
            Lo mismo pasa si contiene un espacio, que sería el separador de cada movimiento.
             */
            if (!Character.isLetterOrDigit(c) && c != ',') {
                break;
            }
        }
        StringBuilder trozo = new StringBuilder();
        int ultChar = 0;
        for (int j = 0; j < piezas.length(); j++) {

            /*
            Se "trocea" el string para validar cada uno de los trozos.
             */
            for (; ultChar < piezas.length(); ultChar++) {
                char c = piezas.charAt(ultChar);
                /*
                Si contiene un espacio el carácter actual, se almacena el trozo de una pieza y del movimiento.
                 */
                if (c == ',') {
                    break;
                }
                trozo.append(c);
            }

                /*
                Si el trozo es de 3 de longitud, es decir, que tiene una pieza y un movimiento, se comprueba que es correcto.
                 */
            if (trozo.length() == 3) {
                /*
                Se comprueba que la primera sea mayúscula (de acuerdo a la notación del ajedrez).
                 */
                if (Character.isLetter(trozo.charAt(0)) && trozo.charAt(0) == trozo.toString().toUpperCase().charAt(0)) {
                    /*
                    Ahora se comprueba que el siguiente carácter sea una letra minúscula.
                     */
                    if (Character.isLetter(trozo.charAt(1)) && trozo.charAt(1) == trozo.toString().toLowerCase().charAt(1)) {
                        /*
                        Por último, se comprueba que el siguiente carácter sea un número.
                         */
                        if (Character.isDigit(trozo.charAt(2))) {
                            /*
                             Si  lo anterior se cumple en ese trozo en concreto, es válido (true).
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
                Comprobamos que el primer carácter sea una letra minúscula.
                 */
                if (Character.isLetter(trozo.charAt(0)) && trozo.charAt(0) == trozo.toString().toLowerCase().charAt(0)) {
                    /*
                    Aquí lo mismo, pero comprobamos el número.
                     */
                    if (Character.isDigit(trozo.charAt(1))) {
                        /*
                        Si es válido, se le asigna true al boolean válido.
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
                Si no, el formato introducido es inválido, por lo tanto, el boolean válido es false.
                 */
            } else {
                valido = false;
                break;
                /*
                Una vez que sea inválido, se acaba la comprobación y finaliza la función.
                 */
            }
            trozo = new StringBuilder();
        }
        return valido;
    }

    public static String pidoMovimiento() {
        String movimiento;
        String patron = "[TCARD]?[1-8]?[a-h][1-8]";
        //Hay un posible número seguido de la mayúscula que indica la fila
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

        //0. Comprobar que no hay piezas fuera de rango
        Pattern patroncorrecto = Pattern.compile("^[A-Z]?[a-h][1-8](,\\s*[A-Z]?[a-h][1-8]){0,15}$");

        if (patroncorrecto.matcher(entrada).matches()) {
            // 1. Comprobación de que haya EXACTAMENTE 1 Rey
            int reyes = entrada.length() - entrada.replace("R", "").length();

            if (reyes != 1) {
                System.out.println("Error: Debe haber exactamente 1 rey (encontrados: " + reyes + ").");
                return false;
            }

            //2.Contar peones
            int contador = 0;
            // Patrón para detectar peones en filas prohibidas (1 y 8)
            Pattern filaProhibida = Pattern.compile("(?<![A-Z])[a-h][18]");

            if (filaProhibida.matcher(entrada).find()) {
                System.out.println("Error: Hay peones en la fila 1 u 8, lo cual es ilegal.");
                return false;
            }

            // Patrón para contar peones válidos (filas 2 a 7)
            Pattern p = Pattern.compile("(?<![A-Z])[a-h][2-7]");
            Matcher m = p.matcher(entrada);

            while (m.find()) {
                contador++;
            }

            // 3. Contar piezas
            int peones = contador;
            int alfiles = contar(entrada, 'A');
            int torres = contar(entrada, 'T');
            int caballos = contar(entrada, 'C');
            int reinas = contar(entrada, 'D'); // D = dama

            // 4. Máximos normales
            int maxPeones = 8;
            int maxAlfiles = 2;
            int maxTorres = 2;
            int maxCaballos = 2;
            int maxReinas = 1;

            // 5. Comprobar peones
            if (peones > maxPeones) {
                System.out.println("Error: No puede haber más de 8 peones.");
                return false;
            }

            // 6. Calcular cuántos peones faltan
            int faltanPeones = maxPeones - peones;

            // 7. Calcular excesos sobre lo normal
            int excesoAlfiles = Math.max(0, alfiles - maxAlfiles);
            int excesoTorres = Math.max(0, torres - maxTorres);
            int excesoCaballos = Math.max(0, caballos - maxCaballos);
            int excesoReinas = Math.max(0, reinas - maxReinas);

            int excesoTotal = excesoAlfiles + excesoTorres + excesoCaballos + excesoReinas;

            // 8. Validar promoción
            if (excesoTotal > faltanPeones) {
                System.out.println("Error: Hay mas piezas de las permitidas según los peones que faltan.");
                return false;
            }

            return true;
        }
        else{
            System.out.println("Error: Formato inválido.");
            return false;
        }
    }

    public static boolean posicionesNoRepetidas(String entrada) {

        String[] split = entrada.split(",\\s*");
        Set<String> posiciones = new HashSet<>();

        for (String subcadena : split) {
            // La posición SIEMPRE son los dos últimos caracteres
            String pos = subcadena.substring(subcadena.length() - 2);

            // add() devuelve false si ya estaba
            if (!posiciones.add(pos)) {
                System.out.println("Posición repetida: " + pos);
                return false;
            }
        }
        return true;
    }

    private static int contar(String entrada, char pieza) {
        int count = 0;
        for (int i = 0; i < entrada.length(); i++) {
            if (entrada.charAt(i) == pieza) count++;
        }
        return count;
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
            Posicion posRey = localizarRey(tablero, blancas);
            boolean jaque = tablero.jaque(posRey, blancas);

            if(jaque){
                if (blancas){
                    System.out.println("El Rey blanco está en Jaque, blancas pierden");
                }else{
                    System.out.println("El Rey negro está en Jaque, negras pierden");
                }
            }
        }
    }

    public static void mover (Tablero t, String movimiento, boolean blancas) {

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
            Posicion posDestino = convertirPosicion(movimiento.charAt(1), movimiento.charAt(2));

            if (tipo != 'p') {
                muevePieza(t,tipo,posDestino, blancas,false,false,0);
            }
            //peon con conflicto
            else {
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

     static void main(String[] args) {

        Tablero tablero = new Tablero();

        Scanner scan = new Scanner(System.in);
        boolean mueven;
        boolean entradaCorrecta;
        boolean posOK;
        String piezasB;
        String piezasN;
        StringBuilder piezasT=new StringBuilder();
        String movimiento;

        // Imprimir tablero
        System.out.println("Tablero Vacío:");
        System.out.println(tablero);
       /*
       Se pide la entrada del tablero inicial
        */
         do {
             System.out.println("Introduce la posición inicial de piezas de color blanco:");
             piezasB = scan.nextLine();
             entradaCorrecta = posicionIni(piezasB) & piezasCorrectas(piezasB);
             piezasT.append(piezasB);
             posOK=posicionesNoRepetidas(piezasT.toString());

             if(!posOK){
                 System.out.println("Hay piezas repetidas");
                 piezasT.delete(0,piezasT.length());
             }
             if (!entradaCorrecta) {
                 System.out.println("Entrada no válida. El formato...");
                 piezasT.delete(0,piezasT.length());
             }
         } while (!entradaCorrecta||!posOK);

         piezasT.append(",");

         do {
             System.out.println("Introduce las posición inicial de piezas de color negro:");
             piezasN = scan.nextLine();
             entradaCorrecta = posicionIni(piezasN) & piezasCorrectas(piezasN);

             piezasT.append(piezasN);

             posOK=posicionesNoRepetidas(piezasT.toString());
             if(!posOK){
                 System.out.println("Hay piezas repetidas");
                 piezasT.delete(0,piezasT.length());
                 piezasT.append(piezasB);
             }
             if (!entradaCorrecta) {
                 System.out.println("Entrada no válida. El formato...");
                 piezasT.delete(0,piezasT.length());
                 piezasT.append(piezasB);
             }
         } while (!entradaCorrecta||!posOK);

         /*inicializamos tablero*/
         tablero.colocarPiezasDesdeNotacion(piezasB, true);
         tablero.colocarPiezasDesdeNotacion(piezasN, false);

        // Imprimir tablero
        System.out.println("Tablero Inicial:");
        System.out.println(tablero);

        //Se establece quien mueve: "Mueven blancas."/"Mueven negras."
        mueven = jugadorInicial(tablero);

        //Pedimos movimiento "[TCARD]?[1-8]?[a-h][1-8]"
        movimiento = pidoMovimiento();

        if(mueven)
        {
            System.out.println("Movimiento:"+ movimiento + " blancas");
        }
        else{
            System.out.println("Movimiento:"+ movimiento + " negras");
        }

        mover(tablero,movimiento, mueven);

        // Imprimir tablero final
        System.out.println("Tablero Final:");
        System.out.println(tablero);
    }
}
