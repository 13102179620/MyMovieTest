package com.example.mymovie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mymovie.Dao.MovieEntity2Db;
import com.example.mymovie.Event.EVENT;
import com.example.mymovie.Event.ShowDetaiFragmentEvent;
import com.example.mymovie.R;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

public class SearchResRvAdaper extends RecyclerView.Adapter<SearchResRvAdaper.VH> {

    LayoutInflater mInfalter;
    List<MovieEntity2Db> mData;
    Context context;

    public SearchResRvAdaper(Context context, List<MovieEntity2Db> mData) {
        this.mInfalter = LayoutInflater.from(context);
        this.mData = mData;
        this.context = context;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemView = mInfalter.inflate(R.layout.fragment_search_res_item, viewGroup, false);
        return new VH(itemView, mData);
    }

    @Override
    public void onBindViewHolder(@NonNull VH vh, int i) {
        vh.tVDetailName.setText(mData.get(i).getName());
        vh.rBDetailRating.setRating((float) mData.get(i).getRating()/2.0f);
        vh.tVDetailRating.setText(mData.get(i).getRating()+"");
        vh.tVDetailActor.setText(mData.get(i).getCasts().toString());
        vh.tVDetailDirector.setText(mData.get(i).getDirectors().toString());
        vh.tVDetailYear.setText(mData.get(i).getYears());
        Glide.with(context).load(mData.get(i).getPic()).into(vh.iVDetailPic);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public static class VH extends RecyclerView.ViewHolder {
        @BindView(R.id.iV_detail_pic)
        ImageView iVDetailPic;
        @BindView(R.id.rB_detail_rating)
        MaterialRatingBar rBDetailRating;
        @BindView(R.id.tV_detail_name)
        TextView tVDetailName;
        @BindView(R.id.tV_detail_rating)
        TextView tVDetailRating;
        @BindView(R.id.tV_detail_director)
        TextView tVDetailDirector;
        @BindView(R.id.tV_detail_actor)
        TextView tVDetailActor;
        @BindView(R.id.tV_detail_year)
        TextView tVDetailYear;
        public VH(@NonNull View itemView , final List<MovieEntity2Db> data) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int index = getAdapterPosition();
                    EVENT event = new ShowDetaiFragmentEvent(data.get(index));
                    EventBus.getDefault().post(event);
                }
            });
        }
    }



}
