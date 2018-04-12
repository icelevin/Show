package com.hb.utils.view.recycler;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.hb.utils.R;
import com.hb.utils.view.fragment.BaseFragment;


public abstract class RecyclerBaseFragment extends BaseFragment {

    protected ParametersBean parametersBean = new ParametersBean();
//    protected int pageCount = 1;
//    protected int pagesize = 10; // 每页加载数据个数
//    protected int curpage = 1; // 当前页面
    protected int loadPage = 1;
    // 访问第一页时无数据时显示的状态
    private boolean isShowNullPage = true;

    protected TipsFragment tipFragment;

    /**
     * 开始加载数据
     */
    public static final int MSG_UI_START_LOADING = 1;
    /**
     * 数据加载成功，且数据个数不为0
     */
    public static final int MSG_UI_LOAD_SUCCESS = 2;
    /**
     * 数据加载失败
     */
    public static final int MSG_UI_LOAD_FAILE = 3;
    /**
     * 成功加载，但没数据
     */
    public static final int MSG_UI_LOAD_NO_DATA = 4;
    /**
     * 当有缓存数据时
     */
    public static final int DATABASE_LOED_SUCCESS = 5;
    /**
     * 已经全部加载数据
     */
    public static final int MSG_ALL_DATA_HAVE_LOADED = 6;
    /**
     * 网络错误
     */
    public static final int MSG_NET_ERROR = 7;

    // 四个页面的提示语句
    private String loadingMsg, load_null, load_faile, net_error;

    public RecyclerBaseFragment() {
    }

    protected Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            String message = "";
            if (msg.obj != null) {
                message = msg.obj.toString().trim();
            }

            if(tipFragment != null){
                tipFragment.setHintMessage(loadingMsg, load_null, load_faile, net_error);
            }

            switch (msg.what) {
                case DATABASE_LOED_SUCCESS:
                    // 当从数据库获取到数据时
                    Log.e("BaseWorkerFragment", "当从数据库获取到数据时");
                    // 当从数据库获取到数据时
                    tipFragment.setHind();
                    break;
                case MSG_UI_START_LOADING:
                    Log.e("BaseWorkerFragment", "开始加载数据");
                    // 开始刷新数据
                    if (tipFragment.isShow()) {
                        tipFragment.showloading(message);
                    }
                    break;
                case MSG_UI_LOAD_SUCCESS:
                    Log.e("BaseWorkerFragment", "数据加载成功，且数据条数不为0");
                    // 数据加载成功，且数据条数不为0
                    parametersBean.setCurPage(loadPage);
                    tipFragment.setHind();
                    break;
                case MSG_UI_LOAD_FAILE:
                    Log.e("BaseWorkerFragment", "数据加载失败");
                    // 数据加载失败
                    loadPage = parametersBean.getCurPage();
                    if (loadPage != 1 || !tipFragment.isShow()) {
                        Log.e("BaseWorkerFragment", "showloadToast");
                    } else {
                        Log.e("BaseWorkerFragment", "TipsLayout.TYPE_FAILE");
                        tipFragment.showFaile(TipsFragment.TipType.TYPE_FAILE, message);
                    }
                    break;
                case MSG_ALL_DATA_HAVE_LOADED:
                    // 已经没有更多的数据了
                    Log.e("BaseWorkerFragment", "已经没有更多的数据了");
                    if (loadPage != 1) {
                        Log.e("BaseWorkerFragment", "loadPage != 1");
                    } else {

                    }
                    if(tipFragment.isShow()){
                        tipFragment.setHind();
                        parametersBean.setCurPage(loadPage);
                    }else{

                    }
                    break;
                case MSG_UI_LOAD_NO_DATA:
                    Log.e("BaseWorkerFragment", "数据加载成功,但数据条数为0");
                    // 数据加载成功,但数据条数为0
                    loadPage = parametersBean.getCurPage();

                    if (loadPage == 1 && isShowNullPage) {
                        tipFragment.showNull(message);
                    }
                    break;
                case MSG_NET_ERROR:
                    loadPage = parametersBean.getCurPage();
                    Log.e("BaseWorkerFragment", "TipsLayout.type_net_error");
                    if (tipFragment.isShow()) {
                        tipFragment.showNull(message);
                    }
                    break;
            }
            // 将消息传递给子类
            onHanderMessage(msg);
        }
    };

    /**
     * 将hander的消息通知给子类
     *
     * @param msg
     */
    protected abstract void onHanderMessage(Message msg);

    @Override
    protected void initView() {
        if(tipFragment != null){
            return;
        }

        // 控制状态显示的页面。如没网络，没数据，加载失败等
        tipFragment = (TipsFragment) getChildFragmentManager().findFragmentById(R.id.tip_parent);
        if (tipFragment == null) {
            tipFragment = new TipsFragment();
            getChildFragmentManager().beginTransaction().add(R.id.tip_parent, tipFragment).commitAllowingStateLoss();
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

        tipFragment.setRefreshListener(new TipsFragment.RefreshListener() {
            @Override
            public void onRefresh() {
                Refresh();
            }
        });
    }

    /**
     * 设置访问第一页时无数据时显示的状态
     *
     * @param isShowNullPage true:在第一页中，即使已经有旧数据，也需要显示空数据页面 false:在第一页中，如果已经有了旧数据，不再显示空数据页面
     */
    public void setNullDateState(boolean isShowNullPage) {
        this.isShowNullPage = isShowNullPage;
    }

    /**
     * 进行刷新操作
     */
    public void Refresh() {
        loadPage = 1;
        sendEmptyMessage(MSG_UI_START_LOADING);
    }

    /**
     * 返回是否还有下一页
     *
     * @return
     */
    public boolean hasNextPage() {
        return parametersBean.getPageCount() > parametersBean.getCurPage();
    }

    /**
     * 加载下一页
     */
    public void loadNextPage() {
        loadPage = parametersBean.getCurPage() + 1;
        sendEmptyMessage(MSG_UI_START_LOADING);
    }

    /**
     * 设置提示语句
     *
     * @param loadingMsg 正在加载
     * @param load_null  数据为空
     * @param load_faile 加载失败
     * @param net_error  网络错误
     */
    public void setHideMessage(String loadingMsg, String load_null, String load_faile, String net_error) {
        this.loadingMsg = loadingMsg;
        this.load_null = load_null;
        this.load_faile = load_faile;
        this.net_error = net_error;

        if(tipFragment != null){
            tipFragment.setHintMessage(loadingMsg, load_null, load_faile, net_error);
        }
    }

    /**
     * 发送空消息给handler
     *
     * @param actionType
     */
    public void sendEmptyMessage(int actionType) {
        if (getActivity() == null || getActivity().isFinishing()) {
            return;
        }

        if (handler != null) {
            handler.sendEmptyMessage(actionType);
        }
    }

    /**
     * 发送包含内容的消息给handle
     *
     * @param actionType
     * @param obj
     */
    public void sendMessage(int actionType, Object obj) {
        if (getActivity() == null || getActivity().isFinishing()) {
            return;
        }

        if (handler != null) {
            Message msg = new Message();
            msg.obj = obj;
            msg.what = actionType;
            handler.sendMessage(msg);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
