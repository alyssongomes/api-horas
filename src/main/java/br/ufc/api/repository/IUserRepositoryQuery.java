package br.ufc.api.repository;

import java.util.List;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.repository.MongoRepository;

import br.ufc.api.model.UserQuery;

public interface IUserRepositoryQuery extends MongoRepository<UserQuery, Long>{
	
	@Cacheable("users")
	@CachePut(cacheNames="users")
	public List<UserQuery> findAll();
	
}
