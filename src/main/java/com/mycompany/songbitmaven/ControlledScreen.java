/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.songbitmaven;

import javafx.event.ActionEvent;

/**
 *
 * @author Ashwin
 */
public interface ControlledScreen {
    public void setScreenParent(ScreensController screenPage);
    public void goToSearch(ActionEvent e);
    public void goToRecommend(ActionEvent e);
    public void goToFavorites(ActionEvent e);
    public void goToPlayingSong(ActionEvent e);
    public void goToSettings(ActionEvent e);
    
    
}
