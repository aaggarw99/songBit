/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.songbitmaven;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

/**
 *
 * @author Ashwin
 */
public class MainApp extends Application{    
    
    public static final String SEARCH_SCREEN = "SearchController";
    public static final String SEARCH_SCREEN_FXML = "/fxml/SearchScreen.fxml";
    public static final String SETTINGS = "SettingsController";
    public static final String SETTINGS_FXML = "/fxml/Settings.fxml";
    /*public static final String SONGBOARD = "songboard";
    public static final String SONGBOARD_FXML = "/fxml/songboard.fxml";
    public static final String LOGIN = "login";
    public static final String LOGIN_FXML = "/fxml/login.fxml";*/
    public static final String RECOMENDATION = "RecommendationController";
    public static final String RECOMENDATION_FXML = "/fxml/RecommendationScreen.fxml";
    /*public static final String REC_LIST = "rec_list";
    public static final String REC_LIST_FXML = "Rec_list_fxml";*/
    public static final String PLAYING_SONG = "PlayingSongController";
    public static final String PLAYING_SONG_FXML = "/fxml/PlayingSong.fxml";
    public static final String FAVORITES = "FavoritesController";
    public static final String FAVORITES_FXML = "/fxml/Favorites.fxml";
    
    
    @Override
    public void start(Stage stage) throws IOException{
        
        
        ScreensController mainContainer = new ScreensController();
        mainContainer.loadScreen(MainApp.RECOMENDATION, MainApp.RECOMENDATION_FXML);
        mainContainer.loadScreen(MainApp.SEARCH_SCREEN, MainApp.SEARCH_SCREEN_FXML);
        
        mainContainer.loadScreen(MainApp.SETTINGS, MainApp.SETTINGS_FXML);
        mainContainer.loadScreen(MainApp.PLAYING_SONG, MainApp.PLAYING_SONG_FXML);
        mainContainer.loadScreen(MainApp.FAVORITES, MainApp.FAVORITES_FXML);
        
        /* add in second MVP -- addition of the fxml files and respective controllers
        mainContainer.loadScreen(MainApp.SONGBOARD, MainApp.SONGBOARD_FXML);
        mainContainer.loadScreen(MainApp.LOGIN, MainApp.LOGIN_FXML);
        mainContainer.loadScreen(MainApp.REC_LIST, MainApp.REC_LIST);
        
        */
        
        
        mainContainer.setScreen(MainApp.RECOMENDATION);
        stage.setTitle("songBit");
        Group root = new Group(); 
        root.getChildren().addAll(mainContainer); 
        Scene scene = new Scene(root); 
        stage.setScene(scene); 
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
    
    
    

    
}
