该库是一个对MVP模式的封装，主要目的解决MVP的内存泄露、代码冗余、Presenter的生命周期管理等问题

如果你想知道为何最终这么封装的过程和思路，请看：

博客地址：http://blog.csdn.net/yulong0809/article/details/78622428


如果你不想知道的话，也可以直接下载perfect-mvp的Module直接使用，

使用方法：

1.  在V层类上生命需要创建的Presenter注解，泛型中声明具体V层接口和Presenter类型，实现自己的V层接口
	View接口必须继承BaseMvpView，Presenter必须继承BaseMvpPresenter，
	
	//声明需要创建的Presenter
	@CreatePresenter(RequestPresenter5.class)
	public class MainActivity extends AbstractMvpAppCompatActivity<RequestView5, RequestPresenter5> implements RequestView5 {

	。。。

	}	

	
2.如何想通过自己的方法创建Presenter，必须在
调用onResume方法和getMvpPresenter方法之前设置，可以在onCreate方法中设置

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //设置自己的Presenter工厂，如果你想自定义的话
		// setPresenterFactory(xxx);

    }
	
	
3.V层使用Presenter直接掉到getMvpPresenter()即可，Presenter中使用View可以直接调用getMvpView()即可


4.如果界面意外销毁,Presenter可以通过重写以下方法进行释放资源，存储数据，和恢复数据

    /**
     * Presenter被创建后调用
     *
     * @param savedState 被意外销毁后重建后的Bundle
     */
    public void onCreatePersenter(@Nullable Bundle savedState) {
    }
  
    /**
     * Presenter被销毁时调用
     */
    public void onDestroyPersenter() {
    }

    /**
     * 在Presenter意外销毁的时候被调用，它的调用时机和Activity、Fragment、View中的onSaveInstanceState
     * 时机相同
     *
     * @param outState
     */
    public void onSaveInstanceState(Bundle outState) {
    }

	
