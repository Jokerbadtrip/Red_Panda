package brainfuck.language;

/**
 * Created by Serrano Simon on 03/11/2016.
 */
public enum Keywords {
    INCR("+"),
    DECR("-"),
    LEFT("<"),
    RIGHT(">"),
    OUT("."),
    IN(","),
    JUMP("["),
    BACK("]");


    Keywords(String shortcut) { }




}
