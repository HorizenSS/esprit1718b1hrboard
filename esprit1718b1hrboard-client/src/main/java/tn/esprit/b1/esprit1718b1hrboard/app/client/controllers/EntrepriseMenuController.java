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
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Ghassen
 */
public class EntrepriseMenuController implements Initializable {

    @FXML
    private Pane MenusPane;
    @FXML
    private JFXButton HierarchiesButton;
    @FXML
    private JFXButton PostsButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	Pane p;
		try {
			p = FXMLLoader.load(getClass().getResource("/views/DepartmentsHierarchy.fxml"));
	    	initialRefresh(p);
		} catch (IOException e) {
		}
    }    
    public void initialRefresh(Pane mainp){
    	MenusPane.getChildren().clear();	
    	    MenusPane.getChildren().add(mainp);
    }

    @FXML
    private void OnHierarchiesClicked(MouseEvent event) {
    	try {
            Pane P = FXMLLoader.load(getClass().getResource("/views/DepartmentsHierarchy.fxml"));
            initialRefresh(P);
        } catch (IOException ex) {
            }
    }

    @FXML
    public void OnPostsClicked(MouseEvent event) {
    	try {
            Pane P = FXMLLoader.load(getClass().getResource("/views/PostsMenu.fxml"));
            initialRefresh(P);
        } catch (IOException ex) {
        }
    }
    
}
