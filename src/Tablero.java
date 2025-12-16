public class Tablero {
    Pieza tablero [][];


    Tablero (){
        this.tablero= new Pieza[8][8];
    }

    public void ColocarPiezas (String posicion){

    }

    public Pieza getTablero(Posicion p) {
        return tablero[p.getFila()][p.getColumna()];
    }

    public void setTablero(Pieza pieza, Posicion p) {
        this.tablero[p.getFila()][p.getColumna()] = pieza;
    }

    @Override
    public String toString() {
        return "Printea";
    }
}
