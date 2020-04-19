package com.agawrysiuk.javafx3dtest.controller;

import com.agawrysiuk.javafx3dtest.utils.TransformGroup;
import com.agawrysiuk.javafx3dtest.view.View;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Box;
import javafx.scene.shape.Sphere;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class Controller {

    private View view;
    private Scene scene;

    public void configureHandlers() {
        Box box = view.getBox();
        TransformGroup group = view.getGroup();

        scene.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            switch (event.getCode()) {
                case NUMPAD6:
                    group.translateZProperty().set(group.getTranslateZ() + 100);
                    break;
                case NUMPAD4:
                    group.translateZProperty().set(group.getTranslateZ() - 100);
                    break;
                case S:
                    group.rotateByX(10);
                    break;
                case W:
                    group.rotateByX(-10);
                    break;
                case A:
                    group.rotateByY(10);
                    break;
                case D:
                    group.rotateByY(-10);
                    break;
            }
        });
    }
}
