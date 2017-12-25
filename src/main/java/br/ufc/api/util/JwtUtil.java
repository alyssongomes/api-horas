package br.ufc.api.util;

import java.util.Date;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.ufc.api.model.UserCommand;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil {
	
	private static final Long EXPIRATION_TIME = 600000L; // 10 Minutos
	private static final String SECRET = "greenmile";
	
	private static ObjectMapper objectMapper = new ObjectMapper();
	
	public static UserCommand parseToken(String token){
		if(token.equals("") || token == null)
			return null;
		try{
			Claims body = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
			return objectMapper.readValue(body.getSubject(), UserCommand.class);
		}catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
	}
	
	public static String generateToken(UserCommand user){
		try{
			Claims claims = Jwts.claims().setSubject(objectMapper.writeValueAsString(user));
			return Jwts.builder().setClaims(claims).setExpiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME)).signWith(SignatureAlgorithm.HS512, SECRET).compact();
		}catch (Exception e) {
			return null;
		}
	}
	
}
