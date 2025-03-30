package arimayuji.eletiva.infra.repositories.jpa;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import arimayuji.eletiva.infra.entities.JpaMusic;

public interface SpringDataMusicRepository extends JpaRepository<JpaMusic, UUID> {

    Optional<JpaMusic> findByName(String name);

}
