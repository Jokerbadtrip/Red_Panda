import brainfuck.language.*;
import brainfuck.language.readers.KernelReader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


public class Main {

    public static void main(String[] args) {

        Date date = new Date();
        String[] arg  ={"-p", "/home/jamatofu/Bureau/PRESENTATION_PROJET/Demonstration/demoLEFT.bf", "--rewrite", "-o", "/home/jamatofu/Bureau/PRESENTATION_PROJET/Demonstration/demoVIDE.bf"};
        long beginTime = date.getTime();

        String[] arg  ={"-p", "D:\\SOPHIA ANTIPOLIS CYCLE INGENIEUR\\SI3\\Projet Red_Panda\\bfck.bf", "--rewrite"};
        Motor motor = new Motor(arg);
        motor.lancerProgramme();

        long endTime = date.getTime();


    }
}
