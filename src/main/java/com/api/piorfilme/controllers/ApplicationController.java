package com.api.piorfilme.controllers;

import com.api.piorfilme.dtos.PrizeIntervalReturnDTO;
import com.api.piorfilme.services.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ApplicationController {

    private final ApplicationService appService;

    public ApplicationController(ApplicationService appService) {
        this.appService = appService;
    }

    @GetMapping
    public ResponseEntity<PrizeIntervalReturnDTO> getIntervaloDePremios() {
        return ResponseEntity.ok(this.appService.getPrizeIntervals());
    }
}
