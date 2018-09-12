package tn.esprit.b1.esprit1718b1hrboard.services;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.awt.Font;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.util.logging.Level;
import java.util.logging.Logger;

import tn.esprit.b1.esprit1718b1hrboard.entities.PaySlip;
import tn.esprit.b1.esprit1718b1hrboard.utilities.GenericDAO;

@Stateless
public class PaySlipService extends GenericDAO<PaySlip> implements PaySlipServiceRemote, PaySlipServiceLocal {

	@PersistenceContext
	private EntityManager entityManager;

	public PaySlipService() {
		super(PaySlip.class);
	}

	@Override
	public List<PaySlip> getAllOrders() throws NamingException {
		Query req=entityManager.createQuery("SELECT  ps FROM PaySlip ps ");
		
		@SuppressWarnings("unchecked")
		List<PaySlip> orderslist= req.getResultList();
		if(orderslist==null)
		{ System.out.println("empty list");}
		else{
			return orderslist;
		}
	
		
		return orderslist;
		
	}

	@Override
	public void ConfirmPayslip(PaySlip payslip) {
		
			Query req=entityManager.createQuery("UPDATE PaySlip SET state=:state WHERE id=:id");
			int rr = req.setParameter("state", "confirmed").setParameter("id",payslip.getId()).executeUpdate();
			
		System.out.println(rr);
	}

	@Override
	public void RejectPayslip(PaySlip payslip) {
		
		Query req=entityManager.createQuery("update PaySlip set state=:state where id=:id");
		req.setParameter("state", "rejected").setParameter("id",payslip.getId()).executeUpdate();		
	}
	
//	public void send(String addresses, String topic, String textMessage)throws NamingException {
//		
//        try {
//        	InitialContext ic = new InitialContext();
//        	session = (Session) ic.lookup("java:jboss/mail/gmail");
//            Message message = new MimeMessage(session);
//            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(addresses));
//            message.setSubject(topic);
//            message.setText(textMessage);
// 
//            Transport.send(message);
// 
//        } catch (MessagingException e) {
//        }
//    }

	
	
	 public void  GenerateAnnualPaySlip (PaySlip payslip) throws FileNotFoundException, DocumentException{
	     
		  Document document = new Document();
 	        String FirstName = payslip.getEmployee().getFirstName();
 	        String LastName = payslip.getEmployee().getLastName();
 	        String grade = payslip.getEmployee().getGrade();
 	       PdfWriter.getInstance(document, new FileOutputStream(FirstName+LastName +".pdf"));
		 
	        try{
	     
	        	
	        	 
	        	 document.open();
	        	
	        	document.add(new Paragraph("PAY SLIP",FontFactory.getFont(FontFactory.TIMES_BOLD,20,Font.BOLD )));
	        	document.add(new Paragraph(new Date().toString()));
	        	document.add(new Paragraph("-------------------------------------------------------------------------------------------"));
	        	document.add((new Paragraph("EMPLOYEE DETAILS",FontFactory.getFont(FontFactory.TIMES_ROMAN,15,Font.BOLD))));
	        	document.add((new Paragraph("Name of Employee: " +FirstName + " "+LastName,FontFactory.getFont(FontFactory.TIMES_ROMAN,10,Font.PLAIN))));
	       //   document.add((new Paragraph("Designation: "+value2,FontFactory.getFont(FontFactory.TIMES_ROMAN,10,Font.PLAIN))));
	        	document.add((new Paragraph("Grade: "+grade,FontFactory.getFont(FontFactory.TIMES_ROMAN,10,Font.PLAIN))));
	        	document.add(new Paragraph("-------------------------------------------------------------------------------------------"));
	        	document.add(new Paragraph("SALARY",FontFactory.getFont(FontFactory.TIMES_ROMAN,15,Font.BOLD)));
	        	document.add(new Paragraph("Basic Salary: $",FontFactory.getFont(FontFactory.TIMES_ROMAN,10,Font.PLAIN)));
	        	document.add(new Paragraph("Overtime:  Hours",FontFactory.getFont(FontFactory.TIMES_ROMAN,10,Font.PLAIN)));
	        	document.add(new Paragraph("Medical: $",FontFactory.getFont(FontFactory.TIMES_ROMAN,10,Font.PLAIN)));
	        	document.add(new Paragraph("Bonus: $",FontFactory.getFont(FontFactory.TIMES_ROMAN,10,Font.PLAIN)));
	        	document.add(new Paragraph("Other: $",FontFactory.getFont(FontFactory.TIMES_ROMAN,10,Font.PLAIN)));
	        	document.add(new Paragraph("-------------------------------------------------------------------------------------------"));
	        	document.add(new Paragraph("DEDUCTION",FontFactory.getFont(FontFactory.TIMES_ROMAN,15,Font.BOLD)));
	        	document.add(new Paragraph("Deduction Details: ",FontFactory.getFont(FontFactory.TIMES_ROMAN,10,Font.PLAIN)));
	        	document.add(new Paragraph("Total Deductions : $" ,FontFactory.getFont(FontFactory.TIMES_ROMAN,10,Font.PLAIN)));
	        	document.add(new Paragraph("-------------------------------------------------------------------------------------------"));
	        	document.add(new Paragraph("TOTAL PAYMENT",FontFactory.getFont(FontFactory.TIMES_ROMAN,15,Font.BOLD)));
	        	document.add(new Paragraph("Total Earnings: ",FontFactory.getFont(FontFactory.TIMES_ROMAN,10,Font.PLAIN)));
	        	document.add(new Paragraph("Net Pay : " ,FontFactory.getFont(FontFactory.TIMES_ROMAN,10,Font.PLAIN)));
	        	document.add(new Paragraph("-------------------------------------------------------------------------------------------"));
	             
	        	
	            
	        	document.close();  
	        }
	        catch(Exception e){
	            System.out.println(e);
	        }
	       
	    }

	@Override
	public List<PaySlip> getConfirmed() throws NamingException {
	Query req=entityManager.createQuery("SELECT  ps FROM PaySlip ps WHERE ps.state='confirmed' ",PaySlip.class);
		
		@SuppressWarnings("unchecked")
		List<PaySlip> orderslist= req.getResultList();
		if(orderslist==null)
		{ System.out.println("empty list");}
		else{
			return orderslist;
		}
	
		
		return orderslist;
	}

	@Override
	public List<PaySlip> getRejected() throws NamingException {
Query req=entityManager.createQuery("SELECT  ps FROM PaySlip ps WHERE ps.state='rejected' ",PaySlip.class);
		
		@SuppressWarnings("unchecked")
		List<PaySlip> orderslist= req.getResultList();
		if(orderslist==null)
		{ System.out.println("empty list");}
		else{
			return orderslist;
		}
	
		
		return orderslist;
	}

	@Override
	public List<PaySlip> getInProcess() throws NamingException {
		
		String st = "in process";
		Query req=entityManager.createQuery("SELECT  ps FROM PaySlip ps WHERE ps.state='in process' ",PaySlip.class);
		
		@SuppressWarnings("unchecked")
		List<PaySlip> orderslist= req.getResultList();
		if(orderslist==null)
		{ System.out.println("empty list");}
		else{
			return orderslist;
		}
	
		
		return orderslist;
	}
	
	
	
	}
	
	
	


