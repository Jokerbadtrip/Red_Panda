package brainfuck.language.flag.commande;

import brainfuck.language.enumerations.Keywords;
import brainfuck.language.exceptions.IsNotACommandException;
import brainfuck.language.readers.LecteurImage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static java.lang.Math.ceil;
import static java.lang.Math.sqrt;

/**
 * Permet de passer d'une liste de commande à une image
 * Permet d'exécuter la commande --translate
 * @author jamatofu on 30/12/16.
 */
public class Translate {
    private static final int WIDTH = LecteurImage.WIDTH_IMAGE;
    private static final int HEIGHT = LecteurImage.HEIGHT_IMAGE;
    private String filePath;
    private int tailleImage;


    public Translate(String filePath) {
        this.filePath = filePath.replace(".bf", ".bmp");
    }
    /**
     * Dessine une image contenant une code couleur des commandes
     * @param commandes la liste des commandes à traduire
     */
    public void translateFromShortcutToImage(List<Keywords> commandes) throws IsNotACommandException, IOException {
        tailleImage = (int) (3*(ceil(sqrt(commandes.size())))); // On calcule la taille de l'image n fonction du nombre d'instruction
        BufferedImage img = new BufferedImage(tailleImage, tailleImage, BufferedImage.TYPE_INT_RGB); // on crée une nouvelle image de la taille souhaitée
        int x = 0;
        int y = 0;

        for(Keywords keywords : commandes) {
            makeImage(keywords, img, x,y);
            if (x > tailleImage) {
                x = 0;
                y += HEIGHT;
            }
            else {
                x += WIDTH;
            }
        }

        createOutFile(img); // on crée l'image de sortie
    }

    /**
     * Crée un carré de pixel 3*3 de la couleur du keyword
     * @param keywords le keyword correspondant
     * @param img l'image que l'on veut remplir
     * @param x la coordonnée en abscisse du carré à remplir
     * @param y la coordonnée en ordonnée du carré à remplir
     */
    private void makeImage(Keywords keywords, BufferedImage img, int x, int y) throws IsNotACommandException {
        for (int i = x; i < x + WIDTH; i++){
            for (int j = y; j < y + HEIGHT; j++){
                if (i < tailleImage && j < tailleImage)
                    img.setRGB(i, j,Keywords.keywordToColor(keywords));
            }
        }
    }

    /**
     * Crée le fichier image de sortie
     * @param img l'image que l'on veut sortir en fichier
     */
    private void createOutFile(BufferedImage img) throws IOException{
        File outfile = new File(filePath);

        ImageIO.write(img, "bmp",outfile);
    }
}
