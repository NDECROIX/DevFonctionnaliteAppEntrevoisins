package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.ui.neighbour_detail.DetailNeighbourActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.openclassrooms.entrevoisins.ui.neighbour_detail.DetailNeighbourActivity.NEIGHBOUR;

public class MyFavouriteRecyclerViewAdapter extends RecyclerView.Adapter<MyFavouriteRecyclerViewAdapter.ViewHolder> {

    private List<Neighbour> mFavourites;

    public MyFavouriteRecyclerViewAdapter(List<Neighbour> mFavourites) {
        this.mFavourites = mFavourites;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.fragment_favourite, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        Neighbour favourite = mFavourites.get(i);

        viewHolder.mNeighbourName.setText(favourite.getName());

        Glide.with(viewHolder.itemView.getContext())
                .load(favourite.getAvatarUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(viewHolder.mNeighbourAvatar);

        viewHolder.itemView.setOnClickListener(l -> {

            Gson gson = new Gson();
            String json = gson.toJson(favourite);

            Context c =  viewHolder.itemView.getContext();
            Intent intent = new Intent(c, DetailNeighbourActivity.class);
            intent.putExtra(NEIGHBOUR, json);
            c.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return mFavourites.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.favourite_item_list_avatar)
        public ImageView mNeighbourAvatar;

        @BindView(R.id.favourite_item_list_name)
        public TextView mNeighbourName;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
