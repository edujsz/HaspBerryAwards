package com.eduardo.raspberryawards.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private Integer publishYear;

    private boolean winner;

    @ManyToMany(mappedBy = "movies")
    List<Studio> studios;

    @ManyToMany(mappedBy = "movies")
    List<Producer> producers;


}
