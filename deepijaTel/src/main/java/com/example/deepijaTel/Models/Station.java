package com.example.deepijaTel.Models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "stations")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "station_id", nullable = false, unique = true)
    private String stationId;

    @Column(name = "station_name", nullable = false)
    private String stationName;

    @Column(name = "active_status", nullable = false)
    private String activeStatus;
}
