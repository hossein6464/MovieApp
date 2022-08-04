package diana.soleil.movieapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.utils.widget.ImageFilterView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import diana.soleil.movieapp.adapters.MovieRecyclerAdapter;
import diana.soleil.movieapp.db.DBManager;
import diana.soleil.movieapp.db.DatabaseDemo;
import diana.soleil.movieapp.db.SQLCommands;
import diana.soleil.movieapp.model.Movie;
import diana.soleil.movieapp.model.MovieFavorite;
import diana.soleil.movieapp.request.Download;
import diana.soleil.movieapp.utilities.Constants;
import diana.soleil.movieapp.utilities.JsonParser;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class FavoriteActivity extends AppCompatActivity implements View.OnClickListener, MovieRecyclerAdapter.OnMovieListener {
    ArrayList<MovieFavorite> favoriteMovieArrayList, favoriteMovieArrayListFromDB;
    RecyclerView favoriteMoviesRecyclerView;
    MovieRecyclerAdapter favoriteMoviesAdapter;
    LinearLayout searchLinearLayout, homeLinearLayout;
    TextView favoriteTextView;
    ImageView favoriteImageView;
    DBManager dbManager;
    Movie movie;
    ArrayList<Movie> movieArrayList;
    // Database .....................
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        initialize();
        databaseDemo();
        searchMovies();
    }

    private void initialize() {
        favoriteMoviesRecyclerView = findViewById(R.id.searchRecycleViewId);
        homeLinearLayout = findViewById(R.id.homeLinearLayoutId);
        homeLinearLayout.setOnClickListener(this);
        searchLinearLayout = findViewById(R.id.searchLinearLayoutId);
        searchLinearLayout.setOnClickListener(this);
        favoriteImageView = findViewById(R.id.favortieImageViewId);
        favoriteTextView = findViewById(R.id.favoriteTextViewId);
        favoriteImageView.setColorFilter(Color.WHITE);
        favoriteTextView.setTextColor(Color.WHITE);
    }

    public void databaseDemo() {
        dbManager = (DBManager) getApplication();
        favoriteMovieArrayList = new ArrayList<>();
        DatabaseDemo databaseDemo = new DatabaseDemo(dbManager);
        favoriteMovieArrayListFromDB = dbManager.cursorToArrayList(databaseDemo.readFromDB());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.homeLinearLayoutId:
                Intent intent = new Intent(FavoriteActivity.this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.searchLinearLayoutId:
                Intent intentFavorite = new Intent(FavoriteActivity.this, SearchActivity.class);
                startActivity(intentFavorite);
                break;
        }
    }

    public void searchMovies() {
        movieArrayList = new ArrayList<>();
        for(MovieFavorite favorite: favoriteMovieArrayListFromDB) {
            movie = new Movie(favorite.getMovieId(),favorite.getMovieTitle(),favorite.getMovieImageWithTitle(),favorite.getMovieDescription(),favorite.getMovieLanguage(),favorite.getMovieReleaseDate());
            movieArrayList.add(movie);
        }
        favoriteMoviesAdapter = new MovieRecyclerAdapter(this, movieArrayList, this);
        favoriteMoviesRecyclerView.setAdapter(favoriteMoviesAdapter);
        favoriteMoviesRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
    }

    @Override
    public void onMovieClick(int position, int parentPosition) {
        Toast.makeText(dbManager, movieArrayList.get(position).getMovieTitle(), Toast.LENGTH_SHORT).show();
//        Intent intentToSendToDetailActivity = new Intent(FavoriteActivity.this, DetailActivity.class);
//        Bundle bundleToSendToDetailActivity = new Bundle();
//        bundleToSendToDetailActivity.putSerializable(Constants.SERIALIZABLE_KEY_MAINACTIVITY_TO_DETAILACTIVTY, movieArrayList.get(position));
//        intentToSendToDetailActivity.putExtra(Constants.BUNDLE_KEY_MAINACTIVITY_TO_DETAILACTIVTY, bundleToSendToDetailActivity);
//        startActivity(intentToSendToDetailActivity);
    }
}