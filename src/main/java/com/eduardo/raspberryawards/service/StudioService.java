package com.eduardo.raspberryawards.service;

import com.eduardo.raspberryawards.dto.StudioDTO;
import com.eduardo.raspberryawards.model.Studio;

import java.util.List;

public interface StudioService {
    List<StudioDTO> findCountWinsForEachStudioOrderByWins();

    Studio save(Studio studio);
}
