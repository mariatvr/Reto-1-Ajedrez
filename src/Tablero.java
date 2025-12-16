public class Tablero {
    Pieza tablero [][];


    Tablero (){
        this.tablero= new Pieza[8][8];
    }

    public void colocarPiezas (String posicion, boolean blancas){

    }

    /**
    Esta función determina si el camino está despejado desde una posición inicial p1 hasta
    **/
    public boolean caminoLibre (Posicion p1, Posicion p2, int dir){
        boolean libre = true;

        return libre;
    }

    public Pieza getPosicion(Posicion p) {
        return tablero[p.getFila()][p.getColumna()];
    }

    public void setPieza(Pieza pieza, Posicion p) {
        this.tablero[p.getFila()][p.getColumna()] = pieza;
    }

    @Override
    public String toString() {
        return "Printea";
    }
}
