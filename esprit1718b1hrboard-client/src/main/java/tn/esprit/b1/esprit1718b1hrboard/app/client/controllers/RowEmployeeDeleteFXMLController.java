/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b1.esprit1718b1hrboard.app.client.controllers;


import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import org.controlsfx.control.Notifications;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import tn.esprit.b1.esprit1718b1hrboard.app.client.util.EJBLookupUtil;
import tn.esprit.b1.esprit1718b1hrboard.entities.Account;
import tn.esprit.b1.esprit1718b1hrboard.entities.Employee;
import tn.esprit.b1.esprit1718b1hrboard.services.AccountServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.EmployeeServiceRemote;

/**
 * FXML Controller class
 *
 * @author ilyes
 */
public class RowEmployeeDeleteFXMLController extends ListCell<Employee> implements Initializable {

    @FXML
    private AnchorPane row;
    @FXML
    private ImageView owner_img;
    @FXML
    private Label owner_name_lbl;
    @FXML
    private Label phone_lbl;
    @FXML
    private Label DateRecruitement;
    @FXML
    private Label employeePost;
    @FXML
    private Label email;
    @FXML
    private Label birthdate;
    @FXML
    private Text description;
    @FXML
    private JFXButton Delete;
    @FXML
    private JFXButton AddAccount;
    public static Employee e;
    private FXMLLoader mLLoader;
    public static DeleteEmployeController sh;
    public   Account a ;
    String jndi = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/AccountService!tn.esprit.b1.esprit1718b1hrboard.services.AccountServiceRemote";
    AccountServiceRemote proxyPost; 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    @Override
    protected void updateItem(Employee employee, boolean empty) {
        super.updateItem(employee, empty);
  	   proxyPost = (AccountServiceRemote) EJBLookupUtil.doLookup(jndi);

        if (empty || employee == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("/views/RowEmployeeDeleteFXML.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                e=employee;
             
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
                System.out.println(e.getAccount());
                
            }}

        }

    @FXML
    private void DeleteAccount(ActionEvent event) throws IOException {
    	System.out.println(e);
 	   proxyPost = (AccountServiceRemote) EJBLookupUtil.doLookup(jndi);
 	proxyPost.delete(e.getAccount());
 	Image valide = new Image("/icons/valide.png");
    Notifications notificationsBuilder = Notifications.create()
            .title("DELETE OF ACCOUNT ")
            .text("THE DELETE OF THE ACCOUNT IS DONE ")
            .graphic(new ImageView(valide))
            .hideAfter(Duration.seconds(5))
            .position(Pos.BOTTOM_RIGHT);
    notificationsBuilder.show();
    notificationsBuilder.darkStyle();
    	
    }
    
}
