package com.ljq.mvp.demo4;

import com.ljq.mvp.demo4.base.IMvpBaseView4;
import com.ljq.mvp.request.WeatherBean;

/**
 * @author 刘镓旗
 * @date 2017/11/17
 * @description
 */
public interface RequestView4  extends IMvpBaseView4{
    void requestLoading();
    void resultSuccess(WeatherBean result);
    void resultFailure(String result);
}
