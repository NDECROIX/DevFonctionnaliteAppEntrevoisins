package com.openclassrooms.entrevoisins.ui.neighbour_detail;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailsNeighbourActivity extends AppCompatActivity {

    public static final String NEIGHBOUR = "Neighbour";

    @BindView(R.id.header)
    ImageView mAvatar;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @BindView(R.id.details_name)
    TextView mName;

    @BindView(R.id.details_facebook)
    TextView mFacebook;

    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;

    private Neighbour mNeighbour;
    private NeighbourApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_neighbour);
        ButterKnife.bind(this);
        mApiService = DI.getNeighbourApiService();
        configView();
        configToolbar();
    }

    /**
     * configure the toolbar with the neighbor's name
     */
    private void configToolbar(){

        setSupportActionBar(toolbar);
        if ( getSupportActionBar() != null ){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    /**
     * displays the neighbor's data on the view
     */
    private void configView() {

        Bundle bundle = getIntent().getExtras();

        mNeighbour = (Neighbour) bundle.getSerializable(NEIGHBOUR);

        collapsingToolbarLayout.setTitle(this.mNeighbour.getName());

        mName.setText(mNeighbour.getName());
        mFacebook.append(mNeighbour.getName().toLowerCase());

        if (mApiService.getFavourites().contains(mNeighbour))
            fab.setImageResource(R.drawable.ic_star_yellow_24);

        if (mNeighbour.getAvatarUrl() != null) {
            String picture = mNeighbour.getAvatarUrl().replace(
                    getString(R.string.target_to_be_replaced), getString(R.string.target_replacement));

            Glide.with(this)
                    .load(picture)
                    .centerCrop()
                    .into(mAvatar);
        }
    }

    @OnClick(R.id.fab)
    public void favourite(View view){

        if (!mApiService.getFavourites().contains(mNeighbour)){
            mApiService.addFavourite(mNeighbour);
            fab.setImageResource(R.drawable.ic_star_yellow_24);
            showSnackBar(view, getString(R.string.add_favourite));

        } else showSnackBar(view, getString(R.string.already_favourite));
    }

    private void showSnackBar(View view, String message){
        Snackbar.make(view, mNeighbour.getName() + " " + message, Snackbar.LENGTH_LONG).show();
    }
}
