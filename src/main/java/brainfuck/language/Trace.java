package brainfuck.language;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Enzo on 07/12/2016.
 */
public class Trace {

    private File backLog;
    FileWriter fw;

    /**
     * Constructeur de la classe Trace
     *
     * @param nomFichier Le nom du fichier dans lequel nous voulons ecrire les logs
     */

    public Trace(String nomFichier) {
        backLog = new File(nomFichier + ".log");
        try {
            fw = new FileWriter(backLog);
        } catch (IOException e) {
            System.out.println("Not implemented yet");
        }
    }

    /**
     * Permet d'écrire dans le fichier .log correspondant au fichier d'entrée, si l'option a été écrite
     * dans la console, différentes données sur l'opération actuelle
     *
     * @param posExePointer La position du pointeur d'execution
     * @param stepNb Le nombre total d'étape qui ont été éxécuté depuis le début de l'interpretation
     * @param mPointer La position du pointeur dans la mémoire
     * @param snapShot La snapshot de la mémoire actuelle
     */

    public void tracerUpdate(int stepNb, int posExePointer, int mPointer, String snapShot) {
        try {
            fw.write("Execution step number = " + (stepNb + 1) + ", execution pointer position : " + (posExePointer+1)
                    + ", data pointer position : " + mPointer + ", Snapshot : " + snapShot);
            fw.write("\r\n");
        } catch (IOException e) {
            System.out.println("Not implemented yet");
        }
    }

    public void end(){
        if(fw != null) {
            try {
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
