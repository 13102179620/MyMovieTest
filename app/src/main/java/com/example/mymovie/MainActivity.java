package com.example.mymovie;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.mymovie.Event.EVENT;
import com.example.mymovie.Event.ShowDetaiFragmentEvent;
import com.example.mymovie.fragment.MovieDetailFragment;
import com.example.mymovie.fragment.OnShowFragment;
import com.example.mymovie.fragment.SearchResFragment;
import com.example.mymovie.utils.KeyBoardUtil;

import org.angmarch.views.NiceSpinner;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements SearchResFragment.CallBack {

    NiceSpinner SpinMovieType;

    public static final String TAG = "MainActivity-app";
    public static final String MOVIE_DETAIL_OBJ = "movieDetailObj";

    //fragment
    OnShowFragment showFragment;
    SearchResFragment searchResFragment;


    @BindView(R.id.layout_fragmentContainer)
    LinearLayout layoutFragmentContainer;
    @BindView(R.id.Edt_movieName)
    EditText EdtMovieName;


    @BindView(R.id.imB_search)
    ImageButton btnSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();


    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        /**
         * @Author SYT
         * @Description 重写该方法用于点击空白隐藏键盘
         * @Date 17:55 2019/6/10
         * @Param [ev]
         * @return boolean
         **/

        KeyBoardUtil.hideInputWhenTouchOtherView(this, ev, null);
        return super.dispatchTouchEvent(ev);
    }



    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    private void initView() {
        //初始化spinner
        SpinMovieType = findViewById(R.id.spinner);
        String[] movieTypes = getResources().getStringArray(R.array.movie_types);
        List<String> dataset = new LinkedList<>(Arrays.asList(movieTypes));
        SpinMovieType.attachDataSource(dataset);

        //searchResFragment 需要与主界面通信，点进home键，切换fragment

        showFragment = OnShowFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.layout_fragmentContainer, showFragment)
                .commit();
    }

    @Subscribe
    public void onChangeFragment(EVENT event) {
        if (event instanceof ShowDetaiFragmentEvent) {

            Bundle bundle = new Bundle();
            bundle.putParcelable(MOVIE_DETAIL_OBJ, event.getMovie());
            Intent intent = new Intent(this, MovieDetailActivity.class);
            intent.putExtra(MOVIE_DETAIL_OBJ, bundle);
            startActivity(intent);
        }
    }



    @OnClick(R.id.imB_search)
    public void onViewClick(View v){
        switch (v.getId()){

            //搜索按钮，点击跳转至搜索结果fragment
            case R.id.imB_search:
                String type = SpinMovieType.getSelectedItem().toString();
                String name = EdtMovieName.getText().toString();
                StringBuilder url = new StringBuilder("https://www.imooc.com/api/movie");
                if (!type.equals("请选择")){
                    if (name.length()>0)
                        url = url.append("?title=").append(name).append("&types=").append(type);
                    else
                        url = url.append("?types=").append(type);
                }else
                    url = url.append("?title=").append(name);

                searchResFragment = SearchResFragment.newInstance(url.toString());
                searchResFragment.setOnClickHomeListener(this);
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.layout_fragmentContainer , searchResFragment)
                        .hide(showFragment)
                        .commit();

        }
    }


    @Override
    public void onClickhome() {
        getSupportFragmentManager().beginTransaction()
                .detach(searchResFragment)
                .show(showFragment)
                .commit();
        searchResFragment.onDestroy();

    }
}
