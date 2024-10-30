package com.example.deepijaTel.Models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "time_entries")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TimeEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String punchIn;

    @Column
    private String punchOut;
}