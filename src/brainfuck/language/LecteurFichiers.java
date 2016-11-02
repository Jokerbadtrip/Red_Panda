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
import java.util.Scanner;

public class LecteurFichiers {

    private File file;
    private Scanner input;

    /**
     * Scans the file given to the scanner
     * @return an array list of each characters read
     */
    public String decodeur(String filepath) throws FileNotFoundException{
    	
    	file=new File(filepath);
    	input= new Scanner(file);
    	String chaine ="";
        while (input.hasNext()){
            chaine += input.next();
        }
        return (chaine);
    }

    /**
     * Checks if the file is empty
     * @param scanner the current scanner
     * @return true if the file is empty
     */
    public boolean isEmpty(){
    	return (input.equals(null));
    }
}
