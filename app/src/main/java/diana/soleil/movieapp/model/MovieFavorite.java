package diana.soleil.movieapp.model;

import java.io.Serializable;
import java.util.ArrayList;

public class MovieFavorite implements Serializable {

    private int movieId;
    private String movieTitle;
    private String movieImageWithTitle;
    private String movieDescription;
    private String movieLanguage;
    private String movieReleaseDate;

    public MovieFavorite() {
    }

    public MovieFavorite(int movieId, String movieTitle, String movieImageWithTitle, String movieDescription, String movieLanguage, String movieReleaseDate) {
        this.movieId = movieId;
        this.movieTitle = movieTitle;
        this.movieImageWithTitle = movieImageWithTitle;
        this.movieDescription = movieDescription;
        this.movieLanguage = movieLanguage;
        this.movieReleaseDate = movieReleaseDate;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getMovieImageWithTitle() {
        return movieImageWithTitle;
    }

    public void setMovieImageWithTitle(String movieImageWithTitle) {
        this.movieImageWithTitle = movieImageWithTitle;
    }

    public String getMovieDescription() {
        return movieDescription;
    }

    public void setMovieDescription(String movieDescription) {
        this.movieDescription = movieDescription;
    }

    public String getMovieLanguage() {
        return movieLanguage;
    }

    public void setMovieLanguage(String movieLanguage) {
        this.movieLanguage = movieLanguage;
    }

    public String getMovieReleaseDate() {
        return movieReleaseDate;
    }

    public void setMovieReleaseDate(String movieReleaseDate) {
        this.movieReleaseDate = movieReleaseDate;
    }

    @Override
    public String toString() {
        return "MovieFavorite{" +
                "movieId=" + movieId +
                ", movieTitle='" + movieTitle + '\'' +
                ", movieImageWithTitle='" + movieImageWithTitle + '\'' +
                ", movieDescription='" + movieDescription + '\'' +
                ", movieLanguage='" + movieLanguage + '\'' +
                ", movieReleaseDate='" + movieReleaseDate + '\'' +
                '}';
    }
}
