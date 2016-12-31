package brainfuck.language;

public class Main {

    public static void main(String[] args) {
        try {
            String[] arg = {"-p", "/home/jamatofu/Documents/Projet Red Panda/Red_Panda2/src/test/resources/prog.bf"};
            Motor motor = new Motor(arg);
            motor.lancerProgramme();
        }
        catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }
    }
}
