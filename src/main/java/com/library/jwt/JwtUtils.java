package com.library.jwt;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.*;

import com.library.service.MyUserPrincipal;

@Component
public class JwtUtils {

	private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
	
	@Value("${library.app.jwtSecret}")
	private String jwtSecret;
	
	@Value("${library.app.jwtExpirationMs}")
	private int jwtExpirationMs;
	
	
	public String generateJwtToken(Authentication authentication) {
		
		MyUserPrincipal myuserPrincipal = (MyUserPrincipal) authentication.getPrincipal();
		
		return Jwts.builder()
				.setSubject((myuserPrincipal.getUsername()))
				.setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
				.signWith(SignatureAlgorithm.HS512, jwtSecret)
				.compact();
	}
	
	public String getUserNameFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	}
	
	public boolean validateJwtToken(String authtoken) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authtoken);
			return true;
		}
		catch(SignatureException ex) {
			logger.error("Invalid JWT signature: {}", ex.getMessage());
		}
		catch(MalformedJwtException ex) {
			logger.error("Invalid JWT Token: {}", ex.getMessage());
		}
		catch(ExpiredJwtException ex) {
			logger.error("JWT token is expired: {}", ex.getMessage());
		}
		catch(UnsupportedJwtException ex) {
			logger.error("JWT token is unsupported: {}", ex.getMessage());
		}
		catch(IllegalArgumentException ex) {
			logger.error("JWT claims string is empty: {}", ex.getMessage());
		}
		
		return false;
	}

}
