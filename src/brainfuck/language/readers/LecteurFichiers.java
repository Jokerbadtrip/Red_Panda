package brainfuck.language.readers;


/**
 * Cette classe permet de crée un lecteur de fichier, de lire le fichier voulu et de savoir si ce dernier est vide
 *
 * @author  Red_Panda
 **/

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class LecteurFichiers {

    private boolean isEmpty; // boolean pour dire si le fichier est vide ou non
    /**
     * Analyse le fichier donné dans le lecteur
     * @return La liste de toutes les commandes trouvées
     */
    public String reader(String filepath) throws FileNotFoundException{
        isEmpty = false;
        File file = new File(filepath);
        if (file.exists() && !file.isDirectory()) {
            Scanner input = new Scanner(file);
            String chaine = "";

            if (file.length() == 0) isEmpty = true;


            while (input.hasNextLine()) {
                chaine += input.nextLine();
                chaine += "\n";
            }
            return (chaine);
        }
        else throw new FileNotFoundException();
    }

    /**
     * Permet d'écrire dans un fichier texte. Si le fichier contient déjà du texte, celui-ci sera écrasé
     *
     * @param filepath le chemin vers le fichier
     * @param textToWrite le texte à stocker dans le fichier
     * @throws FileNotFoundException si on ne trouve pas le fichier
     */
    public void write(String filepath, String textToWrite) throws FileNotFoundException {
        File file = new File(filepath);
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(textToWrite);
            fileWriter.close();
        } catch (IOException e) { }


    }

    public boolean isEmpty() {
        return isEmpty;
    }
}
