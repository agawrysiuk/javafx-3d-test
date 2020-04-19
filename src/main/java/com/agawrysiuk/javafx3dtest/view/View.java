package com.agawrysiuk.javafx3dtest.view;

import com.agawrysiuk.javafx3dtest.controller.Controller;
import com.agawrysiuk.javafx3dtest.utils.TransformGroup;
import javafx.scene.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.shape.Sphere;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class View {
    private BorderPane mainView;
    private Scene scene;
    private Box box;
    private TransformGroup group;

    private Controller controller;

    public View(Controller controller) {
        this.controller = controller;
        mainView = new BorderPane();
    }

    public Parent asParent() {
        return mainView;
    }

    public void createAndConfigurePane() {
        prepareGroup();

        box = new Box(100, 20, 50);
        group.getChildren().add(box);

        Camera camera = new PerspectiveCamera();
        scene.setFill(Color.SILVER);
        scene.setCamera(camera);

        mainView.getChildren().add(group);
    }

    private void prepareGroup() {
        group = new TransformGroup();
        group.translateXProperty().set(1400 / 2);
        group.translateYProperty().set(800 / 2);
        group.translateZProperty().set(-1200);

    }

}
