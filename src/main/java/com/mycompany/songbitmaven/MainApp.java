/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.songbitmaven;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Ashwin
 */
public class MainApp extends Application{
    
    @Override
    public void start(Stage stage) throws IOException{
        SceneController sceneController = new SceneController(stage);
        sceneController.setScene("recommendation");
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
    
    
    

    
}
