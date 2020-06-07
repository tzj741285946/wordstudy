package net.android.workStudy.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by Administrator on 2017/4/19 0019.
 * 非mvp基类Fragment
 */

public abstract class SimpleFragment extends Fragment {
    /**
     * 当前Activity渲染的视图View
     */
    protected View contentView;

    private Unbinder mUnBinder;

    /**
     * 创建fragment中的View
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setRetainInstance(true);
        contentView = inflater.inflate(getLayoutId(), container, false);
        return contentView;
    }

    /**
     * 视图完成加载 在onCreateView后执行
     *
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUnBinder = ButterKnife.bind(this, view);
        if (view != null) {
            Bundle bundle = getArguments();
            //实例化view
            initView(savedInstanceState, contentView);
            //数据与视图初始化完成
            onCreateFinish();
            //初始化数据 如list  intent传值
            initData(bundle);
        }

    }

    protected void onCreateFinish() { }


    protected abstract int getLayoutId();

    //实例化操作
    public void initView(Bundle savedInstanceState, View view){ }


    /**
     * 初始化数据
     *
     * @param bundle
     */
    protected abstract void initData(Bundle bundle);



    @Override
    public void onDestroyView() {
//        if (contentView != null) {
//            ((ViewGroup) contentView.getParent()).removeView(contentView);
//        }
        super.onDestroyView();
        mUnBinder.unbind();
    }

}
