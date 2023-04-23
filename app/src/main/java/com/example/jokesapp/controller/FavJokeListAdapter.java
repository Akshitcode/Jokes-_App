package com.example.jokesapp.controller;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jokesapp.R;
import com.example.jokesapp.fragments.FavJokesFragment;
import com.example.jokesapp.model.Joke;
import com.example.jokesapp.view.FavJokeViewHolder;

import java.util.ConcurrentModificationException;
import java.util.List;

public class FavJokeListAdapter extends RecyclerView.Adapter<FavJokeViewHolder> {


    private List<Joke> mJokelist;
    private Context mContext;

    public FavJokeListAdapter(List<Joke> jokelist, Context context) {
        mJokelist = jokelist;
        mContext = context;
    }

    public Context getContext() {
        return mContext;
    }

    @NonNull
    @Override
    public FavJokeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fav_joke_item, parent, false);
        return new FavJokeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavJokeViewHolder holder, int position) {
        String jokeText = mJokelist.get(position).getJokeText();
        holder.getTxtFavJoke().setText(jokeText);

        holder.getImgButtonShare().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                String shareBody = jokeText;
                intent.setType("text/plain");
                intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Mama Joke!");
                intent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);

                mContext .startActivity(Intent.createChooser(intent,"Share via"));
            }
        });

    }

    @Override
    public int getItemCount() {
        return mJokelist.size();
    }
}
