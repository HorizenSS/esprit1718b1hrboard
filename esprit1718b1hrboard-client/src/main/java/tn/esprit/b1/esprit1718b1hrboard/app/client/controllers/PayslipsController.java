package tn.esprit.b1.esprit1718b1hrboard.app.client.controllers;

import java.awt.Button;
import java.awt.Image;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.naming.NamingException;

import com.jfoenix.controls.JFXDialog;

import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXRadioButton;

import javafx.event.*;
import javafx.scene.control.ToggleGroup;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import javafx.util.Callback;
import javafx.scene.Parent;
import javafx.scene.Scene;
import tn.esprit.b1.esprit1718b1hrboard.app.client.util.EJBLookupUtil;
import tn.esprit.b1.esprit1718b1hrboard.entities.PaySlip;
import tn.esprit.b1.esprit1718b1hrboard.services.PaySlipServiceRemote;

public class PayslipsController  implements Initializable {

    @FXML
    private Label lbl1;
    
    @FXML
    private AnchorPane PayslipsPaneId;
    
    @FXML
    private StackPane stackPane;

    @FXML
    private ListView<PaySlip> PayslipsList;
    
    @FXML
    private JFXRadioButton getAll;

    @FXML
    private ToggleGroup a;

    @FXML
    private JFXRadioButton Rejected;

    @FXML
    private JFXRadioButton Accepted;

    @FXML
    private JFXRadioButton InProcess;

    @FXML
    void OnAccepted(ActionEvent event) throws NamingException {
    	
    	 proxyPayslips = (PaySlipServiceRemote) EJBLookupUtil.doLookup(jndiPayslips);
 		

			ObservableList<PaySlip> ps = FXCollections.observableArrayList(proxyPayslips.getConfirmed());
			PayslipsList.setItems(ps);
			PayslipsList.setCellFactory(payslipRequest -> new PayslipRowController());
		
	   

    }

    @FXML
    void OnGetAll(ActionEvent event) {
    	 proxyPayslips = (PaySlipServiceRemote) EJBLookupUtil.doLookup(jndiPayslips);
 		

			ObservableList<PaySlip> ps = FXCollections.observableArrayList(proxyPayslips.findAll());
			PayslipsList.setItems(ps);
			PayslipsList.setCellFactory(payslipRequest -> new PayslipRowController());
		
	   

    }

    @FXML
    void OnInProcess(ActionEvent event) throws NamingException {
    	
    	 proxyPayslips = (PaySlipServiceRemote) EJBLookupUtil.doLookup(jndiPayslips);
 		

			ObservableList<PaySlip> ps = FXCollections.observableArrayList(proxyPayslips.getInProcess());
			PayslipsList.setItems(ps);
			PayslipsList.setCellFactory(payslipRequest -> new PayslipRowController());
		
	   

    }

   

    @FXML
    void OnRejected(ActionEvent event) throws NamingException {
    	
    	 proxyPayslips = (PaySlipServiceRemote) EJBLookupUtil.doLookup(jndiPayslips);
 		

			ObservableList<PaySlip> ps = FXCollections.observableArrayList(proxyPayslips.getRejected());
			PayslipsList.setItems(ps);
			PayslipsList.setCellFactory(payslipRequest -> new PayslipRowController());
		
	   

    }
    
    PaySlipServiceRemote proxyPayslips;
    String jndiPayslips = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/PaySlipService!tn.esprit.b1.esprit1718b1hrboard.services.PaySlipServiceRemote";
   
    public static JFXDialog dialogSkill;
    public static PaySlip psSelected;
    
	@SuppressWarnings("restriction")
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
		
		 
			   proxyPayslips = (PaySlipServiceRemote) EJBLookupUtil.doLookup(jndiPayslips);
		

				ObservableList<PaySlip> ps = FXCollections.observableArrayList(proxyPayslips.findAll());
				PayslipsList.setItems(ps);
				PayslipsList.setCellFactory(payslipRequest -> new PayslipRowController());
			
		   
		 
		   
		  
		   
		
		
	}
	
    @FXML
    void OnPayslipClicked() throws IOException {
    	
    	
    	
    	psSelected = PayslipsList.getSelectionModel().getSelectedItem(); 
    	
    	
    	Pane child = null;
		try {
			child = FXMLLoader.load(getClass().getResource("/views/PayslipDetailsPopUp.fxml"));
		} catch (IOException ex) {
		}

		JFXDialogLayout dialogLayout = new JFXDialogLayout();
		dialogLayout.getChildren().add(child);
		dialogSkill = new JFXDialog(stackPane, dialogLayout, JFXDialog.DialogTransition.TOP);
		dialogSkill.setTranslateY(-100);
		dialogSkill.show();

    }

}
