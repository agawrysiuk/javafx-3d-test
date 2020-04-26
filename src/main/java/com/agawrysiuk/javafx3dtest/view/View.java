package com.agawrysiuk.javafx3dtest.view;

import com.agawrysiuk.javafx3dtest.controller.Controller;
import com.agawrysiuk.javafx3dtest.utils.TransformGroup;
import javafx.animation.AnimationTimer;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Translate;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class View {
    private Pane mainView;
    private Scene scene;
    private Box box;
    private PointLight light;
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
        group.getChildren().add(box);
        group.getChildren().addAll(prepareLightSource());
        prepareCamera();
        mainView.getChildren().addAll(group);
        createLightAnimation();
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

    private Node[] prepareLightSource() {
        light = new PointLight();
        light.setColor(Color.RED);
        light.getTransforms().add(new Translate(0, -60, 10));
        return new Node[]{light, bindToLight(light)};
    }

    private Sphere bindToLight(PointLight pointLight) {
        Sphere sphere = new Sphere(2);
        sphere.getTransforms().setAll(pointLight.getTransforms());
        sphere.rotateProperty().bind(pointLight.rotateProperty());
        sphere.rotationAxisProperty().bind(pointLight.rotationAxisProperty());
        return sphere;
    }

    private void createLightAnimation() {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                light.setRotate(light.getRotate() + 1);
            }
        };
        timer.start();
    }

}
