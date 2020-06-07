package net.android.workStudy.mvp.model;

import net.android.workStudy.mvp.bean.WorkBook;
import net.android.workStudy.http.RetrofitFactory;
import net.android.workStudy.mvp.contract.TranslateContract;

import java.util.Map;

import io.reactivex.Flowable;


/**
 * 模型层，负责数据的处理
 */

public class TranslateModel implements TranslateContract.IModel {


    @Override
    public Flowable<WorkBook> translate(Map<String, String> map) {
        return RetrofitFactory.getInstance().translate(map);
    }
}
