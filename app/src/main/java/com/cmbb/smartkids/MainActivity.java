package com.cmbb.smartkids;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_scrolling);

        /*HomeBannerModel.getHomeBannerRequest("OTVhMmY2YmYtZGQ0Zi00YzM3LTk3YzAtNTgzZGZlODM1Y2Rm", new OkHttpClientManager.ResultCallback<HomeBannerModel>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(HomeBannerModel response) {
                Log.i("test", response.toString());
            }
        });*/

        /*HomeRecommonedEredarModel.getHomeRecommendEredarRequest(new OkHttpClientManager.ResultCallback<HomeBannerModel>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(HomeBannerModel response) {
                Log.i("test", response.toString());
            }
        });*/

        /*HomeHotServerModel.getHomeHotServiceRequest(10, 0, new OkHttpClientManager.ResultCallback<HomeHotServerModel>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(HomeHotServerModel response) {
                Log.i("test", response.toString());
            }
        });*/

        /*HomeServiceListModel.getHomeAllServiceListRequest(0, 0, 0, 0, 10, new OkHttpClientManager.ResultCallback<HomeServiceListModel>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(HomeServiceListModel response) {
                Log.i("test", response.toString());
            }
        });*/

        /*TopicTypeModel.getTopicTypeRequest(new OkHttpClientManager.ResultCallback<TopicTypeModel>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(TopicTypeModel response) {
                Log.i("test", response.toString());
            }
        });

        TopicListModel.getTopicListRequest("", 0, 10, "OTVhMmY2YmYtZGQ0Zi00YzM3LTk3YzAtNTgzZGZlODM1Y2Rm", new OkHttpClientManager.ResultCallback<TopicListModel>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(TopicListModel response) {
                Log.i("test", response.toString());
            }
        });*/

        /*UserInfoModel.getUserInfoRequest("100768", 1, "OTVhMmY2YmYtZGQ0Zi00YzM3LTk3YzAtNTgzZGZlODM1Y2Rm", new OkHttpClientManager.ResultCallback<UserInfoModel>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(UserInfoModel response) {
                Log.i("test", response.toString());
            }
        });*/
    }
}
