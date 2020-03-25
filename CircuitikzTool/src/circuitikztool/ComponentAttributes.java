package circuitikztool;

public class ComponentAttributes {

    /*
        since components can have attributes that are either selected by the user
    or entered directly by the user this class is designed to make the attribution handling
    a little easier
     */
    private String[] selectionAttributes;
    private String[][] selectionOptions;

    private String[] entryAttributes;
    private String[] entryValues;

    public ComponentAttributes(String[] selectionAttributes, String[][] selectionOptions) {
        this.selectionAttributes = selectionAttributes;
        this.selectionOptions = selectionOptions;
    }

    public ComponentAttributes(String[] entryAttributes, String[] entryValues) {
        this.entryAttributes = entryAttributes;
        this.entryValues = entryValues;
    }

    public ComponentAttributes(String[] selectionAttributes, String[][] selectionOptions, String[] entryAttributes, String[] entryValues) {
        this.entryAttributes = entryAttributes;
        this.entryValues = entryValues;
        this.selectionAttributes = selectionAttributes;
        this.selectionOptions = selectionOptions;
    }

    public String getSelectionAttribute(int index) {
        return selectionAttributes[index];
    }

    public int getNumberOfSelectionAttributes() {
        return selectionAttributes.length;
    }

    public String getSelectionOption(String attribute, int index) {
        int attributeIndex = 0;
        for (int a = 0; a < selectionAttributes.length; a++) {
            if (selectionAttributes[a].equals(attribute)) {
                attributeIndex = a;
                break;
            }
        }
        return selectionOptions[attributeIndex][index];
    }

    public int getNumberOfOptions(String attribute) {
        int attributeIndex = 0;
        for (int a = 0; a < selectionAttributes.length; a++) {
            if (selectionAttributes[a].equals(attribute)) {
                attributeIndex = a;
                break;
            }
        }
        return selectionOptions[attributeIndex].length;
    }

}
