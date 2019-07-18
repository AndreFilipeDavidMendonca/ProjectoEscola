package com.polarising.PortalNet.jwt;
import java.sql.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtCreator {
	
	public String createJWT(String role)
	{
		//The JWT signature algorithm we will be using to sign the token
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		
		//Get the current date for the Issued At claim
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		
		//We will sign our JWT with our ApiKey secret
		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("To change");
		SecretKeySpec signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
		
		//Let's set the JWT claims
		JwtBuilder builder = Jwts.builder()
				.setIssuedAt(now)
				.setSubject(role)
				.signWith(signatureAlgorithm, signingKey);
		
		//Builds the JWT and serializes it to a compact, URL-safe string
		return builder.compact();
	}
	
	public String createJWT(String role, long milsec)
	{
		//The JWT signature algorithm we will be using to sign the token
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		
		//Get the current date for the Issued At claim
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		
		//We will sign our JWT with our ApiKey secret
		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("To change");
		SecretKeySpec signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
		
		//Let's set the JWT claims
		JwtBuilder builder = Jwts.builder()
				.setIssuedAt(now)
				.setSubject(role)
				.signWith(signatureAlgorithm, signingKey);
		
		//Let's add the expiration, if it has been specified
		if (milsec > 0)
		{
			long expMillis = nowMillis + milsec;
			Date exp = new Date(expMillis);
			builder.setExpiration(exp);
		}
		
		//Builds the JWT and serializes it to a compact, URL-safe string
		return builder.compact();
	}
}