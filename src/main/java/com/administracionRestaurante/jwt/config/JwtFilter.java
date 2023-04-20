package com.administracionRestaurante.jwt.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.filter.GenericFilterBean;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;

public class JwtFilter extends GenericFilterBean {
	
	public static String secret = "palabra-secreta";
	

	@Override
	
	// Manejar una solicitud (request), una respuesta (response) y una cadena de filtros (filterChain)
	
	
	//Este filtro intercepta todas esas solicitudes, y revisa que tengan la autorizacion correcta
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		
		
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		
		//Aqui delimitamos las solicitudes que si  o que no necesitan de una autorizacion (va en el encabezado)
		
		String authHeader =  httpServletRequest.getHeader("authorization"); //encabezado
		
		if (  ("POST".equals(httpServletRequest.getMethod())) ||
				( ("GET".equals(httpServletRequest.getMethod())) && 
						(! httpServletRequest.getRequestURI().contains("/restaurante/platillos/") )  ) ||
			  ("PUT".equals(httpServletRequest.getMethod())) ||
			  ("DELETE".equals(httpServletRequest.getMethod()))
			) {
			
			//Si el encabezado no tiene datos o no inicia con la palabra bearer
				if  ( authHeader ==null || !authHeader.startsWith("Bearer ") ) {
					throw new ServletException("1. Invalid Token"); //muestro un error
				}// if authHedaer
				
				//si encuentra en el encabezado, verifica que incluiya la palabra bearer
				String token = authHeader.substring(7);
				try {
					//Sacamos los reclamos o claims para verificar la estructura del JWT
				Claims claims = Jwts.parser().setSigningKey(secret)
						.parseClaimsJws(token).getBody();
				claims.forEach((key, value)->{
					//Se imprimer a manera de depuracion
					System.out.println("key: " + key + " value: " + value);
				});
				
				//Aqui se revisa que tenga una firma, que este dentro del tiempo de expiracion
			}catch (SignatureException | MalformedJwtException | ExpiredJwtException  e) {
					throw new ServletException("2. Invalid Token.");
				}//catch 
		}// if methods

		filterChain.doFilter(request, response);	//cuando pase todo el filtro, muesta tu request y response	
	}//doFilter

	
}//class 

