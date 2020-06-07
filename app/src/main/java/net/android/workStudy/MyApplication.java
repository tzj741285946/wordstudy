package net.android.workStudy;

import android.app.Application;

import androidx.appcompat.app.AppCompatActivity;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;

import java.util.HashSet;
import java.util.Set;

public class MyApplication extends Application {
    private static MyApplication myApplication = null;
    private Set<AppCompatActivity> allActivities;

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;

        // 将“12345678”替换成您申请的APPID，申请地址：http://www.xfyun.cn
        // 请勿在“=”与appid之间添加任何空字符或者转义符
        SpeechUtility.createUtility(this, SpeechConstant.APPID +"=5db941da");
    }

    public static synchronized MyApplication getInstance() {
        return myApplication;
    }

    public void addActivity(AppCompatActivity act) {
        if (allActivities == null) {
            allActivities = new HashSet<>();
        }
        allActivities.add(act);
    }

    public void removeActivity(AppCompatActivity act) {
        if (allActivities != null) {
            allActivities.remove(act);
        }
    }
}
