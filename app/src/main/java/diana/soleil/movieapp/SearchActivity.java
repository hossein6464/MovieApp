package diana.soleil.movieapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.utils.widget.ImageFilterView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import diana.soleil.movieapp.adapters.MoviePopularMoviesCustomAdapter;
import diana.soleil.movieapp.adapters.MovieRecyclerAdapter;
import diana.soleil.movieapp.model.Movie;
import diana.soleil.movieapp.request.Download;
import diana.soleil.movieapp.utilities.Constants;
import diana.soleil.movieapp.utilities.JsonParser;

import android.content.Intent;
import android.graphics.Color;
import android.icu.lang.UCharacter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity implements MovieRecyclerAdapter.OnMovieListener, View.OnClickListener {
    ArrayList<Movie> searchedMovieArrayList;
    RecyclerView searchMoviesRecyclerView;
    MovieRecyclerAdapter searchedMoviesAdapter;
    ImageFilterView searchButton;
    EditText searchEditText;
    LinearLayout homeLinearLayout, favoriteLinearLayout;
    TextView searchTextView;
    ImageView searchImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initialize();
    }

    private void initialize() {
        //customListView = findViewById(R.id.movieListViewId);
        searchMoviesRecyclerView = findViewById(R.id.searchRecycleViewId);
        favoriteLinearLayout = findViewById(R.id.favoriteLinearLayoutId);
        favoriteLinearLayout.setOnClickListener(this);
        homeLinearLayout = findViewById(R.id.homeLinearLayoutId);
        homeLinearLayout.setOnClickListener(this);
        searchEditText = findViewById(R.id.searchEditTextViewId);
        searchButton = findViewById(R.id.searchButtonId);
        searchButton.setOnClickListener(this);
        searchImageView = findViewById(R.id.searchImageViewId);
        searchTextView = findViewById(R.id.searchTextViewId);
        searchImageView.setColorFilter(Color.WHITE);
        searchTextView.setTextColor(Color.WHITE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.searchButtonId:
                searchMovies();
                break;
            case R.id.homeLinearLayoutId:
                Intent intent = new Intent(SearchActivity.this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.favoriteLinearLayoutId:
                Intent intentFavorite = new Intent(SearchActivity.this, FavoriteActivity.class);
                startActivity(intentFavorite);
                break;
        }
    }

    @Override
    public void onMovieClick(int position, int parentPosition) {
        Intent intentToSendToDetailActivity = new Intent(SearchActivity.this, DetailActivity.class);
        Bundle bundleToSendToDetailActivity = new Bundle();
        bundleToSendToDetailActivity.putSerializable(Constants.SERIALIZABLE_KEY_MAINACTIVITY_TO_DETAILACTIVTY, searchedMovieArrayList.get(position));
        intentToSendToDetailActivity.putExtra(Constants.BUNDLE_KEY_MAINACTIVITY_TO_DETAILACTIVTY, bundleToSendToDetailActivity);
        startActivity(intentToSendToDetailActivity);
    }

    public void searchMovies() {
        String query = searchEditText.getText().toString();
        try {
            Download downloadSearchedMovies = new Download();
            String searchMovies = downloadSearchedMovies.execute(Constants.SEARCH_EXTENSION1+query+Constants.SEARCH_EXTENSION2).get();
            JsonParser jsonParserSearchedMovies = new JsonParser();
            searchedMovieArrayList = jsonParserSearchedMovies.processJSONData(searchMovies);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        searchedMoviesAdapter = new MovieRecyclerAdapter(this, searchedMovieArrayList, this);
        searchMoviesRecyclerView.setAdapter(searchedMoviesAdapter);
        searchMoviesRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
    }
}