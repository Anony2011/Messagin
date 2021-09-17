package com.wissam.messaging.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import com.wissam.messaging.controller.MessageController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

import static java.util.Collections.emptyList;

public class AuthenticationService {
  static final long EXPIRATIONTIME = 864_000_00; // 1 day in milliseconds
  static final String PREFIX = "Bearer";
  //should be read from a key vault
  

  
  static public void addToken(HttpServletResponse res, String user) {
    String JwtToken = Jwts.builder().setSubject(user)
        .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
        .signWith(SignatureAlgorithm.HS512, MessageController.signingKey)
        .compact();
    res.addHeader("Authorization", PREFIX + " " + JwtToken);
  }

  static public Authentication getAuthentication(HttpServletRequest request) {
    String token = request.getHeader("Authorization");
    if (token != null) {
      String user = Jwts.parser()
          .setSigningKey(MessageController.signingKey)
          .parseClaimsJws(token.replace(PREFIX, ""))
          .getBody()
          .getSubject();

      if (user != null) 
    	  return new UsernamePasswordAuthenticationToken(user, null, emptyList());
    }
    return null;
  }
}

