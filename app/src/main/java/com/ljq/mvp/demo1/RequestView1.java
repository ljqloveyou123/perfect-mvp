package com.ljq.mvp.demo1;

import com.ljq.mvp.request.WeatherBean;

/**
 * @author 刘镓旗
 * @date 2017/11/16
 * @description V层接口，定义P层和V层的方法
 */
public interface RequestView1 {
    void requestLoading();
    void resultSuccess(WeatherBean result);
    void resultFailure(String result);
}
