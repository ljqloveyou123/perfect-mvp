package com.ljq.mvp.demo2;

import com.ljq.mvp.request.ApiService;
import com.ljq.mvp.request.WeatherBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * @author 刘镓旗
 * @date 2017/11/16
 * @description M层
 */
public class RequestMode2 {

    private static final String BASE_URL = "http://www.weather.com.cn/";
    private Call<WeatherBean> weatherBeanCall;

    /**
     * 请求
     * @param cityId
     * @param callback
     */
    public void request(String cityId, Callback<WeatherBean> callback){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        weatherBeanCall = apiService.requestWeather(cityId);
        weatherBeanCall.enqueue(callback);
    }

    /**
     * 取消网络请求
     */
    public void interruptHttp() {
        if(weatherBeanCall != null && !weatherBeanCall.isCanceled()){
            weatherBeanCall.cancel();
        }
    }
}
