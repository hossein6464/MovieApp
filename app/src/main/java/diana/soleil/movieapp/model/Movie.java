package diana.soleil.movieapp.model;

import android.widget.ArrayAdapter;

import java.io.Serializable;
import java.lang.ref.PhantomReference;
import java.util.ArrayList;

public class Movie implements Serializable {
    private int movieId;
    private String movieTitle;
    private String movieImageWithTitle;
    private String movieImageWithOutTitle;
    private double movieRating;
    private ArrayList<Integer> movieGenre;
    private String movieDescription;
    private String movieLanguage;
    private String movieReleaseDate;

    public Movie() {
    }

    public Movie(int movieId, String movieTitle, String movieImageWithOutTitle, String movieDescription, String movieLanguage, String movieReleaseDate) {
        this.movieId = movieId;
        this.movieTitle = movieTitle;
        this.movieImageWithOutTitle = movieImageWithOutTitle;
        this.movieDescription = movieDescription;
        this.movieLanguage = movieLanguage;
        this.movieReleaseDate = movieReleaseDate;
    }

    public Movie(int movieId, String movieTitle, String movieImageWithTitle, String movieImageWithOutTitle, double movieRating, String movieDescription, String movieLanguage, String movieReleaseDate) {
        this.movieId = movieId;
        this.movieTitle = movieTitle;
        this.movieImageWithTitle = movieImageWithTitle;
        this.movieImageWithOutTitle = movieImageWithOutTitle;
        this.movieRating = movieRating;
        this.movieDescription = movieDescription;
        this.movieLanguage = movieLanguage;
        this.movieReleaseDate = movieReleaseDate;
    }

    public Movie(int movieId, String movieTitle, String movieImageWithTitle, String movieImageWithOutTitle, double movieRating, ArrayList<Integer> movieGenre, String movieDescription, String movieLanguage, String movieReleaseDate) {
        this.movieId = movieId;
        this.movieTitle = movieTitle;
        this.movieImageWithTitle = movieImageWithTitle;
        this.movieImageWithOutTitle = movieImageWithOutTitle;
        this.movieRating = movieRating;
        this.movieGenre = movieGenre;
        this.movieDescription = movieDescription;
        this.movieLanguage = movieLanguage;
        this.movieReleaseDate = movieReleaseDate;
    }

    public Movie(String movieTitle, String movieImageWithTitle, double movieRating) {
        this.movieTitle = movieTitle;
        this.movieImageWithTitle = movieImageWithTitle;
        this.movieRating = movieRating;
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

    public String getMovieImageWithOutTitle() {
        return movieImageWithOutTitle;
    }

    public void setMovieImageWithOutTitle(String movieImageWithOutTitle) {
        this.movieImageWithOutTitle = movieImageWithOutTitle;
    }

    public double getMovieRating() {
        return movieRating;
    }

    public void setMovieRating(double movieRating) {
        this.movieRating = movieRating;
    }

    public ArrayList<Integer> getMovieGenre() {
        return movieGenre;
    }

    public void setMovieGenre(ArrayList<Integer> movieGenre) {
        this.movieGenre = movieGenre;
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
        return "Movie{" +
                "movieId=" + movieId +
                ", movieTitle='" + movieTitle + '\'' +
                ", movieImageWithTitle='" + movieImageWithTitle + '\'' +
                ", movieImageWithOutTitle='" + movieImageWithOutTitle + '\'' +
                ", movieRating=" + movieRating +
                ", movieGenre=" + movieGenre +
                ", movieDescription='" + movieDescription + '\'' +
                ", movieLanguage='" + movieLanguage + '\'' +
                ", movieReleaseDate='" + movieReleaseDate + '\'' +
                '}';
    }
}
