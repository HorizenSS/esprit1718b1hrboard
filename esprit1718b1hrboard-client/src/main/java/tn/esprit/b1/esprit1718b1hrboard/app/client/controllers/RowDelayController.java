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
import java.util.concurrent.Delayed;

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
import tn.esprit.b1.esprit1718b1hrboard.entities.Delay;
import tn.esprit.b1.esprit1718b1hrboard.services.AbsenceServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.DelayServiceRemote;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class RowDelayController extends ListCell<Delay> {

	@FXML
	private AnchorPane rowAbs;
	@FXML
	private Label lblDelayDate;
	@FXML
	private Label lblhours;
	@FXML
	private JFXButton deleteAbsBtn;
	@FXML
	private JFXButton editAbsBtn;

	private StackPane stackAbs;
	private AnchorPane refreshPane;

	private FXMLLoader mLLoader;

	private DelayServiceRemote proxyDelay;

	public static Delay selectedDelay;

	private JFXDialog dialog;

	/**
	 * Initializes the controller class.
	 */

	public RowDelayController(StackPane anchorPane, AnchorPane anchorPane2) {
		stackAbs = anchorPane;
		refreshPane = anchorPane2;
	}

	@Override
	protected void updateItem(Delay delay, boolean empty) {
		super.updateItem(delay, empty);
		if (empty || delay == null) {
			setText(null);
			setGraphic(null);
		} else {
			if (mLLoader == null) {
				mLLoader = new FXMLLoader(getClass().getResource("/views/rowDelay.fxml"));
				mLLoader.setController(this);
				try {
					mLLoader.load();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (delay.getDateDelay() != null) {
				lblDelayDate.setText(delay.getDateDelay().toString());
			} else {
				lblDelayDate.setText("*******");
			}
			if (delay.getDurationPerHour() != null) {
				lblhours.setText(delay.getDurationPerHour().toString());
			} else {
				lblhours.setText("***");
			}

			deleteAbsBtn.setOnMouseClicked(ev -> {
				String jndiDelay = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/DelayService!tn.esprit.b1.esprit1718b1hrboard.services.DelayServiceRemote";
				proxyDelay = (DelayServiceRemote) EJBLookupUtil.doLookup(jndiDelay);

				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("CONFIRMATION");
				alert.setHeaderText("Delete Confirmation");
				alert.setContentText("Are you sure to delete this Absence ?");

				alert.showAndWait().ifPresent(response -> {
					if (response == ButtonType.OK) {
						proxyDelay.delete(delay);
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

			editAbsBtn.setOnMouseClicked(ev -> {
				selectedDelay = delay;
				DelayPopupUpdateController.refreshPa = refreshPane;

				Pane child = null;

				try {
					child = FXMLLoader.load(getClass().getResource("/views/DelayPopupUpdate.fxml"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				JFXDialogLayout absLayout = new JFXDialogLayout();
				absLayout.setMaxWidth(340);
				absLayout.setMinWidth(340);
				absLayout.setPrefWidth(340);
				absLayout.setPrefHeight(90);
				absLayout.setMaxHeight(90);
				absLayout.setMinHeight(90);

				absLayout.getChildren().add(child);

				dialog = new JFXDialog(stackAbs, absLayout, JFXDialog.DialogTransition.CENTER);
				dialog.show();
			});

			setText(null);
			setGraphic(rowAbs);

		}
	}
}
