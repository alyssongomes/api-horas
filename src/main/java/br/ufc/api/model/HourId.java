package br.ufc.api.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Embeddable
public class HourId implements Serializable{

	@ManyToOne(targetEntity=UserCommand.class)
	@JoinColumn(name="cpf")
	@JsonBackReference
	private UserCommand user;
	
	@JoinColumn(name="date_begin")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", locale = "pt-BR", timezone = "Brazil/East")
	private Date dateBegin;
	
	public UserCommand getUser() {
		return user;
	}
	
	public void setUser(UserCommand user) {
		this.user = user;
	}
	
	public Date getDateBegin() {
		return dateBegin;
	}
	
	public void setDateBegin(Date dateBegin) {
		this.dateBegin = dateBegin;
	}

	@Override
	public boolean equals(Object o){
		if (this == o) return true;
		if (!(o instanceof HourId)) return false;
		HourId hi = (HourId) o;
		return Objects.equals(getUser().getCpf(), hi.getUser().getCpf()) && 
				Objects.equals(getDateBegin().getTime(),hi.getDateBegin().getTime());
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(getUser().getCpf());
	}
	
}
