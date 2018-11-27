package com.eduardo.raspberryawards.controller;

import com.eduardo.raspberryawards.dto.ProducerWinIntervalDTO;
import com.eduardo.raspberryawards.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Component
@RequestMapping("/api/producer")
@CrossOrigin("*")
public class ProducerController {

    @Autowired
    ProducerService producerService;

    public ResponseEntity<ProducerWinIntervalDTO> intervals(){
        return ResponseEntity.ok(this.producerService.localizeIntervals());
    }

}
