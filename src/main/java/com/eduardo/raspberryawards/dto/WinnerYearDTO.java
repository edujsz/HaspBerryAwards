package com.eduardo.raspberryawards.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WinnerYearDTO {
    private Integer year;
    private Long winnerCount;
}
