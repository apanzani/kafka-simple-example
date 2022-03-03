package com.simple.example.kafka_prod_cons;

import com.simple.example.kafka_prod_cons.init.StreamInitializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.simple.example.kafka_prod_cons")
@Slf4j
public class KafkaProdConsApplication implements CommandLineRunner {

	private final StreamInitializer streamInitializer;

	public KafkaProdConsApplication(StreamInitializer streamInitializer) {
		this.streamInitializer = streamInitializer;
	}

	public static void main(String[] args) {
		SpringApplication.run(KafkaProdConsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("App starts...");
		streamInitializer.init();
	}
}
