package com.example.mymovie.biz;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.mymovie.Dao.MovieEntity2Db;
import com.example.mymovie.Event.EVENT;
import com.example.mymovie.Event.LoadMovieSuccessEvent;
import com.example.mymovie.Event.LoadSearchResMovieSuccessEvent;
import com.example.mymovie.GsonObj.MovieJson;
import com.example.mymovie.fragment.OnShowFragment;
import com.example.mymovie.fragment.SearchResFragment;
import com.example.mymovie.utils.HttpUtils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.support.constraint.Constraints.TAG;

public class MovieBiz {
    Context context;

    public MovieBiz() {

    }


    public void loadFromNet(Context context, String url, String tag) {
        LoadMovieItemAsyncTask task = new LoadMovieItemAsyncTask(context, tag);
        task.execute(url);
    }


    private static class LoadMovieItemAsyncTask extends AsyncTask<String, Void, List<MovieEntity2Db>> {

        /**
         * @ClassName: LoadMovieItemAsyncTask
         * @Author SYT
         * @Description 业务层，负责加载各个fragment发送来的url请求，在onpostExcute方法中将结果返回至对应的fragment
         * @Date 20:08 2019/6/30
         **/

        //弱引用，放置leak
        WeakReference<Context> contextWeakReference;

        //设立tag，表明url来源地，并将结果返回至来源地
        String tag;

        private LoadMovieItemAsyncTask(Context context, String tag) {
            contextWeakReference = new WeakReference<>(context);
            this.tag = tag;
        }

        @Override
        protected List<MovieEntity2Db> doInBackground(String... strings) {
            String url = strings[0];

            List<MovieEntity2Db> movieListFromNet = loadMoviesFromNet(url);
//            Log.d(TAG, "doInBackground: 从网络加载数据：" + movieListFromNet.toString());
            return movieListFromNet;
        }

        @Override
        protected void onPostExecute(List<MovieEntity2Db> movieEntity2Dbs) {
            super.onPostExecute(movieEntity2Dbs);
            switch (tag){
                case OnShowFragment.ONSHOWFRAGTAG:
                    EVENT event = new LoadMovieSuccessEvent(movieEntity2Dbs);
                    EventBus.getDefault().post(event);
                    break;
                case SearchResFragment.SEARCHRESFRAGTAG:
                    EVENT event1 = new LoadSearchResMovieSuccessEvent(movieEntity2Dbs);
                    EventBus.getDefault().post(event1);
            }



        }

        private List<MovieEntity2Db> loadMoviesFromNet(String url) {
            String Content = HttpUtils.laodMoviesFromNet(contextWeakReference.get(), url);
            List<MovieEntity2Db> res = String2Movies(Content);
            return res;
        }


        private List<MovieEntity2Db> String2Movies(String content) {
            /**
             * @Author SYT
             * @Description 将网络加载的字符串转化问movieEntity2Db对象
             * @Date 19:01 2019/6/28
             * @param content 输入可能为空 , 当输入的url违法时
             * @return java.util.List<com.example.mymovie.Dao.MovieEntity2Db>
             **/
            if (content == null){
                return null;
            }
            List<MovieEntity2Db> res = new ArrayList<>();
            OkHttpClient client = new OkHttpClient.Builder()
                    .retryOnConnectionFailure(true)
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .build();
            try {
                JSONObject source = new JSONObject(content);
                JSONArray moviesJsonArray = source.getJSONArray("movies");
                for (int i = 0; i < moviesJsonArray.length(); i++) {
                    JSONObject item = (JSONObject) moviesJsonArray.get(i);
                    MovieEntity2Db movieItem = new MovieEntity2Db();
                    String title = item.getString("title");
                    String id = item.getString("id");
                    String imageUrl = item.getString("imageUrl");
                    String description = item.getString("description");
                    String year = item.getString("year");


                    JSONArray types = item.getJSONArray("types");
                    String[] typeslist = new String[types.length()];
                    for (int j = 0; j < types.length(); j++) {
                        typeslist[j] = types.getString(j);
                    }

                    JSONArray casts = item.getJSONArray("casts");
                    String[] castslist = new String[casts.length()];
                    for (int c = 0; c < casts.length(); c++) {
                        JSONObject nameObj = casts.getJSONObject(c);
                        castslist[c] = nameObj.getString("name");
                    }

                    JSONArray directors = item.getJSONArray("directors");
                    String[] directorslist = new String[casts.length()];
                    for (int d = 0; d < directors.length(); d++) {
                        JSONObject direcObj = directors.getJSONObject(d);
                        directorslist[d] = direcObj.getString("name");
                    }

                    JSONObject ratingObj = item.getJSONObject("rating");
                    String rating = ratingObj.getString("average");

                    movieItem.setCasts(castslist);
                    movieItem.setDescription(description);
                    movieItem.setTypes(typeslist);
                    movieItem.setDirectors(directorslist);
                    movieItem.setYears(year);
                    movieItem.setRating(Double.parseDouble(rating));
                    movieItem.setName(title);
                    movieItem.setId(Integer.parseInt(id));
                    movieItem.setPic(imageUrl);
                    res.add(movieItem);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


            return res;

        }


        private byte[] loadMoviePic(OkHttpClient client, String imageUrl) {
            //加载图片保存为byte数组
            Request request = new Request.Builder()
                    .url(imageUrl).build();
            try {
                Response response = client.newCall(request).execute();
                if (response.isSuccessful()) {
                    byte[] picbyte = response.body().bytes();
                    return picbyte;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return new byte[]{};
        }

    }

}
