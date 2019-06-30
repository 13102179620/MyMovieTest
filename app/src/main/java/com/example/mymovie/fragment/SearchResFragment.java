package com.example.mymovie.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mymovie.Dao.MovieEntity2Db;
import com.example.mymovie.Event.EVENT;
import com.example.mymovie.R;
import com.example.mymovie.adapter.SearchResRvAdaper;
import com.example.mymovie.biz.MovieBiz;
import com.example.mymovie.utils.KeyBoardUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class SearchResFragment extends Fragment {

    @BindView(R.id.tVonshow)
    TextView tVonshow;
    @BindView(R.id.layout_onShow)
    LinearLayout layoutOnShow;
    @BindView(R.id.rv_movieItemSearch)
    RecyclerView rvMovieItemSearch;
    @BindView(R.id.imb_search_frag_home)
    ImageButton btnHome;
    Unbinder unbinder;

    public static final String SEARCHRESFRAGTAG = "SearchResFragmentTag";
    private String searchUrl;
    private CallBack myCallBack;
    private MovieBiz movieBiz;
    private List<MovieEntity2Db> movielists;
    private static final String SERACH_URL = "param1";




    public SearchResFragment() {
    }




    public static SearchResFragment newInstance(String url) {
        SearchResFragment fragment = new SearchResFragment();
        Bundle bund = new Bundle();
        bund.putString(SERACH_URL, url);
        fragment.setArguments(bund);
        return fragment;
    }

    public static SearchResFragment newInstance() {
        SearchResFragment fragment = new SearchResFragment();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            searchUrl = getArguments().getString(SERACH_URL);
        }
        EventBus.getDefault().register(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_res, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        iniView();

    }



    private void iniView() {
        //加载搜索url
        movieBiz = new MovieBiz();
        movieBiz.loadFromNet(getActivity(), searchUrl , SEARCHRESFRAGTAG);
    }


    //moviebiz 加载成功后的订阅方法
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLoadSearchResMovieSuccess(EVENT event){
        movielists = event.getMovielists();
        if (movielists == null){
            Toast.makeText(getActivity(), "未找到结果", Toast.LENGTH_SHORT).show();
        }else {
            rvMovieItemSearch.setAdapter(new SearchResRvAdaper(getActivity(), movielists));
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
            rvMovieItemSearch.setLayoutManager(layoutManager);
            rvMovieItemSearch.setHasFixedSize(true);
        }
    }


    //eventbus  点击事件处理方法
    @OnClick({ R.id.imb_search_frag_home})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            //点击home按钮
            case R.id.imb_search_frag_home:
                this.onDestroy();
                myCallBack.onClickhome();
                break;

        }
    }



    //为本fragment设置搜索url
    public void setSearchUrl(String url){
        this.searchUrl = url;
    }





    //butterknife 解绑
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    //eventbus 解绑
    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }





    //回调接口，与mainactivity通信
    public void setOnClickHomeListener(CallBack callBack){
        myCallBack = callBack;
    }

    public interface CallBack{
        public void onClickhome();
    }
}
