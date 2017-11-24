package com.ljq.mvp.demo2;


import android.os.Handler;
import android.util.Log;

import com.ljq.mvp.request.WeatherBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author 刘镓旗
 * @date 2017/11/16
 * @description
 */
public class RequestPresenter2 {

    private RequestView2 mView;
    private RequestMode2 mMode;

    public RequestPresenter2() {
        mMode = new RequestMode2();
    }

    public void clickRequest(final String cityId) {
        if(mView != null){
            mView.requestLoading();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mMode.request(cityId, new Callback<WeatherBean>() {
                        @Override
                        public void onResponse(Call<WeatherBean> call, Response<WeatherBean> response) {
                            if(mView != null){
                                mView.resultSuccess(response.body());
                            }
                        }

                        @Override
                        public void onFailure(Call<WeatherBean> call, Throwable t) {
                            if(mView != null){
                                mView.resultFailure(Log.getStackTraceString(t));
                            }
                        }
                    });
                }
            },1000);
        }
    }

    /**
     * 绑定
     * @param view
     */
    public void attach( RequestView2 view) {
        this.mView = view;
    }

    /**
     * 解除绑定
     */
    public void detach() {
        mView = null;
    }

    /**
     * 取消网络请求
     */
    public void interruptHttp(){
        mMode.interruptHttp();
    }
}
