package net.android.workStudy.activity.login;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import net.android.workStudy.db.UserDao;
import net.android.workStudy.db.UserInfo;
import net.android.workStudy.widegt.ToastUtils;


public class LoginViewModel extends ViewModel {
    private UserDao dao;
    private MutableLiveData<UserInfo> mUserInfoMutableData;


    public LoginViewModel() {
        mUserInfoMutableData = new MutableLiveData<>();
    }


    public LiveData<UserInfo> getUserInfo(Context context, String userName, String passWord) {
        dao = new UserDao(context);
        UserInfo userInfo = dao.queryByUserInfo(userName, passWord);
        if (userInfo == null) {
            ToastUtils.show(context, "此用户不存在");
        }  else {
            mUserInfoMutableData.postValue(userInfo);
        }
        return mUserInfoMutableData;
    }
}
