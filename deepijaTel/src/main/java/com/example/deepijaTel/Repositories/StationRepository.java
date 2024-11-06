package com.example.deepijaTel.Repositories;

import com.example.deepijaTel.Models.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationRepository extends JpaRepository<Station, Long> {

}
