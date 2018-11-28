package com.eduardo.raspberryawards.service.impl;

import com.eduardo.raspberryawards.dto.StudioDTO;
import com.eduardo.raspberryawards.model.Studio;
import com.eduardo.raspberryawards.repository.StudioRepository;
import com.eduardo.raspberryawards.service.StudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudioServicceImpl implements StudioService {

    @Autowired
    private StudioRepository studioRepository;

    @Override
    public List<StudioDTO> findCountWinsForEachStudioOrderByWins() {
        return this.studioRepository.findCountWinsForEachStudioOrderByWins();
    }

    @Override
    public Studio save(Studio studio) {
        return this.studioRepository.save(studio);
    }
}
