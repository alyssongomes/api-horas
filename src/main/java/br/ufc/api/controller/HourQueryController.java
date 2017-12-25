package br.ufc.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.ufc.api.model.HoursWorked;
import br.ufc.api.service.HourQueryService;

@RestController
@RequestMapping(value="/api/hour")
public class HourQueryController {

	@Autowired
	private HourQueryService hourService;
	
	@RequestMapping(path="/list/{cpf}",method=RequestMethod.GET)
	public ResponseEntity<Object> list(@PathVariable Long cpf){
		if(cpf == null)
			return new ResponseEntity<Object>("Informe o cpf do usuário", HttpStatus.BAD_REQUEST);
		return new ResponseEntity<Object>(hourService.list(cpf), HttpStatus.OK);
	}
}
