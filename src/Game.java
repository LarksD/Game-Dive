public class Game {
    private String title;
    private double rating;
    private String description;
    private String releaseYear;
    private String image;

    public Game(String title, double rating, String description, String releaseYear, String image) {
        this.title = title;
        this.rating = rating;
        this.description = description;
        this.releaseYear = releaseYear;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReleaseDate() {
        return releaseYear;
    }

    public String getReleaseYear() {
        return releaseYear.substring(0, 4);
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Game{" +
                "title='" + title + '\'' +
                ", rating='" + rating + '\'' +
                ", description='" + description + '\'' +
                ", releaseYear=" + releaseYear +
                ", image='" + image + '\'' +
                '}';
    }
}
