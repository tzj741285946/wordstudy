package net.android.workStudy.activity.video;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import net.android.workStudy.R;
import net.android.workStudy.base.BaseActivity;
import net.android.workStudy.base.BasePresenter;
import net.android.workStudy.widegt.LoadingWebView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VideoActivity extends BaseActivity {
    @BindView(R.id.li_back)
    LinearLayout liBack;
    @BindView(R.id.li_title)
    TextView liTitle;
    @BindView(R.id.webView)
    LoadingWebView mWebView;

    private VideoViewModel mViewModel;
    private List<String> mdata;

    private String name;
    private String path;

    @Override
    protected int getLayout() {
        return R.layout.activity_video;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        mViewModel = ViewModelProviders.of(this).get(VideoViewModel.class);
        liBack.setOnClickListener(v->finish());

    }

    @Override
    protected void initData() {
        name = getIntent().getStringExtra("name");
        path = getIntent().getStringExtra("path");
        liTitle.setText(name);
        mViewModel.getPath().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                mWebView.loadUrl(path);
            }
        });
    }

    @Override
    protected BasePresenter buildPresenter() {
        return null;
    }

    @Override
    public void success(int index, Object o) {

    }

    @Override
    public void error() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
