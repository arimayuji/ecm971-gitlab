package arimayuji.eletiva.domain.entities;

public class Music {

    private Long id;

    private String name;

    private int review = 0;

    public Music(String name) {
        this.name = name;
    }

    public Music(Long id, String name, int review) {
        this.id = id;
        this.review = review;
        this.name = name;
    }

    public Long getId() {
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
