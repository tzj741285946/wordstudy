package net.android.workStudy.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProviders;

import net.android.workStudy.R;
import net.android.workStudy.activity.exercise.ExerciseViewModel;
import net.android.workStudy.base.BaseFragment;
import net.android.workStudy.base.BasePresenter;
import net.android.workStudy.db.AnswerTable;
import net.android.workStudy.db.ExamDao;
import net.android.workStudy.db.ExamTable;

import butterknife.BindView;

public class RecordingFragment extends BaseFragment {
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.radio)
    RadioGroup radio;
    @BindView(R.id.radio_A)
    RadioButton radioA;
    @BindView(R.id.radio_B)
    RadioButton radioB;
    @BindView(R.id.radio_C)
    RadioButton radioC;
    @BindView(R.id.radio_D)
    RadioButton radioD;
    @BindView(R.id.iv_se)
    ImageView ivSe;
    @BindView(R.id.tv_answer)
    TextView tvAnswer;


    private ExerciseViewModel mViewModel;
    private int position;

    private AnswerTable answerTable;
    private ExamDao dao;

    public RecordingFragment(AnswerTable table, int i, int size) {
        this.answerTable = table;
        this.position = i;

    }


    @Override
    public BasePresenter buildPresenter() {
        return null;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_recording;
    }

    @Override
    protected void initData(Bundle bundle) {
        dao = new ExamDao(getActivity());
        mViewModel = ViewModelProviders.of(this).get(ExerciseViewModel.class);

        ExamTable bean = dao.queryById(answerTable.getEid());

        tvContent.setText(position + "." + bean.getContent());
        if (!TextUtils.isEmpty(bean.getA())) {
            radioA.setText("A ." + bean.getA());
        }
        if (!TextUtils.isEmpty(bean.getB())) {
            radioB.setText("B ." + bean.getB());
        }
        if (!TextUtils.isEmpty(bean.getC())) {
            radioC.setText("C ." + bean.getC());
        }
        if (!TextUtils.isEmpty(bean.getD())) {
            radioD.setText("D ." + bean.getD());
        }


        if (bean.getAnswers().equals(answerTable.getAnswer())) {
            tvAnswer.setVisibility(View.GONE);
            ivSe.setBackgroundResource(R.mipmap.icon_success);
        } else {
            tvAnswer.setVisibility(View.VISIBLE);
            tvAnswer.setText("正确答案：" + bean.getAnswers() + "");
            ivSe.setBackgroundResource(R.mipmap.icon_error);
        }

        switch (answerTable.getAnswer()) {
            case "A":
                radioA.setChecked(true);
                break;
            case "B":
                radioB.setChecked(true);
                break;
            case "C":
                radioC.setChecked(true);
                break;
            case "D":
                radioD.setChecked(true);
                break;
        }
    }


    @Override
    public void success(int index, Object o) {

    }

    @Override
    public void error() {

    }
}
