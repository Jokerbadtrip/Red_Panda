import brainfuck.language.Metrics;
import brainfuck.language.Motor;



/**
 * Created by user on 30/11/16.
 */
public class Main {

    public static void main(String[] args){
        long beginTime = System.currentTimeMillis();


            Motor motor = new Motor(args);
            motor.lancerProgramme();



        long endTime = System.currentTimeMillis();
        Metrics.EXEC_TIME = endTime - beginTime;
        Metrics.displayMetrics();
    }

}
