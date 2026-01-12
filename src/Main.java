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
            System.out.println("Mueven blancas.");
        } else if (jaqueNegro){
            System.out.println("Mueven negras.");
        } else {
            System.out.print("Introduce quién quieres que mueva (blancas/negras): ");
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

        pidoMovimiento();

    }
}

