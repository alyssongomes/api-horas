package br.ufc.api.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.api.model.HourQuery;
import br.ufc.api.model.HoursWorked;
import br.ufc.api.model.UserQuery;
import br.ufc.api.repository.IHourRepositoryQuery;

@Service
public class HourQueryService {

	@Autowired
	private IHourRepositoryQuery hourRepository;
	
	/**
	 * Retorna os horários trabalhados por um usuário, compilando
	 * as horas e o minutos totais, além de retornar a lista 
	 * dos horários que o usuário trabalhou.
	 * 
	 * @param cpf do usuário
	 * @return horários trabalhados
	 */
	public HoursWorked list(Long cpf){
		UserQuery user = new UserQuery();
		user.setCpf(cpf);
		List<HourQuery> hours = hourRepository.findByUser(user);
		
		HoursWorked hw = new HoursWorked();
		if(hours.size() > 0){
			hw.setHoursWorked(0L);
			hw.setMinutesWorked(0L);
			hw.setHours(hours);
			
			for(HourQuery hour: hours){
				if(hour.getDateEnd() != null){
					//LocalDateTime begin = LocalDateTime.ofInstant(hour.getHourId().getDateBegin().toInstant(), ZoneId.systemDefault());
					LocalDateTime begin = LocalDateTime.ofInstant(hour.getDateBegin().toInstant(), ZoneId.systemDefault());
					LocalDateTime end = LocalDateTime.ofInstant(hour.getDateEnd().toInstant(), ZoneId.systemDefault());
					hw.setMinutesWorked(hw.getMinutesWorked()+Duration.between(begin, end).toMinutes());
					hw.setHoursWorked(hw.getHoursWorked()+Duration.between(begin, end).toHours());
				}
			}
		}
		
		return hw;
	}
	
}
