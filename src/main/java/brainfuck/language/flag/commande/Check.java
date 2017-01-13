package brainfuck.language.flag.commande;

import brainfuck.language.enumerations.Keywords;

import java.util.*;

/**
 * Permet de vérifier qu'un programme est bien "parenthèsé"
 * @author jamatofu on 29/12/16.
 */
public class Check {
    private Deque<Keywords> stack;
    private static final Map<Keywords, Keywords> brackets = new EnumMap<>(Keywords.class);
    static { // permet l'ajout de nouveau élément parenthèsant
        brackets.put(Keywords.JUMP, Keywords.BACK);
    }

    /**
     * Permet de vérifier qu'un programme est bien "parenthèsé"
     * @return vrai si bien parenthèsé SINON faux
     */
    public boolean isWellChecked(List<Keywords> keywordsList) {
        stack = new ArrayDeque<>();
        for(Keywords keywords : keywordsList) {
            if(Keywords.JUMP.equals(keywords)) {
                stack.addFirst(Keywords.JUMP);
            }
            else if(Keywords.BACK.equals(keywords)) {
                if(stack.isEmpty())
                    return false;
                else
                    closingBracket();
            }
        }
        return stack.isEmpty();
    }

    /**
     * Regarde si le dernier élément de la pile est une "bracket" ouvrante
     */
    private void closingBracket() {
        if (Keywords.JUMP.equals(stack.peekFirst()))
            stack.pop();
    }
}
