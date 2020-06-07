package net.android.workStudy.activity.register;

import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;


import net.android.workStudy.R;
import net.android.workStudy.base.BaseActivity;
import net.android.workStudy.base.BasePresenter;
import net.android.workStudy.customView.FrameEditText;
import net.android.workStudy.db.UserInfo;
import net.android.workStudy.widegt.ToastUtils;

import butterknife.BindView;
import cn.addapp.pickers.common.LineConfig;
import cn.addapp.pickers.picker.SinglePicker;

public class RegisterActivity extends BaseActivity {
    @BindView(R.id.li_back)
    LinearLayout liBack;
    @BindView(R.id.li_title)
    TextView liTitle;
    @BindView(R.id.frame_username)
    FrameEditText frameUsername;
    @BindView(R.id.frame_password)
    FrameEditText framePassword;
    @BindView(R.id.frame_confirm_password)
    FrameEditText frameConfirmPassword;
    @BindView(R.id.btn_register)
    Button btnRegister;

    RegisterViewModel viewModel;

    private String userName;
    private String password;
    private String confirmPwd;
    private int status = 1;//审核状态
    private int character;//角色

    char numberChars[] = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0','a','b','c','d','e','f','g','h','i','j',
            'k','l','m','n','o','p','q','r','s','t','u','v','w','x' ,'y','z'};



    @Override
    protected BasePresenter buildPresenter() {
        return null;
    }


    @Override
    protected int getLayout() {
        return R.layout.activity_register;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        liBack.setOnClickListener(v -> finish());
        liTitle.setText("注册");

        frameUsername.setKeyListener(numberChars);
        frameUsername.setInputType(InputType.TYPE_CLASS_TEXT);
        framePassword.setInputType(0x81);
        frameConfirmPassword.setInputType(0x81);

        viewModel = ViewModelProviders.of(this).get(RegisterViewModel.class);
    }

    @Override
    protected void initData() {

        btnRegister.setOnClickListener(v -> {
            userName = frameUsername.getString();
            password = framePassword.getString();
            confirmPwd = frameConfirmPassword.getString();
            if (userName == null || password == null) {
                ToastUtils.show(this, "用户名或密码不能为空");
                return;
            }
            if (!password.equals(confirmPwd)) {
                ToastUtils.show(this, "两次密码输入不一致");
                return;
            }
            UserInfo user = new UserInfo();
            user.setUsername(userName);
            user.setPassword(password);
            viewModel.register(this, user).observe(RegisterActivity.this, new Observer<Integer>() {
                @Override
                public void onChanged(Integer integer) {
                    if (integer == 1) {
                        ToastUtils.show(RegisterActivity.this, "注册成功");
                        finish();
                    } else {
                        ToastUtils.show(RegisterActivity.this, "注册失败");
                    }
                }
            });
        });
    }

    @Override
    public void success(int index, Object o) {

    }

    @Override
    public void error() {

    }
}
