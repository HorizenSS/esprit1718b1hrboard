/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b1.esprit1718b1hrboard.app.client.controllers;

import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author Ghassen
 */
public class LeaveMenuController implements Initializable {

    @FXML
    private AnchorPane MainLeaveMenuPane;
    @FXML
    private JFXButton VacationButton;
    @FXML
    private JFXButton ResignationButton;
    @FXML
    private Pane MenusPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	try {
            Pane P = FXMLLoader.load(getClass().getResource("/views/VaccMenu.fxml"));
            MenusPane.getChildren().clear();
            MenusPane.getChildren().add(P);
        } catch (IOException ex) {
            }
    }   

    @FXML
    private void OnVacationClicked(MouseEvent event) {
    	try {
            Pane P = FXMLLoader.load(getClass().getResource("/views/VaccMenu.fxml"));
            MenusPane.getChildren().clear();
            MenusPane.getChildren().add(P);
        } catch (IOException ex) {
            }
    }
    

    @FXML
    private void OnResignationClicked(MouseEvent event) {
    	try {
            Pane P = FXMLLoader.load(getClass().getResource("/views/ResignationsMenu.fxml"));
            MenusPane.getChildren().clear();
            MenusPane.getChildren().add(P);
        } catch (IOException ex) {
            }
    }
    
}
