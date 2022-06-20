import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Hanoi {
    int tamanio;
    int palos[][] = new int[3][11];


    public Hanoi(int tamanio) {
        this.tamanio = tamanio;
        int aux = tamanio, i = 0, j = 0;
        for (; i < 3; i++) {

            for (; j < tamanio; j++) {
                palos[i][j] = 0;
            }

        }

        i = 0;
        for (; i < tamanio; i++) {
            palos[0][i] = aux;
            aux--;

        }

    }
    public Hanoi(Hanoi han) {
        this.tamanio = han.tamanio;
        int aux = tamanio, i = 0, j = 0;
        for (; i < 3; i++) {
            for (j=0; j < tamanio; j++) {
                palos[i][j] = han.palos[i][j];
            }

        }


    }

    public void ver() {
        int i = 0, j ;
        for (; i < 3; i++) {
            System.out.printf("Palo " + i + ": [ ");
            for (j = 0; j < tamanio; j++) {
                //if (palos[i][j] != 0)
                System.out.printf(palos[i][j] + " ");


            }
            System.out.println("]");
        }
    }

    public boolean hayPieza(int palo) {

        return palos[palo][0] != 0;
    }

    public boolean esColocable(int paloOrigen, int paloDestino) {
        boolean resultado;
        if (paloOrigen==paloDestino){
            resultado=false;
        }else if (ultimaPieza(paloOrigen)==0){
            resultado=false;
        }else if (!hayPieza(paloDestino)){
            resultado = true;
        }else {
            resultado = ultimaPieza(paloOrigen) < ultimaPieza(paloDestino);
        }
        return resultado;
    }

    public int ultimaPieza(int palo) {
        int resultado = 0;

        int i = 0;
        for (; i < tamanio && palos[palo][i] != 0; i++) {
            resultado = palos[palo][i];

        }


        return resultado;
    }

    public int posicionColocable(int paloDestino) {
        int resultado;
        if (!hayPieza(paloDestino)) {
            resultado = 0;
        } else {
            resultado = posicionUltimaPieza(paloDestino) + 1;
        }
        return resultado;


    }

    public void moverPieza(int paloOrigen, int paloDestino) {


            int aux = ultimaPieza(paloOrigen);
            //System.out.println("la pieza que vamos a quitar es:" + aux);
            quitarUltimaPieza(paloOrigen);
            palos[paloDestino][posicionColocable(paloDestino)] = aux;



    }

    public void quitarUltimaPieza(int palo) {
        palos[palo][posicionUltimaPieza(palo)] = 0;
    }

    public int posicionUltimaPieza(int palo) {
        int i = tamanio;
        for (; i > 0 && palos[palo][i] == 0; i--) {

        }

        return i;
    }

    public boolean finJuego() {
        int aux = tamanio, i = 0;
        boolean resultado = true;


        while(resultado&&i < tamanio){
            resultado =palos[2][i]== aux;

            i++;aux--;
        }


        return resultado;
    }

    public void partida() {
        Scanner lectura = new Scanner(System.in);
        int paloOrigen, paloDestino;
        do {
            do {
                System.out.println("Pon de que palo quieres tomar la pieza");
                paloOrigen = lectura.nextInt();
            } while (paloOrigen >= 3 || paloOrigen < 0);
            do {
                System.out.println("Pon en que palo quieres poner la pieza");
                paloDestino = lectura.nextInt();
            } while (paloDestino >= 3 || paloDestino < 0);

            if (esColocable(paloOrigen, paloDestino))


            moverPieza(paloOrigen, paloDestino);
            //ver();
            dibujar();
        } while (!finJuego());
        System.out.println("Muy bien, has ganado");
        System.out.println("Hecho por Raúl Baeza Osuna");
    }

    public void dibujar() {
        int i = tamanio - 1;
        dibujarPunta();
        dibujarCimaPrimera();
        for (; i > 0; i--) {

            dibujarMedio(i);
            dibujarCima(i - 1);

        }
        dibujarMedio(i);
        dibujarBase();

    }
    public void dibujarBase() {
        int j = 0, i = 0, k = 0;
        for (; i < 3; i++) {
            System.out.printf("╔═══");
            for (j = 0; j < tamanio - palos[i][0]; j++) {
                System.out.printf("═");
            }
            if (palos[i][0] == 0) {
                System.out.printf("╩═╩");
            } else {
                System.out.printf("╩");
                for (k = 0; k < palos[i][0] * 2 + 1; k++) {
                    System.out.printf("═");
                }
                System.out.printf("╩");


            }

            for (j = 0; j < tamanio - palos[i][0]; j++) {
                System.out.printf("═");
            }
            System.out.printf("═══╗");
        }
        System.out.println();
        dibujarBaseFondo();

    }
    public void dibujarBaseFondo() {
        int i = 0, j = 0;
        for (; j < 3; j++) {
            System.out.printf("╚════");
            for (i = 0; i < tamanio; i++) {
                System.out.printf("═");
            }
            System.out.printf(j + "");
            for (i = 0; i < tamanio; i++) {
                System.out.printf("═");
            }
            System.out.printf("════╝");
        }

        System.out.println();
    }
    public void dibujarCima(int altura) {
        int j = 0, i = 0, k = 0;
        for (; i < 3; i++) {
            System.out.printf("    ");
            for (j = 0; j < tamanio - palos[i][altura]; j++) {
                System.out.printf(" ");
            }
            if (palos[i][altura] == 0) {
                System.out.printf("║ ║");
            } else {
                System.out.printf("╔");
                for (k = 0; k < palos[i][altura] - palos[i][altura + 1] - 1; k++) {
                    System.out.printf("═");
                }
                System.out.printf("╩");
                for (k = 0; k < palos[i][altura + 1] * 2 + 1; k++) {
                    System.out.printf("═");
                }
                System.out.printf("╩");
                for (k = 0; k < palos[i][altura] - palos[i][altura + 1] - 1; k++) {
                    System.out.printf("═");
                }
                System.out.printf("╗");
            }
            for (j = 0; j < tamanio - palos[i][altura]; j++) {
                System.out.printf(" ");
            }
            System.out.printf("    ");
        }
        System.out.println();
    }
    public void dibujarMedio(int altura) {
        int j = 0, i = 0, k = 0;
        for (; i < 3; i++) {
            System.out.printf("    ");
            for (j = 0; j < tamanio - palos[i][altura]; j++) {
                System.out.printf(" ");
            }
            if (palos[i][altura] == 0) {
                System.out.printf("║ ║");
            } else {

                System.out.printf("║");
                for (k = 0; k < palos[i][altura]; k++) {
                    System.out.printf(" ");
                }
                System.out.printf("" + palos[i][altura]);
                for (k = 0; k < palos[i][altura]; k++) {
                    System.out.printf(" ");
                }
                System.out.printf("║");
            }

            for (j = 0; j < tamanio - palos[i][altura]; j++) {
                System.out.printf(" ");
            }
            System.out.printf("    ");
        }
        System.out.println();
    }
    public void dibujarCimaPrimera() {
        int j = 0, i = 0;
        for (; i < 3; i++) {
            System.out.printf("    ");
            for (j = 0; j < tamanio - palos[i][tamanio - 1]; j++) {
                System.out.printf(" ");
            }
            if (palos[i][tamanio - 1] == 0) {
                System.out.printf("║ ║");
            } else {
                System.out.printf("╔╩═╩╗");
            }

            for (j = 0; j < tamanio - palos[i][tamanio - 1]; j++) {
                System.out.printf(" ");
            }
            System.out.printf("    ");
        }
        System.out.println();

    }
    public void dibujarPunta() {

        int j = 0, i = 0;
        for (; i < 3; i++) {
            System.out.printf("    ");
            for (j = 0; j < tamanio; j++) {
                System.out.printf(" ");
            }
            System.out.printf("╔═╗");
            for (j = 0; j < tamanio; j++) {
                System.out.printf(" ");
            }
            System.out.printf("    ");
        }
        System.out.println();


    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hanoi hanoi = (Hanoi) o;
        return tamanio == hanoi.tamanio && Arrays.equals(palos, hanoi.palos);
    }

    public boolean palosIguales(int[][] palo1, int[][] palo2){
        boolean igualdad=true;
        int i =0,j=0;
        while(igualdad&&i<3){
            j=0;
            while(igualdad&&j<tamanio){
                igualdad=palo1[i][j]==palo2[i][j];
                j++;
            }
            i++;
        }

        return igualdad;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(tamanio);
        result = 31 * result + Arrays.hashCode(palos);
        return result;
    }

    @Override
    public String toString() {
        return "Hanoi{" +
                "tamanio=" + tamanio +
                ", palos=" + Arrays.toString(palos) +
                '}';
    }

    public void partidaBackTracking(ArrayList<Hanoi> estados,Booleano exito) {
        dibujar();
        estados.add(new Hanoi(this));




        if (finJuego()){
            exito.setBooleano(true);
        }else{
            int movedorActual=0,origen,destino;

            while(!exito.isBooleano()&&movedorActual<9){

                origen=movedorActual/3;
                destino=movedorActual%3;



                if (esColocable(origen,destino)&&!estaEnEstados(estados,origen,destino)){

                        moverPieza(origen, destino);


                    partidaBackTracking(estados,exito);
                        if (!exito.isBooleano()){

                                moverPieza(destino, origen);
                            }


                    }
                movedorActual++;
            }

        }
    }
    public boolean esIgual(Hanoi hanoi){
        boolean esIgual=true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j <hanoi.palos[i].length ; j++) {
                if (palos[i][j]!=hanoi.palos[i][j]){
                    esIgual=false;
                }

            }
        }
        return esIgual;

    }

    public boolean estaEnEstados(ArrayList<Hanoi> estados,int origen,int destino){


        moverPieza(origen, destino);
        boolean res=false;

        for (Hanoi hanoi:estados             ) {
            res=res|| hanoi.esIgual(this);

        }
        moverPieza(destino, origen);

        return res;
    }


}
