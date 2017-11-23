package deepvip;


import deepvip.controller.exceptions.RestResponseEntityExceptionHandler;
import deepvip.controller.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Entity;

@SpringBootApplication
public class DeepVipApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeepVipApplication.class);
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public RestResponseEntityExceptionHandler restResponseEntityExceptionHandler() {
		return new RestResponseEntityExceptionHandler();
	}

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("/WEB-INF/classes/messages");
		return messageSource;
	}
}
