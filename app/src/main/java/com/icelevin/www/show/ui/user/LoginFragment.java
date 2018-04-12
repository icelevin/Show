package com.icelevin.www.show.ui.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.hb.utils.tools.CheckUtils;
import com.hb.utils.tools.LogUtils;
import com.hb.utils.tools.Utils;
import com.hb.utils.view.TitleView;
import com.hb.utils.view.activity.FragmentContainerActivity;
import com.hb.utils.view.fragment.BaseFragment;
import com.icelevin.www.show.R;
import com.icelevin.www.show.model.UserInfo;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by ice on 2017/10/30.
 */

public class LoginFragment extends BaseFragment {
    private TitleView titleView;
    private EditText et_username, et_pwd;
    private Button btn_login;
    private ImageView iv_del, iv_dele;
    private CheckBox checkBox;
    private TextView tv_rigest, tv_forget;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    protected void initView() {
        titleView = (TitleView) getView().findViewById(R.id.title_view);
        titleView.setTitleText("登录/注册");
        et_username = (EditText) getView().findViewById(R.id.et_userName);
        et_pwd = (EditText) getView().findViewById(R.id.et_password);
        btn_login = (Button) getView().findViewById(R.id.btn_login);
        iv_del = (ImageView) getView().findViewById(R.id.iv_del);
        iv_dele = (ImageView) getView().findViewById(R.id.iv_dele);
        checkBox = (CheckBox) getView().findViewById(R.id.checkbox);
        tv_rigest = (TextView) getView().findViewById(R.id.tv_rigest);
        tv_forget = (TextView) getView().findViewById(R.id.tv_forget);

    }

    @Override
    protected void initData() {

    }

    public static void addTextChangedListener(final EditText editText, final ImageView img) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (editText.length() > 0) {
                    img.setVisibility(View.VISIBLE);
                    img.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            editText.setText("");
                        }
                    });
                } else {
                    img.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    img.setVisibility(View.GONE);
                } else {
                    if (editText.length() > 0)
                        img.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    public static void setOnCheckedChangeListener(final CheckBox checkBox, final EditText editText) {
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    editText.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    checkBox.setButtonDrawable(R.mipmap.hide_pwd);
                    editText.setSelection(editText.length());
                } else {
                    editText.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
                    checkBox.setButtonDrawable(R.mipmap.show_password);
                    editText.setSelection(editText.length());
                }
            }
        });
    }

    @Override
    protected void initListener() {
        addTextChangedListener(et_username, iv_del);
        addTextChangedListener(et_pwd, iv_dele);
        setOnCheckedChangeListener(checkBox, et_pwd);


        tv_forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        tv_rigest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FragmentContainerActivity.class);
                intent.putExtra(FragmentContainerActivity.VALUENAME, RegistFragment.class.getName());
                startActivity(intent);
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = et_username.getText().toString();
                String pwd = Utils.md5(et_pwd.getText().toString());
                if (userName.length() < 1) {
                    showTost("用户名不能为空");
                    return;
                }
                if (pwd.length() < 1) {
                    showTost("密码不能为空");

                    return;
                }
                if (CheckUtils.checkEmaile(userName)) {
                    BmobUser.loginByAccount(userName, pwd, new LogInListener<UserInfo>() {
                        @Override
                        public void done(UserInfo userInfo, BmobException e) {
                            if (e == null) {
                                showTost("登陆成功");

                                getActivity().finish();
                            } else {
                                String msg=e.toString();
                                CharSequence c="101";
                                if (msg.contains(c)) {
                                    showTost("用户名或密码不正确");
                                }
                                LogUtils.print("error-->", e.toString());
                            }
                        }
                    });
                    return;
                }
                BmobUser user = new BmobUser();
                user.setUsername(userName);
                user.setPassword(pwd);
                user.login(new SaveListener<UserInfo>() {
                    @Override
                    public void done(UserInfo userInfo, BmobException e) {
                        if (e == null) {
                            showTost("登陆成功");

                            getActivity().finish();
                        } else {
                            String msg=e.toString();
                            CharSequence c="101";
                            if (msg.contains(c)) {
                                showTost("用户名或密码不正确");
                            }
                            LogUtils.print("error-->", e.toString());
                        }
                    }
                });
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (BmobUser.getCurrentUser(UserInfo.class) != null)
            getActivity().finish();
    }
}
