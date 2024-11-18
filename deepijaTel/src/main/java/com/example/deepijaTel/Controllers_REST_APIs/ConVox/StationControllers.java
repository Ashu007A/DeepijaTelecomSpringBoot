package com.example.deepijaTel.Controllers_REST_APIs.ConVox;

import com.example.deepijaTel.Models.Secondary.Station;
import com.example.deepijaTel.Services.Secondary.StationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/stations")
public class StationControllers {

    @Autowired
    private StationServices stationServices;

    @GetMapping
    public List<Station> getAllStations() {
        return stationServices.getAllStations();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Station> getStationById(@PathVariable Long id) {
        Optional<Station> station = stationServices.getStationById(id);
        return station.isPresent() ? ResponseEntity.ok(station.get()) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Station createStation(@RequestBody Station station) {
        return stationServices.createStation(station);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Station> updateStation(@PathVariable Long id, @RequestBody Station stationDetails) {
        Station updatedStation = stationServices.updateStation(id, stationDetails);
        return ResponseEntity.ok(updatedStation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStation(@PathVariable Long id) {
        stationServices.deleteStation(id);
        return ResponseEntity.noContent().build();
    }
}