/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b1.esprit1718b1hrboard.app.client.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXRadioButton;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import tn.esprit.b1.esprit1718b1hrboard.entities.Employee;
import tn.esprit.b1.esprit1718b1hrboard.entities.EmployeeHasSkill;
import tn.esprit.b1.esprit1718b1hrboard.entities.EmployeeHasTraining;
import tn.esprit.b1.esprit1718b1hrboard.entities.Training;
import tn.esprit.b1.esprit1718b1hrboard.services.EmployeeHasTrainingServiceRemote;

/**
 * FXML Controller class
 *
 * @author Majdi Rabie
 */
public class RowEmployeeToTrainingFXMLController extends ListCell<Employee> implements Initializable {

	@FXML
	private StackPane stackPane;

	@FXML
	private AnchorPane ROWEmpID;

	@FXML
	private ImageView ImgId;

	@FXML
	private Label labelId;

	@FXML
	private Label postID;

	@FXML
	private Label emailID;

	@FXML
	private JFXCheckBox CheboxId;


	@FXML
	private Label labelNoteId;


	private FXMLLoader mLLoader;
	public static List<Employee> employeeChecked = new ArrayList<>();

	/**
	 * Initializes the controller class.
	 */
	@Override
	protected void updateItem(Employee employee, boolean empty) {
		super.updateItem(employee, empty);
		if (empty || employee == null) {
			setText(null);
			setGraphic(null);
		} else {
			if (mLLoader == null) {
				mLLoader = new FXMLLoader(
						getClass().getClassLoader().getResource("views/RowEmployeeToTrainingFXML.fxml"));
				mLLoader.setController(this);
				try {
					mLLoader.load();
				} catch (IOException e) {
					System.out.println("ma7abech iloadi ");
				}

			}
			/////////////////////////////////////
			//List<EmployeeHasSkill> l = new ArrayList();
			Float i = 0.0f;
			String n = "";
			Set<EmployeeHasSkill> l = new HashSet<>();
			l = employee.getSpecificationsSkills();
			
			for (EmployeeHasSkill employeeHasSkill : l) {
				if (employeeHasSkill.getSkill().getName().equals( TrainingNormalController.trSelected.getTrainingName())) {
					i = employeeHasSkill.getSkillNote();
					n = employeeHasSkill.getSkill().getName();
				}
			}

			////////////////////////////////////////
			labelId.setText(employee.getFirstName() + " " + employee.getLastName());
			postID.setText(employee.getPost().getEntitled());
			emailID.setText(employee.getEmail());

			for (EmployeeHasTraining employee2 : TrainingNormalController.trSelected.getFormParticipations()) {
				
				if (employee2.getEmployee().getId() == employee.getId()) {
					System.out.println(employee2.getEmployee().getFirstName());
					CheboxId.setSelected(true);
					
				}
			}
			if (i != 0.0f) {
				labelNoteId.setText(new DecimalFormat("#.#").format(i));
			}else{
				labelNoteId.setText("hasn't");
			}
			CheboxId.setOnAction((ev) -> {
				//ListEmployeeToTrainingFXMLController.employeeSelected = employee;
				
				if (CheboxId.isSelected()) {
					employeeChecked.add(employee);
				} else if  (!CheboxId.isSelected() && employeeChecked.contains(employee)){
						employeeChecked.remove(employee);
						
				}
				for (Employee emp : employeeChecked) {
					System.out.println(emp.getFirstName() + " " + emp.getLastName());
					
				}

			});

			setText(null);
			setGraphic(ROWEmpID);

		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

}
