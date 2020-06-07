package net.android.workStudy.fragment;


import android.os.Bundle;
import android.text.TextUtils;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import net.android.workStudy.R;
import net.android.workStudy.activity.exercise.ExerciseActivity;
import net.android.workStudy.activity.exercise.ExerciseViewModel;
import net.android.workStudy.base.BaseFragment;
import net.android.workStudy.base.BasePresenter;
import net.android.workStudy.db.ExamTable;
import net.android.workStudy.widegt.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 答题
 */
public class ExerciseFragment extends BaseFragment {
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


    private ExerciseViewModel mViewModel;
    private ExamTable bean;
    private int position;
    private ExerciseActivity activity;
    private int sid;

    private List<Integer> list = new ArrayList<>();
    private int few;

    @Override
    public BasePresenter buildPresenter() {
        return null;
    }


    public ExerciseFragment(ExerciseActivity activity, ExamTable examTable, int sid, int title) {
        // Required empty public constructor
        this.sid = sid;
        this.bean = examTable;
        this.position = title;
        this.activity = activity;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_exercise;
    }

    @Override
    protected void initData(Bundle bundle) {
        mViewModel = ViewModelProviders.of(this).get(ExerciseViewModel.class);

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

        radio.setOnCheckedChangeListener((group, checkedId) -> {

            switch (checkedId) {
                case R.id.radio_A:
                    getCheckId(1);
                    break;
                case R.id.radio_B:
                    getCheckId(2);
                    break;
                case R.id.radio_C:
                    getCheckId(3);
                    break;
                case R.id.radio_D:
                    getCheckId(4);
                    break;
            }
        });
    }

    private void getCheckId(int i) {
        String answer;
        switch(i){
            case 1:
                answer ="A";
                break;
            case 2:
                answer ="B";
                break;
            case 3:
                answer ="C";
                break;
            case 4:
                answer ="D";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + i);
        }
        if (bean.getAnswers().equals(answer)) {
                activity.few += 1;
        }

        mViewModel.upAnswer(getActivity(), bean, sid,  activity.few ,position, answer);
        if (position == 35) {
            ToastUtils.show(getActivity(), "恭喜你完成测试");
            getActivity().finish();
        } else {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            activity.changPage(position);
        }
    }

    @Override
    public void success(int index, Object o) {

    }

    @Override
    public void error() {

    }
}
