package ch.ffhs.dinf.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "ch.ffhs.dinf.service", "ch.ffhs.dinf.osre" })
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}