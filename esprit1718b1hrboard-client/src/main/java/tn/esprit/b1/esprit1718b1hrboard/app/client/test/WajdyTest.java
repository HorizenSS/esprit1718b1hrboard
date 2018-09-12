package tn.esprit.b1.esprit1718b1hrboard.app.client.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import tn.esprit.b1.esprit1718b1hrboard.app.client.util.EJBLookupUtil;
import tn.esprit.b1.esprit1718b1hrboard.entities.Candidate;
import tn.esprit.b1.esprit1718b1hrboard.entities.Employee;
import tn.esprit.b1.esprit1718b1hrboard.entities.EmployeeHasSkill;
import tn.esprit.b1.esprit1718b1hrboard.entities.EmployeeHasSkillPk;
import tn.esprit.b1.esprit1718b1hrboard.entities.Interview;
import tn.esprit.b1.esprit1718b1hrboard.services.CandidateServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.CandidateHasSkillSeviceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.EmployeeHasSkillServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.EmployeeServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.InterviewServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.JobOfferServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.SkillServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.entities.JobOffer;
import tn.esprit.b1.esprit1718b1hrboard.entities.Skill;
import tn.esprit.b1.esprit1718b1hrboard.entities.CandidateHasSkillPk;
import tn.esprit.b1.esprit1718b1hrboard.entities.CandidateHasSkill;

public class WajdyTest {
	public static void main(String[] args) throws NamingException {
		// TODO Auto-generated method stub

		String jndiEmployee = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/EmployeeService!tn.esprit.b1.esprit1718b1hrboard.services.EmployeeServiceRemote";

		Context context = new InitialContext();

		EmployeeServiceRemote proxy = (EmployeeServiceRemote) context.lookup(jndiEmployee);
		Date birth = new Date();
		Date start = new Date();
		Date end = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			birth = sdf.parse("29/08/1995");
			start = sdf.parse("14/03/2018");
			end = sdf.parse("31/03/2022");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Employee emp = new Employee("Wajdy", "Bouslama", "12807857", "454652", "Male", "Wajdy.Bouslama@esprit.tn",
				(Long) 55054542l, birth, "Tunisien", start, end, "Married", 2, "Sousse", (Long) 011101105216l, "expert",
				50, (Long) 97773824l, "bous.wajdy@gmail.com", (Float) 4.8f, (Float) 4.1f, "", true, 21);
		Employee emp1 = new Employee("Zied", "Zouid", "12857857", "454652", "Male", "Zied.Zouid@esprit.tn",
				(Long) 55054542l, birth, "Tunisien", start, end, "Married", 2, "Tbolba", (Long) 011101105216l, "expert",
				50, (Long) 97773824l, "Zied.Zouid@gmail.com", (Float) 4.8f, (Float) 4.1f, "", true, 21);
		Employee emp2 = new Employee("Majdi", "Rabie", "12807858", "454653", "Male", "Majdi.Rabie@esprit.tn",
				(Long) 55054543l, birth, "Libien", start, end, "Married", 2, "Sfax", (Long) 011101105217l, "expert", 50,
				(Long) 97773824l, "Majdi.Rabie@gmail.com", (Float) 4.3f, (Float) 4.1f, "", true, 17);
		Employee emp3 = new Employee("Ghassen", "Jemai", "12807859", "454654", "Male", "Ghassen.Jemai@esprit.tn",
				(Long) 55054543l, birth, "Austaralien", start, end, "Married", 2, "Aindrahem", (Long) 011101105200l,
				"moyen", 50, (Long) 97773824l, "Ghassen.Jemai@gmail.com", (Float) 3.3f, (Float) 4.1f, "", true, 11);
		Employee emp4 = new Employee("Amine", "Belmabrouk", "12807860", "454655", "Male", "Amine.Belmabrouk@esprit.tn",
				(Long) 55054543l, birth, "Tunisien", start, end, "Married", 2, "Sfax", (Long) 011101105201l, "moyen",
				50, (Long) 97773824l, "Amine.Belmabrouk@gmail.com", (Float) 3.5f, (Float) 4.1f, "", true, 15);
		Employee emp5 = new Employee("Ilyes", "El Ayeb", "12807861", "454656", "Male", "Ilyes.elAyeb@esprit.tn",
				(Long) 55054543l, birth, "Francais", start, end, "Married", 2, "Ben Arous", (Long) 011101105202l,
				"professionel", 50, (Long) 97773824l, "Ilyes.elAyeb@gmail.com", (Float) 4.1f, (Float) 4.1f, "", true,
				2);

		Employee emp6 = new Employee("Melek", "Mekki", "12857862", "454652", "Male", "Melek.Mekki@esprit.tn",
				(Long) 55054542l, birth, "Tunisien", start, end, "Married", 2, "Tbolba", (Long) 011101105216l, "expert",
				50, (Long) 97773824l, "Melek.Mekki@gmail.com", (Float) 4.8f, (Float) 4.1f, "", true, 21);
		Employee emp7 = new Employee("Omar", "Chniti", "12807863", "454653", "Male", "Omar.Chniti@esprit.tn",
				(Long) 55054543l, birth, "Francais", start, end, "Married", 2, "Sfax", (Long) 011101105217l, "expert",
				50, (Long) 97773824l, "Omar.Chniti@gmail.com", (Float) 4.3f, (Float) 4.1f, "", true, 17);
		Employee emp8 = new Employee("Karim", "Ladherie", "12807864", "454654", "Male", "Karim.Ladherie@esprit.tn",
				(Long) 55054543l, birth, "Portugais", start, end, "Married", 2, "Aindrahem", (Long) 011101105200l,
				"moyen", 50, (Long) 97773824l, "Karim.Ladherie@gmail.com", (Float) 3.3f, (Float) 4.1f, "", true, 11);
		Employee emp9 = new Employee("Borhene", "Handous", "12807865", "454655", "Male", "Borhene.Handous@esprit.tn",
				(Long) 55054543l, birth, "Tunisien", start, end, "Married", 2, "Sfax", (Long) 011101105201l, "moyen",
				50, (Long) 97773824l, "Borhene.Handous@gmail.com", (Float) 3.5f, (Float) 4.1f, "", true, 15);
		Employee emp10 = new Employee("Hedi", "Baccouche", "12807866", "454656", "Male", "Hedi.Baccouche@esprit.tn",
				(Long) 55054543l, birth, "Francais", start, end, "Married", 2, "Ben Arous", (Long) 011101105202l,
				"professionel", 50, (Long) 97773824l, "Hedi.Baccouche@gmail.com", (Float) 4.1f, (Float) 4.1f, "", true,
				2);

		Employee emp11 = new Employee("Mehdi", "Mellouli", "12807867", "454652", "Male", "Mehdi.Mellouli@esprit.tn",
				(Long) 55054542l, birth, "Tunisien", start, end, "Married", 2, "Sousse", (Long) 011101105216l, "expert",
				50, (Long) 97773824l, "Mehdi.Mellouli@gmail.com", (Float) 4.8f, (Float) 4.1f, "", true, 21);
		Employee emp12 = new Employee("Ilyes", "Chrif", "12857868", "454652", "Male", "Ilyes.Chrif@esprit.tn",
				(Long) 55054542l, birth, "Portugais", start, end, "Married", 2, "Tbolba", (Long) 011101105216l,
				"expert", 50, (Long) 97773824l, "Ilyes.Chrif@gmail.com", (Float) 4.8f, (Float) 4.1f, "", true, 21);
		Employee emp13 = new Employee("Youssef", "Hajomor", "12807869", "454653", "Male", "Youssef.Hajomor@esprit.tn",
				(Long) 55054543l, birth, "Tunisien", start, end, "Married", 2, "Sfax", (Long) 011101105217l, "expert",
				50, (Long) 97773824l, "Youssef.Hajomor@gmail.com", (Float) 4.3f, (Float) 4.1f, "", true, 17);
		Employee emp14 = new Employee("Amine", "Omezzine", "12807870", "454654", "Male", "Amine.Omezzine@esprit.tn",
				(Long) 55054543l, birth, "Francais", start, end, "Married", 2, "Aindrahem", (Long) 011101105200l,
				"moyen", 50, (Long) 97773824l, "Amine.Omezzine@gmail.com", (Float) 3.3f, (Float) 4.1f, "", true, 11);
		Employee emp15 = new Employee("Firas", "abdelrazak", "12807871", "454655", "Male", "Firas.abdelrazak@esprit.tn",
				(Long) 55054543l, birth, "Tunisien", start, end, "Married", 2, "Sfax", (Long) 011101105201l, "moyen",
				50, (Long) 97773824l, "Firas.abdelrazak@gmail.com", (Float) 3.5f, (Float) 4.1f, "", true, 15);
		Employee emp16 = new Employee("Firas", "kacem", "12807872", "454656", "Male", "Firas.kacem@esprit.tn",
				(Long) 55054543l, birth, "Marocain", start, end, "Married", 2, "Ben Arous", (Long) 011101105202l,
				"professionel", 50, (Long) 97773824l, "Firas.kacem@gmail.com", (Float) 4.1f, (Float) 4.1f, "", true, 2);

		Employee emp17 = new Employee("Oumayma", "Cheweya", "12857873", "454652", "Female", "Oumayma.Cheweya@esprit.tn",
				(Long) 55054542l, birth, "Tunisien", start, end, "Married", 2, "Tbolba", (Long) 011101105216l, "expert",
				50, (Long) 97773824l, "Oumayma.Cheweya@gmail.com", (Float) 4.8f, (Float) 4.1f, "", true, 21);
		Employee emp18 = new Employee("Jihen", "Chrif", "12807874", "454653", "Female", "Jihen.Chrif@esprit.tn",
				(Long) 55054543l, birth, "Portugais", start, end, "Married", 2, "Sfax", (Long) 011101105217l, "expert",
				50, (Long) 97773824l, "Jihen.Chrif@gmail.com", (Float) 4.3f, (Float) 4.1f, "", true, 17);
		Employee emp19 = new Employee("Meriem", "Ben Moustfa", "12807875", "454654", "Female",
				"Meriem.BenMoustfa@esprit.tn", (Long) 55054543l, birth, "Francais", start, end, "Married", 2,
				"Aindrahem", (Long) 011101105200l, "moyen", 50, (Long) 97773824l, "Meriem.BenMoustfa@gmail.com",
				(Float) 3.3f, (Float) 4.1f, "", true, 11);
		Employee emp20 = new Employee("Hejer", "Hraich", "12807876", "454655", "Female", "Hejer.Hraich@esprit.tn",
				(Long) 55054543l, birth, "Marocain", start, end, "Married", 2, "Sfax", (Long) 011101105201l, "moyen",
				50, (Long) 97773824l, "Hejer.Hraich@gmail.com", (Float) 3.5f, (Float) 4.1f, "", true, 15);
		Employee emp21 = new Employee("Dorra", "Triki", "12807877", "454656", "Female", "Dorra.Triki@esprit.tn",
				(Long) 55054543l, birth, "Tunisien", start, end, "Married", 2, "Ben Arous", (Long) 011101105202l,
				"professionel", 50, (Long) 97773824l, "Dorra.Triki@gmail.com", (Float) 4.1f, (Float) 4.1f, "", true, 2);

		/*
		 * proxy.save(emp); proxy.save(emp1); proxy.save(emp2);
		 * proxy.save(emp3); proxy.save(emp4); proxy.save(emp5);
		 * proxy.save(emp6); proxy.save(emp7); proxy.save(emp8);
		 * proxy.save(emp9); proxy.save(emp10); proxy.save(emp11);
		 * proxy.save(emp12); proxy.save(emp13); proxy.save(emp14);
		 * proxy.save(emp15); proxy.save(emp16); proxy.save(emp17);
		 * proxy.save(emp18); proxy.save(emp19); proxy.save(emp20);
		 * proxy.save(emp21);
		 */
		/*
		 * String jndiJobOffer
		 * ="esprit1718b1hrboard-ear/esprit1718b1hrboard-service/JobOfferService!tn.esprit.b1.esprit1718b1hrboard.services.JobOfferServiceRemote";
		 * Context context = new InitialContext(); JobOfferServiceRemote proxy =
		 * (JobOfferServiceRemote) context.lookup(jndiJobOffer);
		 * 
		 * 
		 * List<JobOffer> test = new ArrayList<>(); test = proxy.findAll();
		 * System.out.println(test);
		 */

		///////////////////////////////////// Creation des skills condidat +
		///////////////////////////////////// condidat has skillls
		///////////////////////////////////// Pour teste enlever les commentaire
		///////////////////////////////////// ligne 109 a 146 puis executer
		/////////////////////////////////////

		/////////////////////// Add Skill//////////////////////////////////
		String jndiSkill = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/SkillService!tn.esprit.b1.esprit1718b1hrboard.services.SkillServiceRemote";
		SkillServiceRemote proxy1 = (SkillServiceRemote) EJBLookupUtil.doLookup(jndiSkill);
		Skill s1 = new Skill("JavaEE", "Devloppement", 8f);
		Skill s2 = new Skill("MySQL", "Devloppement", 3f);
		Skill s3 = new Skill("JavaFX", "Devloppement", 4f);
		Skill s4 = new Skill("UML", "Devloppement", 5f);
		Skill s5 = new Skill("Web", "Devloppement", 2f);
		Skill s6 = new Skill("Gestion DE Projet", "Finance", 1f);
		/*
		 * proxy1.save(s1); proxy1.save(s2); proxy1.save(s3); proxy1.save(s4);
		 * proxy1.save(s5); proxy1.save(s6);
		 */
		/*
		 * Skill s1 = proxy1.find(19); Skill s2 = proxy1.find(20); Skill s3 =
		 * proxy1.find(21); Skill s4 = proxy1.find(22); Skill s5 =
		 * proxy1.find(23); Skill s6 = proxy1.find(24);
		 */

		proxy1.save(s6);

		//////////////////////////////////////////////////////////////////////
		///////////////////////// Add Condidat/////////////////////////////////
		String jndiCondidate = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/CandidateService!tn.esprit.b1.esprit1718b1hrboard.services.CandidateServiceRemote";
		// CandidateServiceRemote proxy = (CandidateServiceRemote)
		// EJBLookupUtil.doLookup(jndiCondidate);
		/*
		 * Candidate c1 = new Candidate("Condidate1", "Condidate1",
		 * "Condidate1.Condidate1@esprit.tn", 55000000l , "Sousse", "2 ans",
		 * "Ingenieur"); Candidate c2 = new Candidate("Condidate2",
		 * "Condidate2", "Condidate2.Condidate2@esprit.tn", 55000001l ,
		 * "Sousse", "3 ans", "Ingenieur"); Candidate c3 = new
		 * Candidate("Condidate3", "Condidate3",
		 * "Condidate3.Condidate3@esprit.tn", 55000002l , "Sousse", "4 ans",
		 * "Thechnicien"); Candidate c4 = new Candidate("Condidate4",
		 * "Condidate4", "Condidate4.Condidate4@esprit.tn", 55000003l ,
		 * "Sousse", "5 ans", "Ingenieur"); Candidate c5 = new
		 * Candidate("Condidate5", "Condidate5",
		 * "Condidate5.Condidate5@esprit.tn", 55000004l , "Sousse", "6 ans",
		 * "Thechnicien"); Candidate c6 = new Candidate("Condidate6",
		 * "Condidate6", "Condidate6.Condidate6@esprit.tn", 55000005l ,
		 * "Sousse", "7 ans", "Ingenieur"); Candidate c7 = new
		 * Candidate("Condidate7", "Condidate7",
		 * "Condidate7.Condidate7@esprit.tn", 55000006l , "Sousse", "8 ans",
		 * "Thechnicien"); Candidate c8 = new Candidate("Condidate8",
		 * "Condidate8", "Condidate8.Condidate8@esprit.tn", 55000007l ,
		 * "Sousse", "1 ans", "Thechnicien"); Candidate c9 = new
		 * Candidate("Condidate9", "Condidate9",
		 * "Condidate9.Condidate9@esprit.tn", 55000008l , "Sousse", "5 ans",
		 * "Ingenieur"); Candidate c10 = new Candidate("Condidate10",
		 * "Condidate10", "Condidate10.Condidate10@esprit.tn", 55000009l ,
		 * "Sousse", "4 ans", "Ingenieur"); proxy.save(c1); proxy.save(c2);
		 * proxy.save(c3); proxy.save(c4); proxy.save(c5); proxy.save(c6);
		 * proxy.save(c7); proxy.save(c8); proxy.save(c9); proxy.save(c10);
		 */
		/*
		 * System.out.println(proxy.findAll()); Candidate c1 = proxy.find(33);
		 * Candidate c2 = proxy.find(34); Candidate c3 = proxy.find(35);
		 * Candidate c4 = proxy.find(36); Candidate c5 = proxy.find(37);
		 * Candidate c6 = proxy.find(38); Candidate c7 = proxy.find(39);
		 * Candidate c8 = proxy.find(40); Candidate c9 = proxy.find(41);
		 * Candidate c10 = proxy.find(42);
		 */
		// CandidateServiceRemote proxy = (CandidateServiceRemote)
		// EJBLookupUtil.doLookup(jndiCondidate);
		// Candidate c1 = new Candidate("Condidate1", "Condidate1",
		// "Condidate1.Condidate1@esprit.tn", 55000000l , "Sousse", "2 ans",
		// "Ingenieur");
		// Candidate c2 = new Candidate("Condidate2", "Condidate2",
		// "Condidate2.Condidate2@esprit.tn", 55000001l , "Sousse", "3 ans",
		// "Ingenieur");
		// Candidate c3 = new Candidate("Condidate3", "Condidate3",
		// "Condidate3.Condidate3@esprit.tn", 55000002l , "Sousse", "4 ans",
		// "Thechnicien");
		// Candidate c4 = new Candidate("Condidate4", "Condidate4",
		// "Condidate4.Condidate4@esprit.tn", 55000003l , "Sousse", "5 ans",
		// "Ingenieur");
		// Candidate c5 = new Candidate("Condidate5", "Condidate5",
		// "Condidate5.Condidate5@esprit.tn", 55000004l , "Sousse", "6 ans",
		// "Thechnicien");
		// Candidate c6 = new Candidate("Condidate6", "Condidate6",
		// "Condidate6.Condidate6@esprit.tn", 55000005l , "Sousse", "7 ans",
		// "Ingenieur");
		// Candidate c7 = new Candidate("Condidate7", "Condidate7",
		// "Condidate7.Condidate7@esprit.tn", 55000006l , "Sousse", "8 ans",
		// "Thechnicien");
		// Candidate c8 = new Candidate("Condidate8", "Condidate8",
		// "Condidate8.Condidate8@esprit.tn", 55000007l , "Sousse", "1 ans",
		// "Thechnicien");
		// Candidate c9 = new Candidate("Condidate9", "Condidate9",
		// "Condidate9.Condidate9@esprit.tn", 55000008l , "Sousse", "5 ans",
		// "Ingenieur");
		// Candidate c10 = new Candidate("Condidate10", "Condidate10",
		// "Condidate10.Condidate10@esprit.tn", 55000009l , "Sousse", "4 ans",
		// "Ingenieur");
		// proxy.save(c1);
		// proxy.save(c2);
		// proxy.save(c3);
		// proxy.save(c4);
		// proxy.save(c5);
		// proxy.save(c6);
		// proxy.save(c7);
		// proxy.save(c8);
		// proxy.save(c9);
		// proxy.save(c10);
		//
		// System.out.println(proxy.findAll());

		////////////////////////////////////////////////////////////////////
		/////////////////////////// Add Condidate Has Skills Pk /////////////

		////////////////////// Condidat1

		/*
		 * CandidateHasSkillPk csp11 = new
		 * CandidateHasSkillPk();//System.out.println("***********test");System.
		 * out.println(csp11);System.out.println("test**************");
		 * csp11.setIdCandidate(c1.getId()); csp11.setIdSkill(s1.getId());
		 * CandidateHasSkillPk csp12 = new CandidateHasSkillPk();
		 * csp12.setIdCandidate(c1.getId()); csp12.setIdSkill(s2.getId());
		 * CandidateHasSkillPk csp13= new CandidateHasSkillPk();
		 * csp13.setIdCandidate(c1.getId()); csp13.setIdSkill(s3.getId());
		 * //////////////////////Condidat2 CandidateHasSkillPk csp21 = new
		 * CandidateHasSkillPk(); csp21.setIdCandidate(c2.getId());
		 * csp21.setIdSkill(s4.getId()); CandidateHasSkillPk csp22 = new
		 * CandidateHasSkillPk(); csp22.setIdCandidate(c2.getId());
		 * csp22.setIdSkill(s5.getId()); CandidateHasSkillPk csp23= new
		 * CandidateHasSkillPk(); csp23.setIdCandidate(c2.getId());
		 * csp23.setIdSkill(s6.getId());
		 * 
		 * //////////////////////Condidat3 CandidateHasSkillPk csp31 = new
		 * CandidateHasSkillPk(); csp31.setIdCandidate(c3.getId());
		 * csp31.setIdSkill(s1.getId()); CandidateHasSkillPk csp32 = new
		 * CandidateHasSkillPk(); csp32.setIdCandidate(c3.getId());
		 * csp32.setIdSkill(s2.getId()); CandidateHasSkillPk csp33= new
		 * CandidateHasSkillPk(); csp33.setIdCandidate(c3.getId());
		 * csp33.setIdSkill(s3.getId()); //////////////////////Condidat4
		 * CandidateHasSkillPk csp41 = new CandidateHasSkillPk();
		 * csp41.setIdCandidate(c4.getId()); csp41.setIdSkill(s4.getId());
		 * CandidateHasSkillPk csp42 = new CandidateHasSkillPk();
		 * csp42.setIdCandidate(c4.getId()); csp42.setIdSkill(s5.getId());
		 * CandidateHasSkillPk csp43= new CandidateHasSkillPk();
		 * csp43.setIdCandidate(c4.getId()); csp43.setIdSkill(s6.getId());
		 * //////////////////////Condidat5 CandidateHasSkillPk csp51 = new
		 * CandidateHasSkillPk(); csp51.setIdCandidate(c5.getId());
		 * csp51.setIdSkill(s1.getId()); CandidateHasSkillPk csp52 = new
		 * CandidateHasSkillPk(); csp52.setIdCandidate(c5.getId());
		 * csp52.setIdSkill(s2.getId()); CandidateHasSkillPk csp53= new
		 * CandidateHasSkillPk(); csp53.setIdCandidate(c5.getId());
		 * csp53.setIdSkill(s3.getId()); //////////////////////Condidat6
		 * CandidateHasSkillPk csp61 = new CandidateHasSkillPk();
		 * csp61.setIdCandidate(c6.getId()); csp61.setIdSkill(s4.getId());
		 * CandidateHasSkillPk csp62 = new CandidateHasSkillPk();
		 * csp62.setIdCandidate(c6.getId()); csp62.setIdSkill(s5.getId());
		 * CandidateHasSkillPk csp63= new CandidateHasSkillPk();
		 * csp63.setIdCandidate(c6.getId()); csp63.setIdSkill(s6.getId());
		 * 
		 * //////////////////////Condidat7 CandidateHasSkillPk csp71 = new
		 * CandidateHasSkillPk(); csp71.setIdCandidate(c7.getId());
		 * csp71.setIdSkill(s1.getId()); CandidateHasSkillPk csp72 = new
		 * CandidateHasSkillPk(); csp72.setIdCandidate(c7.getId());
		 * csp72.setIdSkill(s2.getId()); CandidateHasSkillPk csp73= new
		 * CandidateHasSkillPk(); csp73.setIdCandidate(c7.getId());
		 * csp73.setIdSkill(s3.getId()); //////////////////////Condidat8
		 * CandidateHasSkillPk csp81 = new CandidateHasSkillPk();
		 * csp81.setIdCandidate(c8.getId()); csp81.setIdSkill(s1.getId());
		 * CandidateHasSkillPk csp82 = new CandidateHasSkillPk();
		 * csp82.setIdCandidate(c8.getId()); csp82.setIdSkill(s4.getId());
		 * CandidateHasSkillPk csp83= new CandidateHasSkillPk();
		 * csp83.setIdCandidate(c8.getId()); csp83.setIdSkill(s6.getId());
		 * //////////////////////Condidat9 CandidateHasSkillPk csp91 = new
		 * CandidateHasSkillPk(); csp91.setIdCandidate(c9.getId());
		 * csp91.setIdSkill(s1.getId()); CandidateHasSkillPk csp92 = new
		 * CandidateHasSkillPk(); csp92.setIdCandidate(c9.getId());
		 * csp92.setIdSkill(s5.getId()); CandidateHasSkillPk csp93= new
		 * CandidateHasSkillPk(); csp93.setIdCandidate(c9.getId());
		 * csp93.setIdSkill(s3.getId());
		 * 
		 * //////////////////////Condidat10 CandidateHasSkillPk csp101 = new
		 * CandidateHasSkillPk(); csp101.setIdCandidate(c10.getId());
		 * csp101.setIdSkill(s2.getId()); CandidateHasSkillPk csp102 = new
		 * CandidateHasSkillPk(); csp102.setIdCandidate(c10.getId());
		 * csp102.setIdSkill(s3.getId()); CandidateHasSkillPk csp103= new
		 * CandidateHasSkillPk(); csp103.setIdCandidate(c10.getId());
		 * csp103.setIdSkill(s5.getId());
		 */

		//////////////////////////////////////////////// Add condidat Has
		//////////////////////////////////////////////// Skills//////////////////
		String jndiCondidatehasSkill = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/CandidateHasSkillSevice!tn.esprit.b1.esprit1718b1hrboard.services.CandidateHasSkillSeviceRemote";
		CandidateHasSkillSeviceRemote proxy2 = (CandidateHasSkillSeviceRemote) EJBLookupUtil
				.doLookup(jndiCondidatehasSkill);
		/*
		 * CandidateHasSkill cs11 = new CandidateHasSkill(csp11, 4, true, 2);
		 * CandidateHasSkill cs12 = new CandidateHasSkill(csp12, 4, false, 1);
		 * CandidateHasSkill cs13 = new CandidateHasSkill(csp13, 4, true, 0);
		 * 
		 * CandidateHasSkill cs21 = new CandidateHasSkill(csp21, 4, true, 2);
		 * CandidateHasSkill cs22 = new CandidateHasSkill(csp22, 6, false, 1);
		 * CandidateHasSkill cs23 = new CandidateHasSkill(csp23, 3, true, 0);
		 * 
		 * CandidateHasSkill cs31 = new CandidateHasSkill(csp31, 4, true, 2);
		 * CandidateHasSkill cs32 = new CandidateHasSkill(csp32, 2, false, 1);
		 * CandidateHasSkill cs33 = new CandidateHasSkill(csp33, 1, true, 0);
		 * 
		 * CandidateHasSkill cs41 = new CandidateHasSkill(csp41, 5, true, 2);
		 * CandidateHasSkill cs42 = new CandidateHasSkill(csp42, 4, false, 1);
		 * CandidateHasSkill cs43 = new CandidateHasSkill(csp43, 5, true, 0);
		 * 
		 * CandidateHasSkill cs51 = new CandidateHasSkill(csp51, 1, true, 2);
		 * CandidateHasSkill cs52 = new CandidateHasSkill(csp52, 2, false, 1);
		 * CandidateHasSkill cs53 = new CandidateHasSkill(csp53, 1, true, 0);
		 * 
		 * CandidateHasSkill cs61 = new CandidateHasSkill(csp61, 4, true, 2);
		 * CandidateHasSkill cs62 = new CandidateHasSkill(csp62, 5, false, 1);
		 * CandidateHasSkill cs63 = new CandidateHasSkill(csp63, 3, true, 0);
		 * 
		 * CandidateHasSkill cs71 = new CandidateHasSkill(csp71, 6, true, 2);
		 * CandidateHasSkill cs72 = new CandidateHasSkill(csp72, 2, false, 1);
		 * CandidateHasSkill cs73 = new CandidateHasSkill(csp73, 0, true, 0);
		 * 
		 * CandidateHasSkill cs81 = new CandidateHasSkill(csp81, 1, true, 2);
		 * CandidateHasSkill cs82 = new CandidateHasSkill(csp82, 1, false, 1);
		 * CandidateHasSkill cs83 = new CandidateHasSkill(csp83, 2, true, 0);
		 * 
		 * CandidateHasSkill cs91 = new CandidateHasSkill(csp91, 5, true, 2);
		 * CandidateHasSkill cs92 = new CandidateHasSkill(csp92, 5, false, 1);
		 * CandidateHasSkill cs93 = new CandidateHasSkill(csp93, 5, true, 0);
		 * 
		 * CandidateHasSkill cs101 = new CandidateHasSkill(csp101, 7, true, 2);
		 * CandidateHasSkill cs102 = new CandidateHasSkill(csp102, 6, false, 1);
		 * CandidateHasSkill cs103 = new CandidateHasSkill(csp103, 7, true, 0);
		 * 
		 * proxy2.save(cs11); proxy2.save(cs12); proxy2.save(cs13);
		 * 
		 * proxy2.save(cs21); proxy2.save(cs22); proxy2.save(cs23);
		 * 
		 * proxy2.save(cs31); proxy2.save(cs32); proxy2.save(cs33);
		 * 
		 * proxy2.save(cs41); proxy2.save(cs42); proxy2.save(cs43);
		 * 
		 * proxy2.save(cs51); proxy2.save(cs52); proxy2.save(cs53);
		 * 
		 * proxy2.save(cs61); proxy2.save(cs62); proxy2.save(cs63);
		 * 
		 * proxy2.save(cs71); proxy2.save(cs72); proxy2.save(cs73);
		 * 
		 * proxy2.save(cs81); proxy2.save(cs82); proxy2.save(cs83);
		 * 
		 * proxy2.save(cs91); proxy2.save(cs92); proxy2.save(cs93);
		 * 
		 * proxy2.save(cs101); proxy2.save(cs102); proxy2.save(cs103);
		 */
		////////////////////////////////////////////////////////////////////////////////////////////

		// CandidateHasSkillPk csp11 = new
		// CandidateHasSkillPk();//System.out.println("***********test");System.out.println(csp11);System.out.println("test**************");
		// csp11.setIdCandidate(c1.getId());
		// csp11.setIdSkill(s1.getId());
		// CandidateHasSkillPk csp12 = new CandidateHasSkillPk();
		// csp12.setIdCandidate(c1.getId());
		// csp12.setIdSkill(s2.getId());
		// CandidateHasSkillPk csp13= new CandidateHasSkillPk();
		// csp13.setIdCandidate(c1.getId());
		// csp13.setIdSkill(s3.getId());
		// //////////////////////Condidat2
		// CandidateHasSkillPk csp21 = new CandidateHasSkillPk();
		// csp21.setIdCandidate(c2.getId());
		// csp21.setIdSkill(s4.getId());
		// CandidateHasSkillPk csp22 = new CandidateHasSkillPk();
		// csp22.setIdCandidate(c2.getId());
		// csp22.setIdSkill(s5.getId());
		// CandidateHasSkillPk csp23= new CandidateHasSkillPk();
		// csp23.setIdCandidate(c2.getId());
		// csp23.setIdSkill(s6.getId());
		//
		// //////////////////////Condidat3
		// CandidateHasSkillPk csp31 = new CandidateHasSkillPk();
		// csp31.setIdCandidate(c3.getId());
		// csp31.setIdSkill(s1.getId());
		// CandidateHasSkillPk csp32 = new CandidateHasSkillPk();
		// csp32.setIdCandidate(c3.getId());
		// csp32.setIdSkill(s2.getId());
		// CandidateHasSkillPk csp33= new CandidateHasSkillPk();
		// csp33.setIdCandidate(c3.getId());
		// csp33.setIdSkill(s3.getId());
		// //////////////////////Condidat4
		// CandidateHasSkillPk csp41 = new CandidateHasSkillPk();
		// csp41.setIdCandidate(c4.getId());
		// csp41.setIdSkill(s4.getId());
		// CandidateHasSkillPk csp42 = new CandidateHasSkillPk();
		// csp42.setIdCandidate(c4.getId());
		// csp42.setIdSkill(s5.getId());
		// CandidateHasSkillPk csp43= new CandidateHasSkillPk();
		// csp43.setIdCandidate(c4.getId());
		// csp43.setIdSkill(s6.getId());
		// //////////////////////Condidat5
		// CandidateHasSkillPk csp51 = new CandidateHasSkillPk();
		// csp51.setIdCandidate(c5.getId());
		// csp51.setIdSkill(s1.getId());
		// CandidateHasSkillPk csp52 = new CandidateHasSkillPk();
		// csp52.setIdCandidate(c5.getId());
		// csp52.setIdSkill(s2.getId());
		// CandidateHasSkillPk csp53= new CandidateHasSkillPk();
		// csp53.setIdCandidate(c5.getId());
		// csp53.setIdSkill(s3.getId());
		// //////////////////////Condidat6
		// CandidateHasSkillPk csp61 = new CandidateHasSkillPk();
		// csp61.setIdCandidate(c6.getId());
		// csp61.setIdSkill(s4.getId());
		// CandidateHasSkillPk csp62 = new CandidateHasSkillPk();
		// csp62.setIdCandidate(c6.getId());
		// csp62.setIdSkill(s5.getId());
		// CandidateHasSkillPk csp63= new CandidateHasSkillPk();
		// csp63.setIdCandidate(c6.getId());
		// csp63.setIdSkill(s6.getId());
		//
		// //////////////////////Condidat7
		// CandidateHasSkillPk csp71 = new CandidateHasSkillPk();
		// csp71.setIdCandidate(c7.getId());
		// csp71.setIdSkill(s1.getId());
		// CandidateHasSkillPk csp72 = new CandidateHasSkillPk();
		// csp72.setIdCandidate(c7.getId());
		// csp72.setIdSkill(s2.getId());
		// CandidateHasSkillPk csp73= new CandidateHasSkillPk();
		// csp73.setIdCandidate(c7.getId());
		// csp73.setIdSkill(s3.getId());
		// //////////////////////Condidat8
		// CandidateHasSkillPk csp81 = new CandidateHasSkillPk();
		// csp81.setIdCandidate(c8.getId());
		// csp81.setIdSkill(s1.getId());
		// CandidateHasSkillPk csp82 = new CandidateHasSkillPk();
		// csp82.setIdCandidate(c8.getId());
		// csp82.setIdSkill(s4.getId());
		// CandidateHasSkillPk csp83= new CandidateHasSkillPk();
		// csp83.setIdCandidate(c8.getId());
		// csp83.setIdSkill(s6.getId());
		// //////////////////////Condidat9
		// CandidateHasSkillPk csp91 = new CandidateHasSkillPk();
		// csp91.setIdCandidate(c9.getId());
		// csp91.setIdSkill(s1.getId());
		// CandidateHasSkillPk csp92 = new CandidateHasSkillPk();
		// csp92.setIdCandidate(c9.getId());
		// csp92.setIdSkill(s5.getId());
		// CandidateHasSkillPk csp93= new CandidateHasSkillPk();
		// csp93.setIdCandidate(c9.getId());
		// csp93.setIdSkill(s3.getId());
		//
		// //////////////////////Condidat10
		// CandidateHasSkillPk csp101 = new CandidateHasSkillPk();
		// csp101.setIdCandidate(c10.getId());
		// csp101.setIdSkill(s2.getId());
		// CandidateHasSkillPk csp102 = new CandidateHasSkillPk();
		// csp102.setIdCandidate(c10.getId());
		// csp102.setIdSkill(s3.getId());
		// CandidateHasSkillPk csp103= new CandidateHasSkillPk();
		// csp103.setIdCandidate(c10.getId());
		// csp103.setIdSkill(s5.getId());
		//
		// ////////////////////////////////////////////////Add condidat Has
		// Skills//////////////////
		// String jndiCondidatehasSkill
		// ="esprit1718b1hrboard-ear/esprit1718b1hrboard-service/CandidateHasSkillSevice!tn.esprit.b1.esprit1718b1hrboard.services.CandidateHasSkillSeviceRemote";
		// CandidateHasSkillSeviceRemote proxy2 =
		// (CandidateHasSkillSeviceRemote)
		// EJBLookupUtil.doLookup(jndiCondidatehasSkill);
		//
		// CandidateHasSkill cs11 = new CandidateHasSkill(csp11, 4, true, 2);
		// CandidateHasSkill cs12 = new CandidateHasSkill(csp12, 4, false, 1);
		// CandidateHasSkill cs13 = new CandidateHasSkill(csp13, 4, true, 0);
		//
		// CandidateHasSkill cs21 = new CandidateHasSkill(csp21, 4, true, 2);
		// CandidateHasSkill cs22 = new CandidateHasSkill(csp22, 6, false, 1);
		// CandidateHasSkill cs23 = new CandidateHasSkill(csp23, 3, true, 0);
		//
		// CandidateHasSkill cs31 = new CandidateHasSkill(csp31, 4, true, 2);
		// CandidateHasSkill cs32 = new CandidateHasSkill(csp32, 2, false, 1);
		// CandidateHasSkill cs33 = new CandidateHasSkill(csp33, 1, true, 0);
		//
		// CandidateHasSkill cs41 = new CandidateHasSkill(csp41, 5, true, 2);
		// CandidateHasSkill cs42 = new CandidateHasSkill(csp42, 4, false, 1);
		// CandidateHasSkill cs43 = new CandidateHasSkill(csp43, 5, true, 0);
		//
		// CandidateHasSkill cs51 = new CandidateHasSkill(csp51, 1, true, 2);
		// CandidateHasSkill cs52 = new CandidateHasSkill(csp52, 2, false, 1);
		// CandidateHasSkill cs53 = new CandidateHasSkill(csp53, 1, true, 0);
		//
		// CandidateHasSkill cs61 = new CandidateHasSkill(csp61, 4, true, 2);
		// CandidateHasSkill cs62 = new CandidateHasSkill(csp62, 5, false, 1);
		// CandidateHasSkill cs63 = new CandidateHasSkill(csp63, 3, true, 0);
		//
		// CandidateHasSkill cs71 = new CandidateHasSkill(csp71, 6, true, 2);
		// CandidateHasSkill cs72 = new CandidateHasSkill(csp72, 2, false, 1);
		// CandidateHasSkill cs73 = new CandidateHasSkill(csp73, 0, true, 0);
		//
		// CandidateHasSkill cs81 = new CandidateHasSkill(csp81, 1, true, 2);
		// CandidateHasSkill cs82 = new CandidateHasSkill(csp82, 1, false, 1);
		// CandidateHasSkill cs83 = new CandidateHasSkill(csp83, 2, true, 0);
		//
		// CandidateHasSkill cs91 = new CandidateHasSkill(csp91, 5, true, 2);
		// CandidateHasSkill cs92 = new CandidateHasSkill(csp92, 5, false, 1);
		// CandidateHasSkill cs93 = new CandidateHasSkill(csp93, 5, true, 0);
		//
		// CandidateHasSkill cs101 = new CandidateHasSkill(csp101, 7, true, 2);
		// CandidateHasSkill cs102 = new CandidateHasSkill(csp102, 6, false, 1);
		// CandidateHasSkill cs103 = new CandidateHasSkill(csp103, 7, true, 0);
		//
		// proxy2.save(cs11);
		// proxy2.save(cs12);
		// proxy2.save(cs13);
		//
		// proxy2.save(cs21);
		// proxy2.save(cs22);
		// proxy2.save(cs23);
		//
		// proxy2.save(cs31);
		// proxy2.save(cs32);
		// proxy2.save(cs33);
		//
		// proxy2.save(cs41);
		// proxy2.save(cs42);
		// proxy2.save(cs43);
		//
		// proxy2.save(cs51);
		// proxy2.save(cs52);
		// proxy2.save(cs53);
		//
		// proxy2.save(cs61);
		// proxy2.save(cs62);
		// proxy2.save(cs63);
		//
		// proxy2.save(cs71);
		// proxy2.save(cs72);
		// proxy2.save(cs73);
		//
		// proxy2.save(cs81);
		// proxy2.save(cs82);
		// proxy2.save(cs83);
		//
		// proxy2.save(cs91);
		// proxy2.save(cs92);
		// proxy2.save(cs93);
		//
		// proxy2.save(cs101);
		// proxy2.save(cs102);
		// proxy2.save(cs103);
		// ////////////////////////////////////////////////////////////////////////////////////////////
		/*
		 * String jndiInterview
		 * ="esprit1718b1hrboard-ear/esprit1718b1hrboard-service/InterviewService!tn.esprit.b1.esprit1718b1hrboard.services.InterviewServiceRemote";
		 * InterviewServiceRemote proxyInterview = (InterviewServiceRemote)
		 * EJBLookupUtil.doLookup(jndiInterview);
		 * 
		 * Interview i2 = new Interview();
		 * 
		 * i2=proxyInterview.find(10); proxyInterview.delete(i2);
		 */

		String jndiEmployeeHasSkill = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/EmployeeHasSkillService!tn.esprit.b1.esprit1718b1hrboard.services.EmployeeHasSkillServiceRemote";
		EmployeeHasSkillServiceRemote proxyemployeehasskill = (EmployeeHasSkillServiceRemote) EJBLookupUtil
				.doLookup(jndiEmployeeHasSkill);

		///////////////////////////////////////// find employeee && skill

	/*	Employee e1 = proxy.find(66);
		Employee e2 = proxy.find(67);
		Employee e3 = proxy.find(68);
		Employee e4 = proxy.find(69);
		Employee e5 = proxy.find(70);
		Employee e6 = proxy.find(71);
		Employee e7 = proxy.find(72);
		Employee e8 = proxy.find(73);
		Employee e9 = proxy.find(74);
		Employee e10 = proxy.find(75);
		System.out.println(e1.getId());
		System.out.println(e2.getId());
		System.out.println(e3);
		System.out.println(e4);
		System.out.println(e5);
		System.out.println(e6);
		System.out.println(e7);
		System.out.println(e8);
		System.out.println(e9);
		System.out.println(e10);
		Skill sk1 = proxy1.find(19);
		Skill sk2 = proxy1.find(20);
		Skill sk3 = proxy1.find(21);
		Skill sk4 = proxy1.find(22);
		Skill sk5 = proxy1.find(23);
		Skill sk6 = proxy1.find(24);

		/////////////////////////////// create Employee has skill Pk

		EmployeeHasSkillPk ehskp11 = new EmployeeHasSkillPk();
		ehskp11.setIdEmployee(66);
		ehskp11.setIdSkill(19);

		EmployeeHasSkillPk ehskp12 = new EmployeeHasSkillPk();
		ehskp11.setIdEmployee(66);
		ehskp11.setIdSkill(20);

		EmployeeHasSkillPk ehskp13 = new EmployeeHasSkillPk();
		ehskp11.setIdEmployee(66);
		ehskp11.setIdSkill(21);
		/////////////////////////////////////
		EmployeeHasSkillPk ehskp21 = new EmployeeHasSkillPk();
		ehskp11.setIdEmployee(67);
		ehskp11.setIdSkill(22);

		EmployeeHasSkillPk ehskp22 = new EmployeeHasSkillPk();
		ehskp11.setIdEmployee(67);
		ehskp11.setIdSkill(23);

		EmployeeHasSkillPk ehskp23 = new EmployeeHasSkillPk();
		ehskp11.setIdEmployee(67);
		ehskp11.setIdSkill(24);
		/////////////////////////////////////
		/*EmployeeHasSkillPk ehskp31 = new EmployeeHasSkillPk();
		ehskp11.setIdEmployee(e3.getId());
		ehskp11.setIdSkill(sk1.getId());

		EmployeeHasSkillPk ehskp32 = new EmployeeHasSkillPk();
		ehskp11.setIdEmployee(e3.getId());
		ehskp11.setIdSkill(sk2.getId());

		EmployeeHasSkillPk ehskp33 = new EmployeeHasSkillPk();
		ehskp11.setIdEmployee(e3.getId());
		ehskp11.setIdSkill(sk4.getId());
		/////////////////////////////////////
		EmployeeHasSkillPk ehskp41 = new EmployeeHasSkillPk();
		ehskp11.setIdEmployee(e4.getId());
		ehskp11.setIdSkill(sk3.getId());

		EmployeeHasSkillPk ehskp42 = new EmployeeHasSkillPk();
		ehskp11.setIdEmployee(e4.getId());
		ehskp11.setIdSkill(sk4.getId());

		EmployeeHasSkillPk ehskp43 = new EmployeeHasSkillPk();
		ehskp11.setIdEmployee(e4.getId());
		ehskp11.setIdSkill(sk5.getId());
		/////////////////////////////////////
		EmployeeHasSkillPk ehskp51 = new EmployeeHasSkillPk();
		ehskp11.setIdEmployee(e5.getId());
		ehskp11.setIdSkill(sk3.getId());

		EmployeeHasSkillPk ehskp52 = new EmployeeHasSkillPk();
		ehskp11.setIdEmployee(e5.getId());
		ehskp11.setIdSkill(sk4.getId());

		EmployeeHasSkillPk ehskp53 = new EmployeeHasSkillPk();
		ehskp11.setIdEmployee(e5.getId());
		ehskp11.setIdSkill(sk6.getId());
		/////////////////////////////////////
		EmployeeHasSkillPk ehskp61 = new EmployeeHasSkillPk();
		ehskp11.setIdEmployee(e6.getId());
		ehskp11.setIdSkill(sk3.getId());

		EmployeeHasSkillPk ehskp62 = new EmployeeHasSkillPk();
		ehskp11.setIdEmployee(e6.getId());
		ehskp11.setIdSkill(sk4.getId());

		EmployeeHasSkillPk ehskp63 = new EmployeeHasSkillPk();
		ehskp11.setIdEmployee(e6.getId());
		ehskp11.setIdSkill(sk5.getId());
		/////////////////////////////////////
		EmployeeHasSkillPk ehskp71 = new EmployeeHasSkillPk();
		ehskp11.setIdEmployee(e7.getId());
		ehskp11.setIdSkill(sk1.getId());

		EmployeeHasSkillPk ehskp72 = new EmployeeHasSkillPk();
		ehskp11.setIdEmployee(e7.getId());
		ehskp11.setIdSkill(sk3.getId());

		EmployeeHasSkillPk ehskp73 = new EmployeeHasSkillPk();
		ehskp11.setIdEmployee(e7.getId());
		ehskp11.setIdSkill(sk6.getId());
		/////////////////////////////////////
		EmployeeHasSkillPk ehskp81 = new EmployeeHasSkillPk();
		ehskp11.setIdEmployee(e8.getId());
		ehskp11.setIdSkill(sk2.getId());

		EmployeeHasSkillPk ehskp82 = new EmployeeHasSkillPk();
		ehskp11.setIdEmployee(e8.getId());
		ehskp11.setIdSkill(sk4.getId());

		EmployeeHasSkillPk ehskp83 = new EmployeeHasSkillPk();
		ehskp11.setIdEmployee(e8.getId());
		ehskp11.setIdSkill(sk6.getId());
		/////////////////////////////////////
		EmployeeHasSkillPk ehskp91 = new EmployeeHasSkillPk();
		ehskp11.setIdEmployee(e9.getId());
		ehskp11.setIdSkill(sk1.getId());

		EmployeeHasSkillPk ehskp92 = new EmployeeHasSkillPk();
		ehskp11.setIdEmployee(e9.getId());
		ehskp11.setIdSkill(sk3.getId());

		EmployeeHasSkillPk ehskp93 = new EmployeeHasSkillPk();
		ehskp11.setIdEmployee(e9.getId());
		ehskp11.setIdSkill(sk5.getId());
		/////////////////////////////////////
		EmployeeHasSkillPk ehskp101 = new EmployeeHasSkillPk();
		ehskp11.setIdEmployee(e10.getId());
		ehskp11.setIdSkill(sk1.getId());

		EmployeeHasSkillPk ehskp102 = new EmployeeHasSkillPk();
		ehskp11.setIdEmployee(e10.getId());
		ehskp11.setIdSkill(sk2.getId());

		EmployeeHasSkillPk ehskp103 = new EmployeeHasSkillPk();
		ehskp11.setIdEmployee(e10.getId());
		ehskp11.setIdSkill(sk3.getId());*/

		/////////////////////////////////
		/*EmployeeHasSkill ehs11 = new EmployeeHasSkill();
		ehs11.setEmployeeHasSkillPk(ehskp11);
		ehs11.setLevel(5);
		ehs11.setEmployee(emp1);
		EmployeeHasSkill ehs12 = new EmployeeHasSkill();
		ehs12.setEmployeeHasSkillPk(ehskp12);
		ehs12.setLevel(5);
		ehs12.setEmployee(emp1);
		EmployeeHasSkill ehs13 = new EmployeeHasSkill();
		ehs13.setEmployeeHasSkillPk(ehskp13);
		ehs13.setLevel(5);
		ehs13.setEmployee(emp1);
		/////////////////////////////////
		EmployeeHasSkill ehs21 = new EmployeeHasSkill();
		ehs21.setEmployeeHasSkillPk(ehskp21);
		ehs21.setLevel(5);
		ehs13.setEmployee(emp2);
		EmployeeHasSkill ehs22 = new EmployeeHasSkill();
		ehs22.setEmployeeHasSkillPk(ehskp22);
		ehs22.setLevel(5);
		ehs13.setEmployee(emp2);
		EmployeeHasSkill ehs23 = new EmployeeHasSkill();
		ehs23.setEmployeeHasSkillPk(ehskp23);
		ehs23.setLevel(5);
		ehs13.setEmployee(emp2);
		/////////////////////////////////
		/*EmployeeHasSkill ehs31 = new EmployeeHasSkill();
		ehs31.setEmployeeHasSkillPk(ehskp31);
		ehs31.setLevel(5);
		EmployeeHasSkill ehs32 = new EmployeeHasSkill();
		ehs32.setEmployeeHasSkillPk(ehskp32);
		ehs32.setLevel(5);
		EmployeeHasSkill ehs33 = new EmployeeHasSkill();
		ehs33.setEmployeeHasSkillPk(ehskp33);
		ehs33.setLevel(5);
		/////////////////////////////////
		EmployeeHasSkill ehs41 = new EmployeeHasSkill();
		ehs41.setEmployeeHasSkillPk(ehskp41);
		ehs41.setLevel(5);
		EmployeeHasSkill ehs42 = new EmployeeHasSkill();
		ehs42.setEmployeeHasSkillPk(ehskp42);
		ehs42.setLevel(5);
		EmployeeHasSkill ehs43 = new EmployeeHasSkill();
		ehs43.setEmployeeHasSkillPk(ehskp43);
		ehs43.setLevel(5);
		/////////////////////////////////
		EmployeeHasSkill ehs51 = new EmployeeHasSkill();
		ehs51.setEmployeeHasSkillPk(ehskp51);
		ehs51.setLevel(5);
		EmployeeHasSkill ehs52 = new EmployeeHasSkill();
		ehs52.setEmployeeHasSkillPk(ehskp52);
		ehs52.setLevel(5);
		EmployeeHasSkill ehs53 = new EmployeeHasSkill();
		ehs53.setEmployeeHasSkillPk(ehskp53);
		ehs53.setLevel(5);
		/////////////////////////////////
		EmployeeHasSkill ehs61 = new EmployeeHasSkill();
		ehs61.setEmployeeHasSkillPk(ehskp61);
		ehs61.setLevel(5);
		EmployeeHasSkill ehs62 = new EmployeeHasSkill();
		ehs62.setEmployeeHasSkillPk(ehskp62);
		ehs62.setLevel(5);
		EmployeeHasSkill ehs63 = new EmployeeHasSkill();
		ehs63.setEmployeeHasSkillPk(ehskp63);
		ehs63.setLevel(5);
		/////////////////////////////////
		EmployeeHasSkill ehs71 = new EmployeeHasSkill();
		ehs71.setEmployeeHasSkillPk(ehskp71);
		ehs71.setLevel(5);
		EmployeeHasSkill ehs72 = new EmployeeHasSkill();
		ehs72.setEmployeeHasSkillPk(ehskp72);
		ehs72.setLevel(5);
		EmployeeHasSkill ehs73 = new EmployeeHasSkill();
		ehs73.setEmployeeHasSkillPk(ehskp73);
		ehs73.setLevel(5);
		/////////////////////////////////
		EmployeeHasSkill ehs81 = new EmployeeHasSkill();
		ehs81.setEmployeeHasSkillPk(ehskp81);
		ehs81.setLevel(5);
		EmployeeHasSkill ehs82 = new EmployeeHasSkill();
		ehs82.setEmployeeHasSkillPk(ehskp82);
		ehs82.setLevel(5);
		EmployeeHasSkill ehs83 = new EmployeeHasSkill();
		ehs83.setEmployeeHasSkillPk(ehskp83);
		ehs83.setLevel(5);
		/////////////////////////////////
		EmployeeHasSkill ehs91 = new EmployeeHasSkill();
		ehs91.setEmployeeHasSkillPk(ehskp91);
		ehs91.setLevel(5);
		EmployeeHasSkill ehs92 = new EmployeeHasSkill();
		ehs92.setEmployeeHasSkillPk(ehskp92);
		ehs92.setLevel(5);
		EmployeeHasSkill ehs93 = new EmployeeHasSkill();
		ehs93.setEmployeeHasSkillPk(ehskp93);
		ehs93.setLevel(5);
		/////////////////////////////////
		EmployeeHasSkill ehs101 = new EmployeeHasSkill();
		ehs101.setEmployeeHasSkillPk(ehskp101);
		ehs101.setLevel(5);
		EmployeeHasSkill ehs102 = new EmployeeHasSkill();
		ehs102.setEmployeeHasSkillPk(ehskp102);
		ehs102.setLevel(5);
		EmployeeHasSkill ehs103 = new EmployeeHasSkill();
		ehs103.setEmployeeHasSkillPk(ehskp103);
		ehs103.setLevel(5);*/
		
		
	/*	proxyemployeehasskill.save(ehs11);
		proxyemployeehasskill.save(ehs12);
		proxyemployeehasskill.save(ehs13);
		proxyemployeehasskill.save(ehs21);
		proxyemployeehasskill.save(ehs22);
		proxyemployeehasskill.save(ehs23);*/
		/*proxyemployeehasskill.save(ehs31);
		proxyemployeehasskill.save(ehs32);
		proxyemployeehasskill.save(ehs33);
		proxyemployeehasskill.save(ehs41);
		proxyemployeehasskill.save(ehs42);
		proxyemployeehasskill.save(ehs43);
		proxyemployeehasskill.save(ehs51);
		proxyemployeehasskill.save(ehs52);
		proxyemployeehasskill.save(ehs53);
		proxyemployeehasskill.save(ehs61);
		proxyemployeehasskill.save(ehs62);
		proxyemployeehasskill.save(ehs63);
		proxyemployeehasskill.save(ehs71);
		proxyemployeehasskill.save(ehs72);
		proxyemployeehasskill.save(ehs73);
		proxyemployeehasskill.save(ehs81);
		proxyemployeehasskill.save(ehs82);
		proxyemployeehasskill.save(ehs83);
		proxyemployeehasskill.save(ehs91);
		proxyemployeehasskill.save(ehs92);
		proxyemployeehasskill.save(ehs93);
		proxyemployeehasskill.save(ehs101);
		proxyemployeehasskill.save(ehs102);
		proxyemployeehasskill.save(ehs103);*/
		
	}
}
