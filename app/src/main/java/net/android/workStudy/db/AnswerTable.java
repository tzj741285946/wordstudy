package net.android.workStudy.db;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

@DatabaseTable(tableName = "tb_wrong")
public class AnswerTable  {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField
    private int eid;
    /**
     * 1.A
     * 2.B
     * 3.C
     * 4.D
     */
    @DatabaseField
    private String answer;

    /**
     * foreign = true:说明这是一个外部引用关系
     * foreignAutoRefresh = true：当对象被查询时，外部属性自动刷新（暂时我也没看懂其作用）
     *
     */
    @DatabaseField(foreign = true,foreignAutoRefresh = true,foreignColumnName = "id")
    private ScoreTable score;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public ScoreTable getScore() {
        return score;
    }

    public void setScore(ScoreTable score) {
        this.score = score;
    }
}
