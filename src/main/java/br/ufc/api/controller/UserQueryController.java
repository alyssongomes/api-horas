package br.ufc.api.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.ufc.api.model.UserCommand;
import br.ufc.api.model.UserQuery;
import br.ufc.api.service.UserQueryService;
import br.ufc.api.util.JwtUtil;

@RestController
@RequestMapping(value="/api/user")
public class UserQueryController {

	@Autowired
	private UserQueryService userQuery;
	
	@RequestMapping(path="/list", method=RequestMethod.GET)
	public ResponseEntity<List<UserQuery>> list(){
		return new ResponseEntity<List<UserQuery>>(userQuery.list(), HttpStatus.OK);
	}
	
	@RequestMapping(path="/auth",method=RequestMethod.POST)
	public ResponseEntity<String> auth(@RequestBody String user){
		if(user == null)
			return new ResponseEntity<String>("Informe os dados do usuário", HttpStatus.BAD_REQUEST);
		
		UserCommand u = userQuery.authentication(user);
		if (u != null){
			String token = JwtUtil.generateToken(u);
			return new ResponseEntity<String>(token,HttpStatus.OK);
		}
		return new ResponseEntity<String>("Usuário não encontrado",HttpStatus.UNAUTHORIZED);
	}
	
}
