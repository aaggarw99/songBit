/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.songbitmaven;

import java.io.IOException;
import java.util.Arrays;
import javafx.util.Duration;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;


/**
 *
 * @author Ashwin
 */
public class ScreensController extends StackPane {
    public Button goToSearch;
    
    // this assigns a controller (node =  Parent root) to a particular screen
    private HashMap<String, Node> screens = new HashMap<>();
    private HashMap<String, ControlledScreen> screenObjects = new HashMap<>();
    
    public ScreensController(){
        super();   
        
    }
    
    @FXML
    public void addScreen(String name, Node node){
        screens.put(name, node);
    }
    
    public Node getScreen(String className){
        return screens.get(className);
    }
    
    public boolean unloadScreen(String name){
        if(screens.remove(name) == null){
            System.out.println("Screen does not exist");
            return false;
            
        }else{
            
         return true;   
        }
        
    }
    
    public boolean loadScreen(String name, String resource) {
        
          FXMLLoader myLoader = new FXMLLoader(getClass().getResource(resource));
          Parent load;
        try {
            load = (Parent) myLoader.load();
            ControlledScreen myScreenControler = ((ControlledScreen) myLoader.getController());
            myScreenControler.setScreenParent(this); 
            addScreen(name, load);
            screenObjects.put(name, myScreenControler);
            return true; 
        } catch (IOException ex) {
            Logger.getLogger(ScreensController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println(name);
            System.out.println(resource);
        }
        return false;
   } 
    
    public boolean setScreen(final String name) { 

     if(screens.get(name) != null) { //screen loaded 
        final DoubleProperty opacity = opacityProperty();
        
        screenObjects.get(name).refresh(null);
        //Is there is more than one screen 
        if(!getChildren().isEmpty()){ 
            Timeline fade = new Timeline( 
                new KeyFrame(Duration.ZERO, new KeyValue(opacity,1.0)), 
                new KeyFrame(new Duration(1000), new EventHandler<ActionEvent>() { 

                @Override 
                public void handle(ActionEvent t) { 
                   //remove displayed screen 
                   getChildren().remove(0); 
                   //add new screen 
                   getChildren().add(0, screens.get(name));
                   Timeline fadeIn; 
                    fadeIn = new Timeline( 
                            new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)), 
                            new KeyFrame(new Duration(800), new KeyValue(opacity, 1.0)));
                   fadeIn.play(); 
                } 
               }, new KeyValue(opacity, 0.0))); 
         fade.play(); 
        } else { 
         //no one else been displayed, then just show 
         setOpacity(0.0); 
         getChildren().add(screens.get(name)); 
         Timeline fadeIn = new Timeline( 
             new KeyFrame(Duration.ZERO, 
                          new KeyValue(opacity, 0.0)), 
             new KeyFrame(new Duration(2500), 
                          new KeyValue(opacity, 1.0))); 
         fadeIn.play(); 
       } 
       return true; 
     } else { 
         System.out.println("screen hasn't been loaded!\n");
         return false; 
   }
   
    
    }
    
   
}