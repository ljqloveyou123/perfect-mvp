package com.ljq.mvp.demo5;

import com.ljq.mvp.request.WeatherBean;
import com.ljq.mvpframework.view.BaseMvpView;

/**
 * @author 刘镓旗
 * @date 2017/11/17
 * @description
 */
public interface RequestView5 extends BaseMvpView{
    void requestLoading();
    void resultSuccess(WeatherBean result);
    void resultFailure(String result);
}
