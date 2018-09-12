package tn.esprit.b1.esprit1718b1hrboard.app.client.controllers;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import tn.esprit.b1.esprit1718b1hrboard.app.client.util.EJBLookupUtil;
import tn.esprit.b1.esprit1718b1hrboard.entities.Employee;
import tn.esprit.b1.esprit1718b1hrboard.entities.Vacation;
import tn.esprit.b1.esprit1718b1hrboard.services.EmployeeServiceRemote;

public class PayrollCalculateController implements Initializable  {

    @FXML
    private AnchorPane PayslipsPaneId;

    @FXML
    private JFXRadioButton nameSearchRadio;

    @FXML
    private ToggleGroup filter;
    
    @FXML
    private StackPane stackPane;

    @FXML
    private JFXTextField critiriaSearch;

    @FXML
    private JFXListView<Employee> listEmployee;
    
    
    public static Employee empSelected;

    @FXML
    void OnKeyPressedClicked() {
    	
    	EmployeeServiceRemote proxy ;
    	String jndiEmp = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/EmployeeService!tn.esprit.b1.esprit1718b1hrboard.services.EmployeeServiceRemote";
		proxy = (EmployeeServiceRemote) EJBLookupUtil.doLookup(jndiEmp);
		List<Employee> lv = proxy.findAll();
		
		//critiriaSearch
		List<Employee> vf = lv.stream()
		.filter(e -> e.getFirstName().toLowerCase().contains(critiriaSearch.getText().toLowerCase())
				|| e.getLastName().toLowerCase().contains(critiriaSearch.getText().toLowerCase()))
		.collect(Collectors.toList());
		ObservableList<Employee> names = FXCollections.observableArrayList(vf);
		listEmployee.setItems(names);
		listEmployee.setCellFactory(EmployeeList -> new PayrollEmployeeRowController());

		

		

    	

    }
    
    public static JFXDialog dialogSkill;


    @FXML
    void OnListEmployeeClicked() {
    	empSelected = listEmployee.getSelectionModel().getSelectedItem(); 
    	
    	
    	Pane child = null;
		try {
			child = FXMLLoader.load(getClass().getResource("/views/PayrollEmployeePopUp.fxml"));
		} catch (IOException ex) {
		}

		JFXDialogLayout dialogLayout = new JFXDialogLayout();
		dialogLayout.getChildren().add(child);
		dialogSkill = new JFXDialog(stackPane, dialogLayout, JFXDialog.DialogTransition.TOP);
		dialogSkill.setTranslateY(-100);
		dialogSkill.show();


    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}