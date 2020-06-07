package net.android.workStudy.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import net.android.workStudy.R;
import net.android.workStudy.db.StudyTable;
import net.android.workStudy.utils.VoiceUtils;

import java.util.List;

public class ExerciseAdapter extends BaseQuickAdapter<StudyTable, BaseViewHolder> {
    private Context context;

    public ExerciseAdapter(Context context, List<StudyTable> inviteList) {
        super(R.layout.item_work_study, inviteList);
        this.context = context;
    }


    @Override
    protected void convert(BaseViewHolder helper, StudyTable item) {
        TextView tvStudy = helper.getView(R.id.tv_study);
        ImageView ivVoice = helper.getView(R.id.iv_voice);
        tvStudy.setText(item.getName());
        ivVoice.setOnClickListener(v->{
            VoiceUtils.getInstance().initmTts(context,item.getName());
        });
    }

}