/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.songbitmaven;

/**
 *
 * @author alex
 */
public class SongResponse {
    int exp_play_count;
    String song_name;
    String song_artist;
    
    public int getExpPlayCount(){
        return exp_play_count;
    }
    
    public String getSongName(){
        return song_name;
    }
    
    public String getSongArtist(){
        return song_artist;
    }
    
    public String toString(){
        return "(" + song_name + ", " + song_artist + ", " + exp_play_count + ")";
    }
}
