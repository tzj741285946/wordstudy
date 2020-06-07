package net.android.workStudy.db;


import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "tb_source")
public class ScoreTable {

    @DatabaseField(generatedId = true)
    private int id;


    /**
     * 分数
     */
    @DatabaseField
    private int few;


    /**
     * 这里需要注意的是：属性类型只能是ForeignCollection<T>或者Collection<T>
     * 如果需要懒加载（延迟加载）可以在@ForeignCollectionField加上参数eager=false
     * 这个属性也就说明一个部门对应着多个用户
     */
    @ForeignCollectionField(eager = true) // 必须
    private ForeignCollection<AnswerTable> answers;


    /**
     * foreign = true:说明这是一个外部引用关系
     * foreignAutoRefresh = true：当对象被查询时，外部属性自动刷新（暂时我也没看懂其作用）
     *
     */
    @DatabaseField(foreign = true,foreignAutoRefresh = true,foreignColumnName = "id")
    private UserInfo info;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFew() {
        return few;
    }

    public void setFew(int few) {
        this.few = few;
    }

    public ForeignCollection<AnswerTable> getAnswers() {
        return answers;
    }

    public void setAnswers(ForeignCollection<AnswerTable> answers) {
        this.answers = answers;
    }

    public UserInfo getInfo() {
        return info;
    }

    public void setInfo(UserInfo info) {
        this.info = info;
    }
}
