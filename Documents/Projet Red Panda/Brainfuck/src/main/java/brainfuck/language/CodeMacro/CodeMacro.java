package brainfuck.language.CodeMacro;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Enzo on 07/12/2016.
 */
public class CodeMacro {

    private String name;
    private List<CodeMacro> macroCMDansProg = new ArrayList<CodeMacro>();

    public CodeMacro(String name){
        this.name = name;
    }

    public String run(){
        //TODO
        return "";
    }

    private void recenserCM(String texte){
        //A VOIR COMMENT FAIRE, SI LA LISTE EXISTE DEJA OU PAS
        //switch + creation d'objet ?
    }

    public String RemplacerCMParCode(String texte){
        recenserCM(texte);
        for (CodeMacro cm: macroCMDansProg
                ) {
            texte.replaceAll(cm.name, cm.run());
        }
        return texte;
    }
}
