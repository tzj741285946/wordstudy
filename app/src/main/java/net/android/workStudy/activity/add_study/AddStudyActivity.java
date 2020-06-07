package net.android.workStudy.activity.add_study;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import net.android.workStudy.R;
import net.android.workStudy.base.BaseActivity;
import net.android.workStudy.base.BasePresenter;
import net.android.workStudy.customView.FrameEditText;
import net.android.workStudy.db.StudyTable;
import net.android.workStudy.widegt.ToastUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 添加/修改 学习
 */
public class AddStudyActivity extends BaseActivity {
    @BindView(R.id.li_back)
    LinearLayout liBack;
    @BindView(R.id.li_title)
    TextView liTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.fe_name)
    FrameEditText feName;
    @BindView(R.id.fe_address)
    FrameEditText feAddress;

    AddStudyViewModel mViewModel;

    @Override
    protected BasePresenter buildPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_add_study;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        liBack.setOnClickListener(v -> finish());
        liTitle.setText("添加");

        mViewModel = ViewModelProviders.of(this).get(AddStudyViewModel.class);
    }

    @Override
    protected void initData() {

    }


    @Override
    public void success(int index, Object o) {

    }

    @Override
    public void error() {

    }

    @OnClick(R.id.tv_change)
    public void onViewClicked() {
        String name = feName.getString();
        String address = feAddress.getString();
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(address)){
            ToastUtils.show(AddStudyActivity.this,"单词或视频地址不能为空");
            return;
        }
        mViewModel.AddStudy(this,name,address).observe(this, new Observer<List<StudyTable>>() {
            @Override
            public void onChanged(List<StudyTable> studyTables) {
                finish();
            }
        });
    }



}
