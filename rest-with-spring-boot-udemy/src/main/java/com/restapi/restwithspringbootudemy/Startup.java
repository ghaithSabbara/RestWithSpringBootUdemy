package com.restapi.restwithspringbootudemy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
public class Startup {

	// @Autowired
	// UserRepository userRepository;
	//
	// @Autowired
	// private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(Startup.class, args);
	}

	// @Override
	// public void run(String... params) throws Exception {
	// User admin = new User();
	// admin.setUsername("ghaith");
	// admin.setPassword(passwordEncoder.encode("ghaith"));
	// admin.setEmail("ghaith@email.com");
	//
	// userRepository.save(admin);
	// }
}
