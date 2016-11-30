import brainfuck.language.Macro;

public class Main {

    public static void main(String[] args) {
/*        String[] arg = { "-p", "/home/jamatofu/Bureau/PRESENTAION_BF 2/Boucle.bf"};


        long beginTime = System.currentTimeMillis();
        Motor motor = new Motor(arg);
        motor.lancerProgramme();

        long endTime = System.currentTimeMillis();
        Metrics.EXEC_TIME = endTime - beginTime;
        Metrics.displayMetrics();*/

        Macro macro = new Macro("@Clement +\n" +
                "@Simon() Clement\n" +
                "Simon(5)");

        System.out.println(macro.readMacro());
    }
}
