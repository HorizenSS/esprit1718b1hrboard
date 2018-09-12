/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b1.esprit1718b1hrboard.app.client.controllers;

import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import tn.esprit.b1.esprit1718b1hrboard.app.client.util.EJBLookupUtil;
import tn.esprit.b1.esprit1718b1hrboard.entities.Employee;
import tn.esprit.b1.esprit1718b1hrboard.entities.Project;
import tn.esprit.b1.esprit1718b1hrboard.entities.Task;
import tn.esprit.b1.esprit1718b1hrboard.services.ProjectServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.TaskServiceRemote;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

/**
 * FXML Controller class
 *
 * @author ilyes
 */
public class EmployeChartRewardController implements Initializable {

	@FXML
	private AnchorPane RewardChart;
	@FXML
	private BarChart<String, Number> BarcharReward;
	public static Employee employestatic;
	TaskServiceRemote proxyEmployee;
	private FXMLLoader mLLoader;
	Stage stage;
	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		XYChart.Series set1 = new XYChart.Series<>();
		String jndiEmployee = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/TaskService!tn.esprit.b1.esprit1718b1hrboard.services.TaskServiceRemote";

		proxyEmployee = (TaskServiceRemote) EJBLookupUtil.doLookup(jndiEmployee);
		List<Task> projectlist = new ArrayList<>();
		projectlist = proxyEmployee.findAll();
		System.out.println(projectlist);

		System.out.println(employestatic);
		for (Task emp : projectlist)

		{
			if (emp.getEmployee().getId() == employestatic.getId()) {
				set1.getData().add(new XYChart.Data(emp.getProject().getName(), emp.getTaskNote()));
			} else
				System.out.println("erro");
		}

		BarcharReward.getData().addAll(set1);
		
					for (Series<String, Number> serie : BarcharReward.getData()) {
						for (Data<String, Number> item : serie.getData()) {
							item.getNode().setOnMouseEntered((MouseEvent event) -> {
							
					try {
						FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("/views/ProjectInfo.fxml"));
						ProjectInfoController test = fXMLLoader.getController();
						test.pname = item.getXValue();
					Parent	root = (Parent) fXMLLoader.load();
					stage = new Stage();
					stage.setScene(new Scene(root));
					stage.show(); 			
					} catch (IOException e) {
						
					}
						});
							
							
						}
					}
					
					

	}

}
