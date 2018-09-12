/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b1.esprit1718b1hrboard.app.client.controllers;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import org.controlsfx.control.CheckComboBox;
import tn.esprit.b1.esprit1718b1hrboard.app.client.tableModels.ProjectTable;
import tn.esprit.b1.esprit1718b1hrboard.services.ProjectServiceRemote;

/**
 * FXML Controller class
 *
 * @author ilyes
 */
public class GiveAwardToEmployee2FXML implements Initializable {

    @FXML
    private Button SendAwardt;
    @FXML
    private JFXTextArea EncouragementEmployee;
    @FXML
    private ComboBox<String> AwardsEmployee;
    @FXML
    private TableView<?> EmpoyeeProject;
    @FXML
    private TableColumn<?, ?> columnProject;
    @FXML
    private TableColumn<?, ?> columnedebuteDate;
    @FXML
    private TableColumn<?, ?> colomneEndDate;
    @FXML
    private JFXDatePicker dateDepart;
    @FXML
    private JFXDatePicker DateArriver;
    @FXML
    private Text x;
    @FXML
    private Label error;
     @FXML
    private ObservableList<String> ac;
	private ObservableList<ProjectTable> list_project;
	ProjectServiceRemote proxyEmployee;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	EmpoyeeProject.setVisible(false);
    	dateDepart.setVisible(false);
    	DateArriver.setVisible(false);
    	ac	= FXCollections.observableArrayList();
    	ac.add("Voiture");
    	ac.add("Voyage");
    	AwardsEmployee.setItems((ObservableList<String>) ac);
    //	EmpoyeeProject.getSelectionModel().selectedItemProperty()
		//.addListener((ObservableValue<? extends ProjectTable> observable, ProjectTable oldValue,
		//		ProjectTable newValue) -> {
		//	if (newValue == null) {
			//	return;
		//	}
			// System.out.println(newValue.getId());

		//});
    	}    
    private void populateEmployeeTableView() {
	}

    @FXML
    private void AddAccount(ActionEvent event) {
    }
     @FXML
    private void choisirAward(KeyEvent event) {
    	 if (AwardsEmployee.getValue().equals("Voyage"))
    	 {	EmpoyeeProject.setVisible(true);
     	dateDepart.setVisible(true);
     	DateArriver.setVisible(true);}
    }
    
}
