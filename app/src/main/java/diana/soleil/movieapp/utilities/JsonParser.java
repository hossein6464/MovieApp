package diana.soleil.movieapp.utilities;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import diana.soleil.movieapp.model.Cast;
import diana.soleil.movieapp.model.Genre;
import diana.soleil.movieapp.model.Movie;
import diana.soleil.movieapp.model.People;

public class JsonParser {
    ArrayList<Movie> movieArrayList = new ArrayList<>();
    ArrayList<Genre> genreArrayList = new ArrayList<>();
    ArrayList<Cast> castArrayList = new ArrayList<>();
    ArrayList<Movie> tvArrayList = new ArrayList<>();
    private InputStreamReader inputStreamReader, genreInputStream, castInputStream;

    public ArrayList<Movie> processJSONData(String data){

        try {
            JSONObject jsonObject = new JSONObject(data);

            JSONArray jsonArray = new JSONArray(jsonObject.getString(Constants.MOVIE_RESULTS));

            for (int i=0; i < jsonArray.length(); i++){

                JSONObject currentJSONMovieObject = jsonArray.getJSONObject(i);

                int movieId                 = currentJSONMovieObject.getInt(Constants.MOVIE_ID);
                String movieTitle                 = currentJSONMovieObject.getString(Constants.MOVIE_TITLE);
                double movieRating                = currentJSONMovieObject.getDouble(Constants.MOVIE_RATING);
                String moviePhotoWithOutTitle     = currentJSONMovieObject.getString(Constants.MOVIE_IMAGE_WITHOUT_TITLE);
                String moviePhotoWithTitle        = currentJSONMovieObject.getString(Constants.MOVIE_IMAGE_WITH_TITLE);
                String movieDescription                 = currentJSONMovieObject.getString(Constants.MOVIE_DESCRIPTION);
                JSONArray jsonArrayOfGenreIds                 = currentJSONMovieObject.getJSONArray(Constants.MOVIE_GENRE_IDS);
                String movieLanguage                 = currentJSONMovieObject.getString(Constants.MOVIE_LANGUAGE);
                String movieReleaseDate                 = currentJSONMovieObject.getString(Constants.MOVIE_RELEASE_DATE);

                ArrayList<Integer> arrayListOfGenreIds = new ArrayList<>();
                for(int j=0; j < jsonArrayOfGenreIds.length(); j++) {
                    int genreId = jsonArrayOfGenreIds.getInt(j);
                    arrayListOfGenreIds.add(genreId);
                }
                Movie currentMovie = new Movie (movieId,movieTitle, moviePhotoWithTitle, moviePhotoWithOutTitle,movieRating,arrayListOfGenreIds,movieDescription,movieLanguage,movieReleaseDate);
                movieArrayList.add(currentMovie);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return movieArrayList;
    }

    public ArrayList<Genre> processJSONDataOfGenre(String data){

        try {
            JSONObject jsonObject = new JSONObject(data);

            JSONArray jsonArray = new JSONArray(jsonObject.getString(Constants.GENRE_RESULTS));

            for (int i=0; i < jsonArray.length(); i++){

                JSONObject currentJSONGenreObject = jsonArray.getJSONObject(i);

                int genreId                 = currentJSONGenreObject.getInt(Constants.GENRE_ID);
                String genreName                 = currentJSONGenreObject.getString(Constants.GENRE_NAME);

                Genre currentGenre = new Genre (genreId,genreName);
                genreArrayList.add(currentGenre);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return genreArrayList;
    }
    public ArrayList<Cast> processJSONDataOfCast(String data){

        try {
            JSONObject jsonObject = new JSONObject(data);
            JSONArray jsonArray = new JSONArray(jsonObject.getString(Constants.CAST));
            for (int i=0; i < jsonArray.length(); i++){
                JSONObject currentJSONCastObject = jsonArray.getJSONObject(i);

                int castId                 = currentJSONCastObject.getInt(Constants.CAST_ID);
                String castName                 = currentJSONCastObject.getString(Constants.CAST_NAME);
                String castCharacter                 = currentJSONCastObject.getString(Constants.CAST_CHARACTER);
                String castRating                 = currentJSONCastObject.getString(Constants.CAST_RATING);
                String profileImage                 = currentJSONCastObject.getString(Constants.CAST_PROFILE_IMAGE);
                Cast currentCast = new Cast (castId,castName,profileImage,castCharacter,castRating);
                castArrayList.add(currentCast);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return castArrayList;
    }

    public People processJSONDataOfPeople(String data){
        People currentPerson = null;
        try {
            JSONObject jsonObject = new JSONObject(data);
                int peopleId                 = jsonObject.getInt(Constants.PEOPLE_ID);
                String peopleName                 = jsonObject.getString(Constants.PEOPLE_NAME);
                String peopleBiography                 = jsonObject.getString(Constants.PEOPLE_BIOGRAPHY);
                int peopleRating                 = jsonObject.getInt(Constants.PEOPLE_RATING);
                String profileImage                 = jsonObject.getString(Constants.CAST_PROFILE_IMAGE);
                String peopleBirthdate = jsonObject.getString(Constants.PEOPLE_BIRTHDATE);
                String peoplePlaceOfBirth = jsonObject.getString(Constants.PEOPLE_PLACE_OF_BIRTH);
                JSONArray knownBy = jsonObject.getJSONArray(Constants.PEOPLE_KNOWN_FOR);
                ArrayList<String> knownByArrayList = new ArrayList<>();
                for (int i=0; i<knownBy.length(); i++ ) {
                    knownByArrayList.add(knownBy.get(i).toString());
                }
            currentPerson = new People (peopleId, peopleName, peopleBiography,peopleBirthdate, peoplePlaceOfBirth, peopleRating, profileImage, knownByArrayList );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return currentPerson;
    }
    public ArrayList<Movie> processJSONDataTV(String data){

        try {
            JSONObject jsonObject = new JSONObject(data);

            JSONArray jsonArray = new JSONArray(jsonObject.getString(Constants.MOVIE_RESULTS));

            for (int i=0; i < jsonArray.length(); i++){

                JSONObject currentJSONTVObject = jsonArray.getJSONObject(i);

                int tvId                 = currentJSONTVObject.getInt(Constants.TV_ID);
                String tvTitle                 = currentJSONTVObject.getString(Constants.TV_TITLE);
                double tvRating                = currentJSONTVObject.getDouble(Constants.TV_RATING);
                String tvPhotoWithOutTitle     = currentJSONTVObject.getString(Constants.TV_IMAGE_WITHOUT_TITLE);
                String tvPhotoWithTitle        = currentJSONTVObject.getString(Constants.TV_IMAGE_WITH_TITLE);
                String tvDescription                 = currentJSONTVObject.getString(Constants.TV_DESCRIPTION);
                JSONArray jsonArrayOfGenreIds                 = currentJSONTVObject.getJSONArray(Constants.TV_GENRE_IDS);
                String tvLanguage                 = currentJSONTVObject.getString(Constants.TV_Language);
                String tvReleaseDate                 = currentJSONTVObject.getString(Constants.TV_RELEASE_DATE);

                ArrayList<Integer> arrayListOfGenreIds = new ArrayList<>();
                for(int j=0; j < jsonArrayOfGenreIds.length(); j++) {
                    int genreId = jsonArrayOfGenreIds.getInt(j);
                    arrayListOfGenreIds.add(genreId);
                }
                Movie currentTV = new Movie (tvId,tvTitle,tvPhotoWithTitle,tvPhotoWithOutTitle,tvRating,arrayListOfGenreIds,tvDescription,tvLanguage,tvReleaseDate);
                Log.d("TV", "processJSONDataTV: " + currentTV);
                tvArrayList.add(currentTV);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tvArrayList;
    }
}
