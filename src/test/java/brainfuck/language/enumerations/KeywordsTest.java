package brainfuck.language.enumerations;

import brainfuck.language.exceptions.KeywordsConversionException;
import org.junit.Test;

import java.security.Key;

import static org.junit.Assert.*;

/**
 * Created by Enzo on 04/01/2017.
 */
public class KeywordsTest {
    @Test
    public void isWord() throws Exception {
        assertEquals(true, Keywords.isWord("JUMP"));
        assertEquals(false, Keywords.isWord("jump"));
        assertEquals(false, Keywords.isWord("NotAKeyword"));
        assertEquals(false, Keywords.isWord("LEF"));
    }

    @Test
    public void isShortcut() throws Exception {
        assertEquals(true, Keywords.isShortcut('+'));
        assertEquals(false, Keywords.isShortcut('*'));
        assertEquals(false, Keywords.isShortcut('A'));
    }

    @Test
    public void isColor() throws Exception {
        assertEquals(true, Keywords.isColor("#ffffff"));
        assertEquals(false, Keywords.isColor("#00ff01"));
        assertEquals(false, Keywords.isColor("#FFFFFF"));
    }

    @Test (expected = KeywordsConversionException.class)
    public void shortcutToKeyword() throws Exception {
        assertEquals(Keywords.INCR, Keywords.shortcutToKeyword('+'));
        Keywords.shortcutToKeyword('&');
    }

    @Test (expected = KeywordsConversionException.class)
    public void colorToKeyword() throws Exception {
        assertEquals(Keywords.INCR, Keywords.colorToKeyword("#ffffff"));
        Keywords.colorToKeyword("#00ff01");
    }

    @Test
    public void keywordToColor() throws Exception {
        assertEquals(Integer.decode("#ffffff").intValue(), Keywords.keywordToColor(Keywords.INCR));
    }

    @Test
    public void isSimpleKeyword(){
        assertEquals(true, Keywords.isSimpleKeyword(Keywords.INCR));
        assertEquals(false, Keywords.isSimpleKeyword(Keywords.IN));
    }
}