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
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import tn.esprit.b1.esprit1718b1hrboard.app.client.util.EJBLookupUtil;
import tn.esprit.b1.esprit1718b1hrboard.entities.Departement;
import tn.esprit.b1.esprit1718b1hrboard.services.DepartementServiceRemote;

/**
 * FXML Controller class
 *
 * @author Ghassen
 */
public class DepartmentAddNewController implements Initializable {

	@FXML
	private JFXTextField namefield;
	@FXML
	private JFXTextField placefield;
	@FXML
	private JFXTextArea descriptionfield;
	@FXML
	private JFXTreeView<Departement> SupDepTree;
	@FXML
	private Label Old_supdep;
	@FXML
	private JFXButton ConfirmButton;

    public static JFXDialog dialogSkill;

	DepartementServiceRemote proxydep;
	String jndidep = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/DepartementService!tn.esprit.b1.esprit1718b1hrboard.services.DepartementServiceRemote";

	public static Departement Depart;
	public static int choice;
	public Departement val;
	public static DepartmentsHierarchyController dh;
	@FXML
	private StackPane WraperPane;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		proxydep = (DepartementServiceRemote) EJBLookupUtil.doLookup(jndidep);
		Departement dd = new Departement("root", "just for the test ", "here", null);
		TreeItem<Departement> root = new TreeItem<>(dd);
		root.setExpanded(true);
		SupDepTree.setRoot(root);
		SupDepTree.setShowRoot(false);
		List<Departement> dList = new ArrayList<>();
		dList = proxydep.findTopDeps();
		if (dList.contains(Depart)) {
			dList.remove(Depart);
		}
		CompleteUnderDeps(dList, root);
		SupDepTree.getSelectionModel().selectedItemProperty().addListener((v, oldv, newv) -> {
			if (newv != null) {
				val = newv.getValue();
			}
		});
		if (choice == 1) {
			ShowDepInfo(Depart);
			ConfirmButton.setText("Update");
		} else {
			Old_supdep.setVisible(false);
			ConfirmButton.setText("Add");
		}
	}

	public Departement getVal() {
		return val;
	}

	public void setVal(Departement val) {
		this.val = val;
	}

	public void CompleteUnderDeps(List<Departement> ldeps, TreeItem<Departement> parent) {
		if (ldeps.contains(Depart)) {
			ldeps.remove(Depart);
		}
		for (Departement d : ldeps) {
			TreeItem<Departement> x = new TreeItem<>(d);
			if (!d.getUnderDeps().isEmpty()) {
				CompleteUnderDeps(d.getUnderDeps(), x);
			}
			parent.getChildren().add(x);
		}
	}

	public void ShowDepInfo(Departement dep) {
		namefield.setText(dep.getName());
		placefield.setText(dep.getPlace());
		descriptionfield.setText(dep.getDescription());
		SupDepTree.setVisible(true);
		Old_supdep.setVisible(false);
		if (dep.getSuperiorDep() != null) {
			Old_supdep.setText(dep.getSuperiorDep().getName());
			Old_supdep.setVisible(true);
		}

	}
	public void ShowWarning(){
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
	private void ConfirmationClicked(MouseEvent event) {
		if (namefield.getText().equals("")) {
			WarningPaneController.msg = "please fill the name field";
			ShowWarning();
			return;
		} else if (descriptionfield.getText().equals("")) {
			WarningPaneController.msg = "please fill the description field";
			ShowWarning();
			return;
		} else if (placefield.getText().equals("")) {
			WarningPaneController.msg = "please fill the place field";
			ShowWarning();
			return;
		} else {
			Departement tadd = new Departement(namefield.getText(), descriptionfield.getText(), placefield.getText(),
					null);
			proxydep = (DepartementServiceRemote) EJBLookupUtil.doLookup(jndidep);
			if (choice == 1) {
				Depart.setName(tadd.getName());
				Depart.setPlace(tadd.getPlace());
				Depart.setDescription(tadd.getDescription());
				if (val != null) {
					Depart.setSuperiorDep(this.getVal());
				}
				proxydep.update(Depart);
			} else {
				tadd.setSuperiorDep(this.getVal());
				proxydep.save(tadd);
			}
			dh.INITAAT();
			dh.dialogSkill.close();
		}

	}
}