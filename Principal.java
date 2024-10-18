import java.lang.reflect.Array;
import java.util.ArrayList;

public class Principal {
    public static void main(String[] args) {
        MiBuffer buffer = new MiBuffer(4);

        Productor p1 = new Productor("PRODUCTOR", buffer);

        ArrayList<Consumidor> consumidores = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            consumidores.add(new Consumidor("CONSUMIDOR_" + i, buffer));
        }

        // Iniciamos los hilos
        p1.start();             // Productor
        for (Consumidor consumidor : consumidores) {
            consumidor.start(); // Consumidores
        }


    }
}
