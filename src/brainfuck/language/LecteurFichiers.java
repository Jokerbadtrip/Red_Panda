package brainfuck.language;


/**
 *
 * This class creates a new file reader, can scan the given file to the reader and checks if the file is empty
 * @author SERRANO Simon
 * @version 1.0
 *
 **/

import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class LecteurFichiers {

    /**
     * Scans the file given to the scanner
     * @return an array list of each characters read
     */
    public String decodeur(String filepath) throws FileNotFoundException{

        File file = new File(filepath);
        Scanner input = new Scanner(file);
        String chaine = null;

        while (input.hasNextLine()){
            chaine += input.nextLine();
        }
        return (chaine);
    }

    public void write(String filepath, String textToWrite) throws FileNotFoundException {
        File file = new File(filepath);

        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(textToWrite);
            fileWriter.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
