/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.songbitmaven;

import java.util.ArrayList;

/**
 *
 * @author csstudent
 */
public class SongList {
    private SongInfo[] items;
    
    public String[] getNames(){
        String[] fullReturn = new String[items.length];
        for(int i=0; i<items.length; i++){
            fullReturn[i] = items[i].getName();
        }
        return fullReturn;
    }
    
    public String[] getUrls(){
        String[] fullReturn = new String[items.length];
        for(int i=0; i<items.length; i++){
            fullReturn[i] = items[i].getUrl();
        }
        return fullReturn;
    }
    
    public String[] getAlbumNames(){
        String[] fullReturn = new String[items.length];
        for(int i=0; i<items.length; i++){
            fullReturn[i] = items[i].getAlbumName();
        }
        return fullReturn;
    }
    
    public String[] getIDs(){
        String[] fullReturn = new String[items.length];
        for(int i=0; i<items.length; i++){
            fullReturn[i] = items[i].getID();
        }
        return fullReturn;
    }
    
    public SongInfo[] getInfo(){
        return items;
    }
    
    @SuppressWarnings("empty-statement")
    public ArrayList<String[]> getArtistNames(){
        ArrayList<String[]> fullReturn = new ArrayList<>();
        for(int i=0; i<items.length; i++){
            fullReturn.add(items[i].getArtistNames());
        }
        return fullReturn;
    }
    
}
