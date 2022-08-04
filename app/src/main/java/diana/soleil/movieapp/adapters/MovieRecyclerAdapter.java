package diana.soleil.movieapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import diana.soleil.movieapp.DetailActivity;
import diana.soleil.movieapp.MainActivity;
import diana.soleil.movieapp.R;
import diana.soleil.movieapp.model.Movie;
import diana.soleil.movieapp.utilities.Constants;

public class MovieRecyclerAdapter extends RecyclerView.Adapter<MovieRecyclerAdapter.MyViewHolder>  {
    Movie movie;
    Context context;
    ArrayList<Movie> movieArrayList;
    OnMovieListener onMovieListener;

    public MovieRecyclerAdapter(Context context, ArrayList<Movie> movieArrayList, OnMovieListener onMovieListener) {
        this.context = context;
        this.movieArrayList = movieArrayList;
        this.onMovieListener = onMovieListener;
    }

    @NonNull
    @Override
    public MovieRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            LayoutInflater layoutInflater = LayoutInflater
                    .from(context);
                    View view = layoutInflater.inflate(R.layout.custom_recycle_view_layout_movie_popular, parent,false);

        return new MovieRecyclerAdapter.MyViewHolder(view, onMovieListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieRecyclerAdapter.MyViewHolder holder, int position) {
        movie = movieArrayList.get(position);
        String photo = movie.getMovieImageWithOutTitle();
        Picasso.get().load(Constants.MOVIE_IMAGE_URL_PATH+photo).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return movieArrayList.size();
    }



    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imageView;
        OnMovieListener onMovieListener;
        public MyViewHolder(@NonNull View itemView, OnMovieListener onMovieListener) {
            super(itemView);
            this.onMovieListener = onMovieListener;
            imageView = itemView.findViewById(R.id.imageView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            RecyclerView recyclerView = (RecyclerView) v.getParent();
            onMovieListener.onMovieClick(getAdapterPosition(), recyclerView.getId());
        }
    }
    public interface OnMovieListener {
        void onMovieClick(int position, int parentPosition);
    }
}
