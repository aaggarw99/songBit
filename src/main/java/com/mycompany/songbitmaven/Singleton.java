/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.songbitmaven;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author csstudent
 */
public class Singleton implements Serializable{
    private ArrayList<SongInfo> favorites = new ArrayList<SongInfo>();
    private static transient Singleton singleton;
   
    /* A private Constructor prevents any other 
     * class from instantiating.
     */
    private Singleton(){}
   
    /* Static 'instance' method */
    public static Singleton getInstance() {
       init();
       return singleton;
    }
    
    public ArrayList<SongInfo> getFavorites(){
        return favorites;
    }
    
    public void resetFavorites(){
        favorites = new ArrayList<SongInfo>();
    }
    
    public void addToFavorites(SongInfo s){
        favorites.add(s);
    }
    
    private static void init() {
        if (singleton == null) {
            load();
        }
    }
    
    public static void load(){
        try{
            FileInputStream fileIn = new FileInputStream("settings.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            singleton = (Singleton) in.readObject();
            in.close();
            fileIn.close();
            System.out.println("Loaded data successfully");
        }catch(IOException i){
           singleton = new Singleton();
        }catch(ClassNotFoundException c){
           System.out.println("Employee class not found");
           c.printStackTrace();
        }
    }
        
    public static void save() {
        init();
        try {
           FileOutputStream fileOut =
           new FileOutputStream("settings.ser");
           ObjectOutputStream out = new ObjectOutputStream(fileOut);
           out.writeObject(singleton);
           out.close();
           fileOut.close();
           System.out.println("Serialized data is saved in settings.ser");
        }catch(IOException i) {
            i.printStackTrace();
        }    
    }
    
}
