package com.icelevin.www.show.ui.user;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hb.utils.view.TitleView;
import com.hb.utils.view.activity.FragmentContainerActivity;
import com.hb.utils.view.fragment.BaseFragment;
import com.icelevin.www.show.R;
import com.icelevin.www.show.model.UserInfo;
import com.icelevin.www.show.ui.date.DatePickerDialogFragment;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.Date;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by ice on 2017/11/2.
 */

public class UserInfoFragment extends BaseFragment implements View.OnClickListener, DatePickerDialogFragment.OnSetDate {
    private RelativeLayout rl_icon, rl_username, rl_nickname, rl_birth, rl_phone, rl_email;
    private TextView tv_username, tv_nickname, tv_phone, tv_birth, tv_email, tv_regTime;
    private ImageView iv_icon;
    private TitleView titleView;
    private LinearLayout ll_weixin, ll_weibo, ll_qq;
    private TextView tv_weixin, tv_weibo, tv_qq, tv_commit;
    private Button btn_out;
    public static final int NICKNAME = 0x11;
    public static final int CELLPHONE = 0x12;
    public static final int HEADICON = 0x13;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_userinfo, container, false);
    }

    @Override
    protected void initView() {
        rl_icon = (RelativeLayout) getView().findViewById(R.id.rl_icon);
        rl_username = (RelativeLayout) getView().findViewById(R.id.rl_username);
        rl_nickname = (RelativeLayout) getView().findViewById(R.id.rl_nickname);
        rl_birth = (RelativeLayout) getView().findViewById(R.id.rl_birthday);
        rl_phone = (RelativeLayout) getView().findViewById(R.id.rl_phone);
        rl_email = (RelativeLayout) getView().findViewById(R.id.rl_email);

        tv_username = (TextView) getView().findViewById(R.id.tv_username);
        tv_nickname = (TextView) getView().findViewById(R.id.tv_nickname);
        tv_phone = (TextView) getView().findViewById(R.id.tv_phone);
        tv_birth = (TextView) getView().findViewById(R.id.tv_birthday);
        tv_email = (TextView) getView().findViewById(R.id.tv_email);
        tv_commit = (TextView) getView().findViewById(R.id.tv_commit);

        tv_weixin = (TextView) getView().findViewById(R.id.tv_weixin);
        tv_weibo = (TextView) getView().findViewById(R.id.tv_weibo);
        tv_qq = (TextView) getView().findViewById(R.id.tv_qq);
        tv_regTime = (TextView) getView().findViewById(R.id.tv_regTime);

        titleView = (TitleView) getView().findViewById(R.id.title_view);
        titleView.setTitleText("个人资料");

        iv_icon = getView().findViewById(R.id.iv_icon);

        btn_out = (Button) getView().findViewById(R.id.btn_out);
    }

    @Override
    protected void initData() {
        UserInfo currentUser = BmobUser.getCurrentUser(UserInfo.class);
        if (currentUser == null) {
            return;
        }
        if (currentUser.getEmailVerified() && !TextUtils.isEmpty(currentUser.getEmail())) {
            tv_email.setText(currentUser.getEmail());
        }
        Glide.with(getActivity()).load(currentUser.getIconHead()).error(R.mipmap.news).into(iv_icon);
        if (!TextUtils.isEmpty(currentUser.getCreatedAt())) {
            tv_regTime.setText(currentUser.getCreatedAt().substring(0, 11));
        }
        if (!TextUtils.isEmpty(currentUser.getUsername())) {
            tv_username.setText(currentUser.getUsername());
        }
        if (!TextUtils.isEmpty(currentUser.getMobilePhoneNumber())) {
            tv_phone.setText(currentUser.getMobilePhoneNumber());
        }
        if (!TextUtils.isEmpty(currentUser.getNickName())) {
            tv_nickname.setText(currentUser.getNickName());
        }
        if (!TextUtils.isEmpty(currentUser.getBirthday()))
            tv_birth.setText(currentUser.getBirthday());
        if (!TextUtils.isEmpty(currentUser.getNickName()))
            tv_nickname.setText(currentUser.getNickName());
    }

    @Override
    protected void initListener() {
        rl_icon.setOnClickListener(this);
        rl_username.setOnClickListener(this);
        rl_nickname.setOnClickListener(this);
        rl_birth.setOnClickListener(this);
        rl_phone.setOnClickListener(this);
        rl_email.setOnClickListener(this);
        btn_out.setOnClickListener(this);
        tv_commit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_icon:
                Intent intent2 = new Intent(getActivity(), FragmentContainerActivity.class);
                intent2.putExtra(FragmentContainerActivity.VALUENAME, AvatarFragment.class.getName());
                startActivityForResult(intent2, HEADICON);
                break;
            case R.id.rl_username:

                break;
            case R.id.rl_nickname:
                Intent intent = new Intent(getActivity(), FragmentContainerActivity.class);
                intent.putExtra(FragmentContainerActivity.VALUENAME, InputFragment.class.getName());
                startActivityForResult(intent, NICKNAME);
                break;
            case R.id.rl_birthday:
//                DatePickerDialogFragment dialog = new DatePickerDialogFragment(getActivity());
//                dialog.show(getChildFragmentManager(), "date_picker");
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String months = "";
                        String day = "";
                        if ((month + 1) < 10) {
                            months = "0" + (month + 1);
                        } else {
                            months = (month + 1) + "";
                        }
                        if (dayOfMonth < 10) {
                            day = "0" + dayOfMonth;
                        } else {
                            day = dayOfMonth + "";
                        }
                        tv_birth.setText(year + "-" + months + "-" + day);
                        UserInfo currentUser = BmobUser.getCurrentUser(UserInfo.class);
                        currentUser.setBirthday(year + "-" + months + "-" + day);
                        currentUser.update(new UpdateListener() {
                            @Override
                            public void done(BmobException e) {
                                if (e == null) {
                                    showTost("修改成功");
                                } else {
                                    showTost(e.getMessage());
                                }
                            }
                        });
                    }
                }, year, month, day);
                DatePicker datePicker = datePickerDialog.getDatePicker();
                datePicker.setMaxDate(new Date().getTime());
                datePickerDialog.show();
                break;
            case R.id.rl_phone:

                break;
            case R.id.rl_email:

                break;
            case R.id.btn_out:
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("确定退出?");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        BmobUser.logOut();
                        showTost("已退出登录");
                        getActivity().finish();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();


                break;
        }


    }


    @Override
    public void getDate(String date) {
        if (!TextUtils.isEmpty(date)) {
            tv_birth.setText(date);

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NICKNAME && resultCode == Activity.RESULT_OK) {
            initData();
            getActivity().setResult(Activity.RESULT_OK);
        }
    }
}
