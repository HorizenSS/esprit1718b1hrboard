package tn.esprit.b1.esprit1718b1hrboard.mBeans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import tn.esprit.b1.esprit1718b1hrboard.entities.Employee;
import tn.esprit.b1.esprit1718b1hrboard.entities.Response;
import tn.esprit.b1.esprit1718b1hrboard.entities.Topic;
import tn.esprit.b1.esprit1718b1hrboard.services.ResponseServiceLocal;
import tn.esprit.b1.esprit1718b1hrboard.services.TopicServiceLocal;
@ManagedBean
@SessionScoped
public class PageBean {
	 
	  
	 

		public PageBean() {
		super();
	}





		private Employee employeeask = new Employee();
		private String content;
		private Date postdate;
		private int  id_topic;
		private List<Topic> topics = new ArrayList<>();
		private Topic topic ;
		private String title;
		private String subject;
		private List<Response> r = new ArrayList<>();
		private int bool;
		private String x;

		public String getX() {
			return x;
		}





		public void setX(String x) {
			this.x = x;
		}





		@EJB
		TopicServiceLocal TopicServiceLocal ;
		@EJB
		ResponseServiceLocal rcServiceLocal ;
		




		public Employee getEmployeeask() {
			return employeeask;
		}





		public void setEmployeeask(Employee employeeask) {
			this.employeeask = employeeask;
		}





		public String getContent() {
			return content;
		}





		public void setContent(String content) {
			this.content = content;
		}





		public Date getPostdate() {
			return postdate;
		}





		public void setPostdate(Date postdate) {
			this.postdate = postdate;
		}





		public int getId_topic() {
			return id_topic;
		}





		public void setId_topic(int id_topic) {
			this.id_topic = id_topic;
		}





		public List<Topic> getTopics() {
			return topics;
		}





		public void setTopics(List<Topic> topics) {
			this.topics = topics;
		}





		public Topic getTopic() {
			return topic;
		}





		public void setTopic(Topic topic) {
			this.topic = topic;
		}





		public String getTitle() {
			return title;
		}





		public void setTitle(String title) {
			this.title = title;
		}





		public String getSubject() {
			return subject;
		}





		public void setSubject(String subject) {
			this.subject = subject;
		}





		public List<Response> getR() {
			return r;
		}





		public void setR(List<Response> r) {
			this.r = r;
		}





		public int getBool() {
			return bool;
		}





		public void setBool(int bool) {
			this.bool = bool;
		}





		public String gototopic() {
			r.clear();
		topic=TopicServiceLocal.find(id_topic);
		this.setContent(topic.getContent());
		this.setEmployeeask(topic.getEmployee());
		this.setPostdate(topic.getPostDate());
		this.setSubject(topic.getSubject());
		this.setTitle(topic.getTitle());
		String navigateTo = "";
      


			navigateTo = "/template/topicresponse?faces-redirect=true";
			 List<Response> resp1 = new ArrayList<>();
			resp1=rcServiceLocal.findAll();
System.out.println(id_topic);
			for(Response x : resp1)
			{ if(x.getTopic().getId()==id_topic) 
				r.add(x);}
			System.out.println(r.toString());
			if(r.isEmpty())
		      {bool=0;}
			else
			bool=1;
			return navigateTo;

		}

	}



