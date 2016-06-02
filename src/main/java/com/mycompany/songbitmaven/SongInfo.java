/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.songbitmaven;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author csstudent
 */
public class SongInfo {
    private SongAlbumInfo album;
    private List<SongArtistInfo> artists = new ArrayList<>();
    private String name;
    private String uri;
    private String id;
    
    public SongInfo(String n, String alb, String art){
        
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
    
    public String[] getArtistNames(){
        String[] fullReturn = new String[artists.size()];
        for(int i=0; i<artists.size(); i++){
            fullReturn[i] = artists.get(i).getName();
        }
        return fullReturn;
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
    }
    
    public String toString(){
        return "(" + name + ", " + artists.toString() + ")";
    }
    
}
