/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.songbitmaven;

import com.wrapper.spotify.Api;
import com.wrapper.spotify.methods.TrackRequest;
import com.wrapper.spotify.methods.TrackSearchRequest;
import com.wrapper.spotify.models.Page;
import com.wrapper.spotify.models.Track;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;

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
    public Text searchResult;
    
    @FXML
    public void handleSearchBar(){
        String text = searchScreen.getText();
        
        Api api = Api.DEFAULT_API; 
        final TrackRequest request = api.getTrack(text).build();

        try {
           final Track track = request.get();
           searchResult.setText(track.getName());
        } catch (Exception e) {
           System.out.println("Something went wrong!" + e.getMessage());
        }            
    }
    
}
