/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.songbitmaven;

import com.wrapper.spotify.models.Track;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Ashwin
 */
public class PlayingSongController extends ControlledScreen implements Initializable{
    ScreensController myController;
    public Button goToRecommend;
    public Button goToSearch;
    public Button goToSettings;
    public Button goToFavorites;
    public static Track currentSong;
    
    @FXML
    Image img = new Image("file:logo.svg");
    ImageView imageView = new ImageView(img);

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
        System.out.println("You're already in the Playing Song Screen!");
    }

    @Override
    public void goToSettings(ActionEvent e) {
        myController.setScreen(MainApp.SETTINGS);
    }
    
    public static void setSong(Track track){
        currentSong = track;
    }
}
