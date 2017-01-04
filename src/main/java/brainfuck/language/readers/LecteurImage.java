package brainfuck.language.readers;

import brainfuck.language.enumerations.Keywords;
import brainfuck.language.exceptions.IsNotAValidColorException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Classe permettant de lire dans un fichier ".bmp" les instructions qu'il contient, et de les envoyer à l'interpreteur
 *
 * @author  Red_Panda
 */
public class LecteurImage {
    public static final int WIDTH_IMAGE = 3;
    public static final int HEIGHT_IMAGE = 3;

    /**
     * Renvoie une liste de commande contenues dans une image
     * @param filename le nom de l'image
     * @return la liste des commandes
     * @throws IOException
     */
    public List<Keywords> read(String filename) throws IsNotAValidColorException {
        List<Keywords> commandes = new ArrayList<>();
        File file = new File(filename);
        BufferedImage image;
        Color color;

        try {
            image = ImageIO.read(file);

            for(int y = 0; y < image.getWidth(); y += WIDTH_IMAGE) {
                for(int x = 0; x < image.getHeight(); x += HEIGHT_IMAGE) {
                    color = new Color(image.getRGB(x, y));

                    String hexColor = String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());

                    if (Keywords.isColor(hexColor) && isAnUniColorSquare(y, x, image)){
                        Keywords keywordsToAdd = Keywords.colorToKeyword(hexColor);
                        if (keywordsToAdd != null)
                            commandes.add(keywordsToAdd);
                    }
                }
            }
        } catch (IOException ex) {
            System.out.println("The file can't be read.");
        }

        return commandes;
    }

    /**
     * Vérifie qu'un carré contient une seule et unique couleur
     * @param x coordonnée x
     * @param y coordonnée y
     * @return vrai si unicolore
     */
    public boolean isAnUniColorSquare(int x, int y, BufferedImage image) {
        int color = image.getRGB(x, y);
        for(int i = x; i < x + WIDTH_IMAGE; i++) {
            for(int j = y; j < y + HEIGHT_IMAGE; j++) {
                if(color != image.getRGB(i, j))
                    return false;
            }
        }

        return true;
    }
}
