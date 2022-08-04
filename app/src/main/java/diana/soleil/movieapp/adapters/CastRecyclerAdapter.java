package diana.soleil.movieapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import diana.soleil.movieapp.R;
import diana.soleil.movieapp.model.Cast;
import diana.soleil.movieapp.model.Movie;
import diana.soleil.movieapp.utilities.Constants;
import diana.soleil.movieapp.utilities.RoundedCornersTransform;


public class CastRecyclerAdapter extends RecyclerView.Adapter<CastRecyclerAdapter.MyViewHolder>  {
    Cast cast;
    Context context;
    ArrayList<Cast> castsArrayList;
    OnCastListener onCastListener;

    public CastRecyclerAdapter(Context context, ArrayList<Cast> castsArrayList, OnCastListener onCastListener) {
        this.context = context;
        this.castsArrayList = castsArrayList;
        this.onCastListener = onCastListener;
    }

    @NonNull
    @Override
    public CastRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            LayoutInflater layoutInflater = LayoutInflater
                    .from(context);
                    View view = layoutInflater.inflate(R.layout.custom_recycle_view_layout_cast, parent,false);

        return new CastRecyclerAdapter.MyViewHolder(view, onCastListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CastRecyclerAdapter.MyViewHolder holder, int position) {
        cast = castsArrayList.get(position);
        String photo = cast.getProfile_image();


        holder.castNameTextView.setText(cast.getName());
        Picasso.get().load(Constants.MOVIE_IMAGE_URL_PATH+photo).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return castsArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imageView;
        TextView castNameTextView;
        OnCastListener onCastListener;
        public MyViewHolder(@NonNull View itemView, OnCastListener onCastListener) {
            super(itemView);
            this.onCastListener = onCastListener;
            imageView = itemView.findViewById(R.id.imageView);
            castNameTextView = itemView.findViewById(R.id.castNameTextViewId);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            RecyclerView recyclerView = (RecyclerView) v.getParent();
            onCastListener.onCastClick(getAdapterPosition(), recyclerView.getId());
        }
    }
    public interface OnCastListener {
        void onCastClick(int position, int parentPosition);
    }
}
