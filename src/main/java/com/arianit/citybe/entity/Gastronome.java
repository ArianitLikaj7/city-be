package com.arianit.citybe.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "gastronomies")
public class Gastronome {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gastronome_id")
    private Long gastronomeId;

    @Column(nullable = false)
    private String nameOfGastronome;

    @Column(length = 255)
    private String schedule;

    @Column(nullable = false)
    private Double longitude;

    @Column(nullable = false)
    private Double latitude;

    @Column
    private boolean sponsored;

    @Enumerated(EnumType.STRING)
    private TypeOfGastronome typeOfGastronome;

    @Column()
    private Integer rating;

    @Column(length = 500)
    private String description;

    @Column(precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "phone_number")
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "city_id")
    @JsonBackReference
    private City city;

    @OneToMany(mappedBy = "gastronome", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Reservation> reservations;

    @ElementCollection
    @CollectionTable(name = "gastronome_attachments", joinColumns = @JoinColumn(name = "gastronome_id"))
    @Column(name = "attachment")
    private List<String> attachments;
}
