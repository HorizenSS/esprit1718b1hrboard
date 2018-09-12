/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b1.esprit1718b1hrboard.app.client.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextArea;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import tn.esprit.b1.esprit1718b1hrboard.app.client.util.EJBLookupUtil;
import tn.esprit.b1.esprit1718b1hrboard.entities.Departement;
import tn.esprit.b1.esprit1718b1hrboard.entities.Employee;
import tn.esprit.b1.esprit1718b1hrboard.entities.Post;
import tn.esprit.b1.esprit1718b1hrboard.services.DepartementServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.EmployeeServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.PostServiceRemote;

/**
 * FXML Controller class
 *
 * @author Ghassen
 */
public class PostManagingPopUpController implements Initializable {

    @FXML
    private AnchorPane PostPopUpPane;
    @FXML
    private JFXTextArea Description;
    @FXML
    private Label lblFirstLastName;
    @FXML
    private TextField CodeTxtField;
    @FXML
    private TextField EntitledTxtField;
    @FXML
    private JFXComboBox<Departement> DepartmentComboBox;
    @FXML
    private Label OldDepartment;
    @FXML
    private Button DeletePostButton;
    @FXML
    private Button UpdatePostButton;

    public static PostsMenuController pm;
    public static Post PostIn;
    
    PostServiceRemote proxyPost;
    DepartementServiceRemote proxydep;
    EmployeeServiceRemote proxyE;
    String jndi = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/PostService!tn.esprit.b1.esprit1718b1hrboard.services.PostServiceRemote";
    String jndidep = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/DepartementService!tn.esprit.b1.esprit1718b1hrboard.services.DepartementServiceRemote";
    String jndiemp = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/EmployeeService!tn.esprit.b1.esprit1718b1hrboard.services.EmployeeServiceRemote";
    private ObservableList<Departement> deps;
    @FXML
    private StackPane WraperPane;

    public static JFXDialog dialogSkill;
    @FXML
    private Label deletelbl;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	proxydep = (DepartementServiceRemote) EJBLookupUtil.doLookup(jndidep);
    	List<Departement> dList = new ArrayList<>();
		dList = proxydep.findAll();
		deps = FXCollections.observableArrayList();
		for(Departement depart : dList){
			deps.add(depart);
		}
    	DepartmentComboBox.setItems((ObservableList<Departement>) deps);
    	/*setting the Post in the PopUp*/
		Description.setText(PostIn.getDescription());
		OldDepartment.setText(PostIn.getDepartement().getName());
		CodeTxtField.setText(PostIn.getCodePost());
		EntitledTxtField.setText(PostIn.getEntitled());
//		if(PostIn.getEmployees().isEmpty()){
//			deletelbl.setVisible(false);
//			DeletePostButton.setVisible(false);
//		}
    	
    }    

    @FXML
    private void DeleteClicked(MouseEvent event) {
    	proxyPost = (PostServiceRemote) EJBLookupUtil.doLookup(jndi);
    	proxyE = (EmployeeServiceRemote) EJBLookupUtil.doLookup(jndiemp);
    	for (Employee e : proxyE.findAll()) {
			if(e.getPost().equals(PostIn)){
				WarningPaneController.msg = "This Post is linked to employee(s). It can't be removed !";
				ShowDialog();
			}else{
				proxyPost.delete(PostIn);
				pm.INITAAT();
				pm.dialogSkill.close();
			}
		}
    }
    public void ShowDialog(){
    	Pane child = null;
		try {
			child = FXMLLoader.load(getClass().getResource("/views/WarningPane.fxml"));			
		} catch (IOException ex) {
		}
		JFXDialogLayout dialogLayout = new JFXDialogLayout();
		dialogLayout.getChildren().add(child);
		dialogSkill = new JFXDialog(WraperPane, dialogLayout, JFXDialog.DialogTransition.CENTER);
		dialogSkill.show();
    }

    @FXML
    private void UpdateClicked(MouseEvent event) {
    	int x =1;
    	String Msg = null;
    	proxyPost = (PostServiceRemote) EJBLookupUtil.doLookup(jndi);
    	if(!CodeTxtField.getText().equals("")){
    		PostIn.setCodePost(CodeTxtField.getText());
    	}else{
    		x=-1;
    		Msg = "Set a Post code before confirming !";
    		}
    	if(!EntitledTxtField.getText().equals("")){
    		PostIn.setEntitled(EntitledTxtField.getText());
    	}else{
    		x=-1;
    		Msg = "Set a Name before confirming !";
    		}
    	if(!Description.getText().equals("")){
    		PostIn.setDescription(Description.getText());
    	}else{
    		x=-1;
    		Msg = "Set a description before confirming !";
    	}
    	if(DepartmentComboBox.getValue()!= null){
        	PostIn.setDepartement(DepartmentComboBox.getValue());
        	}else{
        		x=-1;
        		Msg = "Select a department for this post !";
        	}
    	if (x != -1) {
			boolean t = false;
			for (Post pepe : proxyPost.findAll()) {
				if ((pepe.getCodePost().equals(PostIn.getCodePost()))&&(pepe.getId()!=PostIn.getId())) {
					t = true;
				}
			}
			if (t) {
				WarningPaneController.msg = "This post's code already exists!";
				ShowDialog();
			} else {
				proxyPost.update(PostIn);
				pm.INITAAT();
				pm.dialogSkill.close();
			}
		}else{
    		WarningPaneController.msg = Msg;
			ShowDialog();
    	}
    }
    
}
