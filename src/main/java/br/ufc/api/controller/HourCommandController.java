package br.ufc.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.ufc.api.model.HourCommand;
import br.ufc.api.service.HourCommandService;
import br.ufc.api.util.JwtUtil;

@RestController
@RequestMapping(value="/api/hour")
public class HourCommandController {

	@Autowired
	private HourCommandService hourService;
	
	@RequestMapping(path="/register", method=RequestMethod.POST)
	public ResponseEntity<Object> register(@RequestBody String hour, @RequestHeader String token){
		if(hour == null)
			return new ResponseEntity<Object>("Informe o horário do usuário", HttpStatus.BAD_REQUEST);
		if(JwtUtil.parseToken(token) == null)
			return new ResponseEntity<Object>("Token inválido", HttpStatus.UNAUTHORIZED);
		
		HourCommand h = hourService.save(hour);
		if(h != null)
			return new ResponseEntity<Object>(h, HttpStatus.OK);
		return new ResponseEntity<Object>("Não foi possível registrar as horas!", HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
