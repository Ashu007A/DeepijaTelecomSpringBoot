package com.example.deepijaTel.Controllers;

import com.example.deepijaTel.Models.Secondary.ConVoxLogin;
import com.example.deepijaTel.Models.Secondary.Station;
import com.example.deepijaTel.Repositories.Secondary.StationRepository;
import com.example.deepijaTel.Services.ConVoxServices;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/convox")
@SessionAttributes("username")
public class ConVoxControllers {

    private static final Logger logger = LoggerFactory.getLogger(ConVoxControllers.class);

    @Autowired
    private ConVoxServices convoxServices;

    @Autowired
    private StationRepository stationRepository;

    @GetMapping("/login")
    public String showAdminLoginForm(Model model) {
        model.addAttribute("page", "convox_login");
        return "convox_login";
    }

//    @PostMapping("/login")
//    public String login(@RequestParam("username") String username,
//                        @RequestParam("password") String password,
//                        Model model) {
//        logger.info("Attempting to log in with username: {}", username);
//        ConVoxLogin user = convoxServices.findByUsername(username);
//        if (user != null && user.getPassword().equals(password)) {
//            logger.info("Login successful for username: {}", username);
//            model.addAttribute("username", username);
//            return "redirect:/convox/dashboard";
//        } else {
//            logger.warn("Login failed for username: {}", username);
//            model.addAttribute("error", "Invalid username or password! ");
//            return "convox_login";
//        }
//    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model) {
        logger.info("Attempting to log in with username: {}", username);
        ConVoxLogin user = convoxServices.findByUsername(username);

        if (user != null) {
            logger.info("Retrieved user: {} with password: {}", user.getUsername(), user.getPassword());
            if (user.getPassword().equals(password)) {
                logger.info("Login successful for username: {}", username);
                model.addAttribute("username", username);
                return "redirect:/convox/dashboard";
            }
        } else {
            logger.warn("No user found for username: {}", username);
        }
        logger.warn("Login failed for username: {}", username);
        model.addAttribute("error", "Invalid username or password!");
        return "convox_login";
    }


    @PostMapping("/register")
    public String register(@RequestParam("username") String username,
                           @RequestParam("password") String password,
                           Model model) {
        logger.info("Registering new user with username: {}", username);
        ConVoxLogin newUser = new ConVoxLogin();
        newUser.setUsername(username);
        newUser.setPassword(password);
        convoxServices.saveUser(newUser);
        return "redirect:/convox/login";
    }

    @GetMapping("/dashboard")
    public String showDashboard(Model model, HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return "redirect:/convox/login";
        }
        model.addAttribute("username", username);
        model.addAttribute("page", "convox_dashboard");
        model.addAttribute("stations", convoxServices.getAllStations());
        return "convox_dashboard";
    }

    @GetMapping("/logout")
    public String logout(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "redirect:/convox/login";
    }

    // Create a new station
    @PostMapping("/stations")
    public String createStation(@ModelAttribute Station station, Model model) {
        stationRepository.save(station);
        return "redirect:/convox/dashboard";
    }

    // Get all stations
    @GetMapping("/stations")
    public String getAllStations(Model model) {
        List<Station> stations = stationRepository.findAll();
        model.addAttribute("stations", stations);
        return "stations_list";
    }

    // Get a station by ID
    @GetMapping("/stations/{id}")
    public ResponseEntity<Station> getStationById(@PathVariable Long id) {
        Optional<Station> station = stationRepository.findById(id);
        return station.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update a station by ID
    @PostMapping("/stations/{id}")
    public String updateStation(@PathVariable Long id, @ModelAttribute Station stationDetails) {
        Optional<Station> station = stationRepository.findById(id);
        if (station.isPresent()) {
            Station existingStation = station.get();
            existingStation.setStationId(stationDetails.getStationId());
            existingStation.setStationName(stationDetails.getStationName());
            existingStation.setActiveStatus(stationDetails.getActiveStatus());
            stationRepository.save(existingStation);
            return "redirect:/convox/dashboard";
        } else {
            return "redirect:/convox/dashboard"; // Or an error page
        }
    }


    // Delete a station by ID
    @DeleteMapping("/stations/delete/{id}")
    public ResponseEntity<Void> deleteStation(@PathVariable Long id) {
        try {
            stationRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace(); // Print the stack trace for debugging
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}