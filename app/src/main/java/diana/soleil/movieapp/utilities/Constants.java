package diana.soleil.movieapp.utilities;

public class Constants {

    // API Info
    public static final String API_KEY = "4b8d0c081312b5a32416b1315e879cde";
    public static final String BASE_URL = "https://api.themoviedb.org/";
    // Image Path https://api.themoviedb.org/3/movie/338953/images?api_key=4b8d0c081312b5a32416b1315e879cde&language=en-US
    // Example https://image.tmdb.org/t/p/w500//jrgifaYeUtTnaH7NF5Drkgjg2MB.jpg
    public static final String MOVIE_IMAGE_URL_PATH = "https://image.tmdb.org/t/p/w500";
    // Latest https://api.themoviedb.org/3/tv/latest?api_key=4b8d0c081312b5a32416b1315e879cde&language=en-US

    //////// Original's To Be Used
    public static final String ACTUAL_BASE_URL = "https://api.themoviedb.org/3/";
    public static final String UPCOMING_MOVIES = "movie/upcoming?";
    public static final String UPCOMING_MOVIES_EXTENSION = "&language=en-US&page=1";
    public static final String ACTUAL_API_KEY = "api_key=4b8d0c081312b5a32416b1315e879cde";

    // Genre Path
    // https://api.themoviedb.org/3/genre/movie/list?api_key=4b8d0c081312b5a32416b1315e879cde&language=en-US
    public static final String GENRE_PATH = "https://api.themoviedb.org/3/genre/movie/list?api_key=4b8d0c081312b5a32416b1315e879cde&language=en-US";
    //https://api.themoviedb.org/3/movie/upcoming?api_key=<<api_key>>&language=en-US&page=1
    // API JSON Object Elements
    public static final String MOVIE_RESULTS = "results";
    public static final String GENRE_RESULTS = "genres";

    // Genre Section
    public static final String GENRE_ID = "id";
    public static final String GENRE_NAME = "name";

    // Movies
    public static final String MOVIE_ID = "id";
    public static final String MOVIE_TITLE = "title";
    public static final String MOVIE_RATING = "vote_average";
    public static final String MOVIE_IMAGE = "poster_path";
    public static final String MOVIE_DESCRIPTION = "overview";
    public static final String MOVIE_RELEASE_DATE = "release_date";
    public static final String MOVIE_GENRE_IDS = "genre_ids";
    public static final String MOVIE_IMAGE_WITH_TITLE = "backdrop_path";
    public static final String MOVIE_IMAGE_WITHOUT_TITLE = "poster_path";
    public static final String MOVIE_LANGUAGE = "original_language";

    // Search URL
    // https://api.themoviedb.org/3/search/movie?api_key=4b8d0c081312b5a32416b1315e879cde&language=en-US&query=jack&page=1&include_adult=false
    public static final String SEARCH_EXTENSION1 = "https://api.themoviedb.org/3/search/movie?api_key=4b8d0c081312b5a32416b1315e879cde&language=en-US&query=";
    public static final String SEARCH_EXTENSION2 = "&page=1&include_adult=false";
    // TV
    public static final String TV_POPULAR_URL = "tv/popular?";
    public static final String ACTUAL_TV_POPULAR_URL = ACTUAL_BASE_URL+TV_POPULAR_URL+ACTUAL_API_KEY+UPCOMING_MOVIES_EXTENSION;
    public static final String TV_RESULTS = "results";
    public static final String TV_ID = "id";
    public static final String TV_TITLE = "name";
    public static final String TV_RATING = "vote_average";
    public static final String TV_IMAGE = "poster_path";
    public static final String TV_DESCRIPTION = "overview";
    public static final String TV_RELEASE_DATE = "first_air_date";
    public static final String TV_GENRE_IDS = "genre_ids";
    public static final String TV_IMAGE_WITH_TITLE = "backdrop_path";
    public static final String TV_IMAGE_WITHOUT_TITLE = "poster_path";
    public static final String TV_Language = "original_language";

    // People url
    public static final String PEOPLE_URL = ACTUAL_BASE_URL+"person/popular?"+ACTUAL_API_KEY+UPCOMING_MOVIES_EXTENSION;
   // People Model Class
    public static final String PEOPLE_RESULTS = "results";
    public static final String PEOPLE_ID = "id";
    public static final String PEOPLE_NAME = "name";
    public static final String PEOPLE_RATING = "popularity";
    public static final String PEOPLE_IMAGE = "profile_path";
    public static final String PEOPLE_KNOWN_FOR = "also_known_as";
    public static final String PEOPLE_BIOGRAPHY = "biography";
    public static final String PEOPLE_PLACE_OF_BIRTH = "place_of_birth";
    public static final String PEOPLE_BIRTHDATE = "birthday";

    // People URL Two Section
    public static final String PEOPLE_URL_PART1 = ACTUAL_BASE_URL+"person/";
    public static final String PEOPLE_URL_PART2 ="?"+ACTUAL_API_KEY+UPCOMING_MOVIES_EXTENSION;
    // CAST url
    public static final String CAST_URL_PART1 = ACTUAL_BASE_URL+"movie/";
    public static final String CAST_URL_PART2 ="/credits?"+ACTUAL_API_KEY+UPCOMING_MOVIES_EXTENSION;

    // CAST Model Class
    public static final String CAST = "cast";
    public static final String CAST_ID = "id";
    public static final String CAST_NAME = "name";
    public static final String CAST_CHARACTER = "character";
    public static final String CAST_PROFILE_IMAGE = "profile_path";
    public static final String CAST_RATING = "popularity";

    ///// Intent Constants
    public static final String BUNDLE_KEY_MAINACTIVITY_TO_DETAILACTIVTY = "bundleToDetail";
    public static final String SERIALIZABLE_KEY_MAINACTIVITY_TO_DETAILACTIVTY = "serializableToDetail";
}
