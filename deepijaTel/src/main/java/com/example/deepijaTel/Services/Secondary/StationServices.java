package com.example.deepijaTel.Services.Secondary;

import com.example.deepijaTel.Models.Secondary.Station;

import java.util.List;
import java.util.Optional;

public interface StationServices {
    Station createStation(Station station);
    List<Station> getAllStations();
    Optional<Station> getStationById(Long id);
    Optional<Station> getStationByStationId(String stationId);
    Station updateStation(Long id, Station stationDetails);
    void deleteStation(Long id);
}