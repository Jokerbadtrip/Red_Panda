import brainfuck.language.Metrics;
import brainfuck.language.Motor;

import java.util.Date;


public class Main {

    public static void main(String[] args) {

        Date date = new Date();
        String[] arg  ={"-p", "/home/jamatofu/Bureau/PRESENTATION_PROJET/Demonstration/demoLEFT.bf", "--rewrite", "-o", "/home/jamatofu/Bureau/PRESENTATION_PROJET/Demonstration/demoVIDE.bf"};
        long beginTime = date.getTime();

        Motor motor = new Motor(arg);
        motor.lancerProgramme();

        long endTime = date.getTime();

        Metrics.displayMetrics();
    }
}
