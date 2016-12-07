package brainfuck.language.readers;

import brainfuck.language.enumerations.Keywords;
import brainfuck.language.exceptions.IsNotAValidColorException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static java.lang.Math.ceil;
import static java.lang.Math.sqrt;


/**
 * Classe permettant de lire dans un fichier ".bmp" les instructions qu'il contient, et de les envoyer à l'interpreteur
 *
 * @author  Red_Panda
 */
public class LecteurImage {


    private int x;
    private int y;
    private BufferedImage img;
    private int tailleImage;

    /**
     * Renvoie une liste de commande contenues dans une image
     * @param filename le nom de l'image
     * @return la liste des commandes
     * @throws IOException
     */
    public ArrayList<Keywords> read(String filename) throws Exception {
        ArrayList<Keywords> commandes = new ArrayList<>();
        File file = new File(filename);
        BufferedImage image = ImageIO.read(file);

        for(int y = 0; y < image.getWidth(); y += 3) {
            for(int x = 0; x < image.getHeight(); x += 3) {
                int clr = image.getRGB(x, y);
                int  red   = (clr & 0x00ff0000) >> 16;
                int  green = (clr & 0x0000ff00) >> 8;
                int  blue  =  clr & 0x000000ff;
                String hexColor = String.format("#%02x%02x%02x", red, green, blue);
                if (Keywords.isColor(hexColor)){
                    Keywords keywordsToAdd = Keywords.colorToKeyword(hexColor);
                    commandes.add(keywordsToAdd);
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
    public void translateFromShortcutToImage(ArrayList<Keywords> commandes, String nameImage) {
        tailleImage =(int) (3*ceil(sqrt(commandes.size())));

        x = 0;
        y = 0;
        img = new BufferedImage(tailleImage, tailleImage, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i<commandes.size(); i++){
            makeImage(commandes.get(i));
            incrementCoordinate();
        }
        File outfile = new File(nameImage + ".bmp");
        try {
            ImageIO.write(img, "bmp",outfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Crée un carré de pixel 3*3 de la couleur du keyword
     * @param keywords le keyword
     */
    private void makeImage(Keywords keywords) {
        for (int i=x; i<x+3; i++){
            for (int j=y; j<y+3; j++){
                if (i<tailleImage && j<tailleImage) img.setRGB(i,j,Keywords.keywordToColor(keywords));
            }
        }
    }

    /**
     * Incrémentation de la bonne coordonnées en fonction de la taille de l'image
     */
    private void incrementCoordinate(){
        if (x==tailleImage){
            x =0;
            y+=3;
        }
        else x+=3;
    }

}
