package tn.esprit.b1.esprit1718b1hrboard.app.client.controllers;



import java.net.URL;
import javafx.util.Duration;

import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;

import org.controlsfx.control.Notifications;
import org.hibernate.dialect.FirebirdDialect;

import javafx.scene.control.Button;

import com.calendarfx.model.Interval;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import tn.esprit.b1.esprit1718b1hrboard.app.client.util.EJBLookupUtil;
import tn.esprit.b1.esprit1718b1hrboard.entities.Tax;
import tn.esprit.b1.esprit1718b1hrboard.services.TaxServiceRemote;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PayrollSettingsController implements Initializable {

    @FXML
    private StackPane stackPane;

    @FXML
    private AnchorPane PayslipsPaneId;

    @FXML
    private Label payementDay;

    @FXML
    private JFXDatePicker calendar;

    @FXML
    private Label days;

    @FXML
    private Label payementDay1;

    @FXML
    private JFXRadioButton monday;

    @FXML
    private JFXRadioButton tuesday;

    @FXML
    private JFXRadioButton wednesday;

    @FXML
    private JFXRadioButton thursday;

    @FXML
    private JFXRadioButton friday;

    @FXML
    private JFXRadioButton saturday;

    @FXML
    private JFXRadioButton sunday;

    @FXML
    private Label payementDay2;

    @FXML
    private Label payementDay21;

    @FXML
    private Label payementDay211;

    @FXML
    private Label payementDay2111;

    @FXML
    private Label payementDay21111;

    @FXML
    private Label payementDay11;

    @FXML
    private JFXTextField BasicSalary;

  
    
    @FXML
    private Spinner<Integer> MinHours;
     
    @FXML
    private Spinner<Double> inputHra;

    @FXML
    private Spinner<Double> BonusInput;

    @FXML
    private Spinner<Double> SSTInput;

    @FXML
    private Spinner<Double> MTinput;

    
    
    
    
    @FXML
    private Button savebtn;

    @FXML
    private Button clearbtn;

   

    @FXML
    void OnClearBtnClicked() {
    	
    	calendar.setValue(null);
    	
    	days.setText(null);
    	payementDay.setText(null);
    	monday.setSelected(false);
    	tuesday.setSelected(false);
    	wednesday.setSelected(false);
    	thursday.setSelected(false);
    	friday.setSelected(false);
    	saturday.setSelected(false);
    	sunday.setSelected(false);

    	

    }

    @FXML
    void OnSaveBtnClicked() {
    	
    	
    	proxyTax = (TaxServiceRemote) EJBLookupUtil.doLookup(jndiTax);
    	
    	
    	Tax tax = new Tax(9,inputHra.getValue().floatValue(), BonusInput.getValue().floatValue(),SSTInput.getValue().floatValue(),MTinput.getValue().floatValue(),Integer.parseInt(payementDay.getText())); 	

    	proxyTax.update(tax);
    	
    	Image valide = new Image("/icons/valide.png");
        Notifications notificationsBuilder = Notifications.create()
                .title("SAVE DONE")
                .text("Generat Settings Saved Succefully ")
                .graphic(new ImageView(valide))
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT);
        notificationsBuilder.show();
        notificationsBuilder.darkStyle();

    }
    
    TaxServiceRemote proxyTax;

    String jndiTax ="esprit1718b1hrboard-ear/esprit1718b1hrboard-service/TaxService!tn.esprit.b1.esprit1718b1hrboard.services.TaxServiceRemote";
    
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
		calendar.setOnAction(e->{
			Integer i = (Integer)calendar.getValue().getDayOfMonth();
			String s = i.toString();
			payementDay.setText(s);
				
			if (LocalDate.now().isBefore( calendar.getValue()))
			{long p2 = ChronoUnit.DAYS.between(LocalDate.now(), calendar.getValue());
			
			    System.out.println(p2);
			    
			    String ss = Long.toString(p2);
			days.setText(ss);
			}
			else {
				
				long p2 = ChronoUnit.DAYS.between(LocalDate.now(), calendar.getValue());
			    System.out.println(p2+30);
			    
			    long p = p2+30;
			    String ss= Long.toString(p);
			    days.setText(ss);
			   

			}
			
			
		});
		
		proxyTax = (TaxServiceRemote) EJBLookupUtil.doLookup(jndiTax);
		
Tax tx= proxyTax.find(9);
System.out.println(tx);
		
		String s = String.valueOf(tx.getPaymentDate());
		payementDay.setText(s);
		
		
		SpinnerValueFactory<Integer> score = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 12,8);
		SpinnerValueFactory<Double> score1 = new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 30,tx.getHRA() );
		SpinnerValueFactory<Double> score2 = new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 30, tx.getBonus());
		SpinnerValueFactory<Double> score13 = new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 30, tx.getMedicareTax());
		SpinnerValueFactory<Double> score14 = new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 30, tx.getSocialSecurityTax());

		
		inputHra.setValueFactory(score1);
		BonusInput.setValueFactory(score2);
		MTinput.setValueFactory(score13);
		SSTInput.setValueFactory(score14);
		MinHours.setValueFactory(score);  
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
