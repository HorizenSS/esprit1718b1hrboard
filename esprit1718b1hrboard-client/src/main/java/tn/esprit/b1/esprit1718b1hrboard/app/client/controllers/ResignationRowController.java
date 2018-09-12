package tn.esprit.b1.esprit1718b1hrboard.app.client.controllers;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import tn.esprit.b1.esprit1718b1hrboard.app.client.util.EJBLookupUtil;
import tn.esprit.b1.esprit1718b1hrboard.entities.Employee;
import tn.esprit.b1.esprit1718b1hrboard.entities.Resignation;
import tn.esprit.b1.esprit1718b1hrboard.entities.Vacation;
import tn.esprit.b1.esprit1718b1hrboard.services.EmployeeServiceRemote;

/**
 * FXML Controller class
 *
 * @author Ghassen
 */
public class ResignationRowController extends ListCell<Resignation> implements Initializable {

    @FXML
    private AnchorPane MainRow;
    @FXML
    private ImageView EmployeePic;
    @FXML
    private Label lblFirstLastName;
    @FXML
    private Label employeePostLbl;
    @FXML
    private Label DemandSubmissionDateLbl;
    @FXML
    private Label RegistredLbl;
    @FXML
    private ImageView Status;
    
    private FXMLLoader mLLoader;
    
    public static Resignation vv;
    
    EmployeeServiceRemote proxyEmployee;
    String jndiEmployee = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/EmployeeService!tn.esprit.b1.esprit1718b1hrboard.services.EmployeeServiceRemote";
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	Status.setVisible(false);
    	RegistredLbl.setVisible(false);
    }
    @Override
    protected void updateItem(Resignation Vacc, boolean empty) {
        super.updateItem(Vacc, empty);
        if (empty || Vacc == null) {
            setText(null);
            setGraphic(null);
        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("/views/ResignationRow.fxml"));
                mLLoader.setController(this);
                vv = Vacc;
                try {
                    mLLoader.load();
                } catch (IOException e) {
                }
                javafx.scene.image.Image ix = new javafx.scene.image.Image(getClass().getResourceAsStream("/icons/X.png"));
                javafx.scene.image.Image iv = new javafx.scene.image.Image(getClass().getResourceAsStream("/icons/V.png"));
                if(Vacc.getStatus()!=null){
                	if(Vacc.getStatus()==true){
                		Status.setImage(iv);
                		MainRow.setStyle("-fx-background-color:#b3ffb3;");
                		Status.setVisible(true);
                		RegistredLbl.setVisible(true);
                	}else{
                		Status.setImage(ix);
                		MainRow.setStyle("-fx-background-color:#ff9980;");
                		Status.setVisible(true);
                		RegistredLbl.setVisible(true);
                	}
                }
                proxyEmployee = (EmployeeServiceRemote) EJBLookupUtil.doLookup(jndiEmployee);
                Employee emp = proxyEmployee.EmployeeByResignation(Vacc); 
//            	lblFirstLastName.setText(emp.getFirstName()+" "+emp.getLastName());
//            	employeePostLbl.setText(emp.getPost().getEntitled());
            	SimpleDateFormat sd = new SimpleDateFormat("dd/MMM/yyyy");
            	System.out.println(Vacc.getSubmissionDate());
            	DemandSubmissionDateLbl.setText("Submitted in : "+sd.format(Vacc.getSubmissionDate()));
            	//DemandSubmissionDateLbl.setText("Submitted in : "+sd.format(Vacc.getSubmissionDate()));
            	setText(null);
                setGraphic(MainRow);
            }
        }
    }
    
    
}
