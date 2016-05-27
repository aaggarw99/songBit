/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.songbitmaven;

import de.umass.lastfm.Artist;
import de.umass.lastfm.Chart;
import de.umass.lastfm.PaginatedResult;
import de.umass.lastfm.Track;
import de.umass.lastfm.User;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;


/**
 * each one of these controllers should have @FXML methods to go from screen to screen
 * @author Ashwin
 */
public class RecommendationController implements Initializable, ControlledScreen {
    ScreensController myController;
    public ArrayList<String> artist = new ArrayList<String>();
    public ArrayList<String> name = new ArrayList<String>();
    public ArrayList<String> numListens = new ArrayList<String>();
    public String key = "d8dec72bb448436493edcb1dec93e22d";
    public String user;
    public Button goToSearch;
    public Button goToFavorites;
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
    public TextField login;
    
    @FXML
    public void handleLogin(){
        try{
            handleRecommend();
        } catch(Exception e){
            System.out.println("Something went wrong!" + e.getMessage());
        }
    }
    
    @FXML
    public Button recommend;
    
    @FXML
    public void handleRecommend(){
        user = login.getText();
        PaginatedResult<Track> recentTracks = User.getRecentTracks(user, key);
        for(Track t : recentTracks){
            System.out.println(t.getArtist());
            System.out.println(t.getName());
        }
    }
}
