package net.android.workStudy.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.adorkable.iosdialog.AlertDialog;

import net.android.workStudy.R;
import net.android.workStudy.activity.login.LoginActivity;
import net.android.workStudy.base.BaseFragment;
import net.android.workStudy.base.BasePresenter;
import net.android.workStudy.utils.DataCleanManager;

import butterknife.BindView;


public class SettingFragment extends BaseFragment {
    @BindView(R.id.rl_catch)
    RelativeLayout rlCatch;
    @BindView(R.id.tv_catch)
    TextView tvCatch;
    @BindView(R.id.tv_login_out)
    TextView tvLoginOut;

    public SettingFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_setting;
    }

    @Override
    public BasePresenter buildPresenter() {
        return null;
    }

    @Override
    public void success(int index, Object o) {

    }

    @Override
    public void error() {

    }


    @Override
    protected void initData(Bundle bundle) {
        try {
            String cacheSize = DataCleanManager.getTotalCacheSize(getActivity());
            tvCatch.setText(cacheSize);
        } catch (Exception e) {
            e.printStackTrace();
        }

        rlCatch.setOnClickListener(v -> {
            new AlertDialog(getActivity()).builder()
                    .setMsg("是否清除缓存")
                    .setPositiveButton("确定", view -> {
                        DataCleanManager.cleanInternalCache(getActivity());
                        try {
                            String cacheSize2 = DataCleanManager.getTotalCacheSize(getActivity());
                            tvCatch.setText(cacheSize2);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }).setNegativeButton("取消", view -> {

            }).show();
        });
        tvLoginOut.setOnClickListener(v -> {
            new AlertDialog(getActivity()).builder()
                    .setMsg("是否退出登录")
                    .setPositiveButton("确定", view -> {
                        DataCleanManager.cleanInternalCache(getActivity());
                        try {
                            Intent intent = new Intent();
                            intent.setClass(getActivity(), LoginActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }).setNegativeButton("取消", view -> {

            }).show();
        });
    }
}
