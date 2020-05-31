/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circuitikztool;

/**
 *
 * @author James
 */
public class preferenceOption {

    String name;
    String value;
    int type;

    public static final int BOOLEAN = 0;
    public static final int INTEGER = 1;

    public preferenceOption(String name, String value, int type) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return name + "=" + value + ";\n";
    }

    public static preferenceOption parsePreferenceOption(String text) {
        if (text.contains("=") && text.contains(";")) {
            if (text.contains("true") || text.contains("false")) {
                return new preferenceOption(text.substring(0, text.indexOf("=")), text.substring(text.indexOf("=") + 1, text.indexOf(";")), BOOLEAN);
            } else {
                return new preferenceOption(text.substring(0, text.indexOf("=")), text.substring(text.indexOf("=") + 1, text.indexOf(";")), INTEGER);
            }
        } else {
            throw new IllegalArgumentException("preference option does not contain '=' or ';' - cannot be parsed");
        }
    }
}

