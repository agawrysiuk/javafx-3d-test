package com.agawrysiuk.javafx3dtest.view;

import com.agawrysiuk.javafx3dtest.controller.Controller;
import javafx.scene.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Sphere;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class View {
    private BorderPane mainView;
    private Scene scene;
    private Sphere sphere;

    private Controller controller;

    public View(Controller controller) {
        this.controller = controller;
        mainView = new BorderPane();
    }

    public Parent asParent() {
        return mainView;
    }

    public void createAndConfigurePane() {
        sphere = new Sphere(250);

        Group group = new Group();
        group.getChildren().add(sphere);

        Camera camera = new PerspectiveCamera();
        scene.setFill(Color.SILVER);
        scene.setCamera(camera);

        sphere.translateXProperty().set(1400 / 2);
        sphere.translateYProperty().set(800 / 2);

        mainView.getChildren().add(group);
    }

}
