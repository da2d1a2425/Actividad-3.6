import java.util.Arrays;

public class MiBuffer {
    private static final int MIN = 1;  // Longitud minima del buffer
    private int[] array;       // Longitud >= 1
    private int posLectura;
    private int posEscritura;

    public MiBuffer(int longitud) {
        crearArray(longitud);
        posLectura = 0;
        posEscritura = 0;
    }

    private void crearArray(int longitud) {
        if (longitud < MIN) {
            throw new IllegalArgumentException(
                    "La longitud debe ser >= %s [longitud = %d".formatted(MIN, longitud));
        }
       this.array = new int[longitud];
    }

    public void agregar(int numero) {
       array[posEscritura++] = numero;
    }

    public int extraer() {
        int aux = array[posLectura];
        array[posLectura++] = 0;
        return aux;
    }

    public boolean estaLleno() {
        for (int i : array) {
            if (i == 0)
                return false;
        }
        return true;
    }

    public boolean estaVacio() {
       for (int i : array) {
           if (i != 0)
               return false;

       }
       return true;
    }

    @Override
    public String toString() {
        return "Array=%s posEscritura=%d posLectura=%d".
                formatted(Arrays.toString(array), posEscritura, posLectura);
    }
}
