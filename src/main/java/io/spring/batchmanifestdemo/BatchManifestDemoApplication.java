package io.spring.batchmanifestdemo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.context.annotation.Bean;

@EnableTask
@SpringBootApplication
public class BatchManifestDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BatchManifestDemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner() {
		return args -> {
			System.out.println(">> My task ran!");
		};
	}
}
