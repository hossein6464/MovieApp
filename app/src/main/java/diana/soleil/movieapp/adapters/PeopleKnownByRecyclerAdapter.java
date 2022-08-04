package diana.soleil.movieapp.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import diana.soleil.movieapp.R;
import diana.soleil.movieapp.model.Cast;
import diana.soleil.movieapp.model.People;
import diana.soleil.movieapp.utilities.Constants;


public class PeopleKnownByRecyclerAdapter extends RecyclerView.Adapter<PeopleKnownByRecyclerAdapter.MyViewHolder>{
    String people;
    Context context;
    ArrayList<String> peopleNameArrayList;

    public PeopleKnownByRecyclerAdapter(Context context, ArrayList<String> peopleNameArrayList) {
        this.context = context;
        this.peopleNameArrayList = peopleNameArrayList;
    }

    @NonNull
    @Override
    public PeopleKnownByRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            LayoutInflater layoutInflater = LayoutInflater
                    .from(context);
                    View view = layoutInflater.inflate(R.layout.custom_cell_recycler_view_known_by, parent,false);

        return new PeopleKnownByRecyclerAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PeopleKnownByRecyclerAdapter.MyViewHolder holder, int position) {
        people = peopleNameArrayList.get(position);
        if (position % 3 == 1) {
            holder.knownByTextView.setTextColor(Color.RED);
        } else if (position % 3 == 2) {
            holder.knownByTextView.setTextColor(Color.BLUE);
        } else {
            holder.knownByTextView.setTextColor(Color.DKGRAY);
        }
        holder.knownByTextView.setText(people);
    }

    @Override
    public int getItemCount() {
        return peopleNameArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView knownByTextView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            knownByTextView = itemView.findViewById(R.id.knownByTextViewId);

        }
    }
}
