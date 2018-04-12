package com.icelevin.www.show.ui.user;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hb.utils.view.TitleView;
import com.hb.utils.view.fragment.BaseFragment;
import com.icelevin.www.show.R;
import com.icelevin.www.show.model.UserInfo;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by ice on 2017/11/3.
 */

public class InputFragment extends BaseFragment implements View.OnClickListener {
    private EditText et_content;
    private Button btn_commit;
    private TitleView titleView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_input, container, false);
    }

    @Override
    protected void initView() {
        et_content = (EditText) getView().findViewById(R.id.et_content);
        btn_commit = (Button) getView().findViewById(R.id.btn_commit);
        titleView = getView().findViewById(R.id.title_view);
        titleView.setTitleText("修改昵称");

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        btn_commit.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        showProgressDialog();
        String et = et_content.getText().toString().trim();
        if (TextUtils.isEmpty(et)) {
            showTost("昵称不能为空");
            return;
        }
        UserInfo currentUser = BmobUser.getCurrentUser(UserInfo.class);
        currentUser.setNickName(et);
        currentUser.update(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    dismisProgressDialog();
                    showTost("修改成功");
                    getActivity().setResult(Activity.RESULT_OK);
                    getActivity().finish();
                } else {
                    showTost(e.getMessage());
                }
            }

        });
    }
}
