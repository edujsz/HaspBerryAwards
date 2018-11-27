package com.eduardo.raspberryawards.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private Integer year;

    private Boolean winner;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="studio_movies",joinColumns=@JoinColumn(name = "movies_id"),
            inverseJoinColumns=@JoinColumn(name = "studios_id"))
    @JsonManagedReference
    private Set<Studio> studios;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="producer_movies",joinColumns=@JoinColumn(name = "movies_id"),
            inverseJoinColumns=@JoinColumn(name = "producers_id"))
    @JsonManagedReference
    private Set<Producer> producers;
}
