package com.example.Backend.Security;
	import org.springframework.context.annotation.Bean;
	import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
	import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
public class SecurityConfig {
	    @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	    
	    @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        http
	            .csrf(csrf -> csrf.disable())
	            .authorizeHttpRequests(auth -> auth
	                .requestMatchers("/RegisterUser","/login").permitAll()
	                .requestMatchers("/GetallUsers","/Getuser/{id}","/UpdateUser/{id}","/RemoveUser/{id}").permitAll()
	                .requestMatchers("/getproduct/{id}","/import-fake","/getproducts","/newproduct","/removeproduct/{id}").permitAll()
	                .requestMatchers("/addToCart","/Getcart/{UserId}","/remove","/placeOrder","/getorders/{userId}").permitAll()
	                .anyRequest().authenticated()
	            )
	            .formLogin(form -> form.disable());

	        return http.build();
	    }
	    
	    
	    
	}



