package com.marcel.browser;

import java.io.IOException;

import com.marcel.browser.controller.RootController;
import com.marcel.browser.util.Resources;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class BrowserMain extends Application{

	private FXMLLoader fxmlLoader;
	
	private GridPane root;
	private Stage stage;
	
	public void start(Stage stage) throws Exception {
		this.stage = stage;
		initFXML();
		
		Scene scene = new Scene(root);
		stage.setResizable(false);
		stage.initStyle(StageStyle.UNDECORATED);
		stage.setScene(scene);
		stage.show();
	}
	
	private void initFXML() throws IOException{
		fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(this.getClass().getResource(Resources.FXML));
		root = fxmlLoader.load();
		
		RootController controller = fxmlLoader.getController();
		controller.initBrowserMain(this);
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	public GridPane getRoot() {
		return root;
	}
	
	public Stage getStage() {
		return stage;
	}
}
