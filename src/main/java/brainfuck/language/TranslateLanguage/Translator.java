package brainfuck.language.TranslateLanguage;


import brainfuck.language.enumerations.Keywords;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;



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

    /**
     * procédure qui écrit dans les fichiers initiallement pour avoir le code de base
     */
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


    public void writeLaunchMethod(short[] memory) throws IOException {
        FileWriter brainfuck = new FileWriter(this.brainfuck);
        brainfuck.write(BaseSyntax.LaunchMethod.code);
        for (int i = 0; i<memory.length; i++){
            if (memory[i] != 0){
                brainfuck.write("memory["+i+"]="+memory[i]+";\n");
            }
        }
        brainfuck.write(BaseSyntax.CloseFile.code);
        brainfuck.flush();
        brainfuck.close();
    }








}
