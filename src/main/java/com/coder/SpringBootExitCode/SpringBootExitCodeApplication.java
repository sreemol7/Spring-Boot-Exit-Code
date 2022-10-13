package com.coder.SpringBootExitCode;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeExceptionMapper;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootExitCodeApplication implements ExitCodeGenerator {

	public static void main(String[] args) {

		System.exit(SpringApplication
				.exit(SpringApplication.run(SpringBootExitCodeApplication.class, args)));
		;
	}

	@Override
	public int getExitCode() {
		return 33;
	}

	@Bean
	ExitCodeExceptionMapper exitCodeExceptionMapper(){

		return exception -> {
			if(exception.getCause() instanceof NumberFormatException)
				return 34;
			if(exception.getCause() instanceof NullPointerException)
				return 35;

			return 1;
		};
	}

	@Bean
	CommandLineRunner createException(){
		return args -> {
			Integer.parseInt("value");
		};
	}
}
