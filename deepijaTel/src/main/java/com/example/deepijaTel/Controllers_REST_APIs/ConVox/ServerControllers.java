package com.example.deepijaTel.Controllers_REST_APIs.ConVox;

import com.example.deepijaTel.Models.Secondary.Server;
import com.example.deepijaTel.Services.Secondary.ServerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/servers")
public class ServerControllers {

    @Autowired
    private ServerServices serverServices;

    @GetMapping
    public List<Server> getAllServers() {
        return serverServices.getAllServers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Server> getServerById(@PathVariable Long id) {
        Optional<Server> server = serverServices.getServerById(id);
        return server.isPresent() ? ResponseEntity.ok(server.get()) : ResponseEntity.notFound().build();
    }

    @GetMapping("/serverId/{serverId}")
    public ResponseEntity<Server> getServerByServerId(@PathVariable String serverId) {
        Optional<Server> server = serverServices.getServerByServerId(serverId);
        return server.isPresent() ? ResponseEntity.ok(server.get()) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Server createServer(@RequestBody Server server) {
        return serverServices.createServer(server);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Server> updateServer(@PathVariable Long id, @RequestBody Server serverDetails) {
        Server updatedServer = serverServices.updateServer(id, serverDetails);
        return ResponseEntity.ok(updatedServer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServer(@PathVariable Long id) {
        serverServices.deleteServer(id);
        return ResponseEntity.noContent().build();
    }
}