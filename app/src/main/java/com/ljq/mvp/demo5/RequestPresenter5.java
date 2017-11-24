package com.ljq.mvp.demo5;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;

import com.ljq.mvp.demo4.RequestMode4;
import com.ljq.mvp.request.WeatherBean;
import com.ljq.mvpframework.presenter.BaseMvpPresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author 刘镓旗
 * @date 2017/11/17
 * @description
 */
public class RequestPresenter5 extends BaseMvpPresenter<RequestView5> {

    private final RequestMode4 mRequestMode;

    public RequestPresenter5() {
        this.mRequestMode = new RequestMode4();
    }

    public void clickRequest(final String cityId){
        Log.e("perfect-mvp","点击事件 P ");
        if(getMvpView() != null){
            getMvpView().requestLoading();
        }
        //模拟网络延迟，可以显示出加载中
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mRequestMode.request(cityId, new Callback<WeatherBean>() {
                    @Override
                    public void onResponse(Call<WeatherBean> call, Response<WeatherBean> response) {
                        Log.e("perfect-mvp","点击事件 P  onResponse");
                        if(getMvpView() != null){
                            getMvpView().resultSuccess(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<WeatherBean> call, Throwable t) {
                        Log.e("perfect-mvp","点击事件 P  onFailure");
                        if(getMvpView() != null){
                            getMvpView().resultFailure(Log.getStackTraceString(t));
                        }
                    }
                });
            }
        },1000);
    }


    @Override
    public void onCreatePersenter(@Nullable Bundle savedState) {
        super.onCreatePersenter(savedState);
        if(savedState != null){
            Log.e("perfect-mvp","RequestPresenter5  onCreatePersenter 测试  = " + savedState.getString("test2") );
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e("perfect-mvp","RequestPresenter5  onSaveInstanceState 测试 " );
        outState.putString("test2","test_save2");
    }

    @Override
    public void onDestroyPersenter() {
        super.onDestroyPersenter();
    }

    public void interruptHttp(){
        mRequestMode.interruptHttp();
    }
}
