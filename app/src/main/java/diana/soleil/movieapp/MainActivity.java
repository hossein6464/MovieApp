package diana.soleil.movieapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import diana.soleil.movieapp.adapters.MovieRecyclerAdapter;
import diana.soleil.movieapp.model.Movie;
import diana.soleil.movieapp.request.Download;
import diana.soleil.movieapp.utilities.Constants;
import diana.soleil.movieapp.utilities.JsonParser;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MovieRecyclerAdapter.OnMovieListener, View.OnClickListener {
    ArrayList<Movie> popularMovieArrayList, upcomingMovieArrayList, popularTVArrayList;
    RecyclerView popularMoviesRecyclerView, upcomingMoviesRecyclerView, popularTVRecyclerView;
    MovieRecyclerAdapter popularMoviesRecyclerAdapter, upcomingMoviesRecyclerAdapter, popularTVRecyclerAdapter;

    LinearLayout searchLinearLayout, favoriteLinearLayout;
    TextView homeTextView;
    ImageView homeImageView;

    final static int REQUEST_CODE1 = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
        initializeLinearLayout();
    }

    private void initialize() {
        popularMoviesRecyclerView = findViewById(R.id.popularMovieRecyclerViewId);
        upcomingMoviesRecyclerView = findViewById(R.id.upcomingMovieRecyclerViewId);
        popularTVRecyclerView = findViewById(R.id.popularTVRecyclerViewId);

        try {
            Download downloadPopularMovies = new Download();
            Download downloadUpcomingMovies = new Download();
            Download downloadPopularTVs = new Download();
           String popularMovies = downloadPopularMovies.execute("https://api.themoviedb.org/3/movie/popular?api_key=4b8d0c081312b5a32416b1315e879cde").get();
           String upcomingMovies = downloadUpcomingMovies.execute(Constants.ACTUAL_BASE_URL+Constants.UPCOMING_MOVIES+Constants.ACTUAL_API_KEY+Constants.UPCOMING_MOVIES_EXTENSION).get();

           String popularTVs = downloadPopularTVs.execute(Constants.ACTUAL_TV_POPULAR_URL).get();
            Log.d("TaG", "initialize: "+ popularTVs);
            JsonParser jsonParserPopularMovies = new JsonParser();
            JsonParser jsonParserUpcomingMovies = new JsonParser();
            JsonParser jsonParserPopularTVs = new JsonParser();

           popularMovieArrayList = jsonParserPopularMovies.processJSONData(popularMovies);
           upcomingMovieArrayList = jsonParserUpcomingMovies.processJSONData(upcomingMovies);
           popularTVArrayList = jsonParserPopularTVs.processJSONDataTV(popularTVs);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        popularMoviesRecyclerAdapter = new MovieRecyclerAdapter(this, popularMovieArrayList, this);
        upcomingMoviesRecyclerAdapter = new MovieRecyclerAdapter(this, upcomingMovieArrayList, this);
        popularTVRecyclerAdapter = new MovieRecyclerAdapter(this, popularTVArrayList, this);

        popularMoviesRecyclerView.setAdapter(popularMoviesRecyclerAdapter);
        upcomingMoviesRecyclerView.setAdapter(upcomingMoviesRecyclerAdapter);
        popularTVRecyclerView.setAdapter(popularTVRecyclerAdapter);

        LinearLayoutManager linearLayoutManagerPopularMovies = new LinearLayoutManager(this);
        linearLayoutManagerPopularMovies.setOrientation(LinearLayoutManager.HORIZONTAL);

        LinearLayoutManager linearLayoutManagerPopularTVS = new LinearLayoutManager(this);
        linearLayoutManagerPopularTVS.setOrientation(LinearLayoutManager.HORIZONTAL);

        LinearLayoutManager linearLayoutManagerUpcomingMovies = new LinearLayoutManager(this);
        linearLayoutManagerUpcomingMovies.setOrientation(LinearLayoutManager.HORIZONTAL);

        popularMoviesRecyclerView.setLayoutManager(linearLayoutManagerPopularMovies);
        upcomingMoviesRecyclerView.setLayoutManager(linearLayoutManagerUpcomingMovies);
        popularTVRecyclerView.setLayoutManager(linearLayoutManagerPopularTVS);
    }

    public void initializeLinearLayout() {
        homeImageView = findViewById(R.id.homeImageViewId);
        homeTextView = findViewById(R.id.homeTextViewId);
        homeImageView.setColorFilter(Color.WHITE);
        homeTextView.setTextColor(Color.WHITE);

        favoriteLinearLayout = findViewById(R.id.favoriteLinearLayoutId);
        favoriteLinearLayout.setOnClickListener(this);
        searchLinearLayout = findViewById(R.id.searchLinearLayoutId);
        searchLinearLayout.setOnClickListener(this);
    }

    @Override
    public void onMovieClick(int position, int parentId) {
        Intent intentToSendToDetailActivity = new Intent(MainActivity.this, DetailActivity.class);
        Bundle bundleToSendToDetailActivity = new Bundle();
        switch (parentId) {
            case R.id.popularMovieRecyclerViewId:
                bundleToSendToDetailActivity.putSerializable(Constants.SERIALIZABLE_KEY_MAINACTIVITY_TO_DETAILACTIVTY, popularMovieArrayList.get(position));
                intentToSendToDetailActivity.putExtra(Constants.BUNDLE_KEY_MAINACTIVITY_TO_DETAILACTIVTY, bundleToSendToDetailActivity);
                startActivity(intentToSendToDetailActivity);
                break;
            case  R.id.upcomingMovieRecyclerViewId:
                bundleToSendToDetailActivity.putSerializable(Constants.SERIALIZABLE_KEY_MAINACTIVITY_TO_DETAILACTIVTY, upcomingMovieArrayList.get(position));
                intentToSendToDetailActivity.putExtra(Constants.BUNDLE_KEY_MAINACTIVITY_TO_DETAILACTIVTY, bundleToSendToDetailActivity);
                startActivity(intentToSendToDetailActivity);
                break;
            case  R.id.popularTVRecyclerViewId:
                bundleToSendToDetailActivity.putSerializable(Constants.SERIALIZABLE_KEY_MAINACTIVITY_TO_DETAILACTIVTY, popularTVArrayList.get(position));
                intentToSendToDetailActivity.putExtra(Constants.BUNDLE_KEY_MAINACTIVITY_TO_DETAILACTIVTY, bundleToSendToDetailActivity);
                startActivity(intentToSendToDetailActivity);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.searchLinearLayoutId:
                Intent intentSearch = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intentSearch);
                break;
            case R.id.favoriteLinearLayoutId:
                Intent intentFavorite = new Intent(MainActivity.this, FavoriteActivity.class);
                startActivity(intentFavorite);
                break;
        }
    }
}