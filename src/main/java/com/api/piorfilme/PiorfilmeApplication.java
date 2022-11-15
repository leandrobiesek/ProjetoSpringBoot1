package com.api.piorfilme;

import com.api.piorfilme.models.ProducerModel;
import com.api.piorfilme.services.ProducerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PiorfilmeApplication {


	public static void main(String[] args) {
		SpringApplication.run(PiorfilmeApplication.class, args);
		//coloco aqui o codigo de leitura do CSV?
	}

}
