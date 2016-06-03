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
public class FavoritesController implements ControlledScreen, Initializable{
    ScreensController myController;
    
    @FXML
    public Button goToSearch;
    @FXML
    public Button goToRecommend;
    @FXML
    public Button goToSettings;
    @FXML
    public Button goToPlayingSong;
    public ArrayList<SongInfo> favorites = Singleton.getInstance().getFavorites();
    // SongInfo song1 = new SongInfo("Bohemian Rhapsody", "Queen", "A Night at the Opera");
    public ArrayList<String> artists;
    public ArrayList<String> albums;    
    
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
        
        TableColumn nameColumn = new TableColumn("Name");
        nameColumn.setMinWidth(100);
        nameColumn.setCellValueFactory(
                new PropertyValueFactory<SongInfo, String>("name"));
        
        
        TableColumn artistColumn = new TableColumn("Artist");
        artistColumn.setCellValueFactory(new PropertyValueFactory<SongInfo, String>("artist"));
        
        table.setItems(tracks);
        table.getColumns().addAll(nameColumn, artistColumn);
        // add values to the artists, names, and albums
        // for loop
        
        
        
        for(int i = 0; i < favorites.size(); i++){
            tracks.add(favorites.get(i));
        }
        
        
        
        
        
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
    public void goToFavorites(ActionEvent e) {
        
        myController.setScreen(MainApp.FAVORITES);
    }

    @Override
    public void goToPlayingSong(ActionEvent e) {
        myController.setScreen(MainApp.PLAYING_SONG);
    }

    @Override
    public void goToSettings(ActionEvent e) {
        myController.setScreen(MainApp.SETTINGS);
    }
    
    public void refresh(ActionEvent e){
        List<String> columns = new ArrayList<String>();
            columns.add("name");
            columns.add("artist");
            columns.add("album");
            TableColumn[] tableColumns = new TableColumn[columns.size()];
            int columnIndex = 0;
            for(int i=0 ; i<columns.size(); i++) {
                final int j = i;
                TableColumn col = new TableColumn(columns.get(i));
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){                   
                   public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {                                                                                             
                        return new SimpleStringProperty(param.getValue().get(j).toString());                       
                    }                   
                });
                table.getColumns().addAll(col);
            }
            ObservableList<ObservableList> songData = FXCollections.observableArrayList();
            ObservableList<String> row = FXCollections.observableArrayList();
            ObservableList<String> row1 = FXCollections.observableArrayList();
            row.addAll("Test");
            row.addAll("Test again");
            row1.addAll("d2");
            row1.addAll("d22");
            songData.add(row);
            songData.add(row1);
            // table.getItems().add(songData);
    }
    
}
