/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b1.esprit1718b1hrboard.app.client.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import tn.esprit.b1.esprit1718b1hrboard.app.client.tableModels.EmployeeTable;
import tn.esprit.b1.esprit1718b1hrboard.app.client.util.EJBLookupUtil;
import tn.esprit.b1.esprit1718b1hrboard.entities.Departement;
import tn.esprit.b1.esprit1718b1hrboard.entities.Post;
import tn.esprit.b1.esprit1718b1hrboard.services.DepartementServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.PostServiceRemote;

/**
 * FXML Controller class
 *
 * @author Ghassen
 */
public class DepartmentsHierarchyController implements Initializable {


    public static JFXDialog dialogSkill;
    String jndiPost = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/PostService!tn.esprit.b1.esprit1718b1hrboard.services.PostServiceRemote";
    PostServiceRemote proxyPost;
    DepartementServiceRemote proxydep;
    String jndidep = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/DepartementService!tn.esprit.b1.esprit1718b1hrboard.services.DepartementServiceRemote";
    private Departement val ;
    public URL url1; 
    public ResourceBundle rb1;
    @FXML
    private StackPane PostsWraperPane;
    @FXML
    private AnchorPane MainPane;
    @FXML
    private Pane TableXTree;
    @FXML
    private JFXTreeView<Departement> DepartmentsHTreeView;
    @FXML
    private Pane DepartmentInfoPane;
    @FXML
    private Label LblPlace;
    @FXML
    private Label lblSupDep;
    @FXML
    private Label lblDepartement;
    @FXML
    private JFXTextArea DescriptionArea;
    @FXML
    private JFXButton ShowSuperDepartment;
    @FXML
    private JFXButton UpdateDepartment;
    @FXML
    private JFXButton DeleteDepartment;
    @FXML
    private JFXTextField critiriaSearch;
    
    private FilteredList<Departement> filterData;
    private ObservableList<Departement> list_deps;
    private SortedList<Departement> sortedDepsList;
    @FXML
    private Label lblPost;
    
    javafx.scene.image.Image icon = new javafx.scene.image.Image(getClass().getResourceAsStream("/icons/dep.png"));
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	proxydep = (DepartementServiceRemote) EJBLookupUtil.doLookup(jndidep);
    	Departement dd = new Departement("root", "just for the test ", "here", null);
    	TreeItem<Departement> root = new TreeItem<>(dd);
    	root.setExpanded(true);
		DepartmentsHTreeView.setRoot(root);
		DepartmentsHTreeView.setShowRoot(false);
    	List<Departement> dList = new ArrayList<>();
		dList = proxydep.findTopDeps();
		CompleteUnderDeps(dList, root);
		DepartmentsHTreeView.getSelectionModel().selectedItemProperty()
			.addListener((v,oldv,newv) -> {
				if(newv != null){
					ShowDepInfo(newv.getValue());
					val = newv.getValue();
				}
			});
		url1 = url;
		rb1 = rb ;
    }
    
    
    public void ShowDepInfo( Departement dep){
    	lblPost.setVisible(false);
    	String str = "This Department has Posts";
    	proxyPost = (PostServiceRemote) EJBLookupUtil.doLookup(jndiPost);
    	List<Post> LP = proxyPost.findAll();
    	lblDepartement.setText(dep.getName());
    	LblPlace.setText(dep.getPlace());
    	DescriptionArea.setText(dep.getDescription());
    	if(dep.getSuperiorDep()==null){
    		lblSupDep.setVisible(false);
    		ShowSuperDepartment.setVisible(false);
    	}else{
    		lblSupDep.setText(dep.getSuperiorDep().getName());
    		lblSupDep.setVisible(true);
    		ShowSuperDepartment.setVisible(true);
    	}
    	for (Post post : LP) {
			if(post.getDepartement().equals(dep)){
				lblPost.setVisible(true);
				lblPost.setText(str);
			}
		}
    }
    
    public void CompleteUnderDeps(List<Departement> ldeps, TreeItem<Departement> parent){
    	for(Departement d : ldeps){
    		TreeItem<Departement> x = new TreeItem<>(d , new javafx.scene.image.ImageView(icon));
    		if(!d.getUnderDeps().isEmpty()){
				CompleteUnderDeps(d.getUnderDeps(), x);
    			}
    		parent.getChildren().add(x);			
    	}
    } 

    @FXML
    private void SuperDepartmentShow(MouseEvent event) throws IOException {
    	proxydep = (DepartementServiceRemote) EJBLookupUtil.doLookup(jndidep);
    	DepartmentInfoPaneController.Depart = val.getSuperiorDep(); 
    	Pane child = null;
		try {
			child = FXMLLoader.load(getClass().getResource("/views/DepartmentInfoPane.fxml"));
		} catch (IOException ex) {
		}
		JFXDialogLayout dialogLayout = new JFXDialogLayout();
		dialogLayout.getChildren().add(child);
		dialogSkill = new JFXDialog(PostsWraperPane, dialogLayout, JFXDialog.DialogTransition.CENTER);
		dialogSkill.show();
        
        

    }

    @FXML
    private void AddNewDepartmentTotheTree(MouseEvent event) throws IOException {
    	DepartmentAddNewController.choice = 0;
    	DepartmentAddNewController.dh = this;
    	Pane child = null;
		try {
			child = FXMLLoader.load(getClass().getResource("/views/DepartmentAddNew.fxml"));
		} catch (IOException ex) {
		}
		JFXDialogLayout dialogLayout = new JFXDialogLayout();
		dialogLayout.getChildren().add(child);
		dialogSkill = new JFXDialog(PostsWraperPane, dialogLayout, JFXDialog.DialogTransition.CENTER);
		dialogSkill.show();
    }

    @FXML
    private void UpdateCurrentDepartment(MouseEvent event) throws IOException {
    	DepartmentAddNewController.Depart = val;
    	DepartmentAddNewController.choice = 1;
    	DepartmentAddNewController.dh = this;
    	Pane child = null;
		try {
			child = FXMLLoader.load(getClass().getResource("/views/DepartmentAddNew.fxml"));
		} catch (IOException ex) {
		}
		JFXDialogLayout dialogLayout = new JFXDialogLayout();
		dialogLayout.getChildren().add(child);
		dialogSkill = new JFXDialog(PostsWraperPane, dialogLayout, JFXDialog.DialogTransition.CENTER);
		dialogSkill.show();
    }

    @FXML
    private void DeleteCurrentDepartment(MouseEvent event) {
    	boolean b = false ;
    	proxydep = (DepartementServiceRemote) EJBLookupUtil.doLookup(jndidep);
    	String str = "Department has Posts ! It can not be deleted";
    	proxyPost = (PostServiceRemote) EJBLookupUtil.doLookup(jndiPost);
    	List<Post> LP = proxyPost.findAll();
    	for (Post post : LP) {
			if(post.getDepartement().equals(val)){
				b = true;
			}
		}
    	if(!b){
    	proxydep.delete(val);
    	INITAAT();	
    	}else{
    		WarningPaneController.msg = str;
    		ShowWarning();
    	}
    }
    public void INITAAT(){
    	this.initialize(url1, rb1);
    }
    public void ShowWarning(){
    	Pane child = null;
		try {
			child = FXMLLoader.load(getClass().getResource("/views/WarningPane.fxml"));			
		} catch (IOException ex) {
		}
		JFXDialogLayout dialogLayout = new JFXDialogLayout();
		dialogLayout.getChildren().add(child);
		dialogSkill = new JFXDialog(PostsWraperPane, dialogLayout, JFXDialog.DialogTransition.CENTER);
		dialogSkill.show();
    }

    @FXML
    private void searchEmployees(KeyEvent event) {
    	proxydep = (DepartementServiceRemote) EJBLookupUtil.doLookup(jndidep);
		critiriaSearch.textProperty().addListener((obsevable, oldvalue, newvalue) -> {
			Departement dd = new Departement("root", "just for the test ", "here", null);
	    	TreeItem<Departement> root = new TreeItem<>(dd);
	    	root.setExpanded(true);
			DepartmentsHTreeView.setRoot(root);
			DepartmentsHTreeView.setShowRoot(false);
			for (Departement departement : proxydep.findDepsByNames(newvalue)) {
				TreeItem<Departement> x = new TreeItem<>(departement, new javafx.scene.image.ImageView(icon));
				System.out.println(departement);
				root.getChildren().add(x);
			}
		});
    }

    @FXML
    private void Refresh(MouseEvent event) {
    	INITAAT();
    }
    
}
