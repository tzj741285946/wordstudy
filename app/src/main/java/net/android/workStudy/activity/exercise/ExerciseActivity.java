package net.android.workStudy.activity.exercise;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.blankj.utilcode.util.SPUtils;

import net.android.workStudy.R;
import net.android.workStudy.base.BaseActivity;
import net.android.workStudy.base.BasePresenter;
import net.android.workStudy.db.AnswerDao;
import net.android.workStudy.db.ExamTable;
import net.android.workStudy.db.ScoreDao;
import net.android.workStudy.db.ScoreTable;
import net.android.workStudy.db.UserDao;
import net.android.workStudy.db.UserInfo;
import net.android.workStudy.fragment.ExerciseFragment;
import net.android.workStudy.widegt.NoScrollViewPager;
import net.android.workStudy.widegt.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExerciseActivity extends BaseActivity {

    @BindView(R.id.li_back)
    LinearLayout liBack;
    @BindView(R.id.li_title)
    TextView liTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.vp)
    NoScrollViewPager vp;
    @BindView(R.id.tv_sum)
    TextView tvSum;

    ExerciseViewModel mViewModel;
    private UserDao dao;
    private AnswerDao asDao;
    private ScoreDao sDao;
    private List<Fragment> fragments;
    private List<ExamTable> examTablesList;
    private int total;
    private int index = 0;

    public int few;
    @Override
    protected BasePresenter buildPresenter() {
        return null;
    }


    @Override
    protected int getLayout() {
        return R.layout.activity_exercise;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        int sid = getIntent().getIntExtra("sid", 0);

        liBack.setOnClickListener(v -> {
            if (index<5){
                ToastUtils.show(ExerciseActivity.this,"至少回答5道题可以返回");
                return;
            }
            finish();
        });
        liTitle.setText("测试");


        dao = new UserDao(this);
        asDao = new AnswerDao(this);
        sDao = new ScoreDao(this);


        mViewModel = ViewModelProviders.of(this).get(ExerciseViewModel.class);
        mViewModel.getExercise(this).observe(this, examTables -> {
            total = examTables.size();
            examTablesList = examTables;
            tvSum.setText("共" + total + "道题目");
            fragments = new ArrayList<>();
            if (examTablesList.size() != 0) {
                for (int i = 0; i < examTablesList.size(); i++) {
                    ExerciseFragment fragment = new ExerciseFragment(ExerciseActivity.this, examTablesList.get(i), sid,  i + 1);
                    fragments.add(fragment);
                    Log.i("TAG", (i + i) + "");
                }
            }
            vp.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT));
        });
    }

    @Override
    protected void initData() {

    }

    public void changPage(int position) {
        vp.setCurrentItem(position);
        index = position;
    }


    private class MyPagerAdapter extends FragmentPagerAdapter {


        public MyPagerAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }


    @Override
    public void success(int index, Object o) {

    }

    @Override
    public void error() {

    }

}
