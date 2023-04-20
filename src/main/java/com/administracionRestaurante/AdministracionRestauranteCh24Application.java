package com.administracionRestaurante;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.administracionRestaurante.jwt.config.JwtFilter;

@SpringBootApplication
public class AdministracionRestauranteCh24Application {

	public static void main(String[] args) {
		SpringApplication.run(AdministracionRestauranteCh24Application.class, args);
		
		
		System.out.println("Hola Generation, estoy vivo!!!");
		
	}//metodo principal
	
	
	@Bean
	public FilterRegistrationBean<JwtFilter> jwtFilter(){
		FilterRegistrationBean<JwtFilter> registrationBean = 
				new FilterRegistrationBean<>();
		registrationBean.setFilter(new JwtFilter());
		registrationBean.addUrlPatterns("/restaurante/usuarios/");
		//registrationBean.addUrlPatterns("/restaurante/platillos/");
		return registrationBean;
	}//FilterRegistrationBean

}//class
