package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.events.DeleteFavouriteEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.ui.neighbour_detail.DetailsNeighbourActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.openclassrooms.entrevoisins.ui.neighbour_detail.DetailsNeighbourActivity.NEIGHBOUR;

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
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        Neighbour favourite = mFavourites.get(position);

        viewHolder.mFavouriteName.setText(favourite.getName());

        Glide.with(viewHolder.itemView.getContext())
                .load(favourite.getAvatarUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(viewHolder.mFavouriteAvatar);

        viewHolder.mFavouriteDelete.setOnClickListener(view ->
                EventBus.getDefault().post(new DeleteFavouriteEvent(favourite)));

        viewHolder.itemView.setOnClickListener(view -> {
            Context context =  viewHolder.itemView.getContext();
            context.startActivity(DetailsNeighbourActivity.newIntent(context, favourite));
        });
    }



    @Override
    public int getItemCount() {
        return mFavourites.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.favourite_item_list_avatar)
        public ImageView mFavouriteAvatar;

        @BindView(R.id.favourite_item_list_name)
        public TextView mFavouriteName;

        @BindView(R.id.favourite_item_list_delete_button)
        public ImageButton mFavouriteDelete;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
