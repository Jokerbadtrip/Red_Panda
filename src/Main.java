import brainfuck.language.*;
import brainfuck.language.readers.KernelReader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


public class Main {

    public static void main(String[] args) {


        String[] arg  ={"-p", "D:\\SOPHIA ANTIPOLIS CYCLE INGENIEUR\\SI3\\Projet Red_Panda\\bfck.bf", "--rewrite"};
        Motor motor = new Motor(arg);
        motor.lancerProgramme();



    }
}
