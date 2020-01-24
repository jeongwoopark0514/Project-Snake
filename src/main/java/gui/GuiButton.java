package gui;

import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import lombok.Setter;

/**
 * Gui class for buttons. (button, radiobuttons)
 */
public class GuiButton {
    @Setter
    private ToggleGroup group;


    /**
     * Check if a selected button from a group is equal to the input button.
     *
     * @param button the button to check for
     * @return if the button is selected or not
     */
    public boolean equalsButton(RadioButton button) {
        RadioButton selected = (RadioButton) group.getSelectedToggle();
        return selected.equals(button);
    }

    /**
     * Disable button.
     *
     * @param button - button
     */
    public void disableButton(Button button) {
        button.setDisable(true);
    }
}
