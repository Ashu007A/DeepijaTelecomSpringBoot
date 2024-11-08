package com.example.deepijaTel.Services;

import com.example.deepijaTel.Models.Secondary.ConVoxLogin;
import com.example.deepijaTel.Models.Secondary.Station;

import java.util.List;
import java.util.Optional;

public interface ConVoxServices {
    ConVoxLogin findByUsername(String username);
    ConVoxLogin saveUser(ConVoxLogin user);

    // Station operations
    Station createStation(Station station);
    List<Station> getAllStations();
    Optional<Station> getStationById(Long id);
    Station updateStation(Long id, Station stationDetails);
    void deleteStation(Long id);
}