package com.api.piorfilme.beans;

import com.api.piorfilme.models.ProducerModel;
import com.api.piorfilme.repositories.ProducerRepository;
import com.api.piorfilme.services.CSVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private final CSVService csvService;

    @Value("${csvfilename}")
    private String csvFileName;
    @Autowired
    public DataLoader(CSVService csvService) {
        this.csvService = csvService;
    }

    public void run(ApplicationArguments args) {
        this.csvService.saveResourceCSV(csvFileName);
    }
}
