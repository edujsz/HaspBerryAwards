package com.eduardo.raspberryawards.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonPropertyOrder({ "year", "title","studios","producers","winner" })
public class FlatFileMovieDTO {
    public String year;
    public String title;
    public String studios;
    public String producers;
    public String winner;
}
