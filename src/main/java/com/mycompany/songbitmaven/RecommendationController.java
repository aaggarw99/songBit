/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.songbitmaven;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
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
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONTokener;
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
import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.HttpGet;


/**
 * each one of these controllers should have @FXML methods to go from screen to screen
 * @author Ashwin
 */
public class RecommendationController extends ControlledScreen implements Initializable {
    ScreensController myController;
    public ArrayList<String> artist = new ArrayList<String>();
    public ArrayList<String> name = new ArrayList<String>();
    public ArrayList<String> numListens = new ArrayList<String>();
    public String key = "d8dec72bb448436493edcb1dec93e22d";
    public List<SongInfo> recommendations;
    public String user;
    
    @FXML
    public void handleLoadProfile(){
        Singleton.load();
    }
    
    @FXML
    public void handleSaveProfile(){
        Singleton.save();
    }

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
                return null;
            }

            // read from the URL
            Scanner scan = null;
            try {
                scan = new Scanner(myurl.openStream());
            } catch (Exception e) {
                System.out.println("Could not connect to " + jsonURL);
                return null;
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
        List<NameValuePair> params = new ArrayList<NameValuePair>(3);
        user = login.getText();
        ArrayList<PaginatedResult<Track>> recentTracksList;
        recentTracksList = new ArrayList<PaginatedResult<Track>>();
        
        for(int page=0; page<5; page++){
            recentTracksList.add(User.getRecentTracks(user, page, 100, key));
        }
        HashMap<String, SongForPosting> songs = new HashMap<>();
        for(PaginatedResult<Track> recentTracks : recentTracksList){
            for(Track t : recentTracks){
                System.out.println(t.getArtist());
                System.out.println(t.getName());
                System.out.println(t.getMbid());
                
                SongForPosting currentSong = songs.get(t.getMbid());
                
                if(currentSong != null){
                    currentSong.incPlayCount();
                }
                else{
                    currentSong = new SongForPosting(t.getName(), t.getArtist(), t.getMbid());
                    songs.put(t.getMbid(), currentSong);
                }
            }
        }
        
        CloseableHttpClient httpclient = HttpClients.createDefault();
        
        params.add(new BasicNameValuePair("user_id", login.getText()));
        // Request parameters and other properties.
        int i = 0;
        for(String key : songs.keySet()){
            
            SongForPosting currentSong = songs.get(key);

            params.add(new BasicNameValuePair("song_name", currentSong.getSongName()));
            params.add(new BasicNameValuePair("song_artist", currentSong.getSongArtist()));
            params.add(new BasicNameValuePair("song_id", currentSong.getSongId()));
            params.add(new BasicNameValuePair("play_count", new Integer(currentSong.getPlayCount()).toString()));
            
        }
        HttpPost httppost = new HttpPost("http://52.38.71.139:8000/adduser");
        httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

        //Execute and get the response.
        //HttpPost httppost = new HttpPost("http://52.38.71.139:8000/users");
        try{
            httpclient.execute(httppost);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        RecResponse recs = tryUserRecs(httpclient);
        
        HashSet<SongResponse> set = new HashSet<>(recs.getRecs());
        recs.getRecs().clear();
        recs.getRecs().addAll(set);
        
        System.out.println(recs);
        try{
            recommendations = songResToInfo(recs.getRecs());
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println("Got recommendations");
        System.out.println(recommendations);
        Singleton.getInstance().getFavorites().addAll(recommendations);
        System.out.println(Singleton.getInstance().getFavorites());
    }
    
    public List<SongInfo> songResToInfo(List<SongResponse> res){
        List<SongInfo> songs = new ArrayList<>();
        for(SongResponse songResponse : res){
            SongInfo songInfo = new SongInfo(songResponse.getSongName(), "", songResponse.getSongArtist());
            
            songs.add(songInfo);
        }
        
        return songs;
    }
    
    public RecResponse tryUserRecs(CloseableHttpClient httpclient) throws IOException{
        HttpGet httpget = new HttpGet("http://52.38.71.139:8000/recs/" + login.getText());
        HttpResponse response = httpclient.execute(httpget);
        
        System.out.println("Got response!");
        
        HttpEntity entity = response.getEntity();
        InputStream instream = entity.getContent();
        StringWriter writer = new StringWriter();
        IOUtils.copy(instream, writer, "UTF-8");
        StringReader reader = new StringReader(writer.toString());
        JsonElement elem = new JsonParser().parse(reader);

        Gson gson = new GsonBuilder().create();
        RecResponse recs = gson.fromJson(elem, RecResponse.class);

        instream.close();
        return recs;
    }
}


