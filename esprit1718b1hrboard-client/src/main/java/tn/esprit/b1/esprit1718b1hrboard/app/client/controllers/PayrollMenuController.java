package tn.esprit.b1.esprit1718b1hrboard.app.client.controllers;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.hibernate.hql.internal.ast.tree.InitializeableNode;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import tn.esprit.b1.esprit1718b1hrboard.services.PaySlipServiceRemote;
import javafx.scene.layout.AnchorPane;


public class PayrollMenuController  implements Initializable {
	
	


    @FXML
    private JFXButton Payslips;
    @FXML
    private JFXButton CalculateSalary;

    @FXML
    private AnchorPane principal_payroll_pane;
    @FXML
    private AnchorPane PayslipsPaneId;



    @FXML
    private JFXButton Settings;

    @FXML
    void OnPayslipsClicked() {
    	
    	try {
			Pane child = FXMLLoader.load(getClass().getResource("/views/Payslips.fxml"));
			PayslipsPaneId.getChildren().clear();
			PayslipsPaneId.getChildren().add(child);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

    @FXML
    void OnSetingsClicked() {
    	
    	
    	try {
			Pane child = FXMLLoader.load(getClass().getResource("/views/PayrollSettings.fxml"));
			PayslipsPaneId.getChildren().clear();
			PayslipsPaneId.getChildren().add(child);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
    
    @FXML
    void OnCalculateSalary() {
    	
     	try {
			Pane child = FXMLLoader.load(getClass().getResource("/views/PayrollCalculate.fxml"));
			PayslipsPaneId.getChildren().clear();
			PayslipsPaneId.getChildren().add(child);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		try {
			Pane child = FXMLLoader.load(getClass().getResource("/views/Payslips.fxml"));
			PayslipsPaneId.getChildren().clear();
			PayslipsPaneId.getChildren().add(child);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	
		
	}

}
