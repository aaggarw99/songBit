/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.songbitmaven;

/**
 *
 * @author csstudent
 */
public class SongForPosting {
    private String songName;
    private String songArtist;
    private int playCount;
    private String songId;
    
    public SongForPosting(String name, String artist, String id){
        songName = name;
        songArtist = artist;
        songId = id;
        playCount = 1;
    }
    
    public String getSongName(){
        return songName;
    }
    public String getSongArtist(){
        return songArtist;
    }
    public int getPlayCount(){
        return playCount;
    }
    public void incPlayCount(){
        playCount++;
    }
    public String getSongId(){
        return songId;
    }
}
