package arimayuji.eletiva.infra.entities;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "music")
public class JpaMusic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Min(0)
    @Max(5)
    @Column(name = "review", nullable = false)
    private int review = 0;

    public JpaMusic() {
    }

    public JpaMusic(String name) {
        this.name = name;
    }

}
