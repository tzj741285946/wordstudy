package net.android.workStudy.activity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.blankj.utilcode.util.SPUtils;
import com.j256.ormlite.dao.ForeignCollection;

import net.android.workStudy.R;
import net.android.workStudy.activity.exercise.ExerciseActivity;
import net.android.workStudy.base.BaseActivity;
import net.android.workStudy.base.BasePresenter;
import net.android.workStudy.db.AnswerTable;
import net.android.workStudy.db.ScoreDao;
import net.android.workStudy.db.ScoreTable;
import net.android.workStudy.fragment.RecordingFragment;
import net.android.workStudy.widegt.ToastUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecordingActivity extends BaseActivity {
    @BindView(R.id.tv_sum)
    TextView tvSum;
    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.li_back)
    LinearLayout liBack;
    @BindView(R.id.li_title)
    TextView liTitle;

    private List<Fragment> fragments;
    private ForeignCollection<AnswerTable> list;
    private int total;

    private ScoreDao dao;

    @Override
    protected int getLayout() {
        return R.layout.activity_recording;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        liBack.setOnClickListener(v -> {
            finish();
        });
        liTitle.setText("答题记录");
        int index = getIntent().getIntExtra("position", 0);
        dao = new ScoreDao(this);
        List<ScoreTable> examTables = dao.queryByScores();

        list = examTables.get(index).getAnswers();
        Iterator<AnswerTable> it = list.iterator();
        List<AnswerTable> mlist = new ArrayList<>();
        while (it.hasNext()) {
            AnswerTable answerTable = it.next();
            mlist.add(answerTable);
        }
        total = list.size();
        tvSum.setText("共" + total + "道题目");
        fragments = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            RecordingFragment fragment = new RecordingFragment(mlist.get(i), i + 1, list.size());
            fragments.add(fragment);
        }
        vp.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT));
        int num = SPUtils.getInstance().getInt("num", 0);


    }

    @Override
    protected void initData() {

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
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
}
