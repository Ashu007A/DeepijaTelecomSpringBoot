package com.example.deepijaTel.Controllers.ConVox_Controllers;

import com.example.deepijaTel.Models.Secondary.Station;
import com.example.deepijaTel.Services.Secondary.StationServices;
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
public class StationController {

    private static final Logger logger = LoggerFactory.getLogger(StationController.class);

    @Autowired
    private StationServices stationServices;

    // Show stations page
    @GetMapping("/stations")
    public String showStations(Model model, HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return "redirect:/convox/login";
        }
        List<Station> stations = stationServices.getAllStations();
        model.addAttribute("stations", stations);
        model.addAttribute("page", "stations");
        return "ConVox/stations";
    }

    // Create a new station
    @PostMapping("/stations")
    public String createStation(@ModelAttribute Station station, Model model) {
        stationServices.createStation(station);
        return "redirect:/convox/stations"; // Stay on the same page after creation
    }

    // Get all stations
    @GetMapping("/all-stations")
    public String getAllStations(Model model) {
        List<Station> stations = stationServices.getAllStations();
        model.addAttribute("stations", stations);
        return "stations_list";
    }

    // Get a station by ID
    @GetMapping("/stations/{id}")
    public ResponseEntity<Station> getStationById(@PathVariable Long id) {
        Optional<Station> station = stationServices.getStationById(id);
        return station.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update a station by ID
    @PostMapping("/stations/{id}")
    public String updateStation(@PathVariable Long id, @ModelAttribute Station stationDetails) {
        stationServices.updateStation(id, stationDetails);
        return "redirect:/convox/stations";
    }

    // Delete a station by ID
    @DeleteMapping("/stations/delete/{id}")
    public ResponseEntity<Void> deleteStation(@PathVariable Long id) {
        try {
            stationServices.deleteStation(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}