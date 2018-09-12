/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b1.esprit1718b1hrboard.app.client.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import tn.esprit.b1.esprit1718b1hrboard.app.client.util.EJBLookupUtil;
import tn.esprit.b1.esprit1718b1hrboard.entities.Absence;
import tn.esprit.b1.esprit1718b1hrboard.entities.Employee;
import tn.esprit.b1.esprit1718b1hrboard.entities.Skill;
import tn.esprit.b1.esprit1718b1hrboard.entities.Training;
import tn.esprit.b1.esprit1718b1hrboard.services.EmployeeServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.SkillServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.TrainingServiceRemote;

/**
 * FXML Controller class
 *
 * @author Majdi Rabie
 */
public class UpdateTrainingController implements Initializable {

	@FXML
	private StackPane Stack_scene;
	@FXML
	private AnchorPane addNTraining;
	@FXML
	private Pane pane_scene;
	@FXML
	private JFXComboBox<String> nameId;
	@FXML
	private JFXButton AddBtnId;
	@FXML
	private JFXTextField placeId;
	@FXML
	private JFXTextField typeId;
	@FXML
	private JFXTextArea SubjectId;
	@FXML
	private JFXComboBox<String> trainerId;
	@FXML
	private JFXDatePicker startDateId;
	@FXML
	private JFXDatePicker endDateId;
	@FXML
	private Label labelUpdateTraining;
	private List<Employee> employeeList = new ArrayList<>();
	private Training tr = TrainingNormalController.trSelected;
	private ObservableList<String> employees;
    private ObservableList<String> skills;
    public static AnchorPane refreshPa;
	TrainingServiceRemote proxyTraining;
	
	EmployeeServiceRemote proxyEmployees;
	String jndiEmployee = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/EmployeeService!tn.esprit.b1.esprit1718b1hrboard.services.EmployeeServiceRemote";
	SkillServiceRemote proxySkill;
	String jndiSkill = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/SkillService!tn.esprit.b1.esprit1718b1hrboard.services.SkillServiceRemote";

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		proxySkill = (SkillServiceRemote) EJBLookupUtil.doLookup(jndiSkill);
		proxyEmployees = (EmployeeServiceRemote)EJBLookupUtil.doLookup(jndiEmployee);
		List<Skill> skillsList = new ArrayList<>();
		if (tr.getTrainingName() != null) {
			nameId.setPromptText(tr.getTrainingName());
		} else {
			nameId.setPromptText("choose an Skill");
		}
    	skillsList=proxySkill.findAll();
    	skills = FXCollections.observableArrayList();
    	for(Skill SKILL : skillsList){
    		skills.add( SKILL.getName());
		}
    	nameId.setItems((ObservableList<String>) skills);
		typeId.setText(tr.getTrainingtype());
		placeId.setText(tr.getPlace());
		SubjectId.setText(tr.getSubject());
		System.out.println(tr.getTrainer());
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(tr.getTarainingDateStart());
		LocalDate DateStart = LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1,
				calendar.get(Calendar.DAY_OF_MONTH));
		startDateId.setValue(DateStart);
		Calendar calendarEnd = new GregorianCalendar();
		calendarEnd.setTime(tr.getTarainingDateEnd());
		LocalDate DateEnd = LocalDate.of(calendarEnd.get(Calendar.YEAR), calendarEnd.get(Calendar.MONTH) + 1,
				calendarEnd.get(Calendar.DAY_OF_MONTH));
		endDateId.setValue(DateEnd);
		if (tr.getTrainer() != null) {
			trainerId.setPromptText(tr.getTrainer().getFirstName() + " " + tr.getTrainer().getLastName());

		} else {
			trainerId.setPromptText("choose an Employee");
		}
		nameId.setOnAction(e->{
		List<Employee> employeeList = new ArrayList<>();
    	employeeList = proxyEmployees.EmployeeBySkill(nameId.getSelectionModel().getSelectedItem());
    	employees = FXCollections.observableArrayList();
		for(Employee EMP : employeeList){
			employees.add( EMP.getFirstName()+" "+EMP.getLastName());    			
		}
		trainerId.setItems((ObservableList<String>) employees);
		});
	}

	@FXML
	private void OnAddNewTrainingClicked(ActionEvent event) {

		Date startdate = java.sql.Date.valueOf(startDateId.getValue());
    	Date enddate = java.sql.Date.valueOf(endDateId.getValue());
    	tr.setTrainingtype(typeId.getText());
    	tr.setPlace(placeId.getText());
    	tr.setSubject(SubjectId.getText());
    	tr.setTarainingDateStart(startdate);
    	tr.setTarainingDateEnd(enddate);
    	tr.setTrainingName(nameId.getValue());
    	String nomPrenom = trainerId.getValue();
    	String[] parts = nomPrenom.split(" ");
    	String firstName = parts[0]; 
    	String lastName = parts[1];
    	Employee emp;
    	emp = proxyEmployees.findbynameComplet(firstName,lastName);
    	tr.setTrainer(emp);
    	
    	String jndiTraining = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/TrainingService!tn.esprit.b1.esprit1718b1hrboard.services.TrainingServiceRemote";
    	proxyTraining = (TrainingServiceRemote)EJBLookupUtil.doLookup(jndiTraining);
     	Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("CONFIRMATION");
		alert.setHeaderText("Update Confiramtion");
		alert.setContentText("Are you sure to Update this Training ?");
		alert.showAndWait().ifPresent(response -> {
			if (response == ButtonType.OK) {
				proxyTraining.update(tr);
				Pane child = null;

				try {
					child = FXMLLoader.load(getClass().getResource("/views/TrainingNormal.fxml"));
				} catch (IOException ex) {
					ex.printStackTrace();
				}
				refreshPa.getChildren().clear();
    					refreshPa.getChildren().add(child);
    					RowNTrainingController.dialogTraining.close();

		}
		});
	}

}
