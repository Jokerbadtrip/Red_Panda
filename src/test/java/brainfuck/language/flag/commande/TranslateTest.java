package brainfuck.language.flag.commande;

import brainfuck.language.enumerations.Keywords;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author jamatofu on 04/01/17.
 */
public class TranslateTest {
    Translate translate;

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Before
    public void setUp() {
        String filepath = temporaryFolder.getRoot().getAbsolutePath() + "/file.bf";
        translate = new Translate(filepath);
    }

    @Test
    public void Translate() {
        assertEquals(temporaryFolder.getRoot().getAbsolutePath() + "/file.bmp", translate.getFilePath());
    }

    @Test
    public void translateFromShortcutToImage() throws Exception {
        List<Keywords> keywordsList = Arrays.asList(new Keywords[] {Keywords.INCR, Keywords.INCR});
        BufferedImage imgOut = new BufferedImage(6, 3, BufferedImage.TYPE_INT_RGB);

        translate.translateFromShortcutToImage(keywordsList);
        assertEquals(6, translate.getTailleImage());

        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 3; j++)
                imgOut.setRGB(i, j, new Color(Keywords.keywordToColor(Keywords.INCR)).getRGB());

        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 3; j++)
                imgOut.setRGB(3+ i, j, new Color(Keywords.keywordToColor(Keywords.INCR)).getRGB());

        File file = new File(temporaryFolder.getRoot().getAbsolutePath() + "/file.bmp");
        BufferedImage imgToTest = ImageIO.read(file);

        for(int i = 0; i < 6; i++)
            for(int j = 0; j < 3; j++)
                assertEquals(imgOut.getRGB(i, j), imgToTest.getRGB(i, j));
    }

}