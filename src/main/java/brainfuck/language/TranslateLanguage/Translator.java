package brainfuck.language.TranslateLanguage;

import brainfuck.language.Main;
import brainfuck.language.enumerations.Keywords;
import brainfuck.language.function.Function;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Classe qui fait la traduction de brainfuck à java
 *
 * Created by Red_Panda on 03/01/2017.
 */
public class Translator {

    private File directory; // Le dossier ou on stocke la traduction
    private File main; // le fichier main du programme
    private File brainfuck; // le fichier secondaire avec le code à executer
    private String filename; // le non du fichier qui doit être traduit
    private Keywords previousKeyword = null; // Variable représentant le keyword avant celui que l'on analyse

    /**
     * Constructeur pour la traduction avec la creation d'un dossier au nom du fichier.bf, d'un main et du programme
     * @param filename le nom du fichier à traduire
     */
    public Translator(String filename) {

        this.filename=filename;
        directory = new File(filename);
        directory.mkdir();
        main = new File(filename+"/Main.java");
        brainfuck = new File(filename+"/Brainfuck.java");

    }

    public void writeFilesInitially(){
        try{
            FileWriter main = new FileWriter(this.main);
            FileWriter brainfuck = new FileWriter(this.brainfuck);
            main.write("package "+filename+";\n");
            main.write(BaseSyntax.MainFile.code);
            main.flush();
            brainfuck.write("package "+filename+";\n");
            brainfuck.write(BaseSyntax.BrainfuckFile.code);
            main.close();
            brainfuck.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void addFunction(Map<Integer, Function> functionMap){
        int counter;
        for (Map.Entry<Integer, Function> function : functionMap.entrySet()){ // Pour chaque fonction
            if (function.getValue().isProcedure()){ // Si c'est une procédure
                try{
                    FileWriter brainfuck = new FileWriter(this.brainfuck); // On essai d'écrire
                    if (function.getValue().hasParameter()){ // S'il y a des paramètres
                        brainfuck.write("public void " + function.getValue().getFunctionName()+ "(int pointeur){\n"); // On écrit les paramètres ( ce sera toujours le pointeur )
                        //Finalement on écrit le code correspondant aux instructions
                    }
                    else {
                        brainfuck.write("public void "+ function.getValue().getFunctionName()+ "(){");
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else {

            }
        }
    }
}
