package com.ljq.mvp.demo4.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * @author 刘镓旗
 * @date 2017/11/17
 * @description MvpActivity
 * 指定子类具体的View必须继承自IMvpBaseView4
 * 指定子类具体的Presenter必须继承自AbstractMvpPersenter4
 */
public abstract class AbstractMvpActivity<V extends IMvpBaseView4, P extends AbstractMvpPersenter4<V>> extends AppCompatActivity implements IMvpBaseView4 {

    private P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (presenter == null) {
            presenter = createPresenter();
        }

        if (presenter == null) {
            throw new NullPointerException("presenter 不能为空!");
        }
        //绑定view
        presenter.attachMvpView((V) this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解除绑定
        if (presenter != null) {
            presenter.detachMvpView();
        }
    }

    /**
     * 创建Presenter
     * @return 子类自己需要的Presenter
     */
    protected abstract P createPresenter();

    /**
     * 获取Presenter
     * @return 返回子类创建的Presenter
     */
    public P getPresenter() {
        return presenter;
    }
}
