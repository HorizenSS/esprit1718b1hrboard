/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b1.esprit1718b1hrboard.app.client.controllers;


import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.Access;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

import org.controlsfx.control.CheckComboBox;
import org.controlsfx.control.Notifications;

import tn.esprit.b1.esprit1718b1hrboard.app.client.util.EJBLookupUtil;
import tn.esprit.b1.esprit1718b1hrboard.app.client.util.MD5;
import tn.esprit.b1.esprit1718b1hrboard.app.client.util.PasswordGenerator;
import tn.esprit.b1.esprit1718b1hrboard.app.client.util.SendMail;
import tn.esprit.b1.esprit1718b1hrboard.entities.Account;
import tn.esprit.b1.esprit1718b1hrboard.entities.Departement;
import tn.esprit.b1.esprit1718b1hrboard.entities.Employee;
import tn.esprit.b1.esprit1718b1hrboard.services.AccountServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.EmployeeServiceRemote;

/**
 * FXML Controller class
 *
 * @author ilyes
 */
public class EmployeeAccoutAddFormFXMLController implements Initializable {

    @FXML
    private JFXTextArea LastNAmeEmployee;
    @FXML
    private JFXTextArea PasswordEmployeeAccount;
    @FXML
    private JFXTextArea LoginAccout;
    @FXML
    private JFXTextArea FirstName;
    @FXML
    private Button AddAccout;
    public static  String hashedmp;
    public static  String mp;
    AccountServiceRemote proxyAccount;

    String jndi = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/AccountService!tn.esprit.b1.esprit1718b1hrboard.services.AccountServiceRemote";
    AccountServiceRemote proxyPost;
    private ObservableList<tn.esprit.b1.esprit1718b1hrboard.entities.Access> acces;
    public static String to;
    public static String sub;
    public static String msg;
    public static String user;
    public static String pass;
    
    EmployeeServiceRemote proxyEmployee;
    String jndiEmployee = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/EmployeeService!tn.esprit.b1.esprit1718b1hrboard.services.EmployeeServiceRemote";
    @FXML
    private Button generatempp;
    @FXML
    private ComboBox <tn.esprit.b1.esprit1718b1hrboard.entities.Access> role;
    public static Employee employeed;
    public static AddAccoutAdminFxmlController lh;
 boolean x ;
   
    //private ObservableList<String> acces = ObservableList <String> ("HRMANAGER", "HRDIRECTOR", "SYSTEMADMIN","NORMALEMPLOYEE");
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    acces	= FXCollections.observableArrayList();
    	acces.add(tn.esprit.b1.esprit1718b1hrboard.entities.Access.HRDIRECTOR);
    	acces.add(tn.esprit.b1.esprit1718b1hrboard.entities.Access.HRMANAGER);
    	acces.add(tn.esprit.b1.esprit1718b1hrboard.entities.Access.NORMALEMPLOYEE);
    	acces.add(tn.esprit.b1.esprit1718b1hrboard.entities.Access.SYSTEMADMIN);
    	
    	role.setItems((ObservableList<tn.esprit.b1.esprit1718b1hrboard.entities.Access>) acces);

    	proxyEmployee = (EmployeeServiceRemote) EJBLookupUtil.doLookup(jndiEmployee);
    	List<Departement> dList = new ArrayList<>();
    	Employee employeegh = employeed  ; 
    	LastNAmeEmployee.setText(employeegh.getLastName());
    	FirstName.setText(employeegh.getFirstName());
    	LoginAccout.setText(employeegh.getLastName()+"."+employeegh.getFirstName())  ;
  }
    
	@FXML
    private void LasteNameemployee(MouseEvent event) {
    }

    @FXML
    private void PasswordAccout(MouseEvent event) {
    	
    
    }

    @FXML
    private void LoginAccount(MouseEvent event) {
    }

    @FXML
    private void FirstEnameùmployee(MouseEvent event) {
    }

    @FXML
    private void AddAccount(ActionEvent event) throws IOException {
    	   proxyPost = (AccountServiceRemote) EJBLookupUtil.doLookup(jndi);
    	
for    (Account acc:proxyPost.findAll())   
{   if (acc.getEmployee()==employeed  )
{  FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("/views/WarningPane.fxml"));
Parent root = (Parent) fXMLLoader.load();
Stage stage = new Stage();
stage.setScene(new Scene(root));
stage.show();
}
}
Employee employeegh = employeed  ; 
       	Long millis = System.currentTimeMillis();
	       Date date = new Date(millis);
       	Account  a = new Account(null, LoginAccout.getText(), PasswordEmployeeAccount.getText(), null, date, role.getValue(), employeed);
       	proxyPost.save(a);
       	
        to = employeed.getEmail();
        sub = "Your account is created";
        msg = "your password is    "
        		+ " "
        		+ " "
        		+ ""+ mp +""
        		+ ""
        		+ "your user name is "
        		+ ""
        		+ ""
        		+ ""+LoginAccout.getText().toString();
        user = "allstarcreww@gmail.com";
        pass="pumapuma";
       SendMail s = null;
     	Stage principalStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	principalStage.hide();
    	 Image valide = new Image("/icons/valide.png");
         Notifications notificationsBuilder = Notifications.create()
                 .title("ADD OF ACCOUNT ")
                 .text("THE ADD OF THE ACCOUNT IS DONE ")
                 .graphic(new ImageView(valide))
                 .hideAfter(Duration.seconds(5))
                 .position(Pos.BOTTOM_RIGHT);
         notificationsBuilder.show();
         notificationsBuilder.darkStyle();
     //  s.send(to, sub, msg, user, pass);
        s.send(to, sub, msg, user, pass);
       	
     //  	lh.INITAAT();
       	 
     
    	
    	
    }

    private void Generatemotp(ActionEvent event) {
    	MD5 md5 = new MD5();
    	PasswordGenerator pass = new PasswordGenerator();
        String x  =	pass.generatePassword(20, "Alphanumeric" );
      mp=x ;
      hashedmp = md5.hash(x);
        System.out.println(x);
        System.out.println(hashedmp);
        PasswordEmployeeAccount.setText(x);

        
        
       // PasswordEmployeeAccount.setText(hash);
    }

    @FXML
    private void generatemp(ActionEvent event) throws NoSuchAlgorithmException, UnsupportedEncodingException {
    	MD5 md5 = new MD5();
    	PasswordGenerator pass = new PasswordGenerator();
        String x  =	pass.generatePassword(20, "Alphanumeric" );
             mp=x ;
          hashedmp=  md5.hash(x);
            
        System.out.println(x);
        System.out.println(hashedmp);
        PasswordEmployeeAccount.setText(hashedmp);

    }
    
}
