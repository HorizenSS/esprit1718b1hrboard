/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b1.esprit1718b1hrboard.app.client.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.ejb.Local;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import tn.esprit.b1.esprit1718b1hrboard.app.client.util.EJBLookupUtil;
import tn.esprit.b1.esprit1718b1hrboard.entities.Absence;
import tn.esprit.b1.esprit1718b1hrboard.entities.Delay;
import tn.esprit.b1.esprit1718b1hrboard.entities.Employee;
import tn.esprit.b1.esprit1718b1hrboard.entities.Task;
import tn.esprit.b1.esprit1718b1hrboard.services.AbsenceServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.DelayServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.TaskServiceRemote;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ListAbscenceAndDelaysController implements Initializable {

	@FXML
	private StackPane stackPaneAbscence;
	@FXML
	private JFXListView<Absence> listViewAbsence;
	@FXML
	private StackPane stackPaneDelays;
	@FXML
	private JFXListView<Delay> listViewDelays;

	ObservableList<Absence> absencesObsList;
	ObservableList<Delay> delayObsList;

	List<Absence> abscenceList = new ArrayList<>();
	List<Delay> delayList = new ArrayList<>();

	AbsenceServiceRemote proxyAbscence;
	TaskServiceRemote proxyTask;
	DelayServiceRemote proxyDelay;

	Employee employee = EmployeesGeneralListController.employee;
	@FXML
	private JFXButton addAbsenceBtn;
	@FXML
	private JFXButton addDelayBtn;

	private List<Task> listTasks = new ArrayList<>();
	@FXML
	private AnchorPane refereshPane;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO

		displayAllAbscence();
		displayAllDelays();
	}

	public void displayAllAbscence() {

		String jndiAbscence = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/AbsenceService!tn.esprit.b1.esprit1718b1hrboard.services.AbsenceServiceRemote";

		proxyAbscence = (AbsenceServiceRemote) EJBLookupUtil.doLookup(jndiAbscence);

		abscenceList = proxyAbscence.findAllAbsByEmployee(employee);

		absencesObsList = FXCollections.observableArrayList(abscenceList);

		listViewAbsence.setItems(absencesObsList);

		listViewAbsence.setCellFactory(absenceEmployeeLv -> new AbsenceRowController(stackPaneAbscence, refereshPane));

	}

	public void displayAllDelays() {
		String jndiDelay = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/DelayService!tn.esprit.b1.esprit1718b1hrboard.services.DelayServiceRemote";

		proxyDelay = (DelayServiceRemote) EJBLookupUtil.doLookup(jndiDelay);

		delayList = proxyDelay.findAllDelayByEmployee(employee);

		delayObsList = FXCollections.observableArrayList(delayList);

		listViewDelays.setItems(delayObsList);

		listViewDelays.setCellFactory(absenceEmployeeLv -> new RowDelayController(stackPaneDelays, refereshPane));

	}

	@FXML
	private void onActionAddAbscence(ActionEvent event) {
		String jndiTask = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/TaskService!tn.esprit.b1.esprit1718b1hrboard.services.TaskServiceRemote";

		proxyTask = (TaskServiceRemote) EJBLookupUtil.doLookup(jndiTask);

		listTasks = proxyTask.findTasksByEmployee(employee);
		int temp = 0;
		for (Task task : listTasks) {
			if (!task.getTaskPk().getName().equals("TASK1")) {
				Calendar calendar = new GregorianCalendar();
				Calendar calendar2 = new GregorianCalendar();
				calendar.setTime(task.getEndDate());
				calendar2.setTime(task.getStartDate());

				LocalDate dateEnd = LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1,
						calendar.get(Calendar.DAY_OF_MONTH));
				LocalDate startDate = LocalDate.of(calendar2.get(Calendar.YEAR), calendar2.get(Calendar.MONTH) + 1,
						calendar2.get(Calendar.DAY_OF_MONTH));

				LocalDate toDay = LocalDate.now();

				if (toDay.isAfter(startDate) && toDay.isBefore(dateEnd) && task.getImportance() >= 5) {
					temp = 1;
				}

			}

		}
		Absence abs = new Absence();
		abs.setAbsentEmployee(employee);
		LocalDate now = LocalDate.now();
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(now.toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		abs.setDateOfAbsence(date);

		abs.setJustified(false);

		if (temp == 0) {

			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("CONFIRMATION");
			alert.setHeaderText("Save Confiramtion");
			alert.setContentText("Confirm the new Absence !");

			alert.showAndWait().ifPresent(response -> {
				if (response == ButtonType.OK) {
					abs.setStatus(false);
					proxyAbscence.save(abs);
				}

			});

		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("CONFIRMATION");
			alert.setHeaderText("Save Confiramtion");
			alert.setContentText("THIS ABSENT EMPLOYEE HAVE A CRITICAL TASK -> AFFECT A SUBSTIUTE EMPLOYEE");

			alert.showAndWait().ifPresent(response -> {
				if (response == ButtonType.OK) {
					abs.setStatus(true);
					proxyAbscence.save(abs);
				}

			});
		}

		displayAllAbscence();

	}

	@FXML
	private void onActionAddDelay(ActionEvent event) {

		String jndiDelay = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/DelayService!tn.esprit.b1.esprit1718b1hrboard.services.DelayServiceRemote";

		proxyDelay = (DelayServiceRemote) EJBLookupUtil.doLookup(jndiDelay);

		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("CONFIRMATION");
		alert.setHeaderText("Save Confiramtion");
		alert.setContentText("Confirm the new Delay !");

		alert.showAndWait().ifPresent(response -> {
			if (response == ButtonType.OK) {
				Delay delay = new Delay();
				delay.setDateDelay(java.sql.Date.valueOf(LocalDate.now()));
				delay.setEmployee(EmployeesGeneralListController.employee);
				proxyDelay.save(delay);
				displayAllDelays();
			}

		});
	}

}
