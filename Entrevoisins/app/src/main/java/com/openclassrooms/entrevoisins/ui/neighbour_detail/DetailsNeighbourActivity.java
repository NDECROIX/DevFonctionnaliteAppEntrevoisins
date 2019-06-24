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

    @BindView(R.id.activity_details_iv_avatar)
    ImageView mAvatar;

    @BindView(R.id.activity_details_toolbar)
    Toolbar toolbar;

    @BindView(R.id.activity_details_fab)
    FloatingActionButton fab;

    @BindView(R.id.activity_details_content_tv_name)
    TextView mName;

    @BindView(R.id.activity_details_content_tv_facebook)
    TextView mFacebook;

    @BindView(R.id.activity_details_collapsing_toolbar)
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
    private void configToolbar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
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
        if (mApiService.isFavourite(mNeighbour))
            fab.setImageResource(R.drawable.ic_star_yellow_24);

        Glide.with(this)
                .load(mNeighbour.getAvatarUrl())
                .centerCrop()
                .into(mAvatar);
    }

    @OnClick(R.id.activity_details_fab)
    public void favourite(View view) {
        if (!mApiService.isFavourite(mNeighbour)) {
            mApiService.addFavourite(mNeighbour);
            fab.setImageResource(R.drawable.ic_star_yellow_24);
            showSnackBar(view, getString(R.string.add_favourite));
        } else {
            showSnackBar(view, getString(R.string.already_favourite));
        }
    }

    private void showSnackBar(View view, String message) {
        Snackbar.make(view, mNeighbour.getName() + " " + message, Snackbar.LENGTH_LONG).show();
    }
}