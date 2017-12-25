package br.ufc.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.ufc.api.model.UserCommand;
import br.ufc.api.model.UserQuery;
import br.ufc.api.repository.IUserRepositoryCommand;
import br.ufc.api.repository.IUserRepositoryQuery;
import br.ufc.api.util.Encryption;

@Service
public class UserQueryService {
	
	@Autowired
	private IUserRepositoryCommand userRepository;
	
	@Autowired
	private IUserRepositoryQuery userRepositoryQuery;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	/**
	 * @return lista de todos os usuários cadastrados
	 */
	public List<UserQuery> list(){
		return userRepositoryQuery.findAll();
	}
	
	/**
	 * Verifica se o usuário passado por parâmetro 
	 * está registrado no banco de dados.
	 * 
	 * @param usuário a ser autenticado
	 * @return usuário compatível com os dados do usuário passado por parâmetro 
	 */
	public UserCommand authentication(String user){
		try{
			UserCommand u = objectMapper.readValue(user, UserCommand.class);
			return userRepository.findByCpfAndPassword(u.getCpf(), Encryption.encrpyt(u.getPassword()));
		}catch (Exception e) {
			return null;
		}
	}
	
}
