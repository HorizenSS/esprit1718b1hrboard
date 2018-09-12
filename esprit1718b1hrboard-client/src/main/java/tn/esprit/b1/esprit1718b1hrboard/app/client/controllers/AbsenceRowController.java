/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b1.esprit1718b1hrboard.app.client.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import tn.esprit.b1.esprit1718b1hrboard.app.client.util.EJBLookupUtil;
import tn.esprit.b1.esprit1718b1hrboard.entities.Absence;
import tn.esprit.b1.esprit1718b1hrboard.entities.Project;
import tn.esprit.b1.esprit1718b1hrboard.services.AbsenceServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.ProjectServiceRemote;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AbsenceRowController extends ListCell<Absence> {

	@FXML
	private JFXButton editAbsenceBtn;
	@FXML
	private JFXButton deleteAbscenceBtn;
	@FXML
	private Label lblabscenceDate;
	@FXML
	private Label lbljustified;
	@FXML
	private Label lblNameSub;
	@FXML
	private FontAwesomeIconView iconWarning;
	@FXML
	private Label lblWarning;

	private StackPane stackAbs;
	private AnchorPane refreshPane;

	private FXMLLoader mLLoader;

	@FXML
	private AnchorPane rowAbs;

	private JFXDialog dialog;

	public static Absence selectedAbs;
	
	public AbsenceServiceRemote proxyAbs ;

	public AbsenceRowController(StackPane anchorPane, AnchorPane anchorPane2) {
		stackAbs = anchorPane;
		refreshPane=anchorPane2;
	}

	@Override
	protected void updateItem(Absence abscence, boolean empty) {
		super.updateItem(abscence, empty);
		if (empty || abscence == null) {
			setText(null);
			setGraphic(null);
		} else {
			if (mLLoader == null) {
				mLLoader = new FXMLLoader(getClass().getResource("/views/absenceRow.fxml"));
				mLLoader.setController(this);
				try {
					mLLoader.load();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			iconWarning.setVisible(false);
			lblWarning.setVisible(false);

			lbljustified.setText(abscence.getJustified().toString());
			lblabscenceDate.setText(abscence.getDateOfAbsence().toString());
			if (abscence.getSubstituteEmployee() != null) {
				lblNameSub.setText(abscence.getSubstituteEmployee().getFirstName() + " "
						+ abscence.getSubstituteEmployee().getLastName());
			} else {
				lblNameSub.setText("NOT AFFECTED");
			}
			if (abscence.getStatus() && abscence.getSubstituteEmployee() == null) {
				iconWarning.setVisible(true);
				lblWarning.setVisible(true);
			}

			editAbsenceBtn.setOnAction(ev -> {

				selectedAbs = abscence;
				AddAbscencePopUpController.refreshPa=refreshPane;

				Pane child = null;

				try {
					child = FXMLLoader.load(getClass().getResource("/views/AddAbscencePopUp.fxml"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				JFXDialogLayout absLayout = new JFXDialogLayout();
				absLayout.setMaxWidth(340);
				absLayout.setMinWidth(340);
				absLayout.setPrefWidth(340);
				absLayout.setPrefHeight(150);
				absLayout.setMaxHeight(150);
				absLayout.setMinHeight(150);

				absLayout.getChildren().add(child);

				dialog = new JFXDialog(stackAbs, absLayout, JFXDialog.DialogTransition.CENTER);
				dialog.show();
			});
			
			deleteAbscenceBtn.setOnMouseClicked(ev->{
				
				String jndiAbs = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/AbsenceService!tn.esprit.b1.esprit1718b1hrboard.services.AbsenceServiceRemote";
				proxyAbs = (AbsenceServiceRemote) EJBLookupUtil.doLookup(jndiAbs);

				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("CONFIRMATION");
				alert.setHeaderText("Delete Confirmation");
				alert.setContentText("Are you sure to delete this Absence ?");

				alert.showAndWait().ifPresent(response -> {
					if (response == ButtonType.OK) {
						proxyAbs.delete(abscence);
						Pane child = null;
						try {
							child = FXMLLoader.load(getClass().getResource("/views/ListAbscenceAndDelays.fxml"));
						} catch (IOException ex) {
							// TODO Auto-generated catch block
							ex.printStackTrace();
						}
						refreshPane.getChildren().clear();
						refreshPane.getChildren().add(child);

						System.out.println("************deleting************");
					}
				});
				
			});

			setText(null);
			setGraphic(rowAbs);
		}
	}

}
