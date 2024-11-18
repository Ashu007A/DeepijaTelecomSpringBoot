package com.example.deepijaTel.Services.Secondary.Implementation;

import com.example.deepijaTel.Models.Secondary.Station;
import com.example.deepijaTel.Repositories.Secondary.StationRepository;
import com.example.deepijaTel.Services.Secondary.StationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StationServicesImpl implements StationServices {

    @Autowired
    private StationRepository stationRepository;

    @Override
    public Station createStation(Station station) {
        return stationRepository.save(station);
    }

    @Override
    public List<Station> getAllStations() {
        return stationRepository.findAll();
    }

    @Override
    public Optional<Station> getStationById(Long id) {
        return stationRepository.findById(id);
    }

    @Override
    public Optional<Station> getStationByStationId(String stationId) { return stationRepository.findByStationId(stationId); }

    @Override
    public Station updateStation(Long id, Station stationDetails) {
        Optional<Station> optionalStation = stationRepository.findById(id);
        if (optionalStation.isPresent()) {
            Station station = optionalStation.get();
            station.setStationId(stationDetails.getStationId());
            station.setStationName(stationDetails.getStationName());
            station.setActiveStatus(stationDetails.getActiveStatus());
            return stationRepository.save(station);
        } else {
            throw new RuntimeException("Station not found");
        }
    }

    @Override
    public void deleteStation(Long id) {
        stationRepository.findById(id).ifPresent(stationRepository::delete);
    }
}