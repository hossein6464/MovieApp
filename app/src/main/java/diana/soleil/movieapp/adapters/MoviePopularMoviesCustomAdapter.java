package diana.soleil.movieapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import diana.soleil.movieapp.R;
import diana.soleil.movieapp.model.Movie;
import diana.soleil.movieapp.utilities.Constants;


public class MoviePopularMoviesCustomAdapter extends BaseAdapter{
    Movie movie;
    Context context;
    int positionOfItem;
    ArrayList<Movie> movieArrayList;

    public MoviePopularMoviesCustomAdapter(Context context, ArrayList<Movie> movieArrayList) {
        this.context = context;
        this.movieArrayList = movieArrayList;
    }

    @Override
    public int getCount() {
        return movieArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return movieArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position,
                        View cellReusableViewObject,
                        ViewGroup parent) {
        movie = movieArrayList.get(position);

        // If cellReusableViewObject is already created
        // do not create it again, just reuse it and fill it with new information
        if (cellReusableViewObject == null) {
            cellReusableViewObject = LayoutInflater
                    .from(context)
                    .inflate(R.layout.cell_custom, parent,false);
        }
        positionOfItem = position;
        // Create reference to cell elements -------------------------------------------------------
        TextView titleTextView        = cellReusableViewObject.findViewById(R.id.titleTextViewId);
        TextView ratingTextView       = cellReusableViewObject.findViewById(R.id.ratingTextViewId);
        ImageView movieImageView = cellReusableViewObject.findViewById(R.id.movieImageViewId);
        //------------------------------------------------------------------------------------------

        // Set value for cell elements -------------------------------------------------------------
        titleTextView.setText(movie.getMovieTitle());
        ratingTextView.setText(String.valueOf(movie.getMovieRating()));

        // load image for cell ................................................
        String photo = movie.getMovieImageWithTitle();

        Picasso.get().load(Constants.MOVIE_IMAGE_URL_PATH+photo).into(movieImageView);

        //.....................................................................

        //------------------------------------------------------------------------------------------
        return cellReusableViewObject;
    }
}
