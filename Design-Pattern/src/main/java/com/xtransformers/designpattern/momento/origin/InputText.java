package com.xtransformers.designpattern.momento.origin;

/**
 * @author daniel
 * @date 2021-08-14
 */
public class InputText {

    private StringBuilder text = new StringBuilder();

    public String getText() {
        return text.toString();
    }

    public void append(String input) {
        text.append(input);
    }

    public void setText(String text) {
        this.text.replace(0, this.text.length(), text);
    }
}
