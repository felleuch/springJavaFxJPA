package com.faiez.ihm;

import com.faiez.application.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Angie
 */
public class Screen3Controller implements Initializable, ControlledScreen {

    ScreensController myController;

    @FXML private MyMenu myMenu;
    @FXML private MyMenuController myMenuController;




    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    
    
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
        myMenuController.setMyController(screenParent);
    }

    @FXML
    private void goToScreen1(ActionEvent event){
       myController.setScreen(MainApplication.petScreenID);
    }
    
    @FXML
    private void goToScreen2(ActionEvent event){
       myController.setScreen(MainApplication.screen2ID);
    }
}
