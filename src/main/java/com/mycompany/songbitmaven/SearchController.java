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
import java.net.URI;
import java.util.Arrays;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Ashwin
 */
public class SearchController implements Initializable, ControlledScreen {
    ScreensController myController;
    public Button goToFavorites;
    public Button goToRecommend;
    public Button goToSettings;
    public Button goToPlayingSong; 
    public Button addToFavorites;
    private SongDataSet dataset;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
    
    
    
    @FXML
    public TextField searchScreen;
    
    @FXML
    Image img = new Image("file:logo.svg");
    ImageView imageView = new ImageView(img);
    
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
        String jsonURL;
        String text = searchScreen.getText();
        
        Api api = Api.DEFAULT_API; 
        final TrackSearchRequest request = api.searchTracks(text).market("US").build();

        try {
            final Page<Track> trackSearchResult = request.get();
            jsonURL = trackSearchResult.getNext();
           
            URL myurl = null;
            try {
                myurl = new URL(jsonURL);
            } catch (Exception e) {
                System.out.println("Improper URL " + jsonURL);
                System.exit(-1);
            }

            // read from the URL
            Scanner scan = null;
            try {
                scan = new Scanner(myurl.openStream());
            } catch (Exception e) {
                System.out.println("Could not connect to " + jsonURL);
                System.exit(-1);
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
            
            searchResult.setText(firstResult);

        } catch (Exception e) {
            System.out.println("Something went wrong!" + e.getMessage());
        }        
        
    }
    
    @FXML
    public void handleAddFavorite(){
        try{
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
