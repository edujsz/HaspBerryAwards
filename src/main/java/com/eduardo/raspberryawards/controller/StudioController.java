package com.eduardo.raspberryawards.controller;

import com.eduardo.raspberryawards.dto.StudioDTO;
import com.eduardo.raspberryawards.service.StudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.util.List;

@RestController
@Component
@RequestMapping("/api/studio")
@CrossOrigin("*")
public class StudioController {

    @Autowired
    private StudioService studioService;

    @RequestMapping(value = "/studiosByWins", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StudioDTO>> listStudioByWins() {
        return ResponseEntity.ok(this.studioService.findCountWinsForEachStudioOrderByWins());
    }
}
