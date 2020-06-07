package net.android.workStudy.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import net.android.workStudy.R;
import net.android.workStudy.db.ExamDao;
import net.android.workStudy.db.ExamTable;
import net.android.workStudy.db.ScoreTable;
import net.android.workStudy.db.StudyTable;
import net.android.workStudy.utils.VoiceUtils;

import java.util.List;

public class GradeAdapter extends BaseQuickAdapter<ScoreTable, BaseViewHolder> {
    private Context context;
    private ExamDao examDao;
    List<ExamTable> examTables;

    public GradeAdapter(Context context, List<ScoreTable> inviteList) {
        super(R.layout.item_grade_score, inviteList);
        this.context = context;
        examDao = new ExamDao(context);
        examTables = examDao.selectAll();
    }


    @Override
    protected void convert(BaseViewHolder helper, ScoreTable item) {
        TextView tvStudy = helper.getView(R.id.tv_study);
        TextView textHas = helper.getView(R.id.tv_has);
        tvStudy.setText("共答" + item.getAnswers().size() + ",答错 " + (item.getAnswers().size() - item.getFew()) + "道");
//        ivVoice.setOnClickListener(v->{
//            VoiceUtils.getInstance().initmTts(context,item.getName());
//        });
    }

}