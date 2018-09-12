/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b1.esprit1718b1hrboard.app.client.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXToggleButton;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import tn.esprit.b1.esprit1718b1hrboard.app.client.util.EJBLookupUtil;
import tn.esprit.b1.esprit1718b1hrboard.entities.Employee;
import tn.esprit.b1.esprit1718b1hrboard.entities.Project;
import tn.esprit.b1.esprit1718b1hrboard.entities.Resignation;
import tn.esprit.b1.esprit1718b1hrboard.services.EmployeeServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.ProjectServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.ResignationServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.VacationServiceRemote;

/**
 * FXML Controller class
 *
 * @author Ghassen
 */
public class CheckResignationMenuController implements Initializable {

	@FXML
	private Label lblFirstLastName;
	@FXML
	private Label lblPost;
	@FXML
	private ImageView EmployeePic;
	@FXML
	private Label registredIn;
	@FXML
	private JFXTextArea RespondingMessage;
	@FXML
	private JFXButton SetButton;
	@FXML
	private JFXToggleButton Confirm;
	@FXML
	private ListView<String> ProjectsList;
	@FXML
	private Label lblVacDays1;
	@FXML
	private JFXTextArea SubmissionMessage;

	public static Resignation v;
	private ObservableList<String> projects;
	public static JFXDialog dialogSkill;

	EmployeeServiceRemote proxyEmployee;
	String jndiEmployee = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/EmployeeService!tn.esprit.b1.esprit1718b1hrboard.services.EmployeeServiceRemote";

	ProjectServiceRemote proxyP;
	String jndiProject = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/ProjectService!tn.esprit.b1.esprit1718b1hrboard.services.ProjectServiceRemote";

	ResignationServiceRemote proxyV;
	String jndiResignation = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/ResignationService!tn.esprit.b1.esprit1718b1hrboard.services.ResignationServiceRemote";
	@FXML
	private StackPane WraperPane;
	public static ResignationsMenuController vm;
	@FXML
	private Label lblresDate;
	public Employee empl = null;
	@FXML
	private Label lblEndContract;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		SimpleDateFormat sd = new SimpleDateFormat("dd/MMM/yyyy");
		if (v.getStatus() != null) {
			SetButton.setText("Update");
			SetButton.setStyle("-fx-background-color:#006600;");
			RespondingMessage.setText(v.getResponseMsg());
			registredIn.setText("* This demand was last updated In : " + sd.format(v.getResponseDate()) + " *");
			if (v.getStatus() == true) {
				Confirm.setSelected(true);
				Confirm.setText("Confirmed");
			} else {
				Confirm.setSelected(false);
				Confirm.setText("Denied");
			}
		} else {
			registredIn.setVisible(false);
			Confirm.setSelected(true);
			if (Confirm.isSelected()) {
				Confirm.setText("Confirmed");
			}
		}
		proxyEmployee = (EmployeeServiceRemote) EJBLookupUtil.doLookup(jndiEmployee);
		Employee emp = proxyEmployee.EmployeeByResignation(v);
		empl = emp;
		if (emp.getContract().getEndDate() == null) {
			lblEndContract.setVisible(false);
		} else {
			lblEndContract.setVisible(true);
			lblEndContract.setText("Contract Ends in : " + sd.format(emp.getContract().getEndDate()));
		}
		lblFirstLastName.setText(emp.getFirstName() + " " + emp.getLastName());
		projects = FXCollections.observableArrayList();
		List<Project> ProjL = GetEmployeeProject(emp);
		for (Project vv : ProjL) {
			projects.add(vv.getName());
		}
		ProjectsList.setItems(projects);
		ProjectsList.getSelectionModel().selectedIndexProperty().addListener((v, ov, nv) -> {
			Project prj = ProjL.get(nv.intValue());
			ShowEmployeeProjectPopUpController.pj = prj;
			Pane child = null;
			try {
				child = FXMLLoader.load(getClass().getResource("/views/ShowEmployeeProjectPopUp.fxml"));
			} catch (IOException ex) {
			}
			JFXDialogLayout dialogLayout = new JFXDialogLayout();
			dialogLayout.getChildren().add(child);
			dialogSkill = new JFXDialog(WraperPane, dialogLayout, JFXDialog.DialogTransition.CENTER);
			dialogSkill.show();
		});
		lblPost.setText(emp.getPost().getEntitled());
		SubmissionMessage.setText(v.getSubmissionMsg());
		lblresDate.setText("Requested resignation date : " + sd.format(v.getResignationDate()));

	}

	@FXML
	private void SetButtonClicked(MouseEvent event) {
		proxyV = (ResignationServiceRemote) EJBLookupUtil.doLookup(jndiResignation);
		proxyEmployee = (EmployeeServiceRemote) EJBLookupUtil.doLookup(jndiEmployee);
		Long millis = System.currentTimeMillis();
		Date date = new Date(millis);
		if (!RespondingMessage.getText().trim().isEmpty()) {
			v.setResponseDate(date);
			v.setResponseMsg(RespondingMessage.getText());
			if (Confirm.getText().equals("Confirmed")) {
				v.setStatus(true);
				if (empl != null) {
					Employee emm = empl;
					emm.setEndDay(v.getResignationDate());
					proxyEmployee.update(emm);
				}
			} else {
				if (empl != null) {
					Employee emm = empl;
					if (empl.getContract().getEndDate() != null) {
						emm.setEndDay(empl.getContract().getEndDate());
						proxyEmployee.update(emm);
					}
				}
				v.setStatus(false);
			}
			proxyV.update(v);
			vm.INITAAT();
			vm.Refresh();
		} else {
			ShowDialog();
		}
	}

	@FXML
	private void ConfirmClicked(MouseEvent event) {
		if (Confirm.isSelected()) {
			Confirm.setText("Denied");
		} else {
			Confirm.setText("Confirmed");
		}
	}

	public List<Project> GetEmployeeProject(Employee empl) {
		proxyP = (ProjectServiceRemote) EJBLookupUtil.doLookup(jndiProject);
		return proxyP.findAllProjectsByEmployee(empl);
	}

	public void ShowDialog() {
		WarningPaneController.msg = "You should write a responding message !";
		Pane child = null;
		try {
			child = FXMLLoader.load(getClass().getResource("/views/WarningPane.fxml"));
		} catch (IOException ex) {
		}
		JFXDialogLayout dialogLayout = new JFXDialogLayout();
		dialogLayout.getChildren().add(child);
		dialogSkill = new JFXDialog(WraperPane, dialogLayout, JFXDialog.DialogTransition.CENTER);
		dialogSkill.show();
	}
}
