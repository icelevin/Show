package com.icelevin.www.show.ui.travel;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;

import com.hb.utils.net.listener.OnResultListener;
import com.hb.utils.view.activity.FragmentContainerActivity;
import com.hb.utils.view.fragment.BaseFragment;
import com.hb.utils.view.recycler.ParametersBean;
import com.hb.utils.view.recycler.RecyclerLoadListener;
import com.hb.utils.view.recycler.RecyclerViewFragment;
import com.hb.utils.view.recycler.adapter.BaseRecyclerAdapter;
import com.icelevin.www.show.R;
import com.icelevin.www.show.adapter.QiongYouScenicAdapter;
import com.icelevin.www.show.model.QiongYouScenicModel;
import com.icelevin.www.show.net.NetWorkController;

/**
 * Created by ice on 2018/1/4.
 */

public class SearchScenicFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    private EditText et_content;
    private TextView tv_search;
    private RecyclerViewFragment fragment;
    private String kw;
    private QiongYouScenicAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search_scenic, container, false);
    }

    @Override
    protected void initView() {
        et_content = (EditText) getView().findViewById(R.id.et_content);
        tv_search = (TextView) getView().findViewById(R.id.tv_search);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        tv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kw = et_content.getText().toString().trim();
                if (TextUtils.isEmpty(kw)) {
                    showTost(et_content.getHint().toString());
                    return;
                }
                initDatas();
                fragment.Refresh();
            }
        });
        et_content.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                //当actionId == XX_SEND 或者 XX_DONE时都触发
                //或者event.getKeyCode == ENTER 且 event.getAction == ACTION_DOWN时也触发
                //注意，这是一定要判断event != null。因为在某些输入法上会返回null。
                kw = et_content.getText().toString().trim();
                if (TextUtils.isEmpty(kw)) {
                    showTost(et_content.getHint().toString());
                    return false;
                }
                if (actionId == EditorInfo.IME_ACTION_SEND
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {
                    initDatas();
                    fragment.Refresh();
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(FragmentActivity.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                    return true;
                }
                return false;
            }
        });
    }

    private void initDatas() {
        ParametersBean bean = new ParametersBean();
        bean.setFragmentParentId(R.id.fragment_parent);
        fragment = RecyclerViewFragment.getInstance(getChildFragmentManager(), bean);
        fragment.setLoadListener(new RecyclerLoadListener<QiongYouScenicModel>() {
            @Override
            public BaseRecyclerAdapter setAdapter() {
                adapter = new QiongYouScenicAdapter(getActivity(), null, SearchScenicFragment.this);
                fragment.getRecyclerView().setAdapter(adapter);
                return adapter;
            }

            @Override
            public void onLoad(ParametersBean parametersBean, OnResultListener<QiongYouScenicModel> listener) {
                NetWorkController.INSTANCE.searchQiongYouScenic(kw, parametersBean.getCurPage() + "", listener);
            }

            @Override
            public void onResult(QiongYouScenicModel model) {
                if (getActivity() == null || getActivity().isFinishing()) {
                    return;
                }
                if (!"000000".equals(model.getRetcode())) {
                    fragment.onResult(-1, model.getMessage(), null);
                    return;
                }
                fragment.onResult(200, model.getMessage(), model.getData());
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), FragmentContainerActivity.class);
        intent.putExtra(FragmentContainerActivity.VALUENAME, ScenicInfoFragment.class.getName());
        QiongYouScenicModel.Data data = adapter.getList().get(position);
        intent.putExtra(QiongYouScenicModel.Data.class.getName(), data);
        startActivity(intent);
    }

}
