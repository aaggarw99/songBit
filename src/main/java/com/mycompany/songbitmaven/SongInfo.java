/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.songbitmaven;


import java.io.Serializable;
import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author csstudent
 */
public class SongInfo implements Serializable{
    private SongAlbumInfo album = new SongAlbumInfo();
    private List<SongArtistInfo> artists = new ArrayList<>();
    private String artist = "Queen";
    private String name = "Bohemian Rhapsody";
    private String uri;
    private String id;
    private String preview_url;
    
    public SongInfo(String n, String alb, String art){
        name = n;
        album.setName(alb);
        if(art != null){
            this.setArtist(art);
        }
        else{
            if(this.artists.size() > 0){
                this.artist = artists.get(0).getName();
            }
        }
    }
    
    public SongInfo(){
        if(this.artists.size() > 0){
            this.artist = artists.get(0).getName();
        }
    }
    
    public String getUrl(){
        return this.preview_url;
    }
    
    public String getID(){
        return this.id;
    }
    
    public String getName(){
        return this.name;
    }
    
    public String getAlbumName(){
        return album.getName();
    }
    
    public void updateArtist(){
        this.artist = artists.get(0).getName();
    }
    
    public String[] getArtistNames(){
        String[] fullReturn = new String[artists.size()];
        for(int i=0; i<artists.size(); i++){
            fullReturn[i] = artists.get(i).getName();
        }
        return fullReturn;
    }
    
    public String getArtist(){
        return this.artist;
    }
    
    public void setName(String name){
        System.out.println("Setting name");
        this.name = name;
    }
    
    public void setArtist(String name){
        System.out.println("Setting artist");
        SongArtistInfo artist = new SongArtistInfo();
        artist.setName(name);
        this.artists.add(artist);
        this.artist = name;
    }
    
    public String toString(){
        return "(" + name + ", " + artists.toString() + ")";
    }
    
}
