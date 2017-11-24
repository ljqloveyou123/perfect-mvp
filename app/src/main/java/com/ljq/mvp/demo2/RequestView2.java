package com.ljq.mvp.demo2;

import com.ljq.mvp.request.WeatherBean;

/**
 * @author 刘镓旗
 * @date 2017/11/16
 * @description V层接口
 */
public interface RequestView2 {
    void requestLoading();
    void resultSuccess(WeatherBean result);
    void resultFailure(String result);
}
