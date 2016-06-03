/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.songbitmaven;

import com.wrapper.spotify.Api;
import com.wrapper.spotify.methods.TrackSearchRequest;
import com.wrapper.spotify.models.Page;
import com.wrapper.spotify.models.Track;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.util.Scanner;
import com.google.gson.*;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Ashwin
 */
public class SearchController extends ControlledScreen implements Initializable {
    ScreensController myController;
    public Button goToFavorites;
    public Button goToRecommend;
    public Button goToSettings;
    public Button goToPlayingSong; 
    public Button addToFavorites;
    private SongDataSet dataset;
    
 
    public void initialize(URL location, ResourceBundle resources) {
    }

  
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }

    
    public void goToSearch(ActionEvent e) {
        myController.setScreen(MainApp.SEARCH_SCREEN);
    }

 
    public void goToRecommend(ActionEvent e) {
        myController.setScreen(MainApp.RECOMENDATION);
    }

    
    public void goToFavorites(ActionEvent e) {
        myController.setScreen(MainApp.FAVORITES);
    }

    
    public void goToPlayingSong(ActionEvent e) {  
        myController.setScreen(MainApp.PLAYING_SONG);
    }
    
    

    
    public void goToSettings(ActionEvent e) {
        myController.setScreen(MainApp.SETTINGS);
    }
    
    
    
    @FXML
    public TextField searchScreen;
    
    @FXML
    public Button addFavorite;
    
    @FXML
    public Label searchResult;
    
    @FXML
    public Button goToPreview;
    
    @FXML
    public void handleGoToPreview(){
        try{
            java.awt.Desktop.getDesktop().browse(new URI(dataset.getUrls()[0]));
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    @FXML
    public void handleSearchBar(){
        String song = searchScreen.getText();
        
        try{
            searchResult.setText(searchSong(song));
        }
        catch(Exception e){
            searchResult.setText("We couldn't find that song");
        }
        
    }

    public String searchSong(String song) throws Exception{
    Api api = Api.DEFAULT_API; 
        final TrackSearchRequest request = api.searchTracks(song).market("US").build();

        final Page<Track> trackSearchResult = request.get();
        String jsonURL = trackSearchResult.getNext();

        URL myurl = null;
        try {
            myurl = new URL(jsonURL);
        } catch (Exception e) {
            System.out.println("Improper URL " + jsonURL);
        }

        // read from the URL
        Scanner scan = null;
        try {
            scan = new Scanner(myurl.openStream());
        } catch (Exception e) {
            System.out.println("Could not connect to " + jsonURL);
        }

        String str = new String();
        while (scan.hasNext()) {
            str += scan.nextLine() + "\n";
        }
        scan.close();

        Gson gson = new Gson();

        System.out.println(jsonURL);
        dataset = gson.fromJson(str, SongDataSet.class);

        System.out.println(Arrays.toString(dataset.getNames()));

        String firstResult = dataset.getNames()[0];
        
        return firstResult;
    }
    
    @FXML
    public void handleAddFavorite(){
        System.out.println("Handling add to favorites");
        try{
            System.out.println(Arrays.toString(dataset.getInfo()));
            Singleton.getInstance().addToFavorites(dataset.getInfo()[0]);
            System.out.println(Singleton.getInstance().getFavorites().get(0));
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    @FXML
    public Button clear;
    
    @FXML
    public void handleClear(){
        Singleton.getInstance().resetFavorites();
    }
    
}
