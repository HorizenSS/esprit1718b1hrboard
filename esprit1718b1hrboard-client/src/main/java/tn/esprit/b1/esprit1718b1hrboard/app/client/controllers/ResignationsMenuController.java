/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b1.esprit1718b1hrboard.app.client.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import tn.esprit.b1.esprit1718b1hrboard.app.client.util.EJBLookupUtil;
import tn.esprit.b1.esprit1718b1hrboard.entities.Employee;
import tn.esprit.b1.esprit1718b1hrboard.entities.Resignation;
import tn.esprit.b1.esprit1718b1hrboard.entities.Vacation;
import tn.esprit.b1.esprit1718b1hrboard.services.EmployeeServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.ResignationServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.VacationServiceRemote;

/**
 * FXML Controller class
 *
 * @author Ghassen
 */
public class ResignationsMenuController implements Initializable {

	@FXML
	private StackPane WraperPane;
	@FXML
	private AnchorPane VaccMainPane;
	@FXML
	private ListView<Resignation> VaccationTreeView;
	@FXML
	private JFXButton RecentDemandsButton;
	@FXML
	private JFXButton CheckedDemandsButton;
	@FXML
	private JFXButton UpdateItemButton;

	public static JFXDialog dialogSkill;

	public URL u1;
	public ResourceBundle r1;

	ResignationServiceRemote proxy;
	String jndi = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/ResignationService!tn.esprit.b1.esprit1718b1hrboard.services.ResignationServiceRemote";

	EmployeeServiceRemote proxyE;
	String jndiEmployee = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/EmployeeService!tn.esprit.b1.esprit1718b1hrboard.services.EmployeeServiceRemote";

	private ObservableList<Resignation> Resignations;
	public Resignation ResIndex;
	@FXML
	private JFXTextField critiriaSearch;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		proxy = (ResignationServiceRemote) EJBLookupUtil.doLookup(jndi);
		Resignations = FXCollections.observableArrayList();
		for (Resignation vv : proxy.findAll()) {
			Resignations.add(vv);
		}
		VaccationTreeView.setItems(Resignations);
		VaccationTreeView.setCellFactory(EmployeeList -> new NewResRowController());
		VaccationTreeView.getSelectionModel().selectedItemProperty().addListener((v, ov, nv) -> {
			if (nv != null) {
				ResIndex = nv;
			}
		});
		u1 = url;
		r1 = rb;
	}

	@FXML
	private void RecentDemandsButton(MouseEvent event) {
		proxy = (ResignationServiceRemote) EJBLookupUtil.doLookup(jndi);
		Resignations = FXCollections.observableArrayList();
		for (Resignation vv : proxy.findAll()) {
			if (vv.getStatus() == null) {
				Resignations.add(vv);
			}
		}
		VaccationTreeView.setItems(Resignations);
		VaccationTreeView.refresh();
		VaccationTreeView.setCellFactory(EmployeeList -> new NewResRowController());

	}

	@FXML
	private void CheckedDemandsButtonClicked(MouseEvent event) {
		proxy = (ResignationServiceRemote) EJBLookupUtil.doLookup(jndi);
		Resignations = FXCollections.observableArrayList();
		for (Resignation vv : proxy.findAll()) {
			if (vv.getStatus() != null) {
				Resignations.add(vv);
			}
		}
		VaccationTreeView.setItems(Resignations);
		VaccationTreeView.refresh();
		VaccationTreeView.setCellFactory(EmployeeList -> new NewResRowController());
	}

	@FXML
	private void UpdateItmeClicked(MouseEvent event) {
		CheckResignationMenuController.v = ResIndex;
		CheckResignationMenuController.vm = this;
		Pane child = null;
		try {
			child = FXMLLoader.load(getClass().getResource("/views/CheckResignationMenu.fxml"));
		} catch (IOException ex) {
		}
		JFXDialogLayout dialogLayout = new JFXDialogLayout();
		dialogLayout.getChildren().add(child);
		dialogSkill = new JFXDialog(WraperPane, dialogLayout, JFXDialog.DialogTransition.CENTER);
		dialogSkill.show();
	}

	public void INITAAT() {
		this.initialize(u1, r1);
	}

	public void Refresh() {
		dialogSkill.close();
	}

	@FXML
	private void searchEmployees(KeyEvent event) {
		proxy = (ResignationServiceRemote) EJBLookupUtil.doLookup(jndi);
		List<Resignation> lv = proxy.findAll();
		proxyE = (EmployeeServiceRemote) EJBLookupUtil.doLookup(jndiEmployee);
		List<Employee> le = proxyE.findAll();
		if (critiriaSearch.getText().equals("")) {
			ObservableList<Resignation> res = FXCollections.observableArrayList(lv);
			VaccationTreeView.setItems(res);
			VaccationTreeView.setCellFactory(EmployeeList -> new NewResRowController());
		} else {
			List<Employee> ef = le.stream()
					.filter(emp -> (emp.getResignation() != null)
					&& (emp.getFirstName().toLowerCase().contains(critiriaSearch.getText().toLowerCase())
							|| emp.getLastName().toLowerCase().contains(critiriaSearch.getText().toLowerCase())))
					.collect(Collectors.toList());
			List<Resignation> fr = new ArrayList<>();
			for (Employee ep : ef) {
				fr.add(ep.getResignation());
			}
			ObservableList<Resignation> rs = FXCollections.observableArrayList(fr);
			VaccationTreeView.setItems(rs);
			VaccationTreeView.setCellFactory(EmployeeList -> new NewResRowController());
		}
	}
}
