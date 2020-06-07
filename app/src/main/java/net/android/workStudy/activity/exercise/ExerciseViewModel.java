package net.android.workStudy.activity.exercise;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.blankj.utilcode.util.SPUtils;

import net.android.workStudy.db.AnswerDao;
import net.android.workStudy.db.AnswerTable;
import net.android.workStudy.db.ExamDao;
import net.android.workStudy.db.ExamTable;
import net.android.workStudy.db.ScoreDao;
import net.android.workStudy.db.ScoreTable;
import net.android.workStudy.db.UserDao;
import net.android.workStudy.db.UserInfo;

import java.util.List;

public class ExerciseViewModel extends ViewModel {
    private MutableLiveData<List<ExamTable>> mutableLiveData;
    private ExamDao dao;
    private AnswerDao asDao;
    private UserDao userDao;
    private ScoreDao sDao;

    public ExerciseViewModel() {
        mutableLiveData = new MutableLiveData<>();
    }


    public LiveData<List<ExamTable>> getExercise(Context context) {
        dao = new ExamDao(context);
        List<ExamTable> examTables = dao.selectAll();
        mutableLiveData.postValue(examTables);
        return mutableLiveData;
    }

    AnswerTable answerTable;

    public void upAnswer(Context context, ExamTable bean,  int sid, int few,int position, String answer) {
        asDao = new AnswerDao(context);
        userDao = new UserDao(context);
        sDao = new ScoreDao(context);

        answerTable = asDao.queryByAnswer(bean.getId(), sid);
        UserInfo info = userDao.queryById(SPUtils.getInstance().getInt("id"));
        if (answerTable == null) {
            answerTable = new AnswerTable();
            answerTable.setEid(bean.getId());
//            answerTable.setScore(info);
            ScoreTable score = sDao.queryById(sid);
            if (score == null) {
                score = new ScoreTable();
                score.setFew(few);
                score.setInfo(info);
                sDao.insert(score);
            }else {
                score.setFew(few);
                score.setInfo(info);
                sDao.update(score);
            }
            answerTable.setScore(score);
            answerTable.setAnswer(answer);
            asDao.insert(answerTable);
            userDao.update(info);
        } else {
            answerTable.setAnswer(answer);
            asDao.update(answerTable);
        }
    }
}
