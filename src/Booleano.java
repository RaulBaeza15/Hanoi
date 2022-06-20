public class Booleano {
    private boolean booleano;
    public Booleano(){

    }
    public Booleano(boolean bul){
        this.booleano=bul;
    }

    public void setBooleano(boolean booleano) {
        this.booleano = booleano;
    }

    public boolean isBooleano() {
        return booleano;
    }

    @Override
    public String toString() {
        return "Booleano{" +
                "booleano=" + booleano +
                '}';
    }
}
