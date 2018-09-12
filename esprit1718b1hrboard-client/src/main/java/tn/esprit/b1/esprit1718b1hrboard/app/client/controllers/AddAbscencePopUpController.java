/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b1.esprit1718b1hrboard.app.client.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXToggleButton;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ResourceBundle;

import org.xnio.ssl.JsseSslUtils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import tn.esprit.b1.esprit1718b1hrboard.app.client.util.EJBLookupUtil;
import tn.esprit.b1.esprit1718b1hrboard.entities.Absence;
import tn.esprit.b1.esprit1718b1hrboard.entities.Departement;
import tn.esprit.b1.esprit1718b1hrboard.entities.Employee;
import tn.esprit.b1.esprit1718b1hrboard.services.AbsenceServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.EmployeeServiceRemote;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AddAbscencePopUpController implements Initializable {

	@FXML
	private AnchorPane rowAbs;
	@FXML
	private JFXToggleButton justifiedTglBtn;
	@FXML
	private JFXDatePicker updateDate;
	@FXML
	private JFXButton ConfirmUpdateBtn;
	@FXML
	private ComboBox<String> substituesCombo;

	private Absence abs = AbsenceRowController.selectedAbs;

	EmployeeServiceRemote proxyEmployee;
	AbsenceServiceRemote proxyAbsence;

	private ObservableList<String> emps;

	private List<Employee> employeeList = new ArrayList<>();

	public static AnchorPane refreshPa;
	@FXML
	private ImageView errorDate;

	List<Employee> employeeListfinal = new ArrayList<>();

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
		// justifiedTglBtn.getStyleClass().clear();
		updateDate.getStyleClass().clear();
		ConfirmUpdateBtn.getStyleClass().clear();
		// substituesCombo.getStyleClass().clear();
		rowAbs.getStyleClass().clear();

		populateOldValues();

	}

	public void populateOldValues() {
		if (abs.getDateOfAbsence() != null) {
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(abs.getDateOfAbsence());
			LocalDate dateAbs = LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1,
					calendar.get(Calendar.DAY_OF_MONTH));
			updateDate.setValue(dateAbs);
		} else {
			updateDate.setValue(null);

		}

		if (abs.getJustified() != null) {
			justifiedTglBtn.setSelected(abs.getJustified());
		} else {
			justifiedTglBtn.setSelected(false);

		}

		if (abs.getSubstituteEmployee() != null) {
			substituesCombo.setPromptText(
					abs.getSubstituteEmployee().getFirstName() + " " + abs.getSubstituteEmployee().getLastName());

		} else {
			substituesCombo.setPromptText("choose an Employee");

		}

		String jndiEmployee = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/EmployeeService!tn.esprit.b1.esprit1718b1hrboard.services.EmployeeServiceRemote";

		proxyEmployee = (EmployeeServiceRemote) EJBLookupUtil.doLookup(jndiEmployee);
		employeeList = proxyEmployee
				.findAll(); /*
							 * A modifier avec la list des employee non abscents
							 * et qui appartiennent au mm departement de celui
							 * qui est abscent
							 */
		String jndiAbsence = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/AbsenceService!tn.esprit.b1.esprit1718b1hrboard.services.AbsenceServiceRemote";
		proxyAbsence = (AbsenceServiceRemote) EJBLookupUtil.doLookup(jndiAbsence);
		employeeListfinal = new ArrayList<>();
		for (Employee employee : employeeList) {
			List<Absence> abscs = new ArrayList<>();
			abscs = proxyAbsence.findAllAbsByEmployee(employee);
			if (abscs.isEmpty()) {
				if (employee.getId() != EmployeesGeneralListController.employee.getId()) {
					if (employee.getPost() != null && EmployeesGeneralListController.employee.getPost() != null) {
						if (employee.getPost().getDepartement() != null
								&& EmployeesGeneralListController.employee.getPost().getDepartement() != null) {
							if (employee.getPost().getDepartement().getId() == EmployeesGeneralListController.employee
									.getPost().getDepartement().getId()) {
								System.out.println("!!!!!!!!!!!! NO ABS !!!!!!!!!!!!!!!!");
								employeeListfinal.add(employee);
							}
						}
					}
				}
			} else {
				for (Absence abss : abscs) {
					if (abss.getDateOfAbsence() != null) {
						if (abss.getDateOfAbsence().compareTo(java.sql.Date.valueOf(LocalDate.now())) != 0) {
							if (employee.getId() != EmployeesGeneralListController.employee.getId()) {
								if (employee.getPost() != null) {
									if (employee.getPost().getDepartement() != null) {
										if (employee.getPost().getDepartement()
												.getId() == EmployeesGeneralListController.employee.getId()) {
											employeeListfinal.add(employee);
										}
									}
								}
							}
						}
					}
				}
			}

		}
		emps = FXCollections.observableArrayList();
		for (Employee emp : employeeListfinal) {
			if ((emp.getFirstName() != null) && (emp.getLastName() != null)) {
				emps.add(emp.getFirstName().toUpperCase() + " " + emp.getLastName().toUpperCase());
			}
		}
		substituesCombo.setItems(emps);

	}

	@FXML
	private void OnActionConfirmUpdate(ActionEvent event) {

		Boolean ok = true;

		if (updateDate.getValue() == null) {
			ok = false;
			errorDate.setVisible(true);
		} else {
			LocalDate dateUp = updateDate.getValue();
			Date date = java.sql.Date.valueOf(dateUp);
			abs.setDateOfAbsence(date);
			errorDate.setVisible(false);

		}

		// System.out.println(justifiedTglBtn.isSelected());
		// System.out.println(substituesCombo.getSelectionModel().getSelectedIndex());

		abs.setJustified(justifiedTglBtn.isSelected());
		if (substituesCombo.getSelectionModel().getSelectedIndex() != -1) {
			abs.setSubstituteEmployee(
					employeeListfinal.get(substituesCombo.getSelectionModel().getSelectedIndex()));

		}

		String jndiAbsence = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/AbsenceService!tn.esprit.b1.esprit1718b1hrboard.services.AbsenceServiceRemote";

		proxyAbsence = (AbsenceServiceRemote) EJBLookupUtil.doLookup(jndiAbsence);

		if (ok == true) {
			proxyAbsence.update(abs);

			Pane child = null;

			try {
				child = FXMLLoader.load(getClass().getResource("/views/ListAbscenceAndDelays.fxml"));
			} catch (IOException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}

			refreshPa.getChildren().clear();
			refreshPa.getChildren().add(child);

		}

	}

}
