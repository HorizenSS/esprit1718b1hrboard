package tn.esprit.b1.esprit1718b1hrboard.services;

import java.io.FileNotFoundException;
import java.util.List;

import javax.ejb.Remote;
import javax.naming.NamingException;

import com.itextpdf.text.DocumentException;

import tn.esprit.b1.esprit1718b1hrboard.entities.PaySlip;
import tn.esprit.b1.esprit1718b1hrboard.utilities.IGenericDAO;

@Remote
public interface PaySlipServiceRemote extends IGenericDAO<PaySlip> {
	public List<PaySlip> getAllOrders()throws NamingException;
	public List<PaySlip> getConfirmed()throws NamingException;
	public List<PaySlip> getRejected()throws NamingException;
	public List<PaySlip> getInProcess()throws NamingException;
	
	public void ConfirmPayslip(PaySlip payslip);
    public void RejectPayslip(PaySlip payslip);
	//public void send(String addresses, String topic, String textMessage)throws NamingException ;
    public void  GenerateAnnualPaySlip (PaySlip payslip) throws FileNotFoundException, DocumentException;

	

}
