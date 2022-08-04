package diana.soleil.movieapp.model;

import java.io.Serializable;
import java.util.ArrayList;

public class People implements Serializable {
    private int id;
    private String name;
    private String biography;
    private String birthdate;
    private String placeOfBirth;
    private int rating;
    private String profileImage;
    private ArrayList<String> knownBy;

    public People(int id, String name, String biography, String birthdate, String placeOfBirth, int rating, String profileImage, ArrayList<String> knownBy) {
        this.id = id;
        this.name = name;
        this.biography = biography;
        this.birthdate = birthdate;
        this.placeOfBirth = placeOfBirth;
        this.rating = rating;
        this.profileImage = profileImage;
        this.knownBy = knownBy;
    }

    public ArrayList<String> getKnownBy() {
        return knownBy;
    }

    public void setKnownBy(ArrayList<String> knownBy) {
        this.knownBy = knownBy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    @Override
    public String toString() {
        return "People{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", biography='" + biography + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", placeOfBirth='" + placeOfBirth + '\'' +
                ", rating=" + rating +
                ", profileImage='" + profileImage + '\'' +
                ", knownBy=" + knownBy +
                '}';
    }
}
