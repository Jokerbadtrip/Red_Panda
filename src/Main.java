import brainfuck.language.enumerations.Keywords;
import brainfuck.language.readers.LecteurImage;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        String[] arg = { "-p", "/home/jamatofu/Bureau/PRESENTAION_BF 2/Boucle.bf"};
        ArrayList<Keywords> liste = new ArrayList<>();
        liste.add(Keywords.INCR);
        liste.add(Keywords.INCR);
        liste.add(Keywords.INCR);
        liste.add(Keywords.DECR);
        liste.add(Keywords.LEFT);
        LecteurImage lecteurImage = new LecteurImage();
        lecteurImage.translateFromShortcutToImage(liste, "test");
//        Motor motor = new Motor(arg);
//        motor.lancerProgramme();
//        Keywords.INCR.name();
//        Metrics.displayMetrics();

    }
}
