package com.example.deepijaTel.Repositories.Secondary;

import com.example.deepijaTel.Models.Secondary.Server;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServerRepository extends JpaRepository<Server, Long> {
    Optional<Server> findByServerId(String serverId);
}
