package com.xtransformers.designpattern.momento.origin;

import java.util.Scanner;

/**
 * @author daniel
 * @date 2021-08-14
 */
public class ApplicationMain {

    public static void main(String[] args) {
        InputText inputText = new InputText();
        SnapShotHolder snapShotHolder = new SnapShotHolder();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String input = scanner.next();
            if (":quit".equals(input)) {
                break;
            } else if (":list".equals(input)) {
                System.out.println(inputText.getText());
            } else if (":undo".equals(input)) {
                InputText snapshot = snapShotHolder.popSnapshot();
                inputText.setText(snapshot.getText());
            } else {
                snapShotHolder.pushSnapshot(inputText);
                inputText.append(input);
            }
        }
    }
}
