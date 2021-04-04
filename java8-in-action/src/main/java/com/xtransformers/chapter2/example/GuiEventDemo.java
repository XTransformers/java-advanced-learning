package com.xtransformers.chapter2.example;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class GuiEventDemo {

    public void test() {
        Button button = new Button("Send");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                button.setText("Send!!!");
            }
        });

        button.setOnAction((ActionEvent event) -> button.setText("Send!!!"));
    }

}
