package com.example.mymovie.adapter;

import android.app.usage.UsageEvents;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mymovie.Dao.MovieEntity2Db;
import com.example.mymovie.Event.EVENT;
import com.example.mymovie.Event.ShowDetaiFragmentEvent;
import com.example.mymovie.R;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainRvAdapter extends RecyclerView.Adapter<MainRvAdapter.MyViewHolder> {

    Context context;
    List<MovieEntity2Db> mData;
    LayoutInflater minfater;

    public MainRvAdapter(Context context, List<MovieEntity2Db> mData) {
        this.context = context;
        this.mData = mData;
        this.minfater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //这个i一直等于0？？？
        View view = minfater.inflate(R.layout.layout_movieitem, viewGroup, false);
        return new MyViewHolder(context, view,mData);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.tVMovieItemName.setText(mData.get(i).getName());
        myViewHolder.tVMovieItemRating.setText(mData.get(i).getRating()+"");
        myViewHolder.ratingBar.setRating((float) mData.get(i).getRating()/2.0f);
        String url = mData.get(i).getPic();
//        Bitmap bitmap = Glide.with()
//                .load(url)
//                .into(200, 180)
//                .get();
        Glide.with(context).load(mData.get(i).getPic())
                .into(myViewHolder.imvMovieItemPic);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imv_movieItemPic)
        ImageView imvMovieItemPic;
        @BindView(R.id.ratingBar)
        me.zhanghai.android.materialratingbar.MaterialRatingBar ratingBar;
        @BindView(R.id.tV_movie_itemName)
        TextView tVMovieItemName;
        @BindView(R.id.tV_movie_itemRating)
        TextView tVMovieItemRating;
        public MyViewHolder(Context context, @NonNull View itemView , final List<MovieEntity2Db>data) {
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
