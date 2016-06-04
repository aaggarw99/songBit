/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.songbitmaven;

import com.google.common.collect.Table;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Callback;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;


/**
 *
 * @author Ashwin
 */
public class FavoritesController extends ControlledScreen implements Initializable{
    ScreensController myController;

    public ArrayList<SongInfo> favorites;
    // SongInfo song1 = new SongInfo("Bohemian Rhapsody", "Queen", "A Night at the Opera");
    public ArrayList<String> artists;
    public ArrayList<String> albums;
    
    @FXML
    public void handleSaveProfile(){
        Singleton.save();
    }
    
    @FXML
    public void handleLoadProfile(){
        Singleton.load();
        refresh(null);
    }
    
    @FXML
    public TableView<SongInfo> table;
    private final ObservableList<SongInfo> tracks =
            FXCollections.observableArrayList(
                    
                    // this is where I add actual songs
                    //new SongInfo("Bohemian Rhapsody", "Queen", "A Night at the Opera"),
                    //new SongInfo("I got a feeling", "Black Eyed Peas", "E.N.D")
            );
    
    /*@FXML
    public TableColumn nameColumn;
    
    @FXML
    public TableColumn artistColumn;*/
    
    @FXML
    public Button refresh;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // tracks.add(song1);
        
        loadTable();
        
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }

    @Override
    public void goToSearch(ActionEvent e) {
        myController.setScreen(MainApp.SEARCH_SCREEN);
    }

    @Override
    public void goToRecommend(ActionEvent e) {
        myController.setScreen(MainApp.RECOMENDATION);
    }

    @Override
    public void goToPlayingSong(ActionEvent e) {
        myController.setScreen(MainApp.PLAYING_SONG);
    }

    @Override
    public void goToSettings(ActionEvent e) {
        myController.setScreen(MainApp.SETTINGS);
    }
    
    
    @FXML
    public void handleClear(){
        Singleton.getInstance().resetFavorites();
        refresh(null);
    }
    
    public void loadTable(){
        TableColumn nameColumn = new TableColumn("Name");
        nameColumn.setMinWidth(250);
        nameColumn.setCellValueFactory(
                new PropertyValueFactory<SongInfo, String>("name"));
        
        
        TableColumn artistColumn = new TableColumn("Artist");
        artistColumn.setMinWidth(250);
        artistColumn.setCellValueFactory(new PropertyValueFactory<SongInfo, String>("artist"));
        
        table.getColumns().setAll(nameColumn, artistColumn);
        // add values to the artists, names, and albums
        // for loop
        
        favorites = Singleton.getInstance().getFavorites();
        
        tracks.clear();
        
        for(int i = 0; i < favorites.size(); i++){
            favorites.get(i).updateArtist();
            tracks.add(favorites.get(i));
        }
        
        table.setItems(tracks);
    }
    
    public void refresh(ActionEvent e){
        loadTable();
    }
    
    private MenuItem Close;
    private MenuItem About;
    @FXML
    public void close(ActionEvent e){
        Platform.exit();
    }
    
    @FXML
    public void about(ActionEvent e){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("Song Recommender Information");
        alert.setContentText("Product Completed June 4th, 2016" + "\n" + "by Alex Gajweski, Ashwin Aggarwal, and Luis de Pablo");
        alert.showAndWait();
    }
    
}
