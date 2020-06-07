package net.android.workStudy.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import net.android.workStudy.utils.AppUtil;


/**
 * <pre>
 *     desc     : activity mvp基础类
 * </pre>
 */
public abstract class BaseActivity<T extends BasePresenter> extends SimpleActivity
        implements BaseView {
    protected T mPresenter;
    protected BaseActivity mActivity;

    @Override
    protected void onViewCreated() {
        super.onViewCreated();
        //初始化 presenter 执行onCreate() 生命周期 绑定view(activity)
        mPresenter = buildPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
            mPresenter.onCreate();
        }
    }

    /**
     * 抽象生成presenter 交给子类去实现
     *
     * @return
     */
    protected abstract T buildPresenter();

    /**
     * 实现baseView获取上下文方法 返回this
     *
     * @return
     */
    @Override
    public Context getViewContext() {
        return this;
    }

    /**
     * onDestroy 事件 释放资源
     */
    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.onDestroy();
        }
        super.onDestroy();
    }

    public void StartForActivity(Class clz) {
        Intent intent = new Intent();
        intent.setClass(this, clz);
        startActivity(intent);
    }

    public void StartForActivity(Class clz, Bundle bundle) {
        AppUtil.startActivity(this, clz, bundle);
    }

    public void StartActivityAndFinsh(Class clz, Bundle bundle) {
        StartForActivity(clz,bundle);
        finish();
    }

    public void StartActivityAndFinsh(Class clz) {
        Intent intent = new Intent();
        intent.setClass(this, clz);
        startActivity(intent);
        finish();
    }


}
