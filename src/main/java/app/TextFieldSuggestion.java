package app;

import javax.swing.*;

public class TextFieldSuggestion extends JTextField {
    private TextFieldSuggestionUI textUI;


    public void TextFieldSuggestion() {
        this.textUI = new TextFieldSuggestionUI(this);
        this.setUI(textUI);

    }
    public void addItemSuggestion(String text) {
        textUI.getItems().add(text);
    }
    public void removeItemSuggestion(String text) {
        textUI.getItems().remove(text);
    }
    public void clearItemSuggestion(String text) {
        textUI.getItems().clear();
    }
    public void setRound(int round) {
        textUI.setRound(round);
    }
    public int getRound(int round) {
        return textUI.getRound();
    }
}
