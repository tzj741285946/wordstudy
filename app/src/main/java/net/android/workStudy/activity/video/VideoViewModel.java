package net.android.workStudy.activity.video;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class VideoViewModel extends ViewModel {
    private MutableLiveData<List<String>> mutableLiveData;

    public VideoViewModel() {
        mutableLiveData = new MutableLiveData<>();
    }

    public LiveData<List<String>> getPath(){
        List<String> mdata = new ArrayList<>();
        mdata.add("http://player.youku.com/embed/XNTc5NzM1NDY4");
        mdata.add("http://player.youku.com/embed/XNTc5NzM0Nzc2");
        mdata.add("http://player.youku.com/embed/XNTc5NzM1MzA4");
        mdata.add("http://player.youku.com/embed/XNTc5NzM1ODg0");
        mdata.add("http://player.youku.com/embed/XNTc5NzM1Njcy");
        mdata.add("http://player.youku.com/embed/XNTc5NzM1MTUy");
        mdata.add("http://player.youku.com/embed/XNTc5NzM0OTcy");
        mdata.add("http://player.youku.com/embed/XNTcwODY1NTY0");
        mdata.add("http://player.youku.com/embed/XNTcwODY3MjQ4");
        mdata.add("http://player.youku.com/embed/XNTcwODY2MDg4");
        mdata.add("http://player.youku.com/embed/XNTcwODY2OTI4");
        mdata.add("http://player.youku.com/embed/XNTcwODY2Mzg4");
        mdata.add("http://player.youku.com/embed/XNTcwODY1MTky");
        mdata.add("http://player.youku.com/embed/XNTcwODY0OTE2");
        mdata.add(" http://player.youku.com/embed/XNTcwODYyNjQw");
        mutableLiveData.postValue(mdata);
        return mutableLiveData;
    }

}
