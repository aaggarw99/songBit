/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.songbitmaven;

import java.io.IOException;
import java.util.HashMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author csstudent
 */
public class SceneController {
    
    private Stage stage;
    private HashMap<String, String> sceneFiles = new HashMap<>();
    
    private static SceneController singleton;
    
    public static void initialize(Stage stage){
        SceneController.singleton = new SceneController(stage);
    }
    
    public static SceneController getSingleton(){
        return SceneController.singleton;
    }
    
    public SceneController(Stage stage) {
        this.stage = stage;
        
        sceneFiles.put("search", "SearchScreen.fxml");
        sceneFiles.put("settings", "Settings.fxml");
        sceneFiles.put("songboard", "Songboard.fxml");
        sceneFiles.put("login", "Login.fxml");
        sceneFiles.put("recommendation", "RecommendationScreen.fxml");
        sceneFiles.put("playing", "RecommendationScreen.fxml");
        sceneFiles.put("recommendation", "PlayingSong.fxml");
        sceneFiles.put("favorites", "Favorites.fxml");
    }
    
    public void setScene(String sceneName){
        Parent root = null;
        try{
            root = FXMLLoader.load(getClass().getResource("/fxml/" + sceneFiles.get(sceneName)));
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        
        // stage.setTitle("JavaFX and Maven");
        stage.setScene(scene);
        stage.show();
    }
    
    
}
