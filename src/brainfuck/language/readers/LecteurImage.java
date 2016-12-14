package brainfuck.language.readers;

import brainfuck.language.enumerations.Keywords;

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
                int  clr = image.getRGB(x, y);
                int  red   = (clr & 0x00ff0000) >> 16;
                int  green = (clr & 0x0000ff00) >> 8;
                int  blue  =  clr & 0x000000ff;
                String hexColor = String.format("#%02x%02x%02x", red, green, blue);
                if (Keywords.isColor(hexColor)){
                    Keywords keywordsToAdd = Keywords.colorToKeyword(hexColor);
                    if (keywordsToAdd!=null) commandes.add(keywordsToAdd);
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

        int tailleImage =(int) (3*(ceil(sqrt(commandes.size()))+1)); // On calcule la taille de l'image n fonction du nombre d'instruction
        int x = 0;
        int y = 0;
        BufferedImage img = new BufferedImage(tailleImage, tailleImage, BufferedImage.TYPE_INT_RGB); // on crée une nouvelle image de la taille souhaitée



        for (int i = 0; i<commandes.size(); i++){
            if (x+3 >=tailleImage || x+2 >=tailleImage || x+1 >=tailleImage || x==tailleImage){
                x=0;
                y+=3;
            }


            makeImage(commandes.get(i), img, x, y, tailleImage); // On rempli l'image de couleurs des instructions

            if (x==tailleImage){ // Si une ligne est remplie, on passe à la ligne suivante
                x =0;
                y+=3;
            }
            else x+=3;
        }

        createOutFile(nameImage, img); // on crée l'image de sortie
    }

    /**
     * Crée un carré de pixel 3*3 de la couleur du keyword
     * @param keywords le keyword correspondant
     * @param img l'image que l'on veut remplir
     * @param x la coordonnée en abscisse du carré à remplir
     * @param y la coordonnée en ordonnée du carré à remplir
     * @param tailleImage la taille de l'image
     */
    private void makeImage(Keywords keywords, BufferedImage img, int x, int y, int tailleImage) {

        for (int i=x; i<x+3; i++){
            for (int j=y; j<y+3; j++){
                if (i<tailleImage && j<tailleImage) img.setRGB(i, j,Keywords.keywordToColor(keywords));
            }
        }
    }

    /**
     * Crée le fichier image de sortie
     * @param name le nom de l'image
     * @param img l'image que l'on veut sortir en fichier
     */
    private void createOutFile(String name, BufferedImage img){

        File outfile = new File(name + ".bmp");

        try { ImageIO.write(img, "bmp",outfile); } catch (IOException e) { e.printStackTrace(); }
    }

}
