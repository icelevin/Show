package com.icelevin.www.show.ui.user;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import com.hb.utils.tools.CheckUtils;
import com.hb.utils.tools.LogUtils;
import com.hb.utils.tools.Utils;
import com.hb.utils.view.fragment.BaseFragment;
import com.icelevin.www.show.R;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by ice on 2017/11/3.
 */

public class UpdatePwdFragment extends BaseFragment {
    private Button btn_confirm;
    private EditText et_base, et_2, et_3;
    private ImageView iv_delete3, iv_delete1, iv_delete2;
    private CheckBox checkbox1, checkbox2, checkbox3;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_update_pwd, container, false);
    }

    @Override
    protected void initView() {
        btn_confirm = (Button) getView().findViewById(R.id.btn_confirm);
        et_base = (EditText) getView().findViewById(R.id.et_base);
        et_2 = (EditText) getView().findViewById(R.id.et_2);
        et_3 = (EditText) getView().findViewById(R.id.et_3);
        iv_delete3 = (ImageView) getView().findViewById(R.id.iv_delete3);
        iv_delete1 = (ImageView) getView().findViewById(R.id.iv_delete1);
        iv_delete2 = (ImageView) getView().findViewById(R.id.iv_delete2);
        checkbox1 = (CheckBox) getView().findViewById(R.id.checkbox1);
        checkbox2 = (CheckBox) getView().findViewById(R.id.checkbox2);
        checkbox3 = (CheckBox) getView().findViewById(R.id.checkbox3);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        LoginFragment.setOnCheckedChangeListener(checkbox1, et_base);
        LoginFragment.setOnCheckedChangeListener(checkbox2, et_2);
        LoginFragment.setOnCheckedChangeListener(checkbox3, et_3);
        LoginFragment.addTextChangedListener(et_base, iv_delete1);
        LoginFragment.addTextChangedListener(et_2, iv_delete2);
        LoginFragment.addTextChangedListener(et_3, iv_delete3);
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(et_base.getText().toString())) {
                    showTost("原密码不能为空");
                    return;
                }
                if (TextUtils.isEmpty(et_2.getText().toString())) {
                    showTost("请输入新密码");
                    return;
                }
                if (TextUtils.isEmpty(et_3.getText().toString())) {
                    showTost("请再次输入新密码");
                }
                if (!et_2.getText().toString().equals(et_3.getText().toString())) {
                    showTost("两次输入密码不一致");
                    return;
                }
                if (!RegistFragment.checkPwd(et_3.getText().toString())) {
                    showTost("密码格式错误");
                    return;
                }
                String oldPwd = Utils.md5(et_base.getText().toString());
                String newPwd = Utils.md5(et_3.getText().toString());
                BmobUser.updateCurrentUserPassword(oldPwd, newPwd, new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if (e == null) {
                            BmobUser.logOut();
                            showTost("修改密码成功请重新登陆");
                        } else {
                            showTost("修改失败,请重试");
                            LogUtils.print("失败" ,e.toString());
                        }
                    }
                });
            }
        });
    }
}
