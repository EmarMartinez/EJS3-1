package com.bosonit.ejs31;

import com.bosonit.ejs31.application.Controller;
import com.bosonit.ejs31.domain.Usuario;
import com.bosonit.ejs31.service.UsuarioService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class Ejs31Application {

	public static void main(String[] args) {
		SpringApplication.run(Ejs31Application.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(Controller controller) {
		return args -> {
			controller.saveUser(new Usuario(null, "emar", "secreto", true));
			controller.saveUser(new Usuario(null, "pepe", "secreto", false));
			controller.saveUser(new Usuario(null, "juan", "secreto", false));
		};
	}

}
