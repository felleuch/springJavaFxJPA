package com.faiez.application;


import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApplication extends Application {


    public static String petScreenID = "pet";
    public static String petScreenFile = "pet.fxml";
    public static String screen2ID = "screen2";
    public static String screen2File = "Screen2.fxml";
    public static String screen3ID = "screen3";
    public static String screen3File = "Screen3.fxml";

    public static String createPetID = "newPet";
    public static String createPetFile = "newPet.fxml";
    
    
    @Override
    public void start(Stage primaryStage) {

        ScreensController mainContainer = new ScreensController();
        mainContainer.loadScreen(MainApplication.petScreenID, MainApplication.petScreenFile);
        mainContainer.loadScreen(MainApplication.screen2ID, MainApplication.screen2File);
        mainContainer.loadScreen(MainApplication.screen3ID, MainApplication.screen3File);
        mainContainer.loadScreen("myMenu", "MyMenu.fxml");
        mainContainer.loadScreen(MainApplication.createPetID, MainApplication.createPetFile);
        
        mainContainer.setScreen(MainApplication.petScreenID);
        
        Group root = new Group();
        root.getChildren().addAll(mainContainer);
        Scene scene = new Scene(root);
        primaryStage.setTitle("Pet clinic application");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
