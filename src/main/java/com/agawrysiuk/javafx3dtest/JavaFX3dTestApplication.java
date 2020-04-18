package com.agawrysiuk.javafx3dtest;

import com.agawrysiuk.javafx3dtest.controller.Controller;
import com.agawrysiuk.javafx3dtest.view.View;
import javafx.application.Application;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Sphere;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JavaFX3dTestApplication extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		Controller controller = new Controller();
		View view = new View(controller);
		controller.setView(view.getMainView());

		Sphere sphere = new Sphere(250);

		Group group = new Group();
		group.getChildren().add(sphere);

		Camera camera = new PerspectiveCamera();
		Scene scene = new Scene(group, 1400, 800);
		scene.setFill(Color.SILVER);
		scene.setCamera(camera);

		sphere.translateXProperty().set(1400 / 2);
		sphere.translateYProperty().set(800 / 2);

		stage.addEventHandler(KeyEvent.KEY_PRESSED, event ->{
			switch (event.getCode()) {
				case W:
					sphere.translateZProperty().set(sphere.getTranslateZ() + 100);
					break;
				case S:
					sphere.translateZProperty().set(sphere.getTranslateZ() - 100);
					break;
			}
		});
//
//		Scene scene = new Scene(view.asParent(), 400, 400);
		stage.setTitle("JavaFX 3d Test");
//		stage.setMaximized(true);
		stage.setScene(scene);
		stage.show();
	}

}
