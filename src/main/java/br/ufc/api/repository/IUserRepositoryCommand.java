package br.ufc.api.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufc.api.model.UserCommand;

@Repository
@Transactional
public interface IUserRepositoryCommand extends JpaRepository<UserCommand, Long>{
	
	public UserCommand findByCpfAndPassword(Long cpf, String password);
	
}
