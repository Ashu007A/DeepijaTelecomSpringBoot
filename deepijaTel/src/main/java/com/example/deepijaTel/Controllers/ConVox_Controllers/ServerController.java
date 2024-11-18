package com.example.deepijaTel.Controllers.ConVox_Controllers;

import com.example.deepijaTel.Models.Secondary.Server;
import com.example.deepijaTel.Services.Secondary.ServerServices;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/convox")
public class ServerController {

    private static final Logger logger = LoggerFactory.getLogger(ServerController.class);

    @Autowired
    private ServerServices serverServices;

    // Show servers page
    @GetMapping("/servers")
    public String showServers(Model model, HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return "redirect:/convox/login";
        }
        List<Server> servers = serverServices.getAllServers();
        model.addAttribute("servers", servers);
        model.addAttribute("page", "servers");
        return "ConVox/servers";
    }

    // Create a new server
    @PostMapping("/servers")
    public String createServer(@ModelAttribute Server server, Model model) {
        serverServices.createServer(server);
        return "redirect:/convox/servers"; // Stay on the same page after creation
    }

    // Get all servers
    @GetMapping("/all-servers")
    public String getAllServers(Model model) {
        List<Server> servers = serverServices.getAllServers();
        model.addAttribute("servers", servers);
        return "servers_list";
    }

    // Get a server by ID
    @GetMapping("/servers/{id}")
    public ResponseEntity<Server> getServerById(@PathVariable Long id) {
        Optional<Server> server = serverServices.getServerById(id);
        return server.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update a server by ID
    @PostMapping("/servers/{id}")
    public String updateServer(@PathVariable Long id, @ModelAttribute Server serverDetails) {
        serverServices.updateServer(id, serverDetails);
        return "redirect:/convox/servers"; // Stay on the same page after update
    }

    // Delete a server by ID
    @DeleteMapping("/servers/delete/{id}")
    public ResponseEntity<Void> deleteServer(@PathVariable Long id) {
        try {
            serverServices.deleteServer(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}