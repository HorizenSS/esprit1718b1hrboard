package tn.esprit.b1.esprit1718b1hrboard.mBeans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import tn.esprit.b1.esprit1718b1hrboard.entities.Employee;
import tn.esprit.b1.esprit1718b1hrboard.entities.ReponsePk;
import tn.esprit.b1.esprit1718b1hrboard.entities.Response;
import tn.esprit.b1.esprit1718b1hrboard.entities.Topic;
import tn.esprit.b1.esprit1718b1hrboard.services.ResponseServiceLocal;
import tn.esprit.b1.esprit1718b1hrboard.services.TopicServiceLocal;
@SessionScoped
@ManagedBean
public class topicBean {
	
	private String content;
	private Date postDate;
	private Float rating;
	private Employee employeeresp;
	private Topic topic;
	private int id_topic;
	public int getId_topic() {
		return id_topic;
	}

	public void setId_topic(int id_topic) {
		this.id_topic = id_topic;
	}
	private List<Response> resp = new ArrayList<>();
	 @ManagedProperty(value="#{loginBean}")
	    private LoginBean loginBean;


	@EJB
	TopicServiceLocal TopicServiceLocal ;
	@EJB
	ResponseServiceLocal responseServiceLocal ;
	public void rate()
	{
		rating=rating+1;
Response r = new Response();
r=responseServiceLocal.find(id_topic);
r.setRating(rating);
responseServiceLocal.update(r);

		
		
	}
	public String addTopic() {
		
		String navigateTo = "";
		Long millis = System.currentTimeMillis();
		Date date = new Date(millis);
		postDate=date;
		employeeresp= loginBean.getUser();
	ReponsePk ResponsePk = new ReponsePk();
	ResponsePk.setIdEmployee(employeeresp.getId());
	ResponsePk.setIdTopic(id_topic);
	ResponsePk.setContent(content);
		responseServiceLocal.save(new Response(ResponsePk, postDate, rating, employeeresp, topic));
		navigateTo = "/template/topicresponse?faces-redirect=true";
		
		return navigateTo;

	}
	
	public List<Response> getResp() {
		return resp;
	}

	public void setResp(List<Response> resp) {
		this.resp = resp;
	}

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getPostDate() {
		return postDate;
	}
	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}
	public Float getRating() {
		return rating;
	}
	public void setRating(Float rating) {
		this.rating = rating;
	}
	public Employee getEmployeeresp() {
		return employeeresp;
	}
	public void setEmployeeresp(Employee employeeresp) {
		this.employeeresp = employeeresp;
	}
	public Topic getTopic() {
		return topic;
	}
	public void setTopic(Topic topic) {
		this.topic = topic;
	}
}
