package com.example.deepijaTel.Models.Secondary;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "servers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Server {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "server_id", nullable = false, unique = true)
    private String serverId;

    @Column(name = "server_name", nullable = false)
    private String serverName;

    @Column(name = "database_ip", nullable = false)
    private String databaseIp;

    @Column(name = "database_web_port", nullable = false)
    private Integer databaseWebPort;

    @Column(name = "voice_ip", nullable = false)
    private String voiceIp;

    @Column(name = "voice_web_port", nullable = false)
    private Integer voiceWebPort;

    @Column(name = "server_description", nullable = false)
    private String serverDescription;

    @Column(name = "active_status", nullable = false)
    private String activeStatus;
}