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

    public static void pidoMovimiento(){
        String movimiento;
        String patron = "[TCARD]?[a-h][1-8]";

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

    public static void main (String[]args){

        pidoMovimiento();

    }
}

