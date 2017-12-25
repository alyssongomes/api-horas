package br.ufc.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.ufc.api.model.UserCommand;
import br.ufc.api.service.UserCommandService;
import br.ufc.api.util.JwtUtil;

@RestController
@RequestMapping(value="/api/user")
public class UserCommandController {
	
	@Autowired
	private UserCommandService userCommand;
	
	@RequestMapping(path="/register",method=RequestMethod.POST)
	public ResponseEntity<Object> register(@RequestBody String user, @RequestHeader String token){
		if(user == null)
			return new ResponseEntity<Object>("Informe os dados do usuário", HttpStatus.BAD_REQUEST);
		if(JwtUtil.parseToken(token) == null)
			return new ResponseEntity<Object>("Token inválido", HttpStatus.UNAUTHORIZED);
		
		UserCommand u = userCommand.save(user);
		if(u != null)
			return new ResponseEntity<Object>(u, HttpStatus.OK);
		return new ResponseEntity<Object>("Não foi possível registrar o usuário!", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
