
package tn.esprit.b1.esprit1718b1hrboard.app.client.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.hibernate.hql.internal.ast.tree.InitializeableNode;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import tn.esprit.b1.esprit1718b1hrboard.entities.JobOffer;
import tn.esprit.b1.esprit1718b1hrboard.entities.PaySlip;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class PayslipRowController extends ListCell<PaySlip> implements Initializable  {
	


    @FXML
    private AnchorPane row;
    @FXML
    private ImageView owner_img;
    @FXML
    private Label name;
    @FXML
    private Label phone_lbl;
    @FXML
    private Label RequestDate;
    @FXML
    private Label employeePost;
    @FXML
    private Label email;
    
	
	private FXMLLoader mLLoader;

    
public PayslipRowController() {
	
}

   @Override
    protected void updateItem(PaySlip ps, boolean empty) {
        super.updateItem(ps, empty);
        
                                    
					 if (empty || ps == null) {
				            setText(null);
				            setGraphic(null);
				        } else {

				            if (mLLoader == null) {

				                mLLoader = new FXMLLoader(getClass().getClassLoader().getResource("views/PayslipRow.fxml"));
				                mLLoader.setController(this);

				               
				                    try {
										mLLoader.load();
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
				                

				            }
				            name.setText(ps.getEmployee().getFirstName()+" "+ps.getEmployee().getLastName());
				            phone_lbl.setText(ps.getEmployee().getPhoneNumber().toString());
				            RequestDate.setText(ps.getRequestExtractionDate().toString());
				            email.setText(ps.getEmployee().getEmail());
				            employeePost.setText(ps.getType());
				            
				            setText(null);
				            setGraphic(row);
   
    
}
    }

@Override
public void initialize(URL location, ResourceBundle resources) {
	
	
}
}
