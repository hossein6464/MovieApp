package diana.soleil.movieapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import diana.soleil.movieapp.adapters.CastRecyclerAdapter;
import diana.soleil.movieapp.adapters.PeopleKnownByRecyclerAdapter;
import diana.soleil.movieapp.model.Cast;
import diana.soleil.movieapp.model.Genre;
import diana.soleil.movieapp.model.Movie;
import diana.soleil.movieapp.model.People;
import diana.soleil.movieapp.request.Download;
import diana.soleil.movieapp.utilities.Constants;
import diana.soleil.movieapp.utilities.JsonParser;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class CastDetailActivity extends AppCompatActivity {
    Intent intentFromDetailActivity;
    Bundle bundleFromDetailActivity;
    Serializable serializableFromDetailActivity;
    Cast castFromDetailActivity;
    ImageView imageView;
    TextView nameTextView, biographyTextView, ratingTextView, birthDateTextView, placeOfBirthTextView;

    RecyclerView peopleRecyclerView;
    PeopleKnownByRecyclerAdapter peopleRecyclerAdapter;
    ArrayList<String> knownByArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cast_detail);
        initialize();
        try {
            getIntentFromDetailActivity();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        initializeKnowBy();
    }

    public void initialize() {
        imageView = findViewById(R.id.castImageViewId);
        nameTextView = findViewById(R.id.castNameTextViewId);
        biographyTextView = findViewById(R.id.biographyTextViewId);
        ratingTextView = findViewById(R.id.ratingTextViewId);
        birthDateTextView = findViewById(R.id.birthdateTextViewId);
        placeOfBirthTextView = findViewById(R.id.placeOfBirthTextViewId);
    }

    public void getIntentFromDetailActivity () throws ExecutionException, InterruptedException {
        intentFromDetailActivity = getIntent();
        bundleFromDetailActivity = intentFromDetailActivity.getBundleExtra(Constants.BUNDLE_KEY_MAINACTIVITY_TO_DETAILACTIVTY);
        serializableFromDetailActivity = bundleFromDetailActivity.getSerializable(Constants.SERIALIZABLE_KEY_MAINACTIVITY_TO_DETAILACTIVTY);
        castFromDetailActivity = (Cast) serializableFromDetailActivity;

        Download download = new Download();
        String peopleDetails = download.execute(Constants.PEOPLE_URL_PART1+castFromDetailActivity.getId()+Constants.PEOPLE_URL_PART2).get();

        JsonParser jsonParser = new JsonParser();
        People peopleObject = jsonParser.processJSONDataOfPeople(peopleDetails);

        knownByArrayList = peopleObject.getKnownBy();

        String photo = peopleObject.getProfileImage();
        System.out.println(Constants.MOVIE_IMAGE_URL_PATH+photo);
        Picasso.get().load(Constants.MOVIE_IMAGE_URL_PATH+photo).into(imageView);
        nameTextView.setText(peopleObject.getName());
        biographyTextView.setText(peopleObject.getBiography());
        ratingTextView.setText(String.valueOf(peopleObject.getRating()));
        birthDateTextView.setText(peopleObject.getBirthdate());
        placeOfBirthTextView.setText(peopleObject.getPlaceOfBirth());
    }

    private void initializeKnowBy() {
        peopleRecyclerView = findViewById(R.id.knownByRecycleViewId);
        peopleRecyclerAdapter = new PeopleKnownByRecyclerAdapter(this, knownByArrayList);
        peopleRecyclerView.setAdapter(peopleRecyclerAdapter);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        peopleRecyclerView.setLayoutManager(staggeredGridLayoutManager);

    }
}
