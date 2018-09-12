/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b1.esprit1718b1hrboard.app.client.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import tn.esprit.b1.esprit1718b1hrboard.app.client.util.EJBLookupUtil;
import tn.esprit.b1.esprit1718b1hrboard.entities.Delay;
import tn.esprit.b1.esprit1718b1hrboard.services.DelayServiceRemote;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class DelayPopupUpdateController implements Initializable {

	@FXML
	private AnchorPane rowAbs;
	@FXML
	private JFXDatePicker updateDate;
	@FXML
	private JFXButton ConfirmUpdateBtn;
	@FXML
	private ImageView errorDate;
	@FXML
	private Spinner<Integer> hrsSpinner;

	SpinnerValueFactory<Integer> score;

	DelayServiceRemote proxyDelay;

	public static AnchorPane refreshPa;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		ConfirmUpdateBtn.getStyleClass().clear();
		updateDate.getStyleClass().clear();
		if (RowDelayController.selectedDelay.getDurationPerHour() != null) {
			score = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100,
					RowDelayController.selectedDelay.getDurationPerHour());
		} else {
			score = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
		}
		hrsSpinner.setValueFactory(score);
		if (RowDelayController.selectedDelay.getDateDelay() != null) {
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(RowDelayController.selectedDelay.getDateDelay());
			LocalDate dateAbs = LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1,
					calendar.get(Calendar.DAY_OF_MONTH));

			updateDate.setValue(dateAbs);
		} else {
			updateDate.setValue(null);
		}

		// TODO
	}

	@FXML
	private void OnActionConfirmUpdate(ActionEvent event) {

		Boolean ok = true;
		String jndiDelay = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/DelayService!tn.esprit.b1.esprit1718b1hrboard.services.DelayServiceRemote";
		proxyDelay = (DelayServiceRemote) EJBLookupUtil.doLookup(jndiDelay);
		if (updateDate.getValue() == null) {
			ok = false;
			errorDate.setVisible(true);
		} else {
			errorDate.setVisible(false);

			if (ok == true) {
				Delay delay = RowDelayController.selectedDelay;
				delay.setDateDelay(Date.valueOf(updateDate.getValue()));
				delay.setDurationPerHour(hrsSpinner.getValue());

				proxyDelay.update(delay);

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

}
