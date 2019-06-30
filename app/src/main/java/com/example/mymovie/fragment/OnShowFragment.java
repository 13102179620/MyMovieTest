package com.example.mymovie.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.mymovie.Dao.MovieEntity2Db;
import com.example.mymovie.Event.EVENT;
import com.example.mymovie.Event.LoadMovieSuccessEvent;
import com.example.mymovie.R;
import com.example.mymovie.adapter.MainRvAdapter;
import com.example.mymovie.biz.MovieBiz;

import org.angmarch.views.NiceSpinner;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class OnShowFragment extends Fragment {

    public static final  String ONSHOWFRAGTAG = "onShowfragtag";
    List<MovieEntity2Db> movies;


    @BindView(R.id.rv_movieItem)
    RecyclerView rvMovieItem;

    MovieBiz movieBiz;


    public OnShowFragment() {
    }


    public static OnShowFragment newInstance() {
        OnShowFragment fragment = new OnShowFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_on_show, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {

        //程序已进入便开始加载所有信息
        movieBiz = new MovieBiz();
        movieBiz.loadFromNet(getActivity(), "https://www.imooc.com/api/movie" , ONSHOWFRAGTAG);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLoadFromNetSuccess(EVENT event) {

        if (event instanceof LoadMovieSuccessEvent) {
            movies = event.getMovielists();
            rvMovieItem.setAdapter(new MainRvAdapter(getActivity(), movies));
            rvMovieItem.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        }
    }




    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
