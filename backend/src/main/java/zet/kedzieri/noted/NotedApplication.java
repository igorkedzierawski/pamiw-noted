package zet.kedzieri.noted;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import zet.kedzieri.noted.user.NotedUserService;
import zet.kedzieri.noted.user.auth.NotedUserDetailsService;

@SpringBootApplication
public class NotedApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotedApplication.class, args);
	}

	@Bean
	public CommandLineRunner clr(NotedUserService nus) {
		return args -> {
			nus.registerNativeUser("Jan", "Kowalski", "jan", "password");
			nus.registerNativeUser("Adam", "Nowak", "adam", "password");
        };
	}

}
