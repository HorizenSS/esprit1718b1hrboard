package tn.esprit.b1.esprit1718b1hrboard.app.client.test;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.itextpdf.text.DocumentException;

import tn.esprit.b1.esprit1718b1hrboard.entities.Account;
import tn.esprit.b1.esprit1718b1hrboard.entities.Employee;
import tn.esprit.b1.esprit1718b1hrboard.entities.PaySlip;
import tn.esprit.b1.esprit1718b1hrboard.entities.Tax;
import tn.esprit.b1.esprit1718b1hrboard.services.AccountServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.EmployeeServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.PaySlipServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.TaxServiceRemote;

public class AmineTest {
	
	
	public static void main(String[] args) throws NamingException, FileNotFoundException, DocumentException {
		// TODO Auto-generated method stub

		
		  String jndiName
		  ="esprit1718b1hrboard-ear/esprit1718b1hrboard-service/TaxService!tn.esprit.b1.esprit1718b1hrboard.services.TaxServiceRemote";
    
		  Context context = new InitialContext();
		  
		  TaxServiceRemote proxy = (TaxServiceRemote)
		  context.lookup(jndiName);
		//  PaySlip ps = new PaySlip(null, null, "Annual Payslip", "in process", null);
			Tax tx = proxy.find(9);
			 System.out.println(tx+" "+"atlaaaaaaaaaaaa3");
			// proxy.GenerateAnnualPaySlip(ps);
		  
		// proxy.ConfirmPayslip(ps);
		  
		
		//  proxy.send("ahmedamine.belmabrouk@esprit.tn", "test mail", "braby emchi");
		  
		//  PaySlip ps = new PaySlip(null, null, null, null, "fiche de paie", null, null);
		//  proxy.save(ps);
		  
//		  List<PaySlip> listps = new ArrayList<>();
//		  listps = proxy.findAll();
//		  System.out.println(listps);
//		  
		  
		
		
	
		
		
		
//
//		 String jndiAccount =
//		 "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/EmployeeService!tn.esprit.b1.esprit1718b1hrboard.services.EmployeeServiceRemote";
//		
//		 Context context = new InitialContext();
//		
//		 EmployeeServiceRemote proxy = (EmployeeServiceRemote)
//		 context.lookup(jndiAccount);
//		
		
//		 Date birth = new Date();
//		 Date start = new Date();
//		 Date end = new Date();
//		 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//		 try {
//		 birth = sdf.parse("29/08/1995");
//		 start = sdf.parse("14/03/2018");
//		 end = sdf.parse("31/03/2022");
//		 } catch (ParseException e) {
//		 // TODO Auto-generated catch block
//		 e.printStackTrace();
//		 }
//		 Employee emp = new Employee("Wajdy", "Bouslama", "12807857",
//		 "454652", "Male", "Wajdy.Bouslama@esprit.tn", (Long) 55054542l, birth
//		 , "Tunisien" , start , end , "Married" , 2 ,"Sousse" ,
//		 (Long)011101105216l ,"expert" , 50 ,(Long) 97773824l ,
//		 "bous.wajdy@gmail.com" , 4.8f, 4.8f , "" , true , 21);
//		 Employee emp1 = new Employee("Zied", "Zouid", "12857857", "454652",
//		 "Male", "Zied.Zouid@esprit.tn", (Long) 55054542l, birth , "Tunisien"
//		 , start , end , "Married" , 2 ,"Tbolba" , (Long)011101105216l ,
//		 "expert" , 50 ,(Long) 97773824l , "Zied.Zouid@gmail.com" , 4.8f ,4.8f
//		 , "" , true , 21);
//		 Employee emp2 = new Employee("Majdi", "Rabie", "12807858", "454653",
//		 "Male", "Majdi.Rabie@esprit.tn", (Long) 55054543l, birth , "Libien" ,
//		 start , end , "Married" , 2 ,"Sfax" , (Long)011101105217l , "expert"
//		 , 50 ,(Long) 97773824l , "Majdi.Rabie@gmail.com" , 4.3f, 4.8f, "" ,
//		 true , 17);
//		 Employee emp3 = new Employee("Ghassen", "Jemai", "12807859",
//		 "454654", "Male", "Ghassen.Jemai@esprit.tn", (Long) 55054543l, birth
//		 , "Austaralien" , start , end , "Married" , 2 ,"Aindrahem" ,
//		 (Long)011101105200l , "moyen" , 50 ,(Long) 97773824l ,
//		 "Ghassen.Jemai@gmail.com" , 3.3f, 3.3f , "" , true , 11);
//		 Employee emp4 = new Employee("Amine", "Belmabrouk", "12807860",
//		 "454655", "Male", "Amine.Belmabrouk@esprit.tn", (Long) 55054543l,
//		 birth , "Tunisien" , start , end , "Married" , 2 ,"Sfax" ,
//		 (Long)011101105201l , "moyen" , 50 ,(Long) 97773824l ,
//		 "Amine.Belmabrouk@gmail.com" , 3.5f, 3.3f , "" , true , 15);
//		 Employee emp5 = new Employee("Ilyes", "El Ayeb", "12807861",
//		 "454656", "Male", "Ilyes.elAyeb@esprit.tn", (Long) 55054543l, birth ,
//		 "Francais" , start , end , "Married" , 2 ,"Ben Arous" ,
//		 (Long)011101105202l , "professionel" , 50 ,(Long) 97773824l ,
//		 "Ilyes.elAyeb@gmail.com" , 4.1f , 3.3f, "" , true , 2);
//		
//		 Employee emp6 = new Employee("Melek", "Mekki", "12857862", "454652",
//		 "Male", "Melek.Mekki@esprit.tn", (Long) 55054542l, birth , "Tunisien"
//		 , start , end , "Married" , 2 ,"Tbolba" , (Long)011101105216l ,
//		 "expert" , 50 ,(Long) 97773824l , "Melek.Mekki@gmail.com" , 4.8f ,
//		 3.3f,"" , true , 21);
//		 Employee emp7 = new Employee("Omar", "Chniti", "12807863", "454653",
//		 "Male", "Omar.Chniti@esprit.tn", (Long) 55054543l, birth , "Francais"
//		 , start , end , "Married" , 2 ,"Sfax" , (Long)011101105217l ,
//		 "expert" , 50 ,(Long) 97773824l , "Omar.Chniti@gmail.com" , 4.3f ,
//		 3.3f,"" , true , 17);
//		 Employee emp8 = new Employee("Karim", "Ladherie", "12807864",
//		 "454654", "Male", "Karim.Ladherie@esprit.tn", (Long) 55054543l, birth
//		 , "Portugais" , start , end , "Married" , 2 ,"Aindrahem" ,
//		 (Long)011101105200l , "moyen" , 50 ,(Long) 97773824l ,
//		 "Karim.Ladherie@gmail.com" , 3.3f, 3.3f , "" , true , 11);
//		 Employee emp9 = new Employee("Borhene", "Handous", "12807865",
//		 "454655", "Male", "Borhene.Handous@esprit.tn", (Long) 55054543l,
//		 birth , "Tunisien" , start , end , "Married" , 2 ,"Sfax" ,
//		 (Long)011101105201l , "moyen" , 50 ,(Long) 97773824l ,
//		 "Borhene.Handous@gmail.com" , 3.5f , 3.3f, "" , true , 15);
//		 Employee emp10 = new Employee("Hedi", "Baccouche", "12807866",
//		 "454656", "Male", "Hedi.Baccouche@esprit.tn", (Long) 55054543l, birth
//		 , "Francais" , start , end , "Married" , 2 ,"Ben Arous" ,
//		 (Long)011101105202l , "professionel" , 50 ,(Long) 97773824l ,
//		 "Hedi.Baccouche@gmail.com" , 4.1f, 3.3f , "" , true , 2);
//		
//		 Employee emp11 = new Employee("Mehdi", "Mellouli", "12807867",
//		 "454652", "Male", "Mehdi.Mellouli@esprit.tn", (Long) 55054542l, birth
//		 , "Tunisien" , start , end , "Married" , 2 ,"Sousse" ,
//		 (Long)011101105216l , "expert" , 50 ,(Long) 97773824l ,
//		 "Mehdi.Mellouli@gmail.com" , 4.8f , 3.3f, "" , true , 21);
//		 Employee emp12 = new Employee("Ilyes", "Chrif", "12857868", "454652",
//		 "Male", "Ilyes.Chrif@esprit.tn", (Long) 55054542l, birth ,
//		 "Portugais" , start , end , "Married" , 2 ,"Tbolba" ,
//		 (Long)011101105216l , "expert" , 50 ,(Long) 97773824l ,
//		 "Ilyes.Chrif@gmail.com" , 4.8f, 3.3f , "" , true , 21);
//		 Employee emp13 = new Employee("Youssef", "Hajomor", "12807869",
//		 "454653", "Male", "Youssef.Hajomor@esprit.tn", (Long) 55054543l,
//		 birth , "Tunisien" , start , end , "Married" , 2 ,"Sfax" ,
//		 (Long)011101105217l , "expert" , 50 ,(Long) 97773824l ,
//		 "Youssef.Hajomor@gmail.com" , 4.3f, 3.3f , "" , true , 17);
//		 Employee emp14 = new Employee("Amine", "Omezzine", "12807870",
//		 "454654", "Male", "Amine.Omezzine@esprit.tn", (Long) 55054543l, birth
//		 , "Francais" , start , end , "Married" , 2 ,"Aindrahem" ,
//		 (Long)011101105200l , "moyen" , 50 ,(Long) 97773824l ,
//		 "Amine.Omezzine@gmail.com" , 3.3f, 3.3f , "" , true , 11);
//		 Employee emp15 = new Employee("Firas", "abdelrazak", "12807871",
//		 "454655", "Male", "Firas.abdelrazak@esprit.tn", (Long) 55054543l,
//		 birth , "Tunisien" , start , end , "Married" , 2 ,"Sfax" ,
//		 (Long)011101105201l , "moyen" , 50 ,(Long) 97773824l ,
//		 "Firas.abdelrazak@gmail.com" ,3.5f , 3.3f, "" , true , 15);
//		 Employee emp16 = new Employee("Firas", "kacem", "12807872", "454656",
//		 "Male", "Firas.kacem@esprit.tn", (Long) 55054543l, birth , "Marocain"
//		 , start , end , "Married" , 2 ,"Ben Arous" , (Long)011101105202l ,
//		 "professionel" , 50 ,(Long) 97773824l , "Firas.kacem@gmail.com" ,
//		 4.1f , 3.3f, "" , true , 2);
//		
//		 Employee emp17 = new Employee("Oumayma", "Cheweya", "12857873",
//		 "454652", "Female", "Oumayma.Cheweya@esprit.tn", (Long) 55054542l,
//		 birth , "Tunisien" , start , end , "Married" , 2 ,"Tbolba" ,
//		 (Long)011101105216l , "expert" , 50 ,(Long) 97773824l ,
//		 "Oumayma.Cheweya@gmail.com" ,4.8f, 3.3f , "" , true , 21);
//		 Employee emp18 = new Employee("Jihen", "Chrif", "12807874", "454653",
//		 "Female", "Jihen.Chrif@esprit.tn", (Long) 55054543l, birth ,
//		 "Portugais" , start , end , "Married" , 2 ,"Sfax" ,
//		 (Long)011101105217l , "expert" , 50 ,(Long) 97773824l ,
//		 "Jihen.Chrif@gmail.com" , 4.3f, 3.3f , "" , true , 17);
//		 Employee emp19 = new Employee("Meriem", "Ben Moustfa", "12807875",
//		 "454654", "Female", "Meriem.BenMoustfa@esprit.tn", (Long) 55054543l,
//		 birth , "Francais" , start , end , "Married" , 2 ,"Aindrahem" ,
//		 (Long)011101105200l , "moyen" , 50 ,(Long) 97773824l ,
//		 "Meriem.BenMoustfa@gmail.com" , 3.3f, 3.3f , "" , true , 11);
//		 Employee emp20 = new Employee("Hejer", "Hraich", "12807876",
//		 "454655", "Female", "Hejer.Hraich@esprit.tn", (Long) 55054543l, birth
//		 , "Marocain" , start , end , "Married" , 2 ,"Sfax" ,
//		 (Long)011101105201l , "moyen" , 50 ,(Long) 97773824l ,
//		 "Hejer.Hraich@gmail.com" , 3.5f , 3.3f, "" , true , 15);
//		 Employee emp21 = new Employee("Dorra", "Triki", "12807877", "454656",
//		 "Female", "Dorra.Triki@esprit.tn", (Long) 55054543l, birth ,
//		 "Tunisien" , start , end , "Married" , 2 ,"Ben Arous" ,
//		 (Long)011101105202l , "professionel" , 50 ,(Long) 97773824l ,
//		 "Dorra.Triki@gmail.com" , 4.1f, 3.3f , "" , true , 2);
//		
//		 proxy.save(emp);
//		 proxy.save(emp1);
//		 proxy.save(emp2);
//		 proxy.save(emp3);
//		 proxy.save(emp4);
//		 proxy.save(emp5);
//		 proxy.save(emp6);
//		 proxy.save(emp7);
//		 proxy.save(emp8);
//		 proxy.save(emp9);
//		 proxy.save(emp10);
//		 proxy.save(emp11);
//		 proxy.save(emp12);
//		 proxy.save(emp13);
//		 proxy.save(emp14);
//		 proxy.save(emp15);
//		 proxy.save(emp16);
//		 proxy.save(emp17);
//		 proxy.save(emp18);
//		 proxy.save(emp19);
//		 proxy.save(emp20);
//		 proxy.save(emp21);
		

//		 List<Employee> employeeList = new ArrayList<>();
//		
//		 employeeList = proxy.findAll();
//		
//		 System.out.println(employeeList);
//		 System.out.println(proxy.find(5));


}
	}
