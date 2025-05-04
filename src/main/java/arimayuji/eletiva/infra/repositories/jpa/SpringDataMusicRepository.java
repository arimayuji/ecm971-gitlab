package arimayuji.eletiva.infra.repositories.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import arimayuji.eletiva.infra.entities.JpaMusic;

public interface SpringDataMusicRepository extends JpaRepository<JpaMusic, Long> {

    Optional<JpaMusic> findByName(String name);

    List<JpaMusic> findAllByOrderByReviewDescNameAsc();

    Optional<JpaMusic> findByNameIgnoreCase(String name);

}
