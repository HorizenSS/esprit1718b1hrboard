/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b1.esprit1718b1hrboard.app.client.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextArea;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import tn.esprit.b1.esprit1718b1hrboard.app.client.tableModels.PostsTable;
import tn.esprit.b1.esprit1718b1hrboard.app.client.util.EJBLookupUtil;
import tn.esprit.b1.esprit1718b1hrboard.entities.Departement;
import tn.esprit.b1.esprit1718b1hrboard.entities.Post;
import tn.esprit.b1.esprit1718b1hrboard.services.DepartementServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.EmployeeServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.PostServiceRemote;

/**
 * FXML Controller class
 *
 * @author Ghassen
 */
public class AddPostPopUpController implements Initializable {

	@FXML
	private AnchorPane PopUpPane;
	@FXML
	private JFXTextArea Description;
	@FXML
	private Label lblFirstLastName;
	@FXML
	private TextField Code;
	@FXML
	private TextField Entitled;
	@FXML
	private JFXButton AddPostToList;
	PostServiceRemote proxyPost;
	DepartementServiceRemote proxydep;
	String jndi = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/PostService!tn.esprit.b1.esprit1718b1hrboard.services.PostServiceRemote";
	String jndidep = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/DepartementService!tn.esprit.b1.esprit1718b1hrboard.services.DepartementServiceRemote";
	@FXML
	private JFXComboBox<Departement> DepartmentComboBox;
	private ObservableList<Departement> deps;
	public static PostsMenuController pm;

	public static JFXDialog dialogSkill;
	@FXML
	private StackPane WraperPane;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		proxydep = (DepartementServiceRemote) EJBLookupUtil.doLookup(jndidep);
		List<Departement> dList = new ArrayList<>();
		dList = proxydep.findAll();
		deps = FXCollections.observableArrayList();
		for (Departement depart : dList) {
			deps.add(depart);
		}
		DepartmentComboBox.setItems((ObservableList<Departement>) deps);

	}

	@FXML
	private void OnAddPostToListClicked(MouseEvent event) {
		int x = 1;
		String Msg = null;
		proxyPost = (PostServiceRemote) EJBLookupUtil.doLookup(jndi);
		Post P = new Post();
		if (!Code.getText().equals("")) {
			P.setCodePost(Code.getText());
		} else {
			x = -1;
			Msg = "Set a Post code before confirming !";
		}
		if (!Entitled.getText().equals("")) {
			P.setEntitled(Entitled.getText());
		} else {
			x = -1;
			Msg = "Set a Name before confirming !";
		}
		if (!Description.getText().equals("")) {
			P.setDescription(Description.getText());
		} else {
			x = -1;
			Msg = "Set a description before confirming !";
		}
		if (DepartmentComboBox.getValue() != null) {
			P.setDepartement(DepartmentComboBox.getValue());
		} else {
			x = -1;
			Msg = "Select a department for this post !";
		}
		if (x != -1) {
			boolean t = false;
			for (Post pepe : proxyPost.findAll()) {
				if (pepe.getCodePost().equals(P.getCodePost())) {
					t = true;
				}
			}
			if (t) {
				WarningPaneController.msg = "This post's code already exists!";
				ShowDialog();
			} else {
				proxyPost.save(P);
				pm.INITAAT();
				pm.dialogSkill.close();
			}
		} else {
			WarningPaneController.msg = Msg;
			ShowDialog();
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
		dialogSkill = new JFXDialog(WraperPane, dialogLayout, JFXDialog.DialogTransition.BOTTOM);
		dialogSkill.show();
	}
}
