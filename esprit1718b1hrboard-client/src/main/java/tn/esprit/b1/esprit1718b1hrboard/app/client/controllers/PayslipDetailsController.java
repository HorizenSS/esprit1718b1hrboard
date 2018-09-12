package tn.esprit.b1.esprit1718b1hrboard.app.client.controllers;

import java.io.File;
import java.io.FileNotFoundException;

import tn.esprit.b1.esprit1718b1hrboard.app.client.util.SendMail;

import java.net.URL;
import javafx.util.Duration;
import javafx.geometry.Pos;

import java.util.ResourceBundle;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.controlsfx.control.Notifications;
import org.hibernate.hql.internal.ast.tree.InitializeableNode;

import com.itextpdf.text.DocumentException;
import com.jfoenix.controls.JFXButton;
import javafx.scene.image.Image;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import tn.esprit.b1.esprit1718b1hrboard.app.client.util.EJBLookupUtil;
import tn.esprit.b1.esprit1718b1hrboard.services.PaySlipServiceRemote;

public class PayslipDetailsController   implements Initializable  {
	
	  PaySlipServiceRemote proxyPayslips;
	    String jndiPayslips = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/PaySlipService!tn.esprit.b1.esprit1718b1hrboard.services.PaySlipServiceRemote";
	   

	    
    @FXML
    private ImageView EmpPic;

    @FXML
    private Label EmpFirstName;

    @FXML
    private Label EmpLastName;

    @FXML
    private Label date;

    @FXML
    private Label type;

    @FXML
    private JFXButton ConfirmPayslip;

    @FXML
    private JFXButton declinePayslip;

	@FXML
    void OnConfirm() throws FileNotFoundException, DocumentException, AddressException, MessagingException {
		
		 proxyPayslips = (PaySlipServiceRemote) EJBLookupUtil.doLookup(jndiPayslips);
    	System.out.println(psC.psSelected);
    	proxyPayslips.ConfirmPayslip(psC.psSelected);
    	
    	proxyPayslips.GenerateAnnualPaySlip(psC.psSelected);
    	
    	
        SendMail s = null ;
        
        
        String firstName = psC.psSelected.getEmployee().getFirstName();
        String lastName = psC.psSelected.getEmployee().getLastName();
        String n = firstName+lastName;
        
        
        String user = "allstarcreww@gmail.com";
        String pass="pumapuma";
        
        String attachFiles = "D:\\Serveur\\wildfly-9.0.1.Final\\wildfly-9.0.1.Final\\bin\\"+n+".pdf";
      
        
        s.sendEmailWithAttachments(psC.psSelected.getEmployee().getEmail(), "haaayaa", lastName, attachFiles);
        	
        	
        	Image valide = new Image("/icons/valide.png");
            Notifications notificationsBuilder = Notifications.create()
                    .title("Request Confirmed")
                    .text("Payslip's Request Confirmed ")
                    .graphic(new ImageView(valide))
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.BOTTOM_RIGHT);
            notificationsBuilder.show();
            notificationsBuilder.darkStyle();

    }

    @FXML
    void OnDecline( ) {
    	
    	 proxyPayslips = (PaySlipServiceRemote) EJBLookupUtil.doLookup(jndiPayslips);
    	proxyPayslips.RejectPayslip(psC.psSelected);


    }
    public PayslipsController psC;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		EmpFirstName.setText(psC.psSelected.getEmployee().getFirstName());
		EmpLastName.setText(psC.psSelected.getEmployee().getLastName());
		date.setText(psC.psSelected.getRequestExtractionDate().toString());
		type.setText(psC.psSelected.getType());

		
		
	}
  
}
