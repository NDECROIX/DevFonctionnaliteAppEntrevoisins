package com.openclassrooms.entrevoisins.ui.neighbour_detail;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailNeighbourActivity extends AppCompatActivity {

    public static final String NEIGHBOUR = "Neighbour";

    @BindView(R.id.detail_name)
    TextView mName;
    @BindView(R.id.detail_avatar)
    ImageView mAvatar;
    @BindView(R.id.fab_favourite)
    FloatingActionButton fabFavourite;

    private Neighbour mNeighbour;
    private NeighbourApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour_detail);
        ButterKnife.bind(this);
        mApiService = DI.getNeighbourApiService();
        configView();
    }

    private void configView() {

        Bundle b = getIntent().getExtras();

        if (b != null){

            Gson gson = new Gson();
            String json = b.getString(NEIGHBOUR);
            this.mNeighbour = gson.fromJson(json, Neighbour.class);

            String name = this.mNeighbour.getName();
            String picture = this.mNeighbour.getAvatarUrl();

            this.mName.setText(name);

            if (picture != null) {
                picture = picture.replace("150?", "500?");
                Glide.with(this)
                        .load(picture)
                        .centerCrop()
                        .into(this.mAvatar);
            }
        }

        List<Neighbour> favourite = mApiService.getFavourites();
        if (favourite.contains(mNeighbour)) changeColor();
    }

    @OnClick(R.id.detail_back)
    public void back() { onBackPressed(); }

    @OnClick(R.id.fab_favourite)
    public void favourite(){
        if (fabFavourite.getTag().equals(getText(R.string.white))) mApiService.addFavourite(this.mNeighbour);
        else mApiService.deleteFavourite(this.mNeighbour);
        changeColor();
    }

    private void changeColor(){
        if (fabFavourite.getTag().equals(getText(R.string.white))) {
            fabFavourite.setImageResource(R.drawable.ic_star_yellow_24);
            fabFavourite.setTag(getText(R.string.yellow));
        }
        else {
            fabFavourite.setImageResource(R.drawable.ic_star_white_24dp);
            fabFavourite.setTag(getText(R.string.white));
        }
    }
}
