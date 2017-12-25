package br.ufc.api.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity(name="hours")
public class HourCommand implements Serializable{

	@EmbeddedId
	private HourId hourId;
	
	@JoinColumn(name="date_end", nullable=true)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", locale = "pt-BR", timezone = "Brazil/East")
	private Date dateEnd;

	public HourId getHourId() {
		return hourId;
	}

	public void setHourId(HourId hourId) {
		this.hourId = hourId;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}
	
}
