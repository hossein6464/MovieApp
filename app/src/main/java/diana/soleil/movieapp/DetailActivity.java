package diana.soleil.movieapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import diana.soleil.movieapp.adapters.CastRecyclerAdapter;
import diana.soleil.movieapp.adapters.MovieRecyclerAdapter;
import diana.soleil.movieapp.db.DBManager;
import diana.soleil.movieapp.db.DatabaseDemo;
import diana.soleil.movieapp.model.Cast;
import diana.soleil.movieapp.model.Genre;
import diana.soleil.movieapp.model.Movie;
import diana.soleil.movieapp.model.MovieFavorite;
import diana.soleil.movieapp.request.Download;
import diana.soleil.movieapp.utilities.Constants;
import diana.soleil.movieapp.utilities.JsonParser;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.DefaultDatabaseErrorHandler;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class DetailActivity extends AppCompatActivity implements CastRecyclerAdapter.OnCastListener, View.OnClickListener {
    Intent intentFromMainActivity;
    Bundle bundleFromMainActivity;
    Serializable serializableFromMainActivity;
    Movie movieFromMainActivity;

    ImageView imageView, emptyHeartImageView;
    TextView titleTextView, descriptionTextView, ratingTextView, releaseDateTextView, languageTextView, genreTextView;

    RecyclerView castRecyclerView;
    CastRecyclerAdapter castRecyclerAdapter;
    ArrayList<Cast> castArrayList;

    DBManager dbManager;
    DatabaseDemo databaseDemo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        dbManager = (DBManager) getApplication();
        databaseDemo = new DatabaseDemo(dbManager);
        initialize();
        try {
            getIntentFromMainActivity();

        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        initializeCasts();
    }
    public void initialize() {
        imageView = findViewById(R.id.movieImageViewId);
        titleTextView = findViewById(R.id.titleTextViewId);
        descriptionTextView = findViewById(R.id.descriptionTextViewId);
        ratingTextView = findViewById(R.id.ratingTextViewId);
        releaseDateTextView = findViewById(R.id.releaseDateTextViewId);
        genreTextView = findViewById(R.id.genreTextViewId);
        languageTextView = findViewById(R.id.languageTextViewId);
        emptyHeartImageView = findViewById(R.id.emptyHeartIconImageViewId);
        emptyHeartImageView.setOnClickListener(this);
        emptyHeartImageView.setColorFilter(Color.BLUE);

    }
    public void getIntentFromMainActivity () throws ExecutionException, InterruptedException {
        intentFromMainActivity = getIntent();
        bundleFromMainActivity = intentFromMainActivity.getBundleExtra(Constants.BUNDLE_KEY_MAINACTIVITY_TO_DETAILACTIVTY);
        serializableFromMainActivity = bundleFromMainActivity.getSerializable(Constants.SERIALIZABLE_KEY_MAINACTIVITY_TO_DETAILACTIVTY);
        movieFromMainActivity = (Movie) serializableFromMainActivity;
        if (checkForMovieInDB(movieFromMainActivity)) {
            emptyHeartImageView.setColorFilter(Color.RED);
        }
        Download download = new Download();
        String movieGenresList = download.execute(Constants.GENRE_PATH).get();
        JsonParser jsonParser = new JsonParser();
        ArrayList<Genre> movieGenresArrayList = jsonParser.processJSONDataOfGenre(movieGenresList);

        ArrayList<Integer> movieGenresFromObject = movieFromMainActivity.getMovieGenre();
        StringBuilder stringBuilderGenres = new StringBuilder("");

        System.out.println(movieGenresArrayList.toString());
        System.out.println(movieGenresFromObject.toString());
        for (int i=0; i<movieGenresFromObject.size(); i++) {
            for(int j=0; j<movieGenresArrayList.size(); j++) {
                if (movieGenresFromObject.get(i) == movieGenresArrayList.get(j).getId()) {
                    stringBuilderGenres.append(movieGenresArrayList.get(j).getName()).append(", ");
                }
            }
        }
        stringBuilderGenres.replace(stringBuilderGenres.length()-2,stringBuilderGenres.length(), "");
        String photo = movieFromMainActivity.getMovieImageWithOutTitle();
        System.out.println(Constants.MOVIE_IMAGE_URL_PATH+photo);
        Picasso.get().load(Constants.MOVIE_IMAGE_URL_PATH+photo).into(imageView);
        titleTextView.setText(movieFromMainActivity.getMovieTitle());
        descriptionTextView.setText(movieFromMainActivity.getMovieDescription());
        genreTextView.setText(stringBuilderGenres);
        ratingTextView.setText(String.valueOf(movieFromMainActivity.getMovieRating()));
        releaseDateTextView.setText(movieFromMainActivity.getMovieReleaseDate());
        languageTextView.setText(movieFromMainActivity.getMovieLanguage());
    }

    private void initializeCasts() {
        castRecyclerView = findViewById(R.id.castRecycleViewId);
        try {
            Download downloadCast = new Download();
           String movieCasts = downloadCast.execute("https://api.themoviedb.org/3/movie/"+String.valueOf(movieFromMainActivity.getMovieId())+"/credits?api_key=4b8d0c081312b5a32416b1315e879cde&language=en-US").get();

            JsonParser jsonParserCasts = new JsonParser();
           castArrayList = jsonParserCasts.processJSONDataOfCast(movieCasts);
          System.out.println(castArrayList.get(0));
        } catch (Exception e) {

        }
        castRecyclerAdapter = new CastRecyclerAdapter(this, castArrayList, this);
        castRecyclerView.setAdapter(castRecyclerAdapter);
        LinearLayoutManager linearLayoutManagerCasts = new LinearLayoutManager(this);
        linearLayoutManagerCasts.setOrientation(LinearLayoutManager.HORIZONTAL);
        castRecyclerView.setLayoutManager(linearLayoutManagerCasts);
    }

    @Override
    public void onCastClick(int position, int parentPosition) {
        Intent intentToSendToDetailCastActivity = new Intent(DetailActivity.this, CastDetailActivity.class);
        Bundle bundleToSendToDetailCastActivity = new Bundle();
                bundleToSendToDetailCastActivity.putSerializable(Constants.SERIALIZABLE_KEY_MAINACTIVITY_TO_DETAILACTIVTY, castArrayList.get(position));
                intentToSendToDetailCastActivity.putExtra(Constants.BUNDLE_KEY_MAINACTIVITY_TO_DETAILACTIVTY, bundleToSendToDetailCastActivity);
                startActivity(intentToSendToDetailCastActivity);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.emptyHeartIconImageViewId) {
            if (checkForMovieInDB(movieFromMainActivity)) {
                createAlertDialogForDeleting(movieFromMainActivity);
            } else {
                createAlertDialogForAdding(movieFromMainActivity);
            }
        }
    }
    public void createAlertDialogForAdding(Movie movie) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Add!");
        alertDialogBuilder.setMessage(" Are You sure you want to add " + movie.getMovieTitle() + " to your favorites?");
        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        }).setPositiveButton("Addd", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                emptyHeartImageView.setColorFilter(Color.RED);
                MovieFavorite movieFavorite = new MovieFavorite(movie.getMovieId(),movie.getMovieTitle(),movie.getMovieImageWithOutTitle(),movie.getMovieDescription(),movie.getMovieLanguage(),movie.getMovieReleaseDate());
                databaseDemo.insertOneDataToDB(movieFavorite);
                Toast.makeText(dbManager,"Movie " +  movie.getMovieTitle() + " successfully got added from DB" , Toast.LENGTH_SHORT).show();
            }
        });
        alertDialogBuilder.create();
        alertDialogBuilder.show();
    }

    public void createAlertDialogForDeleting(Movie movie) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Delete!");
        alertDialogBuilder.setMessage(" Are You sure you want to delete " + movie.getMovieTitle());
        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        }).setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                emptyHeartImageView.setColorFilter(Color.BLUE);
                databaseDemo.deleteFromDB(movie.getMovieId());
                Toast.makeText(dbManager,"Movie " +  movie.getMovieTitle() + " successfully got remove from DB" , Toast.LENGTH_SHORT).show();
            }
        });
        alertDialogBuilder.create();
        alertDialogBuilder.show();
    }

    public boolean checkForMovieInDB (Movie movie) {
        boolean check = false;
        ArrayList<MovieFavorite> favorites = dbManager.cursorToArrayList(databaseDemo.readFromDB());

        if (favorites.size()>0) {
            for (MovieFavorite movieFavorite : favorites) {
                if (movieFavorite.getMovieId() == movie.getMovieId()) {
                    check = true;
                }
            }
        } else {
            return false;
        }
        return  check;
    }
}