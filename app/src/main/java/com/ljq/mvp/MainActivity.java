package com.ljq.mvp;


//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.view.View;
//import android.widget.TextView;
//
//import com.ljq.mvp.request.ApiService;
//import com.ljq.mvp.request.WeatherBean;
//import com.ljq.mvp.util.FieldView;
//import com.ljq.mvp.util.ViewFind;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//import retrofit2.converter.scalars.ScalarsConverterFactory;
//
///**
// * 第一步:直接打开注释代码
// * //1.平时不使用mvp的时候
// */
//public class MainActivity extends AppCompatActivity {
//    @FieldView(R.id.tv_text)
//    private TextView textView;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        ViewFind.bind(this);
//    }
//
//
//    public void request(View view) {
//        clickRequest("101010100");
//    }
//
//    public void clickRequest(final String cityId) {
//        //请求接口
//        Retrofit retrofit = new Retrofit.Builder()
//                //代表root地址
//                .baseUrl("http://www.weather.com.cn/")
//                .addConverterFactory(ScalarsConverterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        ApiService apiService = retrofit.create(ApiService.class);
//
//        //请求
//        Call<WeatherBean> weatherBeanCall = apiService.requestWeather(cityId);
//
//        weatherBeanCall.enqueue(new Callback<WeatherBean>() {
//            @Override
//            public void onResponse(Call<WeatherBean> call, Response<WeatherBean> response) {
//                //成功
//                textView.setText(response.body().getWeatherinfo().toString());
//            }
//
//            @Override
//            public void onFailure(Call<WeatherBean> call, Throwable t) {
//                //失败
//            }
//        });
//    }
//
//}

//---------------------------------------------------------------------------------------------------


//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.view.View;
//import android.widget.TextView;
//
//import com.ljq.mvp.demo1.RequestPresenter1;
//import com.ljq.mvp.demo1.RequestView1;
//import com.ljq.mvp.util.FieldView;
//import com.ljq.mvp.util.ViewFind;
//import com.ljq.mvp.request.WeatherBean;
//
//    /**
//     *   第二步：对应demo1
//     * 2，最简单的mvp模式,
//     * 1.Activity需要实现v层接口
//     * 2.Persenter需要持有v层引用和m层引用
//     * 3.在实现类view中创建persenter
//     *
//     */
//public class MainActivity extends AppCompatActivity implements RequestView1 {
//
//    @FieldView(R.id.tv_text)
//    private TextView textView;
//    private RequestPresenter1 presenter;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        ViewFind.bind(this);
//
//        //为了第二种
//        presenter = new RequestPresenter1(this);
//    }
//
//    //点击事件
//    public void request(View view) {
//        presenter.clickRequest("101010100");
//    }
//
//    @Override
//    public void requestLoading() {
//        textView.setText("请求中,请稍后...");
//    }
//
//    @Override
//    public void resultSuccess(WeatherBean result) {
//        //成功
//        textView.setText(result.getWeatherinfo().toString());
//    }
//
//    @Override
//    public void resultFailure(String result) {
//        //失败
//        textView.setText(result);
//    }
//}


//---------------------------------------------------------------------------------------------------

//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.view.View;
//import android.widget.TextView;
//
//import com.ljq.mvp.demo2.RequestPresenter2;
//import com.ljq.mvp.demo2.RequestView2;
//import com.ljq.mvp.util.FieldView;
//import com.ljq.mvp.util.ViewFind;
//import com.ljq.mvp.request.WeatherBean;
//
//    /**
//     *   第三步：对应demo2
//     * 上面的问题：
//     * 1.是会内存泄露，因为persenter一直持有Activity，如果一个发了一个请求，但是网络有点慢，这个时候退出Activity，那么请求回来后还是会调用
//     * Activity的回调方法，这里还是因为一直持有的问题
//     * 2.如果已经退出了当前界面，这个请求也没有用了，这个时候我们可以断开请求
//     * <p>
//     * 解决问题：
//     * 1.增加绑定和解绑的方法来解决内存泄露和退出后还会回调的问题
//     * 2、增加断开网络连接的方法
//     */
//public class MainActivity extends AppCompatActivity implements RequestView2 {
//
//    @FieldView(R.id.tv_text)
//    private TextView textView;
//    private RequestPresenter2 presenter;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        ViewFind.bind(this);
//        presenter = new RequestPresenter2();
//        presenter.attach(this);
//    }
//
//
//    public void request(View view) {
//        presenter.clickRequest("101010100");
//    }
//
//    @Override
//    public void requestLoading() {
//        textView.setText("请求中,请稍后...");
//    }
//
//    @Override
//    public void resultSuccess(WeatherBean result) {
//        //成功
//        textView.setText(result.getWeatherinfo().toString());
//    }
//
//    @Override
//    public void resultFailure(String result) {
//        //失败
//        textView.setText(result);
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        presenter.detach();
//        presenter.interruptHttp();
//    }
//
//}

//---------------------------------------------------------------------------------------------------

//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.view.View;
//import android.widget.TextView;
//
//import com.ljq.mvp.demo3.RequestPresenter3;
//import com.ljq.mvp.demo3.RequestView3;
//import com.ljq.mvp.util.FieldView;
//import com.ljq.mvp.util.ViewFind;
//import com.ljq.mvp.request.WeatherBean;
//
///**
// * 第四步:对应demo3
// * 上面的问题：
// * 1.Presenter中attach和detach每个Presenter都需要定义这个方法
// * 解决问题：
// * 抽象出basePresenter
// * 但是如果抽象出BasePresenter，那么attach方法中的view就不能写死，那么就需要泛型设计
// */
//public class MainActivity extends AppCompatActivity implements RequestView3 {
//
//    @FieldView(R.id.tv_text)
//    private TextView textView;
//    private RequestPresenter3 presenter;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        ViewFind.bind(this);
//        presenter = new RequestPresenter3();
//        presenter.attachMvpView(this);
//    }
//
//
//    public void request(View view) {
//        presenter.clickRequest("101010100");
//    }
//
//    @Override
//    public void requestLoading() {
//        textView.setText("请求中,请稍后...");
//    }
//
//    @Override
//    public void resultSuccess(WeatherBean result) {
//        //成功
//        textView.setText(result.getWeatherinfo().toString());
//    }
//
//    @Override
//    public void resultFailure(String result) {
//        //失败
//        textView.setText(result);
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        presenter.detachMvpView();
//        presenter.interruptHttp();
//    }
//
//}


//---------------------------------------------------------------------------------------------------

//import android.os.Bundle;
//import android.view.View;
//import android.widget.TextView;
//
//import com.ljq.mvp.demo3.RequestPresenter3;
//import com.ljq.mvp.demo4.RequestPresenter4;
//import com.ljq.mvp.demo4.RequestView4;
//import com.ljq.mvp.demo4.base.AbstractMvpActivity;
//import com.ljq.mvp.request.WeatherBean;
//import com.ljq.mvp.util.FieldView;
//import com.ljq.mvp.util.ViewFind;
//
///**
// * 第五步:对应demo4
// * 上面的问题：
// * 1.每个Activity都需要调用attach和detach两个方法，
// * 解决问题：
// * 我们可以将他们抽到基类中
// */
//public class MainActivity extends AbstractMvpActivity<RequestView4, RequestPresenter4> implements RequestView4 {
//
//    @FieldView(R.id.tv_text)
//    private TextView textView;
//    private RequestPresenter3 presenter;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        ViewFind.bind(this);
//    }
//
//    /**
//     * 创建Presenter
//     * @return RequestPresenter4
//     */
//    @Override
//    protected RequestPresenter4 createPresenter() {
//        return new RequestPresenter4();
//    }
//
//
//    //点击事件
//    public void request(View view) {
//        getPresenter().clickRequest("101010100");
//    }
//
//
//    @Override
//    public void requestLoading() {
//        textView.setText("请求中,请稍后...");
//    }
//
//    @Override
//    public void resultSuccess(WeatherBean result) {
//        //成功
//        textView.setText(result.getWeatherinfo().toString());
//    }
//
//    @Override
//    public void resultFailure(String result) {
//        //失败
//        textView.setText(result);
//    }
//}


//---------------------------------------------------------------------------------------------------

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.ljq.mvp.demo5.RequestPresenter5;
import com.ljq.mvp.demo5.RequestView5;
import com.ljq.mvp.request.WeatherBean;
import com.ljq.mvp.util.FieldView;
import com.ljq.mvp.util.ViewFind;
import com.ljq.mvpframework.factory.CreatePresenter;
import com.ljq.mvpframework.view.AbstractMvpAppCompatActivity;

/**
 * 第六步:对应demo5,终极封装
 * 上面的问题：
 * 1.在AbstractMvpActivity中，绑定View，解绑View这些方法，如果现在再创建一个AbstractMvpFragment或者
 * AbstractMvpView、或者是一个AbstractMvpXXX等，那么这些代码全部都要再写一遍，代码冗余，可以抽出去
 * <p>
 * 2.实现AbstractMvpActivity的实现类中创建Presenter的方法，每个实现类都要写new，这一步父类完全可以帮忙解决
 *
 * <p>
 * 3.如果Activity或者Fragment或者View等V层意外销毁，那么我们的Presenter也没有必要存在了，界面都没有了还
 * 要这个有什么用，但是如果又被重启
 * 那么我们还需要再恢复Presenter，也就是说让我们的Presenter和View的生命周期同步，这样才算完美
 * <p>
 * 4.创建Presenter的动作放到使用时再创建，可以稍微优化一下性能问题
 * <p>
 * 解决问题：
 * 1.使用代理模式将绑定和解绑view的操作抽离出来
 * 定义绑定解绑
 * <p>
 * 2.使用工厂模式和注解在上层统一创建Presenter
 * <p>
 * 3.序列化保存Presenter，销毁Presenter对象，如果View重建反序列化重新获取Presenter
 * <p>
 * 4.将Presenter的创建过程放入到获取Presenter的方法中，如果存在返回，不存在创建，保证Presenter不会为空
 * ，而且这样还可以保证在使用的时候再创建，节省内存资源
 */
@CreatePresenter(RequestPresenter5.class)
public class MainActivity extends AbstractMvpAppCompatActivity<RequestView5, RequestPresenter5> implements RequestView5 {

    @FieldView(R.id.tv_text)
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewFind.bind(this);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,SecondActivity.class));
            }
        });
        if(savedInstanceState != null){
            Log.e("perfect-mvp","MainActivity  onCreate 测试  = " + savedInstanceState.getString("test") );
        }
    }


    //点击事件
    public void request(View view) {
        Log.e("perfect-mvp","点击事件");
        getMvpPresenter().clickRequest("101010100");
    }


    @Override
    public void requestLoading() {
        textView.setText("请求中,请稍后...");
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e("perfect-mvp","MainActivity onSaveInstanceState 测试");
        outState.putString("test","test_save");
    }

    @Override
    public void resultSuccess(WeatherBean result) {
        //成功
        textView.setText(result.getWeatherinfo().toString());
    }

    @Override
    public void resultFailure(String result) {
        //失败
        textView.setText(result);
    }

}