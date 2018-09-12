/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b1.esprit1718b1hrboard.app.client.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;

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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import tn.esprit.b1.esprit1718b1hrboard.app.client.util.EJBLookupUtil;
import tn.esprit.b1.esprit1718b1hrboard.entities.Employee;
import tn.esprit.b1.esprit1718b1hrboard.entities.Vacation;
import tn.esprit.b1.esprit1718b1hrboard.services.EmployeeServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.VacationServiceRemote;

/**
 * FXML Controller class
 *
 * @author Ghassen
 */
public class VaccDemandsRowController extends ListCell<Vacation> implements Initializable {

    @FXML
    private ImageView EmployeePic;
    @FXML
    private Label lblFirstLastName;
    @FXML
    private Label employeePostLbl;
    @FXML
    private Label DemandSubmissionDateLbl;
    @FXML
    private Label VaccTypeLbl;
    @FXML
    private Label VaccSdateLbl;
    @FXML
    private Label VaccEDateLbl;
    @FXML
    private JFXButton MangeDemandButton;
    
    private FXMLLoader mLLoader;
    @FXML
    private AnchorPane MainRow;
    @FXML
    private StackPane WraperPane;
    
    public VaccMenuController vmc;
    public static Vacation vv;
    /*for the checked rows */
    @FXML
    private Label ResponseDateLbl;
    @FXML
    private JFXButton UpdateButton;
    @FXML
    private ImageView Status;
     
     

    public VaccDemandsRowController(VaccMenuController vmc) {
		super();
		this.vmc = vmc;
	}
	/**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    @Override
    protected void updateItem(Vacation Vacc, boolean empty) {
        super.updateItem(Vacc, empty);

        if (empty || Vacc == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (mLLoader == null) {
            	if( Vacc.getStatus()!= null){
            		mLLoader = new FXMLLoader(getClass().getResource("/views/CheckedDemandsRow.fxml"));
                    mLLoader.setController(this);
                    vv = Vacc;
                    try {
                        mLLoader.load();
                    } catch (IOException e) {
                    }
                    javafx.scene.image.Image ix = new javafx.scene.image.Image(getClass().getResourceAsStream("/icons/X.png"));
                    javafx.scene.image.Image iv = new javafx.scene.image.Image(getClass().getResourceAsStream("/icons/V.png"));
                    if(Vacc.getStatus()){
                    	Status.setImage(iv);
                    }else{
                    	Status.setImage(ix);
                		MainRow.setStyle("-fx-background-color:#ff9980;");
                    }
                    Employee emp = Vacc.getEmployee(); 
                	lblFirstLastName.setText(emp.getFirstName()+" "+emp.getLastName());
                	employeePostLbl.setText(emp.getPost().getEntitled());
                	SimpleDateFormat sd = new SimpleDateFormat("dd/MMM/yyyy");
                	ResponseDateLbl.setText("Response saved in :"+sd.format(Vacc.getResponseDate()));
                	DemandSubmissionDateLbl.setText("Submitted in : "+sd.format(Vacc.getSubmissionDate()));
                	setText(null);
                    setGraphic(MainRow);
            	}else
            	{
                mLLoader = new FXMLLoader(getClass().getResource("/views/VaccDemandsRow.fxml"));
                mLLoader.setController(this);
                vv = Vacc;

                try {
                    mLLoader.load();
                } catch (IOException e) {
                }
                Employee emp = Vacc.getEmployee(); 
            	lblFirstLastName.setText(emp.getFirstName()+" "+emp.getLastName());
            	employeePostLbl.setText(emp.getPost().getEntitled());
            	VaccTypeLbl.setText(Vacc.getVacationType().toString());
                SimpleDateFormat sd = new SimpleDateFormat("dd/MMM/yyyy");
            	VaccEDateLbl.setText("End in : "+sd.format(Vacc.getEndDate()));
            	VaccSdateLbl.setText("Starts in : "+sd.format(Vacc.getStartDate()));
            	DemandSubmissionDateLbl.setText("Submitted in : "+sd.format(Vacc.getSubmissionDate()));
            	setText(null);
                setGraphic(MainRow);
            	}
            }

        }

    }
    
}
