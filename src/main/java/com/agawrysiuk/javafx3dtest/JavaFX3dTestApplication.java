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
		Scene scene = new Scene(view.asParent(), 1400, 800);
		view.setScene(scene);
		view.createAndConfigurePane();
		controller.setView(view.getMainView());
		stage.setTitle("JavaFX 3d Test");
//		stage.setMaximized(true);
		stage.setScene(scene);
		stage.show();
	}

}
