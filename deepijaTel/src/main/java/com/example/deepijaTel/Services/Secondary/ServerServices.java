package com.example.deepijaTel.Services.Secondary;

import com.example.deepijaTel.Models.Secondary.Server;

import java.util.List;
import java.util.Optional;

public interface ServerServices {
    Server createServer(Server server);
    List<Server> getAllServers();
    Optional<Server> getServerById(Long id);
    Server updateServer(Long id, Server serverDetails);
    void deleteServer(Long id);
}