package sample;

import javafx.beans.property.SimpleStringProperty;

public class TextWordProperty {


    private String Word;
    private int Count;

    public TextWordProperty(String word, int count) {
        Word = word;
        Count = count;
    }

    public TextWordProperty() {

    }

    public String getWord() {
        return Word;
    }

    public void setWord(String word) {
        Word = word;
    }

    public int getCount() {
        return Count;
    }

    public void setCount(int count) {
        Count = count;
    }
}
