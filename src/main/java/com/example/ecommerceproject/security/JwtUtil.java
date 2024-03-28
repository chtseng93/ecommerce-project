package com.example.ecommerceproject.security;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.ecommerceproject.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {


	private final JwtParser jwtParser;
	private final String TOKEN_HEADER = "Authorization";
    private final String TOKEN_PREFIX = "Bearer ";
	private String jwt_key = "botlandsecretkey";
    private long tokenValidTime = 60*60*1000;

  
    public JwtUtil(){
        this.jwtParser = Jwts.parser().setSigningKey(jwt_key);
    }
    
    public String getEmail(Claims claims) {
        return claims.getSubject();
    }

    private List<String> getRoles(Claims claims) {
        return (List<String>) claims.get("roles");
    }

    public String createToken(User user) {
        Claims claims = Jwts.claims().setSubject(user.getEmail());
        claims.put("firstName",user.getFirstName());
        claims.put("lastName",user.getLastName());
//        Date tokenStartTime = new Date();
//        Date tokenValidity = new Date(tokenStartTime.getTime() + TimeUnit.MINUTES.toMillis(tokenValidTime));
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis()+ TimeUnit.MINUTES.toMillis(tokenValidTime)))
//              .setExpiration(tokenValidity)
                .signWith(SignatureAlgorithm.HS256, jwt_key)
                .compact();
    }

    private Claims parseJwtClaims(String token) {
        return jwtParser.parseClaimsJws(token).getBody();
    }
    
    public boolean isExpiredClaims(Claims claims) throws AuthenticationException {
        try {
            return claims.getExpiration().after(new Date());
        } catch (Exception exception) {
            throw exception;
        }
    }

    public Claims resolveClaims(HttpServletRequest httpServletRequest) {
        try {
            String token = resolveToken(httpServletRequest);
            if (token != null) {
                return parseJwtClaims(token);
            }
            return null;
        } catch (ExpiredJwtException exception) {
        	httpServletRequest.setAttribute("expired", exception.getMessage());
            throw exception;
        } catch (Exception exception) {
        	httpServletRequest.setAttribute("invalid", exception.getMessage());
            throw exception;
        }
    }

    public String resolveToken(HttpServletRequest httpServletRequest) {

        String bearerToken = httpServletRequest.getHeader(TOKEN_HEADER);
        if (bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX)) {
            return bearerToken.substring(TOKEN_PREFIX.length());
        }
        return null;
    }

    

  


}
