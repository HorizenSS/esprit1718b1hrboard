


package tn.esprit.b1.esprit1718b1hrboard.app.client.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import org.controlsfx.control.Notifications;
import org.hibernate.hql.internal.ast.tree.InitializeableNode;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import tn.esprit.b1.esprit1718b1hrboard.app.client.util.EJBLookupUtil;
import tn.esprit.b1.esprit1718b1hrboard.entities.Payroll;
import tn.esprit.b1.esprit1718b1hrboard.entities.Tax;
import tn.esprit.b1.esprit1718b1hrboard.services.EmployeeServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.PayrollServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.TaxServiceRemote;

public class PayrollEmployeePopUpController  implements Initializable  {

    @FXML
    private ImageView EmpPic;

    @FXML
    private Label EmpFirstName;

    @FXML
    private Label EmpLastName;

    @FXML
    private Label date;

    @FXML
    private Label poste;

    @FXML
    private Label dateEmbauche;

    @FXML
    private Label EmpFirstName1;

    @FXML
    private Label EmpFirstName11;

    @FXML
    private Label workedHours;

    @FXML
    private Label EmpFirstName111;

    @FXML
    private Label hourWage;

    @FXML
    private Label EmpFirstName1111;

    @FXML
    private Label overtime;

    @FXML
    private JFXButton CalculateS;

    @FXML
    private Label EmpFirstName12;

    @FXML
    private Label basicSalary;

    @FXML
    private Label EmpFirstName121;

    @FXML
    private Label GrossSalary;

    @FXML
    private Label EmpFirstName122;

    @FXML
    private Label NetSalary;

    @FXML
    private JFXButton SaveSalary;
    
    public PayrollCalculateController pcc;
    
    public static double GS;
    public static double NS;
   
    @FXML
    void OnCalculate() {
    	
    proxyemp = (EmployeeServiceRemote) EJBLookupUtil.doLookup(jndiEmp);

    		proxy = (PayrollServiceRemote) EJBLookupUtil.doLookup(jndipayroll);

    		proxyTax = (TaxServiceRemote) EJBLookupUtil.doLookup(jndiTax);
    	
    	double basic = pcc.empSelected.getContract().getHourPrice()*160;
		basicSalary.setText(Double.toString(basic));
		
		Tax tx = proxyTax.find(9);
		
		 GS = proxy.CalculateGrossSalary(basic, pcc.empSelected.getWorkedHours()-160, pcc.empSelected.getContract().getHourPrice(), tx.getHRA(), tx.getBonus());
		
		
		
    	GrossSalary.setText(Double.toString(GS));
    	
    	
    	 NS = proxy.CalculateNetSalary(GS, tx.getSocialSecurityTax(), tx.getMedicareTax());
    	NetSalary.setText(Double.toString(NS));
    	
    }

    @FXML
    void OnSave() {
    	
    	Payroll pp = new Payroll(GS	, NS);
    	
    	//proxy.add(pp, pcc.empSelected);
    	
    	// proxy.save(pp);
    	
   	pcc.empSelected.setPayroll(pp);
    	proxyemp.update(pcc.empSelected);
    	
    	
    	Image valide = new Image("/icons/valide.png");
        Notifications notificationsBuilder = Notifications.create()
                .title("Confirmation")
                .text("Salary saved  ")
                .graphic(new ImageView(valide))
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT);
        notificationsBuilder.darkStyle();
        notificationsBuilder.show();
   
    	
    		

    }

    
    TaxServiceRemote proxyTax;

    String jndiTax ="esprit1718b1hrboard-ear/esprit1718b1hrboard-service/TaxService!tn.esprit.b1.esprit1718b1hrboard.services.TaxServiceRemote";
    
    PayrollServiceRemote proxy ;
	String jndipayroll = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/PayrollService!tn.esprit.b1.esprit1718b1hrboard.services.PayrollServiceRemote" ;      

	EmployeeServiceRemote proxyemp ;
	String jndiEmp = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/EmployeeService!tn.esprit.b1.esprit1718b1hrboard.services.EmployeeServiceRemote";
	
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		EmpFirstName.setText(pcc.empSelected.getFirstName());
		EmpLastName.setText(pcc.empSelected.getLastName());
		date.setText(pcc.empSelected.getBirthDate().toString());
		poste.setText(pcc.empSelected.getPost().getEntitled());
		dateEmbauche.setText(pcc.empSelected.getStartDay().toString());
		workedHours.setText(pcc.empSelected.getWorkedHours().toString());
		hourWage.setText(pcc.empSelected.getContract().getHourPrice().toString());
		overtime.setText(Integer.toString(pcc.empSelected.getWorkedHours()-160));
		
		
		
		
		
	}

}

