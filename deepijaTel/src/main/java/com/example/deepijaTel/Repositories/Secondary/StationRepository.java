package com.example.deepijaTel.Repositories.Secondary;

import com.example.deepijaTel.Models.Secondary.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationRepository extends JpaRepository<Station, Long> {

}