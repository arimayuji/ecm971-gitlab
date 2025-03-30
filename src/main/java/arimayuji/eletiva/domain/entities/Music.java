package arimayuji.eletiva.domain.entities;

import java.util.UUID;

public class Music {

    private UUID id;

    private String name;

    private int review = 0;

    public Music(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getReview() {
        return review;
    }

    public void setReview(int review) {
        this.review = review;
    }

}
