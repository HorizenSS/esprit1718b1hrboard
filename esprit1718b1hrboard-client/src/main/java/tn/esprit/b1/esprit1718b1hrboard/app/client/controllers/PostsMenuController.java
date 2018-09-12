/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b1.esprit1718b1hrboard.app.client.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXRippler;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import tn.esprit.b1.esprit1718b1hrboard.app.client.tableModels.EmployeeTable;
import tn.esprit.b1.esprit1718b1hrboard.app.client.tableModels.PostsTable;
import tn.esprit.b1.esprit1718b1hrboard.app.client.util.EJBLookupUtil;
import tn.esprit.b1.esprit1718b1hrboard.entities.Employee;
import tn.esprit.b1.esprit1718b1hrboard.entities.Post;
import tn.esprit.b1.esprit1718b1hrboard.services.EmployeeServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.PostServiceRemote;

/**
 * FXML Controller class
 *
 * @author Ghassen
 */
public class PostsMenuController implements Initializable {

    @FXML
    private AnchorPane PostsMainPane;
    @FXML
    private JFXButton AddButton;
    @FXML
    private Label LIstPostsLabel;
    @FXML
    private TableView<PostsTable> PostsTable;
    @FXML
    private TableColumn<PostsTable, String> idEmployee;
    @FXML
    private TableColumn<PostsTable, String> nameEmployee;
    @FXML
    private TableColumn<PostsTable, String> postEmployee;

    public static JFXDialog dialogSkill;
    String jndi = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/PostService!tn.esprit.b1.esprit1718b1hrboard.services.PostServiceRemote";
    PostServiceRemote proxy;
    private ObservableList<PostsTable> list_employees;
    
    @FXML
    private TableColumn<PostsTable, String> descriptionpost;
    @FXML
    private StackPane PostsWraperPane;
    public URL url1; 
    public ResourceBundle rb1;
    @FXML
    private JFXButton ManageButton;
    private PostsTable pp;
    @FXML
    private JFXRadioButton nameSearchRadio;
    @FXML
    private ToggleGroup filter;
    @FXML
    private JFXRadioButton postSerchRadio;
    @FXML
    private JFXRadioButton departementSearchRadio;
    @FXML
    private JFXTextField critiriaSearch;
    
    private FilteredList<PostsTable> filterData;

	private SortedList<PostsTable> sortedEmplyeesList;
    
    /**
     * Initializes the controller class.
     */
    @SuppressWarnings("restriction")
	@Override
    public void initialize(URL url, ResourceBundle rb) {
    	nameSearchRadio.setSelected(true);
    	postSerchRadio.setSelected(false);
    	departementSearchRadio.setSelected(false);
    	critiriaSearch.setText("");
		populatePostsTableView();
		PostsTable.getSelectionModel().selectedItemProperty()
				.addListener((ObservableValue<? extends PostsTable> observable, PostsTable oldValue,
						PostsTable newValue) -> {
					if (newValue == null) {
						return;
					}
					try {
						pp = newValue;
					} catch (Exception e) {
					}
				});
    }  

	private void populatePostsTableView() {
		proxy = (PostServiceRemote) EJBLookupUtil.doLookup(jndi);
		List<Post> pList = new ArrayList<>();
		pList = proxy.findAll();
		list_employees = FXCollections.observableArrayList();
		
		for (Post emp : pList) {
			PostsTable PostsTable = new PostsTable(emp.getCodePost(), emp.getEntitled(), emp.getDepartement().getName(), emp.getDescription());
			list_employees.add(PostsTable);
		}
		idEmployee.setCellValueFactory(new PropertyValueFactory<>("Code"));
		nameEmployee.setCellValueFactory(new PropertyValueFactory<>("Entitled"));
		descriptionpost.setCellValueFactory(new PropertyValueFactory<>("Description"));
		postEmployee.setCellValueFactory(new PropertyValueFactory<>("Department"));
		PostsTable.getItems().clear();
		PostsTable.getItems().addAll(list_employees);
	}

    @FXML
    private void OnAddPostsClicked(MouseEvent event) throws IOException {

			AddPostPopUpController.pm = this;
    		Pane child = null;
			try {
				child = FXMLLoader.load(getClass().getResource("/views/AddPostPopUp.fxml"));
			} catch (IOException ex) {
			}

			JFXDialogLayout dialogLayout = new JFXDialogLayout();
			dialogLayout.getChildren().add(child);
			dialogSkill = new JFXDialog(PostsWraperPane, dialogLayout, JFXDialog.DialogTransition.CENTER);
			dialogSkill.show();
        
    }
    public void ShowPostPane(String code) throws IOException{ 
    		proxy = (PostServiceRemote) EJBLookupUtil.doLookup(jndi);
    		PostManagingPopUpController.PostIn = proxy.findPostCode(code);
    		PostManagingPopUpController.pm = this;
    		Pane child = null;
			try {
				child = FXMLLoader.load(getClass().getResource("/views/PostManagingPopUp.fxml"));
			} catch (IOException ex) {
			}
			JFXDialogLayout dialogLayout = new JFXDialogLayout();
			dialogLayout.getChildren().add(child);
			dialogSkill = new JFXDialog(PostsWraperPane, dialogLayout, JFXDialog.DialogTransition.CENTER);
			dialogSkill.show();	
    }
    public void INITAAT(){
    	this.initialize(url1, rb1);
    }

    @FXML
    private void OnManagePostsClicked(MouseEvent event) throws IOException {
    	ShowPostPane(pp.getCode());
    }

    @FXML
    private void searchEmployees(KeyEvent event) {
    	filterData = new FilteredList<>(list_employees, p -> true);

		critiriaSearch.textProperty().addListener((obsevable, oldvalue, newvalue) -> {
			filterData.setPredicate(employee -> {

				if (newvalue == null || newvalue.isEmpty()) {
					return true;
				}

				String typedText = newvalue.toLowerCase();
				if (nameSearchRadio.isSelected()) {
					if (employee.getEntitled().toLowerCase().indexOf(typedText) != -1) {

						return true;
					}
				} else if (postSerchRadio.isSelected()) {
					if (employee.getCode().toLowerCase().indexOf(typedText) != -1) {

						return true;
					}
				} else {
					if (employee.getDepartment().toLowerCase().indexOf(typedText) != -1) {

						return true;
					}

				}

				return false;
			});
			sortedEmplyeesList = new SortedList<>(filterData);
			sortedEmplyeesList.comparatorProperty().bind(PostsTable.comparatorProperty());
			PostsTable.setItems(sortedEmplyeesList);

		});
    }
    
}
