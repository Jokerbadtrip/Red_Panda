package brainfuck.language.readers;

import brainfuck.language.enumerations.Keywords;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author jamatofu on 04/01/17.
 */
public class LecteurImageTest {
    LecteurImage lecteurImage = new LecteurImage();
    BufferedImage testImg;

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Before
    public void setUp() throws Exception {
        testImg = new BufferedImage(3,3, BufferedImage.TYPE_INT_RGB);
        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 3; j++)
                testImg.setRGB(i, j, Color.WHITE.getRGB());

        File file = new File(temporaryFolder.getRoot().getAbsolutePath() + "/img.bmp");
        ImageIO.write(testImg, "bmp", file);
    }

    @Test
    public void read() throws Exception {
        assertEquals(new ArrayList<Keywords>(), lecteurImage.read("img.bmp"));
        List<Keywords> list = Arrays.asList(new Keywords[]{Keywords.INCR});

        assertEquals(list, lecteurImage.read(temporaryFolder.getRoot().getAbsolutePath() + "/img.bmp"));
    }

    @Test
    public void isAnUniColorSquare() {
        assertTrue(lecteurImage.isAnUniColorSquare(0,0, testImg));
        testImg.setRGB(0,0, Color.BLACK.getRGB());
        assertFalse(lecteurImage.isAnUniColorSquare(0,0, testImg));
    }

}