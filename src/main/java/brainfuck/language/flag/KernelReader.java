package brainfuck.language.flag;


import brainfuck.language.exceptions.FilePathNotFoundException;
import brainfuck.language.exceptions.IncompatibleFlagsException;
import brainfuck.language.exceptions.MainFlagNotFoundException;

import java.io.File;
import java.nio.file.InvalidPathException;
import java.util.EnumMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;


/**
 * Classe permettant de lire les instructions reçues dans la console, ainsi que de les interpreter dans notre programme
 *
 * @author  Red_Panda
 */
public class KernelReader {
    private Map<Flags, Boolean> flagMap;
    private Map<Flags, String> filePathMap;
    private List<String> flagList;

    public KernelReader(List<String> flagList) {
        this.flagMap = new EnumMap(Flags.class);
        this.filePathMap = new EnumMap(Flags.class);
        this.flagList = flagList;

        for(Flags flags : Flags.values()) { // remplie flagMap avec tous les flags existants
            this.flagMap.put(flags, false);
            if(flags.isNeedAFilePath()) // dans le cas le flag a besoin d'un chemin d'accès
                this.filePathMap.put(flags, null);
        }
    }

    /**
     * Permet d'identifier les flags dans une liste. Si un flag est reconnu, on le met sur vrai dans le flagMap
     */
    public void identifyFlag() {
        for(String flag : flagList) {
            for(Flags flagsTag : Flags.values()) {
                if(flag.equals(flagsTag.getFlag())) {
                    this.flagMap.put(flagsTag, true);
                }
            }
        }
    }

    /**
     * Identifie le chemin d'accès derrière les flags nécessitant un chemin d'accès
     */
    public void identifyFilePathForSpecificFlag() throws FilePathNotFoundException {
        String flag;
        String path;
        for(int i = 0; i < flagList.size(); i++) {
            flag = flagList.get(i);
            if(flag.equals(Flags.FILE_TO_READ.getFlag()) || flag.equals(Flags.IN.getFlag()) || flag.equals(Flags.OUT.getFlag())) {
                try {
                    path = flagList.get(i + 1);
                }catch(IndexOutOfBoundsException e){
                    throw new FilePathNotFoundException(flagList.get(i));
                }

                if(isValidPath(path))
                    this.filePathMap.put(Flags.fromFlagToEnum(flag), path);
                else {
                    throw new InvalidPathException(path, "Invalid path ");
                }
            }
        }
    }

    /**
     * Vérifie que la liste des flags de contient pas de faux flags
     * @return vrai si tous le flags existent SINON faux
     */
    public void verifyIfAllFlagsAreValid() throws IncompatibleFlagsException {
        List<String> list = flagList;
        for(Flags flags : Flags.values()) { // si un flag dans la liste des flags n'existe pas, il restera dans la liste
            if(list.contains(flags.getFlag())) {
                list.remove(flags.getFlag());
            }
        }

        // list contient uniquement les potentiels chemins d'accès
        ListIterator<String> listIt = list.listIterator();

        while (listIt.hasNext()) { // vérifie que les chemins d'accès sont valides
            if(isValidPath(listIt.next()))
                listIt.remove();
        }

        listIt.forEachRemaining(list::add);
        if (!list.isEmpty())
            throw new IncompatibleFlagsException();
    }

    /**
     * Vérifie que les flags nécessaires au fonctionnement du programme soit présent (-p, chemin d'accès)
     * @return vrai si tous les flags nécessaire SINON faux
     */
    public void goodFlag() throws MainFlagNotFoundException, FilePathNotFoundException {
        if(!hasPFlag()) {
            throw new MainFlagNotFoundException();
        }

        for(Flags flags : Flags.values()) {
            // Si un flag avec chemin d'accès on vérifie que ce dernier est correct
            if(flagMap.get(flags) && filePathMap.containsKey(flags) && filePathMap.get(flags).equals(null))
                throw new FilePathNotFoundException("Filepath for " + flags.getFlag() + " flag not found.");
        }
    }

    /**
     * Vérifie si un chemin d'accès donné est bien un chemin d'accès valide
     * @param path le chemin d'accès
     * @return vrai si chemin d'accès valide SINON faux
     */
    private boolean isValidPath(String path) {
        File file = new File(path);
        return file.exists() && file.canRead() && file.canWrite() && file.isFile();
    }

    /**
     * Regarde si le flag -p est là
     * @return vrai si le -p est un flag reçu SINON faux
     */
    private boolean hasPFlag() {
        return this.filePathMap.get(Flags.FILE_TO_READ) != null;
    }

    public Map<Flags, Boolean> getFlagMap() {
        return flagMap;
    }

    public String getFilePath(Flags flags) {
        return filePathMap.get(flags);
    }

    /**
     * Dis si un flag est présent
     * @param flags un flag à vérifier
     * @return vrai si le flag est là
     */
    public boolean getFlag(Flags flags) {
        return flagMap.get(flags);
    }

    public Map<Flags, String> getFilePathMap() {
        return filePathMap;
    }

    public List<String> getFlagList() {
        return flagList;
    }

    public void setFlagList(List<String> flagList) {
        this.flagList = flagList;
    }
}