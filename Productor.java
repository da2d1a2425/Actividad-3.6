public class Productor extends Thread {
    private static final int NUM_EJECUCIONES = 12;
    private String nombre;
    private MiBuffer buffer;

    public Productor(String nombre, MiBuffer buffer) {
        this.nombre = nombre;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        int contador = 0;
        while (++contador <= NUM_EJECUCIONES) {
            System.out.printf("#%d | [%s] Soy el productor y voy a intentar escribir en el buffer\n", contador, nombre);
            synchronized (buffer) {
                System.out.printf("#%d | [%s] Buffer: %s\n", contador, nombre, buffer);
                while (buffer.estaLleno()) {
                    try {
                        buffer.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                buffer.agregar(1);
                System.out.printf("#%d | Soy el productor y he agregado el numero %d", contador, 1);
                System.out.printf("#%d | [%s] Buffer: %s\n", contador, nombre, buffer);
            }
        }
    }
}
