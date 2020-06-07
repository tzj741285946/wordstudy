package net.android.workStudy.fragment;


import android.annotation.TargetApi;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.TimeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.squareup.picasso.Picasso;

import net.android.workStudy.R;
import net.android.workStudy.activity.add_study.AddStudyActivity;
import net.android.workStudy.activity.video.VideoActivity;
import net.android.workStudy.adapter.StudyWorkAdapter;
import net.android.workStudy.db.ExamTable;
import net.android.workStudy.db.StudyDao;
import net.android.workStudy.db.StudyTable;
import net.android.workStudy.http.HttpUtil;
import net.android.workStudy.mvp.bean.DailySentence;
import net.android.workStudy.utils.AppUtil;
import net.android.workStudy.utils.JinshanParseUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


/**
 * CreateDate: 2019/11/1
 * ClassName: StudyFragment
 * Description:学习
 * Version:
 */
public class StudyFragment extends Fragment {

    @BindView(R.id.iv_pic)
    ImageView ivPic;
    @BindView(R.id.tv_content)
    TextView tvContent;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private StudyWorkAdapter adapter;
    private List<StudyTable> mdata;

    private StudyDao studyDao;

    private Unbinder bind;

    public StudyFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_study, container, false);
        bind = ButterKnife.bind(this, view);
        initView(view);
        initData();
        initListener();
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (bind != null) {
            bind.unbind();
        }
    }

    private void initView(View view) {
        mdata = new ArrayList<>();
        studyDao = new StudyDao(getActivity());
        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppUtil.startActivity((AppCompatActivity) getActivity(), AddStudyActivity.class);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mdata = getData();
        adapter = new StudyWorkAdapter(getActivity(),mdata);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener((adapter, view, position) -> {
            Bundle bundle = new Bundle();
            bundle.putString("name",mdata.get(position).getName());
            bundle.putString("path",mdata.get(position).getVideoPath());
            AppUtil.startActivity((AppCompatActivity) getActivity(),VideoActivity.class,bundle);
        });
    }

    private void initData() {
        String url = "http://open.iciba.com/dsapi/?date=";
        HttpUtil.sendOkHttpRequest(url, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Toast.makeText(getActivity(), "获取数据失败！", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull final Response response) throws IOException {

                final String result = response.body().string();
                Log.d("TAG", result);

                getActivity().runOnUiThread(() -> {
                    Gson gson = new Gson();//创建Gson对象
                    DailySentence sentence = gson.fromJson(result, DailySentence.class);//解析
                    Picasso.with(getActivity()).load(sentence.getPicture2())
                            .placeholder(R.drawable.ic_placeholder)
                            .error(R.drawable.ic_placeholder)
                            .into(ivPic);
                    tvContent.setText(sentence.getContent());

                });
            }
        });
    }

    private void initListener() {

    }

    private List<StudyTable> getData() {
        List<StudyTable> mlist = new ArrayList<>();
        if (studyDao.selectAll() == null || studyDao.selectAll().size() == 0){
            StudyTable study = new StudyTable();
            study.setName("capture");
            study.setVideoPath("http://player.youku.com/embed/XNTc5NzM1NDY4");
            mlist.add(study);
            StudyTable study1 = new StudyTable();
            study1.setName("resign");
            study1.setVideoPath("http://player.youku.com/embed/XNTc5NzM0Nzc2");
            mlist.add(study1);
            StudyTable study2 = new StudyTable();
            study2.setName("brand");
            study2.setVideoPath("http://player.youku.com/embed/XNTc5NzM1MzA4");
            mlist.add(study2);
            StudyTable study3 = new StudyTable();
            study3.setName("navy");
            study3.setVideoPath("http://player.youku.com/embed/XNTc5NzM1ODg0");
            mlist.add(study3);
            StudyTable study4 = new StudyTable();
            study4.setName("mould");
            study4.setVideoPath("http://player.youku.com/embed/XNTc5NzM1Njcy");
            mlist.add(study4);
            StudyTable study5 = new StudyTable();
            study5.setName("beard");
            study5.setVideoPath("http://player.youku.com/embed/XNTc5NzM1MTUy");
            mlist.add(study5);
            StudyTable study6 = new StudyTable();
            study6.setName("erupt");
            study6.setVideoPath("http://player.youku.com/embed/XNTc5NzM0OTcy");
            mlist.add(study6);
            StudyTable study7 = new StudyTable();
            study7.setName("steady");
            study7.setVideoPath("http://player.youku.com/embed/XNTcwODY1NTY0");
            mlist.add(study7);
            StudyTable study8 = new StudyTable();
            study8.setName("genius");
            study8.setVideoPath("http://player.youku.com/embed/XNTcwODY3MjQ4");
            mlist.add(study8);
            StudyTable study9 = new StudyTable();
            study9.setName("scan");
            study9.setVideoPath("http://player.youku.com/embed/XNTcwODY2MDg4");
            mlist.add(study9);
            StudyTable study10 = new StudyTable();
            study10.setName("remote");
            study10.setVideoPath("http://player.youku.com/embed/XNTcwODY2OTI4");
            mlist.add(study10);
            StudyTable study11 = new StudyTable();
            study11.setName("vote");
            study11.setVideoPath("http://player.youku.com/embed/XNTcwODY2Mzg4");
            mlist.add(study11);
            StudyTable study12 = new StudyTable();
            study12.setName("element");
            study12.setVideoPath("http://player.youku.com/embed/XNTcwODY1MTky");
            mlist.add(study12);
            StudyTable study13 = new StudyTable();
            study13.setName("duke");
            study13.setVideoPath("http://player.youku.com/embed/XNTcwODY0OTE2");
            mlist.add(study13);
            StudyTable study14 = new StudyTable();
            study14.setName("attention");
            study14.setVideoPath("http://player.youku.com/embed/XNTcwODYyNjQw");
            mlist.add(study14);
        }else {
            mlist = studyDao.selectAll();
        }

        return mlist;
    }

}
