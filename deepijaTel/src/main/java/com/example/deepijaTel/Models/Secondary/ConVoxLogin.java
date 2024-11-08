package com.example.deepijaTel.Models.Secondary;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ConVoxLogin")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ConVoxLogin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;
}