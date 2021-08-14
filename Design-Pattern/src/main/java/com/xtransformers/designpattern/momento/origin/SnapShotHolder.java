package com.xtransformers.designpattern.momento.origin;

import java.util.Stack;

/**
 * @author daniel
 * @date 2021-08-14
 */
public class SnapShotHolder {

    private Stack<InputText> snapshots = new Stack<>();

    public InputText popSnapshot() {
        return snapshots.pop();
    }

    public void pushSnapshot(InputText inputText) {
        InputText deepClonedInputText = new InputText();
        deepClonedInputText.setText(inputText.getText());
        snapshots.push(deepClonedInputText);
    }
}
