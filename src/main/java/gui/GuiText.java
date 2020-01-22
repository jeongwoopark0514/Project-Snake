package gui;

import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * Gui class that reads or modifies the value of Gui Component.
 */
public class GuiText {

    /**
     * Set the text with the parameter given.
     * @param text text you want to modify.
     * @param setting String that you want to change to.
     */
    public void setText(Text text, String setting) {
        text.setText(setting);
    }

    /**
     * Get score from the text in the main board and then change in into integer.
     * @param text to read in.
     * @return integer in the text.
     */
    public int getScoreFromText(Text text) {
        return Integer.parseInt(text.getText());
    }

    /**
     * Getting text from textfield.
     * @param any Textfield
     * @return content of textfield
     */
    public String getText(TextField any) {
        return any.getText();
    }
}
