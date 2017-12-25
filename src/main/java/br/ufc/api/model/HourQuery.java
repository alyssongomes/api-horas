package br.ufc.api.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Document(collection="hours")
public class HourQuery implements Serializable{

	@Id
	@JsonIgnore
	private String id;
	
	@JsonBackReference
	private UserQuery user;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", locale = "pt-BR", timezone = "Brazil/East")
	private Date dateBegin;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", locale = "pt-BR", timezone = "Brazil/East")
	private Date dateEnd;
	

	public HourQuery(){}
	
	public HourQuery(UserQuery user, Date dateBegin, Date dateEnd){
		this.user = user;
		this.dateBegin = dateBegin;
		this.dateEnd = dateEnd;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public UserQuery getUser() {
		return user;
	}

	public void setUser(UserQuery user) {
		this.user = user;
	}

	public Date getDateBegin() {
		return dateBegin;
	}

	public void setDateBegin(Date dateBegin) {
		this.dateBegin = dateBegin;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}
	
	@Override
	public String toString() {
		return "id: "+this.id+",user: "+this.user.getCpf()+", dateBegin: "+this.dateBegin;
	}
	
}
