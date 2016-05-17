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
    
    public static Stage stage;
    public static HashMap<String, String> sceneFiles = new HashMap<>();
    
    public static void initialize(Stage stage){
        SceneController.stage = stage;
        
        sceneFiles.put("search", "SearchScreen.fxml");
        sceneFiles.put("settings", "Settings.fxml");
        sceneFiles.put("songboard", "Songboard.fxml");
        sceneFiles.put("login", "Login.fxml");
        sceneFiles.put("recommendation", "RecommendationScreen.fxml");
        sceneFiles.put("playing", "PlayingSong.fxml");
        sceneFiles.put("favorites", "Favorites.fxml");
    }
    
    public static void setScene(String sceneName){
        Parent root = null;
        String filename = "/fxml/" + sceneFiles.get(sceneName);
        System.out.println(filename);
        System.out.println(SceneController.class.getResource(filename));
        try{
            root = FXMLLoader.load(SceneController.class.getResource(filename));
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
        System.out.println(root);
        
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        
        // stage.setTitle("JavaFX and Maven");
        stage.setScene(scene);
        stage.show();
    }
    
    
}
