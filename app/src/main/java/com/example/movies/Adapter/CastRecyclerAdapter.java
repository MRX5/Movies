package com.example.movies.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movies.Entity.Cast;
import com.example.movies.R;
import com.squareup.picasso.Picasso;

import java.security.PublicKey;
import java.util.List;

public class CastRecyclerAdapter extends RecyclerView.Adapter<CastRecyclerAdapter.movieViewHolder> {

    List<Cast> casts;
    Context context;

    public CastRecyclerAdapter(Context context) {
        this.context = context;
    }

    public void setCasts(List<Cast> casts) {
        this.casts = casts;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CastRecyclerAdapter.movieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cast_card, parent, false);
        return new movieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull movieViewHolder holder, int position) {
        String castImageUrl = casts.get(position).getProfilePicture();
        String castName = casts.get(position).getName();
        String castCharacter = casts.get(position).getCharacter();

        Picasso.get().load(castImageUrl).placeholder(R.drawable.placeholder_poster).into(holder.castProfileImage);
        holder.castName.setText(castName);
        holder.castCharacter.setText(castCharacter);
    }

    @Override
    public int getItemCount() {
        if (casts == null) {
            return 0;
        }
        return casts.size();
    }

    public class movieViewHolder extends RecyclerView.ViewHolder {
        ImageView castProfileImage;
        TextView castName;
        TextView castCharacter;

        public movieViewHolder(@NonNull View itemView) {
            super(itemView);
            castProfileImage = itemView.findViewById(R.id.cast_profile_img);
            castName = itemView.findViewById(R.id.cast_name);
            castCharacter = itemView.findViewById(R.id.cast_character);
        }
    }
}
