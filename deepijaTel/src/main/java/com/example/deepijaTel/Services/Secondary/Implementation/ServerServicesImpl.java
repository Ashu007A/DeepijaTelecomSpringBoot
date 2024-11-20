package com.example.deepijaTel.Services.Secondary.Implementation;

import com.example.deepijaTel.Models.Secondary.Server;
import com.example.deepijaTel.Repositories.Secondary.ServerRepository;
import com.example.deepijaTel.Services.Secondary.ServerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServerServicesImpl implements ServerServices {

    @Autowired
    private ServerRepository serverRepository;

    @Override
    public Server createServer(Server server) {
        return serverRepository.save(server);
    }

    @Override
    public List<Server> getAllServers() {
        return serverRepository.findAll();
    }

    @Override
    public Optional<Server> getServerById(Long id) {
        return serverRepository.findById(id);
    }

    @Override public Optional<Server> getServerByServerId(String serverId) { return serverRepository.findByServerId(serverId); }

    @Override
    public Server updateServer(Long id, Server serverDetails) {
        Optional<Server> optionalServer = serverRepository.findById(id);
        if (optionalServer.isPresent()) {
            Server server = optionalServer.get();
            server.setServerId(serverDetails.getServerId());
            server.setServerName(serverDetails.getServerName());
            server.setDatabaseIp(serverDetails.getDatabaseIp());
            server.setDatabaseWebPort(serverDetails.getDatabaseWebPort());
            server.setVoiceIp(serverDetails.getVoiceIp());
            server.setVoiceWebPort(serverDetails.getVoiceWebPort());
            server.setServerDescription(serverDetails.getServerDescription());
            server.setActiveStatus(serverDetails.getActiveStatus());
            return serverRepository.save(server);
        } else {
            throw new RuntimeException("Server not found");
        }
    }

    @Override
    public void deleteServer(Long id) {
        serverRepository.findById(id).ifPresent(serverRepository::delete);
    }
}