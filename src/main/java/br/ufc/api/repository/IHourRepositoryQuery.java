package br.ufc.api.repository;

import java.util.Date;
import java.util.List;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.repository.MongoRepository;

import br.ufc.api.model.HourQuery;
import br.ufc.api.model.UserQuery;

public interface IHourRepositoryQuery extends MongoRepository<HourQuery, String>{
	
	@Cacheable("hours")
	@CachePut(cacheNames="hours")
	public List<HourQuery> findByUser(UserQuery user); 
	
	public HourQuery findByUserAndDateBegin(UserQuery user, Date dateBegin);
	
}

