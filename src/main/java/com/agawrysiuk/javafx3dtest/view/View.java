package com.agawrysiuk.javafx3dtest.view;

import com.agawrysiuk.javafx3dtest.controller.Controller;
import com.agawrysiuk.javafx3dtest.utils.TransformGroup;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.transform.Translate;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class View {
    private Pane mainView;
    private Scene scene;
    private Box box;
    private TransformGroup group;

    private Controller controller;

    public View(Controller controller) {
        this.controller = controller;
        mainView = new Pane();
    }

    public Parent asParent() {
        return mainView;
    }

    public void createAndConfigurePane() {
        prepareGroup();
        createBox();
        group.getChildren().addAll(box, prepareLightSource());
        prepareCamera();
        mainView.getChildren().addAll(group);
    }

    private void createBox() {
        box = new Box(100, 20, 50);
        PhongMaterial material = new PhongMaterial();
//        material.setDiffuseColor(Color.ROYALBLUE);
        material.setDiffuseMap(new Image(getClass().getResourceAsStream("/wood.jpg")));
        material.setSpecularColor(Color.SILVER);
        material.setSpecularPower(20.0);
        box.setMaterial(material);
    }

    private void prepareGroup() {
        group = new TransformGroup();
        group.translateXProperty().set(1400 / 2);
        group.translateYProperty().set(800 / 2);
        group.translateZProperty().set(-1200);

    }

    private void prepareCamera() {
        Camera camera = new PerspectiveCamera();
        scene.setFill(Color.SILVER);
        scene.setCamera(camera);
    }

    private LightBase prepareLightSource() {
        PointLight pointLight = new PointLight();
        pointLight.setColor(Color.RED);
        pointLight.getTransforms().add(new Translate(0,-50,-50));
        return pointLight;
    }

}
