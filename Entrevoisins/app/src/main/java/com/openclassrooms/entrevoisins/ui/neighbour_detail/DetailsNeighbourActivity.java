package com.openclassrooms.entrevoisins.ui.neighbour_detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
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

public class DetailsNeighbourActivity extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener {

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

    @BindView(R.id.activity_details_tv_name)
    TextView mTitleName;

    @BindView(R.id.activity_details_collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @BindView(R.id.activity_details_app_bar)
    AppBarLayout appBarLayout;

    private Neighbour mNeighbour;
    private NeighbourApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_neighbour);
        ButterKnife.bind(this);
        mApiService = DI.getNeighbourApiService();
        configView();
        configAppBarLayout();
    }

    public static Intent newIntent(Context context, Neighbour favourite){
        Bundle bundle = new Bundle();
        bundle.putSerializable(NEIGHBOUR, favourite);
        Intent intent = new Intent(context, DetailsNeighbourActivity.class);
        intent.putExtras(bundle);
        return intent;
    }

    /**
     * configure the AppBarLayout
     */
    private void configAppBarLayout() {
        collapsingToolbarLayout.setTitleEnabled(false);
        appBarLayout.addOnOffsetChangedListener(this);

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }

    /**
     * displays the neighbor's data on the view
     */
    private void configView() {
        Bundle bundle = getIntent().getExtras();
        mNeighbour = (Neighbour) bundle.getSerializable(NEIGHBOUR);

        mTitleName.setText(mNeighbour.getName());
        collapsingToolbarLayout.setTitle(mNeighbour.getName());
        mName.setText(mNeighbour.getName());
        mFacebook.append(mNeighbour.getName().toLowerCase());

        if (mApiService.isFavourite(mNeighbour)){
            fab.setImageResource(R.drawable.ic_star_yellow_24);
        }

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

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        if (Math.abs(verticalOffset) == appBarLayout.getTotalScrollRange()) {
            // collapsed
            collapsingToolbarLayout.setTitleEnabled(true);
        } else {
            collapsingToolbarLayout.setTitleEnabled(false);
        }
    }

    private void showSnackBar(View view, String message) {
        Snackbar.make(view, mNeighbour.getName() + " " + message, Snackbar.LENGTH_LONG).show();
    }
}