import java.util.ArrayList;
import java.util.Scanner;

public class Run {
    public static void main(String[] args) {


        System.out.println("LAS TORRES DE HANOI");
        int tamanio;
        System.out.println("Empezamos el juego");
        int eleccion=2;Scanner lectura = new Scanner(System.in);
        do {
            System.out.println("¿Quieres jugar o ver cómo se resuelve solo con Back Tracking? (0=Jugar, 1=BT)");
            eleccion = lectura.nextInt();


        }while (!(eleccion==1||eleccion==0));



        do {
            System.out.println("Por favor introduzca el nivel de dificultad/numero de piezas con las que quiere\njugar por teclado (min=1, max=8):");
            tamanio = lectura.nextInt();
        } while (tamanio > 8 || tamanio < 1);


        Hanoi juego = new Hanoi(tamanio);
        ArrayList<Hanoi> estados=new ArrayList<>();
        //juego.ver();
        juego.dibujar();
        Booleano bul =new Booleano();




        if (eleccion==0){
            juego.partida();
        }else{
            juego.partidaBackTracking(estados,bul);
        }


    }
}
