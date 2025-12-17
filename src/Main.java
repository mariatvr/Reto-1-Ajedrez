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

public class Main {
    public static void main(String[] args) {
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
    }
}
