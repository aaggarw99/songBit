/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.songbitmaven;

import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
/**
 *
 * @author Ashwin
 */
public abstract class ControlledScreen {
    public abstract void setScreenParent(ScreensController screenPage);
    public abstract void goToSearch(ActionEvent e);
    public abstract void goToRecommend(ActionEvent e);
    public void goToFavorites(ActionEvent e){};
    public abstract void goToPlayingSong(ActionEvent e);
    public abstract void goToSettings(ActionEvent e);
    public void refresh(ActionEvent e){};
    
}
