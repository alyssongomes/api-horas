package br.ufc.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import br.ufc.api.model.HourCommand;
import br.ufc.api.model.HourId;
import br.ufc.api.model.HourQuery;
import br.ufc.api.repository.IHourRepositoryCommand;
import br.ufc.api.repository.IHourRepositoryQuery;

@Service
public class HourCommandService {
	
	@Autowired
	private IHourRepositoryCommand hourRepositoryCommand;
	
	@Autowired
	private IHourRepositoryQuery hourRepositoryQuery;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	/**
	 * Registra no PostgreSQL e no MongoDB um horário de um 
	 * usuário. Caso não haja um horário de início ou um usuário
	 * o método retornará nulo.
	 * 
	 * @param horário a ser registrado em formato json
	 * @return horário que foi registrado nos banco de dados
	 */
	public HourCommand save(String hour){
		HourCommand h = new HourCommand();
		try{
			HourId hid = objectMapper.readValue(hour, HourId.class);
			
			if(hid.getUser() == null || hid.getDateBegin() == null)
				return null;
			
			ObjectNode node = objectMapper.readValue(hour, ObjectNode.class);
			
			if(node.has("dateEnd")){
				h = objectMapper.readValue(hour, HourCommand.class);
				if(h.getDateEnd().before(hid.getDateBegin()) || hid.getDateBegin().compareTo(h.getDateEnd()) == 0)
					return null;
			}
				
			h.setHourId(hid);
			
			if(hourRepositoryCommand.save(h) != null){
				HourQuery hq = objectMapper.readValue(hour, HourQuery.class);
				HourQuery oldHq = hourRepositoryQuery.findByUserAndDateBegin(hq.getUser(), hq.getDateBegin());
				if(oldHq == null){
					if(hourRepositoryQuery.insert(hq) != null)
						return h;
				}else{
					oldHq.setDateEnd(hq.getDateEnd());
				}
				if(hourRepositoryQuery.insert(oldHq) != null)
					return h;
			}
			
			return null;
		}catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
	}
	
}
