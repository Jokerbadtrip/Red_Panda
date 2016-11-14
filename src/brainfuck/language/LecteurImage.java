package brainfuck.language;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static java.lang.Math.*;

// Google Java

/**
 * @author jamatofu on 09/11/16.
 */
public class LecteurImage {

    /**
     * Renvoie une liste de commande contenues dans une image
     * @param filename le nom de l'image
     * @return la liste des commandes
     * @throws IOException
     */
    public ArrayList<String> read(String filename) throws IOException {
        ArrayList<String> commandes = new ArrayList<>();
        File file = new File(filename);
        BufferedImage image = ImageIO.read(file);

        for(int y = 0; y < image.getWidth(); y += 3) {
            for(int x = 0; x < image.getHeight(); x += 3) {
                int clr = image.getRGB(x, y);
                int  red   = (clr & 0x00ff0000) >> 16;
                int  green = (clr & 0x0000ff00) >> 8;
                int  blue  =  clr & 0x000000ff;
                String hexColor = String.format("#%02x%02x%02x", red, green, blue);

                switch (hexColor) {
                    case "#ffffff":
                        commandes.add("+");
                        break;
                    case "#4b0082":
                        commandes.add("-");
                        break;
                    case "#9400d3":
                        commandes.add("<");
                        break;
                    case "#0000ff":
                        commandes.add(">");
                        break;
                    case "#00ff00":
                        commandes.add(".");
                        break;
                    case "#ffff00":
                        commandes.add(",");
                        break;
                    case "#ff7f00":
                        commandes.add("[");
                        break;
                }
            }
        }
        return commandes;
    }

    /**
     * Dessine une image contenant une code couleur des commandes
     * @param commandes la liste des commandes à traduire
     * @param nameImage le nom de l'image sortante
     */
    public void translateFromShortcutToImage(ArrayList<String> commandes, String nameImage) {
        int tailleImage = (int) ceil(sqrt(commandes.size()));
        BufferedImage img = new BufferedImage(tailleImage * 3, tailleImage * 3, BufferedImage.TYPE_INT_RGB);
        int i = 0;

        for(int y = 0; y < tailleImage * 3; y += 3) {
            for(int x = 0; x < tailleImage * 3; x += 3) {
                int color;
                String comm;

                try{
                    comm = commandes.get(i);
                }
                catch (IndexOutOfBoundsException e) {
                    comm = "";
                }

                switch (comm) {
                    case "+":
                        color = 0xffffff;
                        break;
                    case "-":
                        color = 0x4b0082;
                        break;
                    case "<":
                        color = 0x9400d3;
                        break;
                    case ">":
                        color = 0x0000ff;
                        break;
                    case ".":
                        color = 0x00ff00;
                        break;
                    case ",":
                        color = 0xffff00;
                        break;
                    case "[":
                        color = 0xff7f00;
                        break;
                    case "]":
                        color = 0xff0000;
                        break;
                    default:
                        color = 0x000000;
                        break;
                }

                img = drawSquare(img, x, y, color);
                i++;
            }
        }

        File outfile = new File(nameImage + ".bmp");
        try {
            ImageIO.write(img, "bmp",outfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage drawSquare(BufferedImage img, int x, int y, int color) {
        Color col = new Color(color);

        for(int j = y; j < 3 + y; j++) {
            for(int i = x; i < 3 + x; i++) {
                img.setRGB(i, j, col.getRGB());
            }
        }

        return img;
    }
}
