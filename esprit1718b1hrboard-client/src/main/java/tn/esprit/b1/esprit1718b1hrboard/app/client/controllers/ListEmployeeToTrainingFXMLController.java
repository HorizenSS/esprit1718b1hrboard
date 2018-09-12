/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b1.esprit1718b1hrboard.app.client.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXRadioButton;

import java.io.PrintStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import tn.esprit.b1.esprit1718b1hrboard.app.client.util.EJBLookupUtil;
import tn.esprit.b1.esprit1718b1hrboard.entities.Employee;
import tn.esprit.b1.esprit1718b1hrboard.entities.EmployeeHasSkill;
import tn.esprit.b1.esprit1718b1hrboard.entities.EmployeeHasTraining;
import tn.esprit.b1.esprit1718b1hrboard.entities.EmployeeHasTrainingPk;
import tn.esprit.b1.esprit1718b1hrboard.entities.Training;
import tn.esprit.b1.esprit1718b1hrboard.services.TrainingServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.EmployeeHasSkillServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.EmployeeHasTrainingServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.EmployeeServiceRemote;

/**
 * FXML Controller class
 *
 * @author Majdi Rabie
 */
public class ListEmployeeToTrainingFXMLController implements Initializable {

	@FXML
	private JFXRadioButton RadioAllId;
	@FXML
	private JFXListView<Employee> EmployeeListId;
	@FXML
	private JFXRadioButton RadioEmployeeBySkillId;
	@FXML
	private JFXButton AddEmpoyeeToTrainingId;
	@FXML
	private AnchorPane AnchorPaneId;
	@FXML
	private ToggleGroup A;

	@FXML
	private StackPane StackPaneListEmployee;
	private TrainingNormalController tnC;
	public static Training tr;
	public static Employee employeeSelected;

	TrainingServiceRemote proxyTraining;
	String jndiTraining = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/TrainingService!tn.esprit.b1.esprit1718b1hrboard.services.TrainingServiceRemote";
	EmployeeServiceRemote proxyEmployee;
	String jndiEmployee = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/EmployeeService!tn.esprit.b1.esprit1718b1hrboard.services.EmployeeServiceRemote";
	EmployeeHasSkillServiceRemote proxyEmployeeHasSkill;
	String jndiEmployeeHasSkill = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/EmployeeHasSkillService!tn.esprit.b1.esprit1718b1hrboard.services.EmployeeHasSkillServiceRemote";
	EmployeeHasTrainingServiceRemote proxyEmployeeHasTraining;
	String jndiEmployeeHasTraining = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/EmployeeHasTrainingService!tn.esprit.b1.esprit1718b1hrboard.services.EmployeeHasTrainingServiceRemote";

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		List<EmployeeHasTraining> listEmployee = tnC.trSelected.getFormParticipations();
		// System.out.println(listEmployee);
	}

	@FXML
	private void OnAddEmployeeClicked(ActionEvent event) {
		List<Employee> employeesHasTraining = RowEmployeeToTrainingFXMLController.employeeChecked;
		for (Employee emp : employeesHasTraining) {
			EmployeeHasTrainingPk emppk1 = new EmployeeHasTrainingPk();
			emppk1.setIdEmployee(emp.getId());
			emppk1.setIdTraining(tnC.trSelected.getId());
			EmployeeHasTraining emphasT = new EmployeeHasTraining();
			emphasT.setEmployeeHasTrainingPk(emppk1);
			proxyEmployeeHasTraining = (EmployeeHasTrainingServiceRemote) EJBLookupUtil
					.doLookup(jndiEmployeeHasTraining);
			proxyEmployeeHasTraining.save(emphasT);
			

		}
		RowNTrainingController.dialogTraining.close();
	}

	@FXML
	void OnAllEmployee(ActionEvent event) {
		try {
			RowEmployeeToTrainingFXMLController.employeeChecked.clear();
			proxyEmployee = (EmployeeServiceRemote) EJBLookupUtil.doLookup(jndiEmployee);
			ObservableList<Employee> emp = FXCollections.observableArrayList(proxyEmployee.findAll());
			EmployeeListId.setItems(emp);
			EmployeeListId.setCellFactory(EmployeeListView -> new RowEmployeeToTrainingFXMLController());
		}

		catch (Exception e) {
			System.out.println("Erreur getall Employee ou affichage des celulles de la liste");
		}

	}

	@FXML
	void OnBySkillSelected(ActionEvent event) {
		RowEmployeeToTrainingFXMLController.employeeChecked.clear();
		proxyEmployee = (EmployeeServiceRemote) EJBLookupUtil.doLookup(jndiEmployee);
		proxyEmployeeHasSkill = (EmployeeHasSkillServiceRemote) EJBLookupUtil.doLookup(jndiEmployeeHasSkill);
		Set<EmployeeHasSkill> employeesHasSkill;
		List<EmployeeHasSkill> empp;
		empp = proxyEmployeeHasSkill.findEmployeeBySkill(tnC.trSelected.getTrainingName());
		employeesHasSkill = empp.stream().collect(Collectors.toSet());
		List<Employee> e = new ArrayList();
		for (EmployeeHasSkill EMP : employeesHasSkill) {
			e.add(EMP.getEmployee());
		}
		ObservableList<Employee> emp = FXCollections.observableArrayList(e);
		EmployeeListId.setItems(emp);
		EmployeeListId.setCellFactory(EmployeeListView -> new RowEmployeeToTrainingFXMLController());

	}

}
