package net.android.workStudy.activity.add_study;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import net.android.workStudy.db.StudyDao;
import net.android.workStudy.db.StudyTable;
import net.android.workStudy.widegt.ToastUtils;

import java.util.ArrayList;
import java.util.List;

public class AddStudyViewModel extends ViewModel {
    private MutableLiveData<List<StudyTable>>  mutableLiveData;
    StudyDao dao;


    public AddStudyViewModel() {
        mutableLiveData= new MutableLiveData<>();
    }

    public LiveData<List<StudyTable>> AddStudy(Context context,String name, String address) {
        List<StudyTable> list = new ArrayList<>();
        dao = new StudyDao(context);

        StudyTable studyTable = dao.queryByUserInfo(name, address);
        if (studyTable !=null){
            ToastUtils.show(context,"此单词已存在");
        }else {
            StudyTable bean = new StudyTable();
            bean.setName(name);
            bean.setVideoPath(address);
            dao.insert(bean);
            ToastUtils.show(context,"添加成功");
        }
        list =  dao.selectAll();
        mutableLiveData.postValue(list);
        return mutableLiveData;
    }
}
