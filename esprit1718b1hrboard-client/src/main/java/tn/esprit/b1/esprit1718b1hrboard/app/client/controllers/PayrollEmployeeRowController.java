package tn.esprit.b1.esprit1718b1hrboard.app.client.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import tn.esprit.b1.esprit1718b1hrboard.entities.Employee;
import tn.esprit.b1.esprit1718b1hrboard.entities.PaySlip;

public class PayrollEmployeeRowController  extends ListCell<Employee> implements Initializable  {

    @FXML
    private AnchorPane row;

    @FXML
    private ImageView owner_img;

    @FXML
    private Label name;

    @FXML
    private Label phone_lbl;

    @FXML
    private Label dateDembauche;

    @FXML
    private Label employeePost;

    @FXML
    private Label email;

    @FXML
    private Label WorkedHours;

    @FXML
    private Label hourPrice;
    
	private FXMLLoader mLLoader;

    
	
	
	
    public PayrollEmployeeRowController() {
		
	}

    @Override
    protected void updateItem(Employee emp, boolean empty) {
        super.updateItem(emp, empty);
        
                                    
					 if (empty || emp == null) {
				            setText(null);
				            setGraphic(null);
				        } else {

				            if (mLLoader == null) {

				                mLLoader = new FXMLLoader(getClass().getClassLoader().getResource("views/PayrollEmployeeRow.fxml"));
				                mLLoader.setController(this);

				               
				                    try {
										mLLoader.load();
									} catch (IOException e) {
										e.printStackTrace();
									}
				                

				            }
				            
				            
				            name.setText(emp.getFirstName()+" "+emp.getLastName());
				            phone_lbl.setText(emp.getPhoneNumber().toString());
				            dateDembauche.setText(emp.getStartDay().toString());
				            email.setText(emp.getEmail());
				            //employeePost.setText(emp.getPost().getEntitled());
				            WorkedHours.setText(emp.getWorkedHours().toString());
				            //hourPrice.setText(emp.getContract().getHourPrice().toString());
				            
				        
				            setText(null);
				            setGraphic(row);
   
    
}
    }

    
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}

}
