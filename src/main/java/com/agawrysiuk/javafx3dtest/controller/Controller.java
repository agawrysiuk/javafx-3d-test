package com.agawrysiuk.javafx3dtest.controller;

import com.agawrysiuk.javafx3dtest.utils.TransformGroup;
import com.agawrysiuk.javafx3dtest.view.View;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Box;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class Controller {

    private View view;
    private Scene scene;

    private double anchorX, anchorY;
    private double anchorAngleX = 0;
    private double anchorAngleY = 0;
    private final DoubleProperty angleX = new SimpleDoubleProperty(0);
    private final DoubleProperty angleY = new SimpleDoubleProperty(0);

    public void configureHandlers() {
        TransformGroup group = view.getGroup();

        initMouseControl(group,scene);

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

    private void initMouseControl(TransformGroup group, Scene scene) {
        Rotate xRotate;
        Rotate yRotate;
        group.getTransforms().addAll(
                xRotate = new Rotate(0, Rotate.X_AXIS),
                yRotate = new Rotate(0, Rotate.Y_AXIS)
        );
        xRotate.angleProperty().bind(angleX);
        yRotate.angleProperty().bind(angleY);

        scene.setOnMousePressed(event -> {
            anchorX = event.getSceneX();
            anchorY = event.getSceneY();
            anchorAngleX = angleX.get();
            anchorAngleY = angleY.get();
        });

        scene.setOnMouseDragged(event -> {
            angleX.set(anchorAngleX - anchorY + event.getSceneY());
            angleY.set(anchorAngleY + anchorX - event.getSceneX());
        });

        scene.addEventHandler(ScrollEvent.SCROLL, event -> {
            double delta = event.getDeltaY();
            group.translateZProperty().set(group.getTranslateZ() + delta);
        });
    }
}
