package com.ljq.mvp.demo4.base;

/**
 * @author 刘镓旗
 * @date 2017/11/17
 * @description 指定绑定的View必须继承自IMvpBaseView4
 */
public abstract class AbstractMvpPersenter4<V extends IMvpBaseView4> {

    private V mMvpView;

    /**
     * 绑定V层
     * @param view
     */
    public void attachMvpView(V view){
        this.mMvpView = view;
    }

    /**
     * 解除绑定V层
     */
    public void detachMvpView(){
        mMvpView = null;
    }

    /**
     * 获取V层
     * @return
     */
    public V getmMvpView() {
        return mMvpView;
    }
}
