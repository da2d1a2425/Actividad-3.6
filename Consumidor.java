public class Consumidor extends Thread {
    private static final int NUM_EJECUCIONES = 4;
    private String nombre;
    private MiBuffer buffer;

    public Consumidor(String nombre, MiBuffer buffer) {
        this.nombre = nombre;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        int contador = 0;
        while (++contador <= NUM_EJECUCIONES) {
            System.out.printf(
                    "#%d | [%s] Soy el consumidor y voy a intentar consumir del buffer\n", contador, nombre);
            synchronized (buffer) {
                System.out.printf("#%d | [%s] Buffer: %s\n", contador, nombre, buffer);
                while (buffer.estaVacio()) {
                    try {
                        buffer.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.printf(
                        "#%d | [%s] Soy el consumidor y he extraido el valor %d del buffer\n",
                        contador, nombre, buffer.extraer());
                System.out.printf("#%d |  [%s] Buffer: %s\n", contador, nombre, buffer);
            }
        }
    }
}
