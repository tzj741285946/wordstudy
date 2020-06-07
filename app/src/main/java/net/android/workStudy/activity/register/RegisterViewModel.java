package net.android.workStudy.activity.register;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import net.android.workStudy.db.UserDao;
import net.android.workStudy.db.UserInfo;
import net.android.workStudy.widegt.ToastUtils;

public class RegisterViewModel extends ViewModel {
    private Context mContext;
    private UserDao dao;
    private MutableLiveData<Integer> mUserInfoMutableData;

    public RegisterViewModel() {
        mUserInfoMutableData = new MutableLiveData<>();
    }


    public LiveData<Integer> register(Context context, UserInfo user) {
        dao = new UserDao(context);
        this.mContext = context;
        UserInfo userInfo = dao.queryByUserInfo(user.getUsername(), user.getPassword());
        if (userInfo == null) {
            dao.insert(user);
            mUserInfoMutableData.postValue(1);
            ToastUtils.show(mContext, "注册成功");
        } else {
            ToastUtils.show(mContext, "注册失败");
            mUserInfoMutableData.postValue(0);
        }
        return mUserInfoMutableData;
    }
}
