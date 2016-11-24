package brainfuck.language.enumerations;


/**
 * Created by Serrano Simon on 03/11/2016.
 */
public enum Keywords {
    INCR("INCR", "+", "#ffffff"),
    DECR("DECR", "-", "#4b0082"),
    LEFT("LEFT", "<", "#9400d3"),
    RIGHT("RIGHT", ">", "#0000ff"),
    OUT("OUT", ".", "#00ff00"),
    IN("IN", ",", "#ffff00"),
    JUMP("JUMP", "[", "#ff7f00"),
    BACK("BACK", "]", "#ff0000");

    private String word;
    private String shortcut;
    private String color;

    Keywords(String word, String shortcut, String color) {
        this.word = word;
        this.shortcut = shortcut;
        this.color = color;
    }


    /**
     * transforms a String into a keyword
     * @param keyword the given keyword
     * @return the associated keyword
     */
    public static Keywords toKeyword(String keyword){
        for(Keywords value : values()) {
            if (value.getWord().equals(keyword) || value.getShortcut().equals(keyword) || value.getColor().equals(keyword)) {
                return valueOf(value.getWord());
            }
        }

        return null;
    }

    public String getWord() { return this.word; }
    public String getShortcut() { return this.shortcut; }
    public String getColor() { return this.color; }



}
