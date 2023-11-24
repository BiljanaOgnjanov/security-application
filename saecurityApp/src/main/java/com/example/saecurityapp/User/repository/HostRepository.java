package com.example.saecurityapp.User.repository;

import com.example.saecurityapp.User.model.Host;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface HostRepository extends JpaRepository<Host, UUID> {
    Optional<Host> findByEmail(String email);
    Host findFirstByEmail(String email);
}
