package com.ljq.mvp.demo3.base;

/**
 * @author 刘镓旗
 * @date 2017/11/16
 * @description MVP架构中所有Presenter的基类
 */
public abstract class AbstractMvpPersenter3<V extends MvpView3> {
    /**
     * V层引用
     */
    private V mMvpView;

    /**
     * 绑定V层
     * @param v
     */
    public void attachMvpView(V v){
        this.mMvpView = v;
    }

    /**
     * 解除绑定V层
     */
    public void detachMvpView(){
        this.mMvpView = null;
    }

    /**
     * 获取V层引用
     * @return 返回V层
     */
    public V getMvpView() {
        return mMvpView;
    }
}
