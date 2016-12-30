package brainfuck.language;

public class Main {

    public static void main(String[] args) {
        try {
            Motor motor = new Motor(args);
            motor.lancerProgramme();
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
