/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.songbitmaven;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author csstudent
 */
public class SongInfo {
    private SongAlbumInfo album;
    private SongArtistInfo[] artists;
    private String name;
    private String uri;
    private String id;
    
    public SongInfo(String n, String alb, String art){
        name = n;
        album.setName(alb);
        for(int i = 0; i < artists.length; i++){
            artists[i].setArtistName(art);
        }
        
        
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
        String[] fullReturn = new String[artists.length];
        for(int i=0; i<artists.length; i++){
            fullReturn[i] = artists[i].getName();
        }
        return fullReturn;
    }
    
}
