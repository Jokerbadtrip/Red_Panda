package brainfuck.language.exceptions;

/**
 * Exception pour la commande IN. Si on rentre une entrée
 * @author jamatofu on 01/11/16.
 */
public class WrongInput extends RuntimeException {


    public WrongInput() { }

    @Override
    public String toString(){return "Donnée rentrée incorrecte.";}
}