//package brainfuck.language;
//
//import brainfuck.language.enumerations.Keywords;
//import brainfuck.language.exceptions.IsNotACommandException;
//import brainfuck.language.readers.LecteurTextuel;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.Assert.assertEquals;
//
///**
// * @author jamatofu on 07/12/16.
// */
//public class LecteurTextuelTest {
//    LecteurTextuel lecteurTextuel;
//    @Before
//    public void setUp() throws Exception {
//        lecteurTextuel = new LecteurTextuel("/HOME", true);
//    }
//
//    @Test
//    public void creeTableauCommande() throws Exception {
//        lecteurTextuel.setProgram("INCR\n+\nDECR\n[\n]\nBACK\nIN\n.\nOUT\n,\nJUMP\n<>\nLEFT\nRIGHT\n-");
//        ArrayList<Keywords> listeTest = new ArrayList<>();
//        listeTest.add(Keywords.INCR);
//        listeTest.add(Keywords.INCR);
//        listeTest.add(Keywords.DECR);
//        listeTest.add(Keywords.JUMP);
//        listeTest.add(Keywords.BACK);
//        listeTest.add(Keywords.BACK);
//        listeTest.add(Keywords.IN);
//        listeTest.add(Keywords.OUT);
//        listeTest.add(Keywords.OUT);
//        listeTest.add(Keywords.IN);
//        listeTest.add(Keywords.JUMP);
//        listeTest.add(Keywords.LEFT);
//        listeTest.add(Keywords.RIGHT);
//        listeTest.add(Keywords.LEFT);
//        listeTest.add(Keywords.RIGHT);
//        listeTest.add(Keywords.DECR);
//        assertEquals(listeTest, lecteurTextuel.creeTableauCommande());
//    }
//
//    @Test
//    public void removeCommentary() throws Exception {
//        assertEquals("INCRINCRALALA", lecteurTextuel.removeCommentary("INCRINCR#bonjour tout le monde!\nALALA#####plusieurs dieses\n"));
//        assertEquals("INCRINCR  ALALA", lecteurTextuel.removeCommentary("INCRINCR  #bonjour tout le monde!\nALALA#####plusieurs dieses\n"));
//
//    }
//
//    @Test(expected = IsNotACommandException.class)
//    public void inTheCaseOfShortcut() throws IsNotACommandException{
//        String line = "+-.,[]<>";
//        List<Keywords> result = new ArrayList<>();
//        result.add(Keywords.INCR);
//        result.add(Keywords.DECR);
//        result.add(Keywords.OUT);
//        result.add(Keywords.IN);
//        result.add(Keywords.JUMP);
//        result.add(Keywords.BACK);
//        result.add(Keywords.LEFT);
//        result.add(Keywords.RIGHT);
//
//        assertEquals(result, lecteurTextuel.inTheCaseOfShortcut(line));
//
//        line += "{";
//        assertEquals(result, lecteurTextuel.inTheCaseOfShortcut(line));
//    }
//
//}