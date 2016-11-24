import brainfuck.language.Metrics;
import brainfuck.language.Motor;


public class Main {

    public static void main(String[] args) {
        String[] arg = { "-p", "/home/jamatofu/Bureau/PRESENTAION_BF 2/Boucle.bf"};
        long beginTime = System.currentTimeMillis();


        Motor motor = new Motor(arg);
        motor.lancerProgramme();

        long endTime = System.currentTimeMillis();
        Metrics.EXEC_TIME = endTime - beginTime;
        Metrics.displayMetrics();
    }
}
