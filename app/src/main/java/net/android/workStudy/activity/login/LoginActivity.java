package net.android.workStudy.activity.login;

import android.os.Bundle;
import android.text.InputType;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.blankj.utilcode.util.SPUtils;

import net.android.workStudy.R;
import net.android.workStudy.activity.MainActivity;
import net.android.workStudy.activity.register.RegisterActivity;
import net.android.workStudy.base.BaseActivity;
import net.android.workStudy.base.BasePresenter;
import net.android.workStudy.customView.FrameEditText;
import net.android.workStudy.db.UserInfo;
import net.android.workStudy.widegt.ToastUtils;

import butterknife.BindView;


/**
 * 登录
 */
public class LoginActivity extends BaseActivity {
    @BindView(R.id.frame_username)
    FrameEditText frameUsername;
    @BindView(R.id.frame_password)
    FrameEditText framePassword;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    @BindView(R.id.btn_login)
    Button btnLogin;

    private String userName;
    private String password;

    private LoginViewModel viewModel;

    char numberChars[] = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
            'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};


    @Override
    protected BasePresenter buildPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return R.layout.activity_login;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
            viewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
            frameUsername.setKeyListener(numberChars);
            frameUsername.setInputType(InputType.TYPE_CLASS_TEXT);
            framePassword.setInputType(0x81);
        }

    @Override
    protected void initData() {
        btnLogin.setOnClickListener(v -> {
            userName = frameUsername.getString();
            password = framePassword.getString();
            if (userName == null || password == null) {
                ToastUtils.show(this, "用户名或密码不能为空");
                return;
            }
            viewModel.getUserInfo(this, userName, password).observe(LoginActivity.this, new Observer<UserInfo>() {
                @Override
                public void onChanged(UserInfo userInfoTable) {
                    if (userInfoTable.getUsername() != null){
                        SPUtils.getInstance().put("id", userInfoTable.getId());
                        Bundle bundle = new Bundle();
                        bundle.putInt("type", 1);
                        StartActivityAndFinsh(MainActivity.class, bundle);
                    }
                }
            });
        });
        tvRegister.setOnClickListener(v -> {
            StartForActivity(RegisterActivity.class);
        });
    }

    @Override
    public void success(int index, Object o) {

    }

    @Override
    public void error() {

    }


}
