package brainfuck.language.interpreter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Enzo on 07/12/2016.
 */
public class Trace {
    private int stepNb = 0;
    private File backLog;
    FileWriter fw;

    /**
     * Constructeur de la classe TRACE
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
     * @param mPointer La position du pointeur dans la mémoire
     * @param snapShot La snapshot de la mémoire actuelle
     */

    public void tracerUpdate(int cursor, int mPointer, String snapShot) {
        try {
            fw.write("Execution step number = " + (stepNb + 1) + ", execution pointer position : " + (cursor+1)
                    + ", data pointer position : " + mPointer + ", Snapshot : " + snapShot);
            fw.write(System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Not implemented yet");
        }

        stepNb++;
    }

    /**
     * Permet de fermer les flux de donnée
     */
    public void end(){
        if(fw != null) {
            try {
                fw.close();
            } catch (IOException e) {
                System.out.println("Flux de donnée de la trace mal fermé.");
            }
        }
    }

    public void setStepNb(int stepNb) {
        this.stepNb = stepNb;
    }
}
