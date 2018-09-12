package tn.esprit.b1.esprit1718b1hrboard.mBeans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import tn.esprit.b1.esprit1718b1hrboard.entities.Account;
import tn.esprit.b1.esprit1718b1hrboard.entities.Employee;
import tn.esprit.b1.esprit1718b1hrboard.entities.JobOffer;
import tn.esprit.b1.esprit1718b1hrboard.entities.Response;
import tn.esprit.b1.esprit1718b1hrboard.entities.Topic;
import tn.esprit.b1.esprit1718b1hrboard.services.AccountServiceLocal;
import tn.esprit.b1.esprit1718b1hrboard.services.ResponseServiceLocal;
import tn.esprit.b1.esprit1718b1hrboard.services.TopicServiceLocal;
import tn.esprit.b1.esprit1718b1hrboard.utils.MD5;
@ManagedBean
@SessionScoped
public class ForumBean {
	 @ManagedProperty(value="#{loginBean}")
	    private LoginBean loginBean;
	 
	
	 
	
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

@EJB
TopicServiceLocal TopicServiceLocal ;
@EJB
ResponseServiceLocal rcServiceLocal ;
public String addTopic() {
	
	System.out.println(content);
	String navigateTo = "";
	Long millis = System.currentTimeMillis();
	Date date = new Date(millis);
	
	postdate=date;
	employeeask= loginBean.getUser();
	TopicServiceLocal.save(new Topic(subject, content, postdate, title, employeeask));

	topic= new Topic(content, date, title, employeeask);
	navigateTo = "/template/Topic?faces-redirect=true";
	
	return navigateTo;

}

public String search() {
	topics.clear();
	 List<Topic> l = new ArrayList<>();
		String navigateTo = "";
l = TopicServiceLocal.findAll();
for(Topic e:l)
{   if(topics.contains(e)==false)
	{if(e.getSubject().equals(subject))
	topics.add(e)	;
	}
}
	
	navigateTo = "/template/TopicChosen?faces-redirect=true";
	
	return navigateTo;

}




public String gototopic() {
	
topic=TopicServiceLocal.find(id_topic);
this.setContent(topic.getContent());
this.setEmployeeask(topic.getEmployee());
this.setPostdate(topic.getPostDate());
this.setSubject(topic.getSubject());
this.setTitle(topic.getTitle());
String navigateTo = "";



	navigateTo = "/template/Topic?faces-redirect=true";
	 List<Response> resp1 = new ArrayList<>();
	resp1=rcServiceLocal.findAll();

	for(Response x : resp1)
	{ if(x.getTopic().getId()==id_topic) 
		r.add(x);}

	
	if(r.isEmpty())
      {bool=0;}
	else
	bool=1;
	return navigateTo;

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

public String getSubject() {
	return subject;
}

public void setSubject(String subject) {
	this.subject = subject;
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
public LoginBean getLoginBean() {
	return loginBean;
}
public void setLoginBean(LoginBean loginBean) {
	this.loginBean = loginBean;
}
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
}}
