package circuitikztool;

//simply a seperate file for storing static variables. makes life easier having
import java.awt.Color;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;

//all the important configuration preferences in one place. 
public class Preferences {

    private static preferenceOption[] options = {
        new preferenceOption("Use Dark Theme", "false", preferenceOption.BOOLEAN),
        new preferenceOption("American Style Components", "true", preferenceOption.BOOLEAN),
        new preferenceOption("Wrap in Figure", "true", preferenceOption.BOOLEAN),
        new preferenceOption("Use [h] annotation", "true", preferenceOption.BOOLEAN),
        new preferenceOption("Smaller Path Components", "false", preferenceOption.BOOLEAN)
    };

    //basically apply changes
    public static void ConfigPrefrences() {
        if (getPreference("Use Dark Theme").equals("true")) {
            backgroundColor = new Color((float) .14, (float) .14, (float) .21);
            componentColor = Color.WHITE;
            gridColor = Color.darkGray;
            CircuitikzTool.ui.getContentPane().setBackground(darkThemeBackground);
            themeBackgroundColor = darkThemeBackground;
            themeText = darkThemeText;
            themeAccent = darkThemeAccent;
        } else {
            CircuitikzTool.ui.getContentPane().setBackground(lightThemeBackground);
            backgroundColor = new Color((float) .93, (float) .93, (float) .93);
            componentColor = Color.BLACK;
            selectedColor = Color.blue;
            gridColor = Color.GRAY;
            themeBackgroundColor = lightThemeBackground;
            themeText = lightThemeText;
            themeAccent = lightThemeAccent;
        }
        CircuitikzTool.ui.updateTheme();
    }

    public static Color themeBackgroundColor, themeAccent, themeText;

    public static Color darkThemeBackground = new Color((float) .14, (float) .14, (float) .21);
    public static Color darkThemeAccent = new Color((float) .21, (float) .21, (float) .35);
    public static Color darkThemeText = new Color((float) .93, (float) .93, (float) .93);

    public static Color lightThemeBackground = new Color((float) .93, (float) .93, (float) .93);
    public static Color lightThemeAccent = Color.white;
    public static Color lightThemeText = Color.BLACK;

    public static Color windowBackgroundColor = new Color((float) .07, (float) .07, (float) .14);

    /**
     * controls the background color of the circuitmaker window, this way it's
     * easy to change visual parameters if we want to do so
     */
    public static Color backgroundColor = new Color((float) .93, (float) .93, (float) .93);

    /**
     * color of components by default when they're placed and being displayed
     */
    public static Color componentColor = Color.BLACK;

    /**
     * color of components when they are selected in the Components list box
     */
    public static Color selectedColor = Color.blue;

    /**
     * color of the grid dots that are drawn in the circuitmaker window
     */
    public static Color gridColor = Color.GRAY;

    public static preferenceOption[] getPreferences() {
        return options;
    }

    public static void setPreference(String name, String value) {
        for (int a = 0; a < options.length; a++) {
            if (options[a].name.equals(name)) {
                options[a].value = value;
                return; // really why finish the loop
            }
        }
        throw new IllegalArgumentException("No preference with name \"" + name + "\" exists");
    }

    public static String getPreference(String name) {
        for (int a = 0; a < options.length; a++) {
            if (options[a].name.equals(name)) {
                return options[a].value;

            }
        }
        throw new IllegalArgumentException("No preference with name \"" + name + "\" exists");
    }

    public static void exportPreferences() {
        try {
            BufferedWriter w = new BufferedWriter(new FileWriter(new File("preferences.config")));
            for (int a = 0; a < options.length; a++) {
                w.write(options[a].toString());
            }
            w.close();
        } catch (IOException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void importPreferences() {
        try {
            String data = new String(Files.readAllBytes(new File("preferences.config").toPath()));
            String[] splitData = data.split("\n");

            //now we track down each option and see how it's been configured
            for (int a = 0; a < options.length; a++) {
                options[a] = preferenceOption.parsePreferenceOption(splitData[a]);
            }

        } catch (IOException ex) {
            //no preferences means we need to create the file
            exportPreferences();
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
