package net.android.workStudy.fragment;


import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;

import net.android.workStudy.R;
import net.android.workStudy.activity.RecordingActivity;
import net.android.workStudy.activity.exercise.ExerciseActivity;
import net.android.workStudy.adapter.GradeAdapter;
import net.android.workStudy.base.BaseFragment;
import net.android.workStudy.base.BasePresenter;
import net.android.workStudy.db.ScoreDao;
import net.android.workStudy.db.ScoreTable;
import net.android.workStudy.db.UserDao;
import net.android.workStudy.db.UserInfo;
import net.android.workStudy.utils.AppUtil;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * CreateDate: 2019/11/1
 * ClassName: GradeFragment
 * Author: hk
 * Description: 成绩
 * Version:
 */
public class GradeFragment extends BaseFragment {
    @BindView(R.id.grade_recycler)
    RecyclerView gradeRecycler;

    GradeAdapter adapter;

    private ScoreDao dao;
    private UserDao userDao;

    @Override
    public BasePresenter buildPresenter() {
        return null;
    }


    public GradeFragment() {
        // Required empty public constructor
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_grade;
    }

    @Override
    protected void initData(Bundle bundle) {
        dao = new ScoreDao(getActivity());
        userDao = new UserDao(getActivity());

//        tvGrade.setOnClickListener(v -> {
//
//        });
    }


    @Override
    public void onResume() {
        super.onResume();
        List<ScoreTable> examTables = dao.queryByScores();
        adapter = new GradeAdapter(getActivity(), examTables);
        gradeRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        gradeRecycler.setAdapter(adapter);
        adapter.setOnItemClickListener((adapter, view, position) -> {
            Bundle bundle1 = new Bundle();
            bundle1.putSerializable("position", position);
            AppUtil.startActivity((AppCompatActivity) getActivity(), RecordingActivity.class, bundle1);
        });
    }

    @Override
    public void success(int index, Object o) {

    }

    @Override
    public void error() {

    }

    @OnClick(R.id.tv_grade)
    public void onViewClicked() {
        ScoreTable score = new ScoreTable();
        UserInfo info = userDao.queryById(SPUtils.getInstance().getInt("id"));
        score.setFew(0);
        score.setInfo(info);
        dao.insert(score);

        List<ScoreTable> examTables = dao.queryByScores();
        Bundle bundle1 = new Bundle();
        bundle1.putInt("sid", examTables.get(examTables.size()-1).getId());
        AppUtil.startActivity((AppCompatActivity) getActivity(), ExerciseActivity.class, bundle1);
    }
}
