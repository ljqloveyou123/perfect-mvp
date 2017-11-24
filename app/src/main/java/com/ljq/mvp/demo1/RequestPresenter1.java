package com.ljq.mvp.demo1;

import android.os.Handler;
import android.util.Log;

import com.ljq.mvp.request.WeatherBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author 刘镓旗
 * @date 2017/11/16
 * @description P层
 * 特点:需要持有M层和V层
 */
public class RequestPresenter1 {

    private final RequestView1 mRequestView;
    private final RequestMode1 mRequestMode;

    public RequestPresenter1(RequestView1 requestView) {
        this.mRequestView = requestView;
        this.mRequestMode = new RequestMode1();
    }

    public void clickRequest(final String cityId){

        mRequestView.requestLoading();
        //模拟耗时，可以展示出loading
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mRequestMode.request(cityId, new Callback<WeatherBean>() {
                    @Override
                    public void onResponse(Call<WeatherBean> call, Response<WeatherBean> response) {
                        mRequestView.resultSuccess(response.body());
                    }

                    @Override
                    public void onFailure(Call<WeatherBean> call, Throwable t) {
                        mRequestView.resultFailure(Log.getStackTraceString(t));
                    }
                });
            }
        },1000);

    }
}
