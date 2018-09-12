/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b1.esprit1718b1hrboard.app.client.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import tn.esprit.b1.esprit1718b1hrboard.app.client.util.EJBLookupUtil;
import tn.esprit.b1.esprit1718b1hrboard.entities.Employee;
import tn.esprit.b1.esprit1718b1hrboard.entities.Interview;
import tn.esprit.b1.esprit1718b1hrboard.entities.Vacation;
import tn.esprit.b1.esprit1718b1hrboard.entities.VacationType;
import tn.esprit.b1.esprit1718b1hrboard.services.EmployeeServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.VacationServiceRemote;

/**
 * FXML Controller class
 *
 * @author Ghassen
 */
public class VaccMenuController implements Initializable {

	@FXML
	private AnchorPane VaccMainPane;
	@FXML
	private ListView<Vacation> VaccationTreeView;
	@FXML
	private JFXButton RecentDemandsButton;
	@FXML
	private JFXButton CheckedDemandsButton;
	@FXML
	private StackPane WraperPane;
	@FXML
	private JFXButton UpdateItemButton;

	private ObservableList<Vacation> vacations;

	public static JFXDialog dialogSkill;
	public Vacation vindex;
	public URL u1;
	public ResourceBundle r1;

	VacationServiceRemote proxyV;
	String jndiVacation = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/VacationService!tn.esprit.b1.esprit1718b1hrboard.services.VacationServiceRemote";

	EmployeeServiceRemote proxyE;
	String jndiEmployee = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/EmployeeService!tn.esprit.b1.esprit1718b1hrboard.services.EmployeeServiceRemote";
	@FXML
	private JFXRadioButton nameSearchRadio;
	@FXML
	private ToggleGroup filter;
	@FXML
	private JFXRadioButton postSerchRadio;
	@FXML
	private JFXRadioButton departementSearchRadio;
	@FXML
	private JFXTextField critiriaSearch;
	@FXML
	private JFXDatePicker StartDatePicker;
	@FXML
	private JFXDatePicker EndDatePicker;
	@FXML
	private JFXButton findBtn;
	@FXML
	private JFXComboBox<VacationType> TypeComboBox;
	@FXML
	private JFXButton findTypeBtn;

	Long millis = System.currentTimeMillis();
	Date date = new Date(millis);
	@FXML
	private JFXButton CheckedDemandsButton1;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		vindex = new Vacation();
		proxyV = (VacationServiceRemote) EJBLookupUtil.doLookup(jndiVacation);
		vacations = FXCollections.observableArrayList();
		for (Vacation vv : proxyV.findAll()) {
			if (vv.getStartDate().after(date)) {
				vacations.add(vv);
			}
		}
		VaccationTreeView.setItems(vacations);
		VaccationTreeView.setCellFactory(EmployeeList -> new VaccDemandsRowController(this));
		VaccationTreeView.getSelectionModel().selectedItemProperty().addListener((v, ov, nv) -> {
			if (nv != null) {
				vindex = nv;
				System.out.println(vindex);
			}
		});
		ObservableList<VacationType> vt = FXCollections.observableArrayList();
		vt.addAll(VacationType.BEREAVEMENT, VacationType.MATERNITYLEAVE, VacationType.PERSONALDAYS,
				VacationType.SICKLEAVE);
		TypeComboBox.setItems(vt);
		if (nameSearchRadio.isSelected()) {
			critiriaSearch.setVisible(true);
			StartDatePicker.setVisible(false);
			EndDatePicker.setVisible(false);
			findBtn.setVisible(false);
			TypeComboBox.setVisible(false);
			findTypeBtn.setVisible(false);
		} else if (postSerchRadio.isSelected()) {
			critiriaSearch.setVisible(false);
			StartDatePicker.setVisible(true);
			EndDatePicker.setVisible(true);
			findBtn.setVisible(true);
			TypeComboBox.setVisible(false);
			findTypeBtn.setVisible(false);
		} else {
			critiriaSearch.setVisible(false);
			StartDatePicker.setVisible(false);
			EndDatePicker.setVisible(false);
			findBtn.setVisible(false);
			TypeComboBox.setVisible(true);
			findTypeBtn.setVisible(true);
		}
		nameSearchRadio.setOnMouseClicked(e -> {
			critiriaSearch.setVisible(true);
			StartDatePicker.setVisible(false);
			EndDatePicker.setVisible(false);
			findBtn.setVisible(false);
			TypeComboBox.setVisible(false);
			findTypeBtn.setVisible(false);
		});
		postSerchRadio.setOnMouseClicked(e -> {
			critiriaSearch.setVisible(false);
			StartDatePicker.setVisible(true);
			EndDatePicker.setVisible(true);
			findBtn.setVisible(true);
			TypeComboBox.setVisible(false);
			findTypeBtn.setVisible(false);
		});
		departementSearchRadio.setOnMouseClicked(eve -> {
			critiriaSearch.setVisible(false);
			StartDatePicker.setVisible(false);
			EndDatePicker.setVisible(false);
			findBtn.setVisible(false);
			TypeComboBox.setVisible(true);
			findTypeBtn.setVisible(true);
		});
	}

	@FXML
	private void RecentDemandsButton(MouseEvent event) {
		proxyV = (VacationServiceRemote) EJBLookupUtil.doLookup(jndiVacation);
		vacations = FXCollections.observableArrayList();
		for (Vacation vv : proxyV.findAll()) {
			if (vv.getStartDate().after(date)) {
				if (vv.getStatus() == null) {
					vacations.add(vv);
				}
			}
		}
		VaccationTreeView.setItems(vacations);
		VaccationTreeView.setCellFactory(EmployeeList -> new VaccDemandsRowController(this));
	}

	@FXML
	private void CheckedDemandsButtonClicked(MouseEvent event) {
		proxyV = (VacationServiceRemote) EJBLookupUtil.doLookup(jndiVacation);
		vacations = FXCollections.observableArrayList();
		for (Vacation vv : proxyV.findAll()) {
			if (vv.getStartDate().after(date)) {
				if (vv.getStatus() != null) {
					vacations.add(vv);
				}
			}
		}
		VaccationTreeView.setItems(vacations);
		VaccationTreeView.setCellFactory(EmployeeList -> new CheckedDemandsRowController(this));
	}

	public void ShowDialog(Vacation V) {
		VacationPopUpController.vm = this;
		VacationPopUpController.v = V;
		Pane child = null;
		try {
			child = FXMLLoader.load(getClass().getResource("/views/VacationPopUp.fxml"));
		} catch (IOException ex) {
		}
		JFXDialogLayout dialogLayout = new JFXDialogLayout();
		dialogLayout.getChildren().add(child);
		dialogSkill = new JFXDialog(WraperPane, dialogLayout, JFXDialog.DialogTransition.TOP);
		dialogSkill.show();
	}

	public void ShowWarning() {
		Pane child = null;
		try {
			child = FXMLLoader.load(getClass().getResource("/views/WarningPane.fxml"));
		} catch (IOException ex) {
		}
		JFXDialogLayout dialogLayout = new JFXDialogLayout();
		dialogLayout.getChildren().add(child);
		dialogSkill = new JFXDialog(WraperPane, dialogLayout, JFXDialog.DialogTransition.TOP);
		dialogSkill.show();
	}

	public void INITAAT() {
		this.initialize(u1, r1);
	}

	public void Refresh() {
		dialogSkill.close();
	}

	@FXML
	private void UpdateItmeClicked(MouseEvent event) {
		System.out.println(vindex);
		ShowDialog(vindex);
	}

	@FXML
	private void searchEmployees(KeyEvent event) {
		proxyV = (VacationServiceRemote) EJBLookupUtil.doLookup(jndiVacation);
		List<Vacation> lv = proxyV.findAll();
		if (critiriaSearch.getText().equals("")) {
			ObservableList<Vacation> names = FXCollections.observableArrayList(lv);
			VaccationTreeView.setItems(names);
			VaccationTreeView.setCellFactory(EmployeeList -> new VaccDemandsRowController(this));
		} else {
			List<Vacation> vf = lv.stream()
					.filter(e -> e.getEmployee().getFirstName().toLowerCase()
							.contains(critiriaSearch.getText().toLowerCase())
							|| e.getEmployee().getLastName().toLowerCase()
									.contains(critiriaSearch.getText().toLowerCase()))
					.collect(Collectors.toList());
			ObservableList<Vacation> names = FXCollections.observableArrayList(vf);
			VaccationTreeView.setItems(names);
			VaccationTreeView.setCellFactory(EmployeeList -> new VaccDemandsRowController(this));
		}
	}

	@FXML
	private void FindByDates(MouseEvent event) {
		proxyV = (VacationServiceRemote) EJBLookupUtil.doLookup(jndiVacation);
		List<Vacation> lv = proxyV.findAll();
		if ((EndDatePicker.getValue() != null) && (StartDatePicker.getValue() != null)) {
			Date Edate = Date.from(EndDatePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
			Date Sdate = Date.from(StartDatePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
			List<Vacation> vf = lv.stream()
					.filter(e -> (e.getStartDate().after(Sdate) || e.getStartDate().equals(Sdate))
							&& (e.getEndDate().before(Edate) || e.getEndDate().equals(Edate)))
					.collect(Collectors.toList());
			if (vf.isEmpty()) {
				WarningPaneController.msg = "No results found";
				ShowWarning();
			} else {
				ObservableList<Vacation> datess = FXCollections.observableArrayList(vf);
				VaccationTreeView.setItems(datess);
				VaccationTreeView.setCellFactory(EmployeeList -> new VaccDemandsRowController(this));
				VaccationTreeView.refresh();
			}
		} else if ((EndDatePicker.getValue() == null) && (StartDatePicker.getValue() != null)) {
			Date Sdate = Date.from(StartDatePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
			List<Vacation> vf = lv.stream()
					.filter(e -> (e.getStartDate().after(Sdate) || e.getStartDate().equals(Sdate)))
					.collect(Collectors.toList());
			if (vf.isEmpty()) {
				WarningPaneController.msg = "No results found";
				ShowWarning();
			} else {
				ObservableList<Vacation> datess = FXCollections.observableArrayList(vf);
				VaccationTreeView.setItems(datess);
				VaccationTreeView.setCellFactory(EmployeeList -> new VaccDemandsRowController(this));
				VaccationTreeView.refresh();
			}
		} else if ((EndDatePicker.getValue() != null) && (StartDatePicker.getValue() == null)) {
			Date Edate = Date.from(EndDatePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
			List<Vacation> vf = lv.stream().filter(e -> (e.getEndDate().before(Edate) || e.getEndDate().equals(Edate)))
					.collect(Collectors.toList());
			if (vf.isEmpty()) {
				WarningPaneController.msg = "No results found";
				ShowWarning();
			} else {
				ObservableList<Vacation> datess = FXCollections.observableArrayList(vf);
				VaccationTreeView.setItems(datess);
				VaccationTreeView.setCellFactory(EmployeeList -> new VaccDemandsRowController(this));
				VaccationTreeView.refresh();
			}
		} else {
			WarningPaneController.msg = "You should fill both date fields !";
			ShowWarning();
		}
	}

	@FXML
	private void FindByType(MouseEvent event) {
		proxyV = (VacationServiceRemote) EJBLookupUtil.doLookup(jndiVacation);
		List<Vacation> lv = proxyV.findAll();
		if (TypeComboBox.getValue() == null) {
			ObservableList<Vacation> types = FXCollections.observableArrayList();
			VaccationTreeView.setItems(types);
			VaccationTreeView.setCellFactory(EmployeeList -> new VaccDemandsRowController(this));
			WarningPaneController.msg = "Choose the vacation Type first !";
			ShowWarning();
		} else {
			List<Vacation> vf = lv.stream()
					.filter(f -> f.getVacationType().toString().equals(TypeComboBox.getValue().toString()))
					.collect(Collectors.toList());
			if (vf.isEmpty()) {
				ObservableList<Vacation> types = FXCollections.observableArrayList(vf);
				VaccationTreeView.setItems(types);
				VaccationTreeView.setCellFactory(EmployeeList -> new VaccDemandsRowController(this));
				VaccationTreeView.refresh();
				WarningPaneController.msg = "No results found";
				ShowWarning();
			} else {
				ObservableList<Vacation> types = FXCollections.observableArrayList(vf);
				VaccationTreeView.setItems(types);
				VaccationTreeView.setCellFactory(EmployeeList -> new VaccDemandsRowController(this));
				VaccationTreeView.refresh();
			}
		}
	}

	@FXML
	private void AllDemandsButtonClicked(MouseEvent event) {
		proxyV = (VacationServiceRemote) EJBLookupUtil.doLookup(jndiVacation);
		vacations = FXCollections.observableArrayList();
		for (Vacation vv : proxyV.findAll()) {
			vacations.add(vv);

		}
		VaccationTreeView.setItems(vacations);
		VaccationTreeView.setCellFactory(EmployeeList -> new VaccDemandsRowController(this));
	}
}
