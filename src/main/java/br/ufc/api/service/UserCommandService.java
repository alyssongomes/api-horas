package br.ufc.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.ufc.api.model.UserCommand;
import br.ufc.api.model.UserQuery;
import br.ufc.api.repository.IUserRepositoryCommand;
import br.ufc.api.repository.IUserRepositoryQuery;
import br.ufc.api.util.Encryption;

@Service
public class UserCommandService {

	@Autowired
	private IUserRepositoryCommand userRepositoryCommand;
	
	@Autowired
	private IUserRepositoryQuery userRespositoryQuery;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	/**
	 * Registra no PostgreSQL e no MongoDB um usuário.
	 * Caso o usuário não possua cpf ou senha retornará nulo.
	 * 
	 * @param  usuário a ser registrado em formato json
	 * @return usuário que foi registrado nos bancos de dados 
	 */
	public UserCommand save(String user){
		try{
			UserCommand u = objectMapper.readValue(user, UserCommand.class);
			
			if(u.getCpf() == null || u.getPassword() == null)
				return null;
			
			if (userRepositoryCommand.findOne(u.getCpf()) != null)
				return null;
			
			u.setPassword(Encryption.encrpyt(u.getPassword()));
			
			if(userRepositoryCommand.save(u) != null){
				UserQuery uq = new UserQuery(u.getCpf(), u.getFirstName(), u.getLastName());
				if(userRespositoryQuery.insert(uq) != null)
					return u;
			}
			
			return null;
		}catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
	}
	
}
