package net.android.workStudy.base;

import android.content.Context;

/**
 * Created by PrinceOfAndroid on 2018/4/9 0009.
 */

public interface BaseView<T> {

    /**
     * 获取上下文
     * @return
     */
    Context getViewContext();

    void success(int index, T t);

    void error();
}
