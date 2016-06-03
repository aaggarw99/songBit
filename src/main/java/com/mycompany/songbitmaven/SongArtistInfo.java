/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.songbitmaven;

import java.io.Serializable;

/**
 *
 * @author csstudent
 */
public class SongArtistInfo implements Serializable{
    private String name;
    private String uri;
    private String id;
    
    public String getName(){
        return name;
    }
    public void setArtistName(String artName){
        name = artName;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String toString(){
        return name;
    }
    
}
