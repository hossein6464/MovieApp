package diana.soleil.movieapp.model;

import java.io.Serializable;

public class Cast implements Serializable {
    private int id;
    private String name;
    private String profile_image;
    private String character;
    private String rating;

    public Cast(int id, String name, String profile_image, String character, String rating) {
        this.id = id;
        this.name = name;
        this.profile_image = profile_image;
        this.character = character;
        this.rating = rating;
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

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Cast{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", profile_image='" + profile_image + '\'' +
                ", character='" + character + '\'' +
                ", rating='" + rating + '\'' +
                '}';
    }
}
