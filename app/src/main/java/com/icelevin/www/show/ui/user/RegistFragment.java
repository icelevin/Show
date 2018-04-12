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

import com.hb.utils.tools.LogUtils;
import com.hb.utils.tools.Utils;
import com.hb.utils.view.TitleView;
import com.hb.utils.view.fragment.BaseFragment;
import com.icelevin.www.show.R;
import com.icelevin.www.show.model.UserInfo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by ice on 2017/10/30.
 */

public class RegistFragment extends BaseFragment implements View.OnClickListener {
    private Button btn_regist;
    private EditText et_userName, et_password, et_ag;
    private ImageView iv_dele, iv_delete, iv_del;
    private CheckBox checkbox, checkbox1;
    private TitleView titleView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_regist, container, false);
    }

    @Override
    protected void initView() {
        btn_regist = (Button) getView().findViewById(R.id.btn_regist);
        et_userName = (EditText) getView().findViewById(R.id.et_userName);
        et_password = (EditText) getView().findViewById(R.id.et_password);
        et_ag = (EditText) getView().findViewById(R.id.et_ag);
        iv_dele = (ImageView) getView().findViewById(R.id.iv_dele);
        iv_delete = (ImageView) getView().findViewById(R.id.iv_delete);
        checkbox = (CheckBox) getView().findViewById(R.id.checkbox);
        checkbox1 = (CheckBox) getView().findViewById(R.id.checkbox1);
        iv_del = (ImageView) getView().findViewById(R.id.iv_del);
        titleView = (TitleView) getView().findViewById(R.id.title_view);
        titleView.setTitleText("注册");

    }

    @Override
    protected void initData() {


    }

    @Override
    protected void initListener() {
        LoginFragment.addTextChangedListener(et_userName, iv_del);
        LoginFragment.addTextChangedListener(et_password, iv_dele);
        LoginFragment.addTextChangedListener(et_ag, iv_delete);
        LoginFragment.setOnCheckedChangeListener(checkbox, et_password);
        LoginFragment.setOnCheckedChangeListener(checkbox1, et_ag);
        btn_regist.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String userName = et_userName.getText().toString();
        String passWord = et_password.getText().toString();
        String confirm = et_ag.getText().toString();
        if (TextUtils.isEmpty(userName)) {
            showTost("用户名不能为空");
            return;
        }
        if (TextUtils.isEmpty(passWord)) {
            showTost("密码不能为空");
            return;
        }
        if (TextUtils.isEmpty(confirm)) {
            showTost("密码不能为空");
            return;
        }
        if (!passWord.equals(confirm)) {
            showTost("两次输入的密码不一致");
            return;
        }
        if (!checkUserName(userName)) {
            showTost("用户名格式不正确");
            return;
        }
        if (!checkPwd(passWord)) {
            showTost("密码格式不正确");
            return;
        }
        final BmobUser user=new BmobUser();
        user.setUsername(userName);
        user.setPassword(Utils.md5(passWord));
        user.setEmailVerified(false);
        user.signUp(new SaveListener<UserInfo>() {
            @Override
            public void done(UserInfo userInfo, BmobException e) {
                if (e == null) {
                    LogUtils.print("userinfo-->",userInfo.toString());
                    user.login(new SaveListener<UserInfo>() {
                        @Override
                        public void done(UserInfo userInfo1, BmobException e) {
                            if (e == null) {

                                getActivity().finish();

                            }else {
                                showTost("注册成功请登录");
                                getActivity().finish();
                            }
                        }
                    });
                }else{
                    showTost("注册失败请重新注册");
                    LogUtils.print("error-->",e.toString());
                }
            }
        });

    }

    public boolean checkUserName(String str) {
        //用户名正则，4到16位（字母，数字，下划线，减号）
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String pattern = "[A-Za-z0-9]{4,16}$";

        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        return m.matches();
    }

    public static boolean checkPwd(String str) {
        //密码由6-21字母和数字组成
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String pattern = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        return m.matches();
    }


}


