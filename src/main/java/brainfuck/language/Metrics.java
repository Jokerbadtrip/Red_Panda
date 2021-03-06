package brainfuck.language;

/**
 *
 * Contient toutes les métriques du programme
 * @author  Red_Panda
 */
public class Metrics {
    public static int PROC_SIZE;
    public static long EXEC_TIME; // millisecond
    public static long START_TIME; // millisecond
    public static int EXEC_MOVE;
    public static int DATA_MOVE;
    public static int DATA_WRITE;
    public static int DATA_READ;

    private Metrics() {
        throw new UnsupportedOperationException();
    }

    /**
     * Permet d'afficher dans la console les différents metrics
     */

    public static void displayMetrics() {
        System.out.println("Taille du programme : " + PROC_SIZE);
        System.out.println("Temps d'exécution : " + EXEC_TIME + "ms");
        System.out.println("Nombre de fois que le pointeur d'exécution a bougé : "  + EXEC_MOVE);
        System.out.println("Nombre de fois que le pointeur de mémoire a bougé : "  + DATA_MOVE);
        System.out.println("Nombre de fois que la mémoire a été modifiée : " + DATA_WRITE);
        System.out.println("Nombre de fois que la mémoire a été lue : " + DATA_READ);
    }

    public static void execTime(long endTime) {
        EXEC_TIME = endTime - START_TIME;
    }
}
