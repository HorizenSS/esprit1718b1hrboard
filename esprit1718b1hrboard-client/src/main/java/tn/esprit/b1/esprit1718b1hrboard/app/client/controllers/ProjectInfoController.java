/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b1.esprit1718b1hrboard.app.client.controllers;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import tn.esprit.b1.esprit1718b1hrboard.app.client.util.EJBLookupUtil;
import tn.esprit.b1.esprit1718b1hrboard.entities.Project;
import tn.esprit.b1.esprit1718b1hrboard.entities.Task;
import tn.esprit.b1.esprit1718b1hrboard.services.ProjectServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.TaskServiceRemote;

/**
 * FXML Controller class
 *
 * @author ilyes
 */
public class ProjectInfoController implements Initializable {

    @FXML
    private AnchorPane pop;
    @FXML
    private Label note;
    @FXML
    private Label projectname;
    @FXML
    private Label startdate;
    @FXML
    private Label enddate;
    public static  String pname;
    ProjectServiceRemote proxyEmployee;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	String jndiEmployee = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/ProjectService!tn.esprit.b1.esprit1718b1hrboard.services.ProjectServiceRemote";

		proxyEmployee = (ProjectServiceRemote) EJBLookupUtil.doLookup(jndiEmployee);
		List<Project> projectlist = new ArrayList<>();
       projectlist = proxyEmployee.findProjectByname(pname); 
       for (Project emp : projectlist)
    	   
        {
    	    if (emp.getClientAppreciation()==null)
    	    	note.setText("****");
    	    else
            note.setText(emp.getClientAppreciation().toString());    
    	    if(emp.getStartDate()==null)
    	    	startdate.setText("***");
    	    else	
    	    { SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
            String sdd = sd.format(emp.getStartDate());
            startdate.setText(sdd);}
    	    if(emp.getEndDate()==null)
    	    	enddate.setText("***");
    	    else	
    	    {  SimpleDateFormat sd1 = new SimpleDateFormat("dd/MM/yyyy");
            String sddd = sd1.format(emp.getEndDate());
            enddate.setText(sddd);}
    	   if (pname==null)
    		   projectname.setText("****");
    	   else
            projectname.setText(pname);
            
       
        
      
        }
    }}
    
    

