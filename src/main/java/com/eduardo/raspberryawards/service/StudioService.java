package com.eduardo.raspberryawards.service;

import com.eduardo.raspberryawards.dto.StudioDTO;

import java.util.List;

public interface StudioService {
    List<StudioDTO> findCountWinsForEachStudioOrderByWins();
}
