package com.arianit.citybe.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "cities")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cityId;

    @Column(nullable = false)
    private String nameOfCity;

    @Column(nullable = false)
    private String culturalHeritage;

    @Column(
            nullable = false,
            unique = true
    )
    private String cityPrefix;

    @OneToMany(mappedBy = "city")
    @JsonManagedReference
    private List<Gastronome> gastronomes;

}