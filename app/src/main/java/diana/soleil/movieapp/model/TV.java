package diana.soleil.movieapp.model;

import java.io.Serializable;
import java.util.ArrayList;

public class TV implements Serializable {
    private int tvId;
    private String tvName;
    private String tvImageWithTitle;
    private String tvImageWithOutTitle;
    private double tvRating;
    private ArrayList<Integer> tvGenre;
    private String tvDescription;
    private String tvLanguage;
    private String tvReleaseDate;
}
