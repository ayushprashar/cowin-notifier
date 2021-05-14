package com.cowin.slotfinder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
@EnableScheduling
public class SlotFinderApplication {

	public static void main(String[] args) {
		SpringApplication.run(SlotFinderApplication.class, args);
	}

}
