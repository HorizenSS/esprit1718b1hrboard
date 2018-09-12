package tn.esprit.b1.esprit1718b1hrboard.app.client.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.ChronoPeriod;
import java.time.chrono.Chronology;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import tn.esprit.b1.esprit1718b1hrboard.entities.Absence;
import tn.esprit.b1.esprit1718b1hrboard.entities.Account;
import tn.esprit.b1.esprit1718b1hrboard.entities.Employee;
import tn.esprit.b1.esprit1718b1hrboard.entities.EmployeeHasSkill;
import tn.esprit.b1.esprit1718b1hrboard.entities.EmployeeHasSkillPk;
import tn.esprit.b1.esprit1718b1hrboard.entities.Project;
import tn.esprit.b1.esprit1718b1hrboard.entities.Skill;
import tn.esprit.b1.esprit1718b1hrboard.entities.Task;
import tn.esprit.b1.esprit1718b1hrboard.entities.TaskPk;
import tn.esprit.b1.esprit1718b1hrboard.services.AbsenceServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.AccountServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.EmployeeHasSkillService;
import tn.esprit.b1.esprit1718b1hrboard.services.EmployeeHasSkillServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.EmployeeServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.ProjectServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.SkillServiceLocal;
import tn.esprit.b1.esprit1718b1hrboard.services.SkillServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.TaskServiceRemote;

public class ZiedTest {

	public static void main(String[] args) throws NamingException {
		// TODO Auto-generated method stub

		/*
		 * String jndiName
		 * ="esprit1718b1hrboard-ear/esprit1718b1hrboard-service/AbsenceService!tn.esprit.b1.esprit1718b1hrboard.services.AbsenceServiceRemote";
		 * 
		 * Context context = new InitialContext();
		 * 
		 * AbsenceServiceRemote proxy = (AbsenceServiceRemote)
		 * context.lookup(jndiName);
		 * 
		 * Absence absence = new Absence(); absence.setStatus(false);
		 * absence.setJustified(true);
		 * absence.setJustifiation("justified absence from Client project");
		 * proxy.save(absence);
		 */
		//
//		 String jndiAccount =
//		 "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/AccountService!tn.esprit.b1.esprit1718b1hrboard.services.AccountServiceRemote";
//		
//		 Context context = new InitialContext();
//		
//		 AccountServiceRemote proxy = (AccountServiceRemote)
//		 context.lookup(jndiAccount);
		//
		// Account account = new Account();
		// account.setLogin("zied");
		// account.setPassword("pass");
		//
		// proxy.save(account);

//		 String jndiAccount =
//		 "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/EmployeeService!tn.esprit.b1.esprit1718b1hrboard.services.EmployeeServiceRemote";
//		
//		 Context context = new InitialContext();
//		
//		 EmployeeServiceRemote proxy = (EmployeeServiceRemote)
//		 context.lookup(jndiAccount);
//		
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
		// Employee emp = new Employee("Wajdy", "Bouslama", "12807857",
		// "454652", "Male", "Wajdy.Bouslama@esprit.tn", (Long) 55054542l, birth
		// , "Tunisien" , start , end , "Married" , 2 ,"Sousse" ,
		// (Long)011101105216l ,"expert" , 50 ,(Long) 97773824l ,
		// "bous.wajdy@gmail.com" , 4.8f, 4.8f , "" , true , 21);
		// Employee emp1 = new Employee("Zied", "Zouid", "12857857", "454652",
		// "Male", "Zied.Zouid@esprit.tn", (Long) 55054542l, birth , "Tunisien"
		// , start , end , "Married" , 2 ,"Tbolba" , (Long)011101105216l ,
		// "expert" , 50 ,(Long) 97773824l , "Zied.Zouid@gmail.com" , 4.8f ,4.8f
		// , "" , true , 21);
//		 Employee emp2 = new Employee("Majdi", "Rabie", "12807858", "454653",
//		 "Male", "Majdi.Rabie@esprit.tn", (Long) 55054543l, birth , "Libien" ,
//		 start , end , "Married" , 2 ,"Sfax" , (Long)011101105217l , "expert"
//		 , 50 ,(Long) 97773824l , "Majdi.Rabie@gmail.com" , 4.3f, 4.8f, "" ,
//		 true , 17);
		// Employee emp3 = new Employee("Ghassen", "Jemai", "12807859",
		// "454654", "Male", "Ghassen.Jemai@esprit.tn", (Long) 55054543l, birth
		// , "Austaralien" , start , end , "Married" , 2 ,"Aindrahem" ,
		// (Long)011101105200l , "moyen" , 50 ,(Long) 97773824l ,
		// "Ghassen.Jemai@gmail.com" , 3.3f, 3.3f , "" , true , 11);
		// Employee emp4 = new Employee("Amine", "Belmabrouk", "12807860",
		// "454655", "Male", "Amine.Belmabrouk@esprit.tn", (Long) 55054543l,
		// birth , "Tunisien" , start , end , "Married" , 2 ,"Sfax" ,
		// (Long)011101105201l , "moyen" , 50 ,(Long) 97773824l ,
		// "Amine.Belmabrouk@gmail.com" , 3.5f, 3.3f , "" , true , 15);
		// Employee emp5 = new Employee("Ilyes", "El Ayeb", "12807861",
		// "454656", "Male", "Ilyes.elAyeb@esprit.tn", (Long) 55054543l, birth ,
		// "Francais" , start , end , "Married" , 2 ,"Ben Arous" ,
		// (Long)011101105202l , "professionel" , 50 ,(Long) 97773824l ,
		// "Ilyes.elAyeb@gmail.com" , 4.1f , 3.3f, "" , true , 2);
		//
		// Employee emp6 = new Employee("Melek", "Mekki", "12857862", "454652",
		// "Male", "Melek.Mekki@esprit.tn", (Long) 55054542l, birth , "Tunisien"
		// , start , end , "Married" , 2 ,"Tbolba" , (Long)011101105216l ,
		// "expert" , 50 ,(Long) 97773824l , "Melek.Mekki@gmail.com" , 4.8f ,
		// 3.3f,"" , true , 21);
		// Employee emp7 = new Employee("Omar", "Chniti", "12807863", "454653",
		// "Male", "Omar.Chniti@esprit.tn", (Long) 55054543l, birth , "Francais"
		// , start , end , "Married" , 2 ,"Sfax" , (Long)011101105217l ,
		// "expert" , 50 ,(Long) 97773824l , "Omar.Chniti@gmail.com" , 4.3f ,
		// 3.3f,"" , true , 17);
		// Employee emp8 = new Employee("Karim", "Ladherie", "12807864",
		// "454654", "Male", "Karim.Ladherie@esprit.tn", (Long) 55054543l, birth
		// , "Portugais" , start , end , "Married" , 2 ,"Aindrahem" ,
		// (Long)011101105200l , "moyen" , 50 ,(Long) 97773824l ,
		// "Karim.Ladherie@gmail.com" , 3.3f, 3.3f , "" , true , 11);
		// Employee emp9 = new Employee("Borhene", "Handous", "12807865",
		// "454655", "Male", "Borhene.Handous@esprit.tn", (Long) 55054543l,
		// birth , "Tunisien" , start , end , "Married" , 2 ,"Sfax" ,
		// (Long)011101105201l , "moyen" , 50 ,(Long) 97773824l ,
		// "Borhene.Handous@gmail.com" , 3.5f , 3.3f, "" , true , 15);
		// Employee emp10 = new Employee("Hedi", "Baccouche", "12807866",
		// "454656", "Male", "Hedi.Baccouche@esprit.tn", (Long) 55054543l, birth
		// , "Francais" , start , end , "Married" , 2 ,"Ben Arous" ,
		// (Long)011101105202l , "professionel" , 50 ,(Long) 97773824l ,
		// "Hedi.Baccouche@gmail.com" , 4.1f, 3.3f , "" , true , 2);
		//
		// Employee emp11 = new Employee("Mehdi", "Mellouli", "12807867",
		// "454652", "Male", "Mehdi.Mellouli@esprit.tn", (Long) 55054542l, birth
		// , "Tunisien" , start , end , "Married" , 2 ,"Sousse" ,
		// (Long)011101105216l , "expert" , 50 ,(Long) 97773824l ,
		// "Mehdi.Mellouli@gmail.com" , 4.8f , 3.3f, "" , true , 21);
		// Employee emp12 = new Employee("Ilyes", "Chrif", "12857868", "454652",
		// "Male", "Ilyes.Chrif@esprit.tn", (Long) 55054542l, birth ,
		// "Portugais" , start , end , "Married" , 2 ,"Tbolba" ,
		// (Long)011101105216l , "expert" , 50 ,(Long) 97773824l ,
		// "Ilyes.Chrif@gmail.com" , 4.8f, 3.3f , "" , true , 21);
		// Employee emp13 = new Employee("Youssef", "Hajomor", "12807869",
		// "454653", "Male", "Youssef.Hajomor@esprit.tn", (Long) 55054543l,
		// birth , "Tunisien" , start , end , "Married" , 2 ,"Sfax" ,
		// (Long)011101105217l , "expert" , 50 ,(Long) 97773824l ,
		// "Youssef.Hajomor@gmail.com" , 4.3f, 3.3f , "" , true , 17);
		// Employee emp14 = new Employee("Amine", "Omezzine", "12807870",
		// "454654", "Male", "Amine.Omezzine@esprit.tn", (Long) 55054543l, birth
		// , "Francais" , start , end , "Married" , 2 ,"Aindrahem" ,
		// (Long)011101105200l , "moyen" , 50 ,(Long) 97773824l ,
		// "Amine.Omezzine@gmail.com" , 3.3f, 3.3f , "" , true , 11);
		// Employee emp15 = new Employee("Firas", "abdelrazak", "12807871",
		// "454655", "Male", "Firas.abdelrazak@esprit.tn", (Long) 55054543l,
		// birth , "Tunisien" , start , end , "Married" , 2 ,"Sfax" ,
		// (Long)011101105201l , "moyen" , 50 ,(Long) 97773824l ,
		// "Firas.abdelrazak@gmail.com" ,3.5f , 3.3f, "" , true , 15);
		// Employee emp16 = new Employee("Firas", "kacem", "12807872", "454656",
		// "Male", "Firas.kacem@esprit.tn", (Long) 55054543l, birth , "Marocain"
		// , start , end , "Married" , 2 ,"Ben Arous" , (Long)011101105202l ,
		// "professionel" , 50 ,(Long) 97773824l , "Firas.kacem@gmail.com" ,
		// 4.1f , 3.3f, "" , true , 2);
		//
		// Employee emp17 = new Employee("Oumayma", "Cheweya", "12857873",
		// "454652", "Female", "Oumayma.Cheweya@esprit.tn", (Long) 55054542l,
		// birth , "Tunisien" , start , end , "Married" , 2 ,"Tbolba" ,
		// (Long)011101105216l , "expert" , 50 ,(Long) 97773824l ,
		// "Oumayma.Cheweya@gmail.com" ,4.8f, 3.3f , "" , true , 21);
		// Employee emp18 = new Employee("Jihen", "Chrif", "12807874", "454653",
		// "Female", "Jihen.Chrif@esprit.tn", (Long) 55054543l, birth ,
		// "Portugais" , start , end , "Married" , 2 ,"Sfax" ,
		// (Long)011101105217l , "expert" , 50 ,(Long) 97773824l ,
		// "Jihen.Chrif@gmail.com" , 4.3f, 3.3f , "" , true , 17);
		// Employee emp19 = new Employee("Meriem", "Ben Moustfa", "12807875",
		// "454654", "Female", "Meriem.BenMoustfa@esprit.tn", (Long) 55054543l,
		// birth , "Francais" , start , end , "Married" , 2 ,"Aindrahem" ,
		// (Long)011101105200l , "moyen" , 50 ,(Long) 97773824l ,
		// "Meriem.BenMoustfa@gmail.com" , 3.3f, 3.3f , "" , true , 11);
		// Employee emp20 = new Employee("Hejer", "Hraich", "12807876",
		// "454655", "Female", "Hejer.Hraich@esprit.tn", (Long) 55054543l, birth
		// , "Marocain" , start , end , "Married" , 2 ,"Sfax" ,
		// (Long)011101105201l , "moyen" , 50 ,(Long) 97773824l ,
		// "Hejer.Hraich@gmail.com" , 3.5f , 3.3f, "" , true , 15);
		// Employee emp21 = new Employee("Dorra", "Triki", "12807877", "454656",
		// "Female", "Dorra.Triki@esprit.tn", (Long) 55054543l, birth ,
		// "Tunisien" , start , end , "Married" , 2 ,"Ben Arous" ,
		// (Long)011101105202l , "professionel" , 50 ,(Long) 97773824l ,
		// "Dorra.Triki@gmail.com" , 4.1f, 3.3f , "" , true , 2);
		//
		// proxy.save(emp);
		// proxy.save(emp1);
//		 proxy.save(emp2);
		// proxy.save(emp3);
		// proxy.save(emp4);
		// proxy.save(emp5);
		// proxy.save(emp6);
		// proxy.save(emp7);
		// proxy.save(emp8);
		// proxy.save(emp9);
		// proxy.save(emp10);
		// proxy.save(emp11);
		// proxy.save(emp12);
		// proxy.save(emp13);
		// proxy.save(emp14);
		// proxy.save(emp15);
		// proxy.save(emp16);
		// proxy.save(emp17);
		// proxy.save(emp18);
		// proxy.save(emp19);
		// proxy.save(emp20);
		// proxy.save(emp21);
		//
		//
		// List<Employee> employeeList = new ArrayList<>();
		//
		// employeeList = proxy.findAll();
		//
		// System.out.println(employeeList);

		String jndiName = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/EmployeeService!tn.esprit.b1.esprit1718b1hrboard.services.EmployeeServiceRemote";
//		String jndiName2 = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/SkillService!tn.esprit.b1.esprit1718b1hrboard.services.SkillServiceRemote";
//		String jndiName3 = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/EmployeeHasSkillService!tn.esprit.b1.esprit1718b1hrboard.services.EmployeeHasSkillServiceRemote";
//
		Context context = new InitialContext();
//
		EmployeeServiceRemote proxy = (EmployeeServiceRemote) context.lookup(jndiName);
//		SkillServiceRemote proxy2 = (SkillServiceRemote) context.lookup(jndiName2);
//		EmployeeHasSkillServiceRemote proxy3 = (EmployeeHasSkillServiceRemote) context.lookup(jndiName3);
//
		Employee employee1, employee2;
//		Skill skill1, skill2, skill3, skill4;
		//
		 employee1 = proxy.find(1);
		 employee2 = proxy.find(2);
		//
		// skill1 = proxy2.find(1);
		// skill2 = proxy2.find(2);
		// skill3 = proxy2.find(3);
		// skill4 = proxy2.find(4);
		//
		// System.out.println(employee1);
		// System.out.println(employee2);
		// System.out.println(skill1);
		// System.out.println(skill2);
		// System.out.println(skill3);
		// System.out.println(skill4);
		//
		// EmployeeHasSkillPk pk = new EmployeeHasSkillPk();
		// pk.setIdEmployee(employee1.getId());
		// pk.setIdSkill(skill1.getId());
		//
		// EmployeeHasSkillPk pk2 = new EmployeeHasSkillPk();
		// pk2.setIdEmployee(employee1.getId());
		// pk2.setIdSkill(skill2.getId());
		//
		// EmployeeHasSkillPk pk3 = new EmployeeHasSkillPk();
		// pk3.setIdEmployee(employee2.getId());
		// pk3.setIdSkill(skill3.getId());
		//
		//
		// EmployeeHasSkillPk pk4 = new EmployeeHasSkillPk();
		// pk4.setIdEmployee(employee2.getId());
		// pk4.setIdSkill(skill4.getId());
		//
		//
		// EmployeeHasSkill employeeHasSkill = new EmployeeHasSkill();
		// employeeHasSkill.setCertifcation(true);
		// employeeHasSkill.setLevel(5);
		// employeeHasSkill.setSkillNote(5F);
		// employeeHasSkill.setEmployee(employee1);
		// employeeHasSkill.setSkill(skill1);
		// employeeHasSkill.setEmployeeHasSkillPk(pk);
		//
		// EmployeeHasSkill employeeHasSkill2 = new EmployeeHasSkill();
		// employeeHasSkill2.setCertifcation(true);
		// employeeHasSkill2.setLevel(5);
		// employeeHasSkill2.setSkillNote(5F);
		// employeeHasSkill2.setEmployee(employee1);
		// employeeHasSkill2.setSkill(skill2);
		// employeeHasSkill2.setEmployeeHasSkillPk(pk2);
		//
		// EmployeeHasSkill employeeHasSkill3 = new EmployeeHasSkill();
		// employeeHasSkill3.setCertifcation(true);
		// employeeHasSkill3.setLevel(5);
		// employeeHasSkill3.setSkillNote(5F);
		// employeeHasSkill3.setEmployee(employee2);
		// employeeHasSkill3.setSkill(skill3);
		// employeeHasSkill3.setEmployeeHasSkillPk(pk3);
		//
		// EmployeeHasSkill employeeHasSkill4 = new EmployeeHasSkill();
		// employeeHasSkill4.setCertifcation(true);
		// employeeHasSkill4.setLevel(5);
		// employeeHasSkill4.setSkillNote(5F);
		// employeeHasSkill4.setEmployee(employee2);
		// employeeHasSkill4.setSkill(skill4);
		// employeeHasSkill4.setEmployeeHasSkillPk(pk4);
		//
		// proxy3.save(employeeHasSkill);
		// proxy3.save(employeeHasSkill2);
		// proxy3.save(employeeHasSkill3);
		// proxy3.save(employeeHasSkill4);

		// // System.out.println(proxy3.findAll());
		// //
		// // System.out.println(employee1.getSpecificationsSkills());
		//
		// Employee employee3 = new Employee();
//		employee1 = proxy.find(1);
//
//		System.out.println(employee1);

		// for(EmployeeHasSkill skill : employee3.getSpecificationsSkills()){
		//
		// System.out.println(skill.getSkill());
		//
		// }

		// System.out.println(proxy3.findSkillsDetailsByEmployee(employee2));;

		//

		// String jndiProject2 =
		// "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/ProjectService!tn.esprit.b1.esprit1718b1hrboard.services.ProjectServiceRemote";
		// Context context1 = new InitialContext();
		//
		// ProjectServiceRemote proxyProject;
		//
		// proxyProject = (ProjectServiceRemote) context1.lookup(jndiProject2);
		// Date birth = new Date();
		// Date start = new Date();
		// SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		// try {
		// birth = sdf.parse("29/08/1995");
		// start = sdf.parse("14/03/2018");
		// } catch (ParseException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		//
		// Project pr1 = new Project("Pidev1 Java EE", "Developpement", birth,
		// start, 200d, 6f);
		// Project pr2 = new Project("Pidev2 Java EE", "Developpement", birth,
		// start, 200d, 6f);
		// Project pr3 = new Project("Pidev3 Java EE", "Developpement", birth,
		// start, 200d, 6f);
		// Project pr4 = new Project("Pidev4 Java EE", "Developpement", birth,
		// start, 200d, 6f);
		//
		// proxyProject.save(pr1);
		// proxyProject.save(pr2);
		// proxyProject.save(pr3);
		// proxyProject.save(pr4);

		// System.out.println("/**************test******************/");

		// String jndiProject2 =
		// "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/EmployeeService!tn.esprit.b1.esprit1718b1hrboard.services.EmployeeServiceRemote";
		// Context context1 = new InitialContext();
		//
		// EmployeeServiceRemote proxyProject1;
		//
		// proxyProject1 = (EmployeeServiceRemote)
		// context1.lookup(jndiProject2);
		// System.out.println(proxyProject1.EmployeeWithoutAccount(null, null));

		/* Tasks Add */

		String jndiTask = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/TaskService!tn.esprit.b1.esprit1718b1hrboard.services.TaskServiceRemote";
		Context context1 = new InitialContext();

		TaskServiceRemote proxyTask;
		proxyTask = (TaskServiceRemote) context1.lookup(jndiTask);
		//
		// TaskPk taskPk1 = new TaskPk();
		// taskPk1.setIdEmployee(1);
		// taskPk1.setIdProject(1);
		// taskPk1.setName("HIBERNATE");
		//
		// TaskPk taskPk2 = new TaskPk();
		// taskPk2.setIdEmployee(1);
		// taskPk2.setIdProject(1);
		// taskPk2.setName("EJB");
		//
		// TaskPk taskPk3 = new TaskPk();
		// taskPk3.setIdEmployee(1);
		// taskPk3.setIdProject(2);
		// taskPk3.setName("COMPOSER");
		//
		// Date birth = new Date();
		// Date start = new Date();
		// SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		// try {
		// birth = sdf.parse("29/08/1995");
		// start = sdf.parse("14/03/2018");
		// } catch (ParseException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		//
		// Task task1 = new Task(taskPk1,birth,start,6f,5f);
		// Task task2 = new Task(taskPk2,birth,start,6f,5f);
		// Task task3 = new Task(taskPk3,birth,start,6f,5f);
		//
		// proxyTask.save(task1);
		// proxyTask.save(task2);
		// proxyTask.save(task3);

		/* afficher les projet d'un employee */
		
//		 String jndiProject2 =
//		 "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/ProjectService!tn.esprit.b1.esprit1718b1hrboard.services.ProjectServiceRemote";
//		 Context context2 = new InitialContext();
//		
//		 ProjectServiceRemote proxyProject;
//		
//		 proxyProject = (ProjectServiceRemote) context2.lookup(jndiProject2);
//
//		 List<Project> projects =
//		 proxyProject.findAllProjectsByEmployee(employee1);
//
//		 for (Project project : projects) {
//		 System.out.println(project);
//		 }
//		 Project project1 ;
//		
//		 project1 = proxyProject.find(1);
//		
//		 System.out.println(project1);
//		
//		
//		
//		 List<Task> tasks = proxyTask.findTasksByEmployeeAndProject(employee1,
//		 project1);
		
		 /*Tasks List*/
		
//		 for(Task task : tasks){
//		 System.out.println(task);
//		 }

//		LocalDate localDate = LocalDate.now();
//
//		System.out.println(localDate);
//
//		Date birth = new Date();
//		Date start = new Date();
//		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//		try {
//			birth = sdf.parse("29/08/1995");
//			start = sdf.parse("14/10/2018");
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		LocalDate localDate2 = LocalDate.of(birth.getYear(), birth.getMonth(), birth.getDay());
//		
//		if(localDate2.isBefore(localDate)){
//			System.out.println("Before success");
//		}

//		 String jndiProject2 =
//		 "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/ProjectService!tn.esprit.b1.esprit1718b1hrboard.services.ProjectServiceRemote";
//		 Context context2 = new InitialContext();
//		
//		 ProjectServiceRemote proxyProject;
//		
//		 proxyProject = (ProjectServiceRemote) context2.lookup(jndiProject2);
//		 
//		 Project project1,project2;
//		 
//			project1= proxyProject.find(1);
//			project2= proxyProject.find(2);
//			
//			
//			project1.setProjectMaster(employee2);
//			project2.setProjectMaster(employee2);
//			
//			proxyProject.update(project1);
//			proxyProject.update(project2);
		 
		 
		 String jndiAbscence =
		 "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/AbsenceService!tn.esprit.b1.esprit1718b1hrboard.services.AbsenceServiceRemote";
		 Context context2 = new InitialContext();
		
		 AbsenceServiceRemote proxyAbscence;
		
		 proxyAbscence = (AbsenceServiceRemote) context2.lookup(jndiAbscence);
		 
			 Date birth = new Date();
			 Date start = new Date();
			 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			 try {
			 birth = sdf.parse("29/08/1995");
			 start = sdf.parse("14/03/2018");
			 } catch (ParseException e) {
			 // TODO Auto-generated catch block
			 e.printStackTrace();
			 }
		 
			 Absence absence1 = new Absence(birth,true,"abs1",true,start);
			 Absence absence2 = new Absence(birth,true,"abs2",true,start);
			 Absence absence3 = new Absence(birth,true,"abs3",true,start);
			 Absence absence4 = new Absence(birth,true,"abs4",true,start);
			 
			 absence1.setAbsentEmployee(employee1);
			 absence2.setAbsentEmployee(employee1);
			 
			 absence1.setSubstituteEmployee(employee2);
			 absence2.setSubstituteEmployee(employee2);
			 
			 
				proxyAbscence.save(absence1);
				proxyAbscence.save(absence2);
				proxyAbscence.save(absence3);
				proxyAbscence.save(absence4);
				
	}
}
