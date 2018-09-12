/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b1.esprit1718b1hrboard.app.client.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXMLLoader;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import tn.esprit.b1.esprit1718b1hrboard.entities.Employee;

/**
 * FXML Controller class
 *
 * @author ilyes
 */
public class RowEmployeeRewardkFXMLController extends ListCell<Employee> implements Initializable {

    @FXML
    private AnchorPane row;
    @FXML
    private ImageView owner_img;
    @FXML
    private Label owner_name_lbl;
    @FXML
    private Label phone_lbl;
    @FXML
    private Label recuitementdate;
    @FXML
    private Label postl;
    @FXML
    private Label email;
    @FXML
    private Label birthdate;
    @FXML
    private Text description;
    @FXML
    private JFXButton Rewardbutton;
    private FXMLLoader mLLoader;
    public String combo;
    public  Employee  employee_static;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @Override
    protected void updateItem(Employee employee, boolean empty) {
        super.updateItem(employee, empty);

        if (empty || employee == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("/views/RowEmployeeRewardkFXML.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                employee_static=employee;
                if (employee.getLastName()==null)
                {owner_name_lbl.setText("*****");}
                else
            	{owner_name_lbl.setText(employee.getFirstName());}
                
                if (employee.getEmail()==null)
                {email.setText("*****");}
                else
                {  email.setText(employee.getEmail());}
                
   
                if (employee.getPhoneNumber()==null)
                {phone_lbl.setText("*****");}
                else{ phone_lbl.setText(employee.getPhoneNumber().toString());}
                if (employee.getBirthDate()==null)
                {birthdate.setText("*****");}     
                else
                {SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
	            String startDate = sd.format(employee.getBirthDate());
	            birthdate.setText(startDate);}
                setText(null);
                setGraphic(row);
            }}

        }

    @FXML
    private void GiveReward(ActionEvent event) throws IOException {      

    	
    	 FXMLLoader fXMLLoader1 = new FXMLLoader(getClass().getResource("/views/RewardFXML.fxml"));
         RewardFXMLController test = fXMLLoader1.getController();
    	combo=test.combo;
    	System.out.println(combo);
    	if(combo.equals("Mounth Ranking"))
    	{FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("/views/GiveAwardToEmployeeFXML.fxml"));
        RewardFXMLController test1 = fXMLLoader1.getController();
        

    	Parent root = (Parent) fXMLLoader.load();
    Stage stage = new Stage();
    stage.setScene(new Scene(root));
    stage.show();}
    	else if (combo.equals("Year Ranking")) {FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("/views/GiveAwardToEmployee2FXML.fxml"));
    	GiveAwardToEmployee2FXMLController test1 = fXMLLoader1.getController();
       test1.employestatic=employee_static;
    	Parent root = (Parent) fXMLLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();}
      	else if (combo.trim().isEmpty())
 {Alert alert = new Alert(AlertType.WARNING);

     	alert.setTitle("Please");
     	alert.setHeaderText("Rank the Employee");
     	alert.setContentText("RETURN");
     	alert.showAndWait().ifPresent(response -> {
     		if (response == ButtonType.OK) {
        
      }});}
}
    
   
    @FXML
    private void showemployeechart(javafx.scene.input.MouseEvent event) throws IOException {
    	FXMLLoader fXMLLoader1 = new FXMLLoader(getClass().getResource("/views/EmployeChartReward.fxml"));
    	EmployeChartRewardController test1 = fXMLLoader1.getController();
        test1.employestatic=employee_static;

    	Parent root = (Parent) fXMLLoader1.load();
    Stage stage = new Stage();
    stage.setScene(new Scene(root));
    stage.show();
    }
    
    }
    

