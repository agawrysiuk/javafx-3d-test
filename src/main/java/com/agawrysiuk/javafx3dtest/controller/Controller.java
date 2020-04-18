package com.agawrysiuk.javafx3dtest.controller;

import com.agawrysiuk.javafx3dtest.view.View;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Sphere;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class Controller {

    private View view;
    private Scene scene;

    public void configureHandlers() {

        Sphere sphere = view.getSphere();

        scene.addEventHandler(KeyEvent.KEY_PRESSED, event ->{
            switch (event.getCode()) {
                case W:
                    sphere.translateZProperty().set(sphere.getTranslateZ() + 100);
                    break;
                case S:
                    sphere.translateZProperty().set(sphere.getTranslateZ() - 100);
                    break;
            }
        });
    }
}
