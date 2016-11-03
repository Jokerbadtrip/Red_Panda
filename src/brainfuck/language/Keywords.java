package brainfuck.language;

/**
 * Created by MSI on 03/11/2016.
 */
public enum Keywords {
    INCR("INCR","+", "color"),
    DECR("DECR","-","color"),
    LEFT("LEFT","<","color"),
    RIGHT("RIGHT",">","color"),
    OUT("OUT",".","color"),
    IN("IN",",","color"),
    JUMP("JUMP","[","color"),
    BACK("BACK","]","color");


    Keywords(String command, String shortcut, String color) { }



}
