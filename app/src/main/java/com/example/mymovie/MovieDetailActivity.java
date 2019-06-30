package com.example.mymovie;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mymovie.Dao.MovieEntity2Db;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailActivity extends AppCompatActivity {

    MovieEntity2Db detaiMovie;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.iV_movie_detail_pic)
    ImageView iVMovieDetailPic;

    @BindView(R.id.layout_toolbar)
    CollapsingToolbarLayout layoutToolbar;

    @BindView(R.id.tV_movie_description)
    TextView tVMovieDescrip;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        detaiMovie = getIntent().getBundleExtra(MainActivity.MOVIE_DETAIL_OBJ).getParcelable(MainActivity.MOVIE_DETAIL_OBJ);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        toolbar.setTitle(detaiMovie.getName());
        toolbar.setPadding(0,48,0,0);
        toolbar.setTitleMarginTop(12);
        Glide.with(this).load(detaiMovie.getPic()).into(iVMovieDetailPic);
        tVMovieDescrip.setText(detaiMovie.getDescription());
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detai_actionmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            //系统默认home键 actionBar.setDisplayHomeAsUpEnabled(true);
            case android.R.id.home:
                finish();
                break;


            case R.id.action_fav:

                break;

            case R.id.action_delete:

                break;


            case R.id.action_setting:

                break;


        }

        return super.onOptionsItemSelected(item);
    }
}
