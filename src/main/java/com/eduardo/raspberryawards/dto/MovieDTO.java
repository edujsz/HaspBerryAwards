package com.eduardo.raspberryawards.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MovieDTO {
    Long id;
    Integer year;
    String title;
    List<String> studios;
    List<String> producers;
    Boolean winner;

}
