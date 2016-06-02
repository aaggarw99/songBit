/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.songbitmaven;

import com.google.gson.Gson;
import com.wrapper.spotify.Api;
import com.wrapper.spotify.methods.TrackSearchRequest;
import com.wrapper.spotify.models.Page;
import de.umass.lastfm.Artist;
import de.umass.lastfm.Chart;
import de.umass.lastfm.PaginatedResult;
import de.umass.lastfm.Track;
import de.umass.lastfm.User;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.apache.commons.httpclient.HttpClient;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;


/**
 * each one of these controllers should have @FXML methods to go from screen to screen
 * @author Ashwin
 */
public class RecommendationController implements ControlledScreen, Initializable {
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
    
    public static SongDataSet trackLookup(String text){
        Api api = Api.DEFAULT_API; 
        final TrackSearchRequest request = api.searchTracks(text).market("US").build();

        try {
            final Page<com.wrapper.spotify.models.Track> trackSearchResult = request.get();
            String jsonURL = trackSearchResult.getNext();

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
            SongDataSet dataset = gson.fromJson(str, SongDataSet.class);

            return dataset;
        } catch (Exception e) {
            System.out.println("Something went wrong!" + e.getMessage());
            return null;
        }
    }
    
    @FXML
    public void handleRecommend() throws IOException{
        ArrayList<String> trackIDs = new ArrayList<String>();
        ArrayList<String> recommendations = new ArrayList<String>();
        user = login.getText();
        PaginatedResult<Track> recentTracks = User.getRecentTracks(user, key);
        for(Track t : recentTracks){
            System.out.println(t.getArtist());
            System.out.println(t.getName());
            
            trackIDs.add(trackLookup(t.getName()).getIDs()[0]);
        }
        
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost("");

        // Request parameters and other properties.
        for(Track t : recentTracks){
            List<NameValuePair> params = new ArrayList<NameValuePair>(3);
            int i = 0;

            params.add(new BasicNameValuePair("song_name", t.getName()));
            params.add(new BasicNameValuePair("song_artist", t.getArtist()));
            params.add(new BasicNameValuePair("song_id", trackIDs.get(i)));
            i++;

            httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
            
            //Execute and get the response.
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                InputStream instream = entity.getContent();
                
                try {
                    recommendations.add(instream.toString());
                } finally {
                    instream.close();
                }
            }
        }     
    }
}


