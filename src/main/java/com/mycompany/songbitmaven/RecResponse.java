/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.songbitmaven;

import java.util.List;

/**
 *
 * @author alex
 */
public class RecResponse {
    List<SongResponse> recs;
    
    public List<SongResponse> getRecs(){
        return recs;
    }
    
    public String toString(){
        return recs.toString();
    }
    
}
