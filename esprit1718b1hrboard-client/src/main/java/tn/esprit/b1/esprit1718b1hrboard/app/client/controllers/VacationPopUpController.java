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
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import tn.esprit.b1.esprit1718b1hrboard.app.client.util.EJBLookupUtil;
import tn.esprit.b1.esprit1718b1hrboard.entities.Employee;
import tn.esprit.b1.esprit1718b1hrboard.entities.Project;
import tn.esprit.b1.esprit1718b1hrboard.entities.Vacation;
import tn.esprit.b1.esprit1718b1hrboard.entities.VacationType;
import tn.esprit.b1.esprit1718b1hrboard.services.EmployeeServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.ProjectServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.VacationServiceRemote;

/**
 * FXML Controller class
 *
 * @author Ghassen
 */
public class VacationPopUpController implements Initializable {

	@FXML
	private Label lblFirstLastName;
	@FXML
	private Label lblPost;
	@FXML
	private ImageView EmployeePic;
	@FXML
	private Label lblVacDays;
	@FXML
	private Label LblVaccTypeLbl;
	@FXML
	private Label lblSartDate;
	@FXML
	private Label lblEndDate;
	@FXML
	private JFXTextArea RespondingMessage;
	@FXML
	private JFXButton SetButton;

	public static Vacation v;
	@FXML
	private JFXToggleButton Confirm;
	@FXML
	private ListView<String> ProjectsList;
	@FXML
	private Label lblVacDays1;

	public static JFXDialog dialogSkill;

	private ObservableList<String> projects;
	ProjectServiceRemote proxyE;
	String jndiEmployee = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/ProjectService!tn.esprit.b1.esprit1718b1hrboard.services.ProjectServiceRemote";

	EmployeeServiceRemote proxyEs;
	String jndiEmployees = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/EmployeeService!tn.esprit.b1.esprit1718b1hrboard.services.EmployeeServiceRemote";

	public static VaccMenuController vm;
	VacationServiceRemote proxyV;
	String jndiVacation = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/VacationService!tn.esprit.b1.esprit1718b1hrboard.services.VacationServiceRemote";
	public static int idv;

	@FXML
	private StackPane WraperPane;
	@FXML
	private JFXTextArea SubmissionMessage;
	@FXML
	private Button ShowEmployeeProjectButton;

	private Project prj;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		if (v.getStatus() != null) {
			SetButton.setText("Update");
			SetButton.setStyle("-fx-background-color:#006600;");
			RespondingMessage.setText(v.getResponseMessage());
			if (v.getStatus() == true) {
				Confirm.setSelected(true);
				Confirm.setText("Confirmed");
			} else {
				Confirm.setSelected(false);
				Confirm.setText("Denied");
			}
		} else {
			Confirm.setSelected(true);
			if (Confirm.isSelected()) {
				Confirm.setText("Confirmed");
			}
		}
		proxyE = (ProjectServiceRemote) EJBLookupUtil.doLookup(jndiEmployee);
		LblVaccTypeLbl.setText(v.getVacationType().toString());
		lblFirstLastName.setText(v.getEmployee().getFirstName() + " " + v.getEmployee().getLastName());
		if (v.getEmployee().getVacationDays() != null) {
			lblVacDays.setText("Vacation days : " + v.getEmployee().getVacationDays().toString());
		} else {
			lblVacDays.setText("Vacation days : NONE");
		}
		projects = FXCollections.observableArrayList();
		List<Project> ProjL = GetEmployeeProject(v.getEmployee());
		for (Project vv : ProjL) {
			projects.add(vv.getName());
		}
		ProjectsList.setItems(projects);
		ProjectsList.getSelectionModel().selectedIndexProperty().addListener((v, ov, nv) -> {
			prj = ProjL.get(nv.intValue());
		});
		lblPost.setText(v.getEmployee().getPost().getEntitled());
		SubmissionMessage.setText(v.getSbmissionMessage());
		SimpleDateFormat sd = new SimpleDateFormat("dd/MMM/yyyy");
		lblEndDate.setText("End in : " + sd.format(v.getEndDate()));
		lblSartDate.setText("Starts in : " + sd.format(v.getStartDate()));
		long diff = v.getEndDate().getTime() - v.getStartDate().getTime();
		int days = (int) Math.abs(diff / 86400000);
		if (v.getVacationType().equals(VacationType.PERSONALDAYS)) {
			if ((days > v.getEmployee().getVacationDays())&&(v.getStatus()==null)){
				WarningPaneController.msg = "Request's days are more than his vacation days";
				ShowDialog();
			}
		}
	}

	@FXML
	private void SetButtonClicked(MouseEvent event) {
		proxyV = (VacationServiceRemote) EJBLookupUtil.doLookup(jndiVacation);
		proxyEs = (EmployeeServiceRemote) EJBLookupUtil.doLookup(jndiEmployees);
		Long millis = System.currentTimeMillis();
		Date date = new Date(millis);
		if (!RespondingMessage.getText().trim().isEmpty()) {
			if (Confirm.getText().equals("Confirmed")) {
				if (v.getVacationType().equals(VacationType.PERSONALDAYS)) {
					if (v.getEmployee().getVacationDays() != null || v.getEmployee().getVacationDays() != 0) {
						long diff = v.getEndDate().getTime() - v.getStartDate().getTime();
						int days = (int) Math.abs(diff / 86400000);
						Employee eee = proxyEs.find(v.getEmployee().getId());
						if (days < v.getEmployee().getVacationDays()) {
							eee.setVacationDays(eee.getVacationDays() - days);
							if ((v.getStatus() == null) || (v.getStatus() != true)) {
								proxyEs.update(eee);
							}
						}
					}
				}
				v.setStatus(true);
				v.setResponseDate(date);
				v.setResponseMessage(RespondingMessage.getText());
			} else {
				if (v.getVacationType().equals(VacationType.PERSONALDAYS)) {
					if (v.getEmployee().getVacationDays() != null) {
						long diff = v.getEndDate().getTime() - v.getStartDate().getTime();
						int days = (int) Math.abs(diff / 86400000);
						Employee eee = proxyEs.find(v.getEmployee().getId());
						eee.setVacationDays(eee.getVacationDays() + days);
						if (v.getStatus() == null) {
							v.setStatus(false);
							v.setResponseDate(date);
							v.setResponseMessage(RespondingMessage.getText());
						} else if ((v.getStatus() != null) && (v.getStatus() == true)) {
							proxyEs.update(eee);
							v.setStatus(false);
							v.setResponseDate(date);
							v.setResponseMessage(RespondingMessage.getText());
						}
					}
				}
				v.setStatus(false);
				v.setResponseDate(date);
				v.setResponseMessage(RespondingMessage.getText());
			}
			proxyV.update(v);
			vm.INITAAT();
			vm.Refresh();
		} else {
			WarningPaneController.msg = "You should write a responding message !";
			ShowDialog();
		}

	}

	public List<Project> GetEmployeeProject(Employee empl) {
		if (empl.getSupervisor() != null) {
			return GetEmployeeProject(empl.getSupervisor());
		} else {
			proxyE = (ProjectServiceRemote) EJBLookupUtil.doLookup(jndiEmployee);
			return proxyE.findAllProjectsByEmployee(empl);
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

	public void ShowDialog() {
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

	@FXML
	private void ShowProjectClicked(MouseEvent event) {
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
	}

}