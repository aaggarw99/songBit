/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.songbitmaven;

import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author csstudent
 */
public class SongDataSet {
    private SongList tracks;
    
    public String[] getNames(){
        return tracks.getNames();
    }
    
    public String[] getAlbumNames(){
        return tracks.getAlbumNames();
    }
    
    public ArrayList<String[]> getArtistNames(){
        return tracks.getArtistNames();
    }
    
}
