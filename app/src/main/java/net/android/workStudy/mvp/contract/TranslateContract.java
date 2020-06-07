package net.android.workStudy.mvp.contract;


import net.android.workStudy.mvp.bean.WorkBook;

import java.util.Map;

import io.reactivex.Flowable;


/**
 * mvp的管理者，方法都在这里进行定义
 */

public interface TranslateContract {

    interface IModel {
        Flowable<WorkBook> translate(Map<String, String> map);
    }

    interface IPresenter {
        void translate(String q);
    }
}
