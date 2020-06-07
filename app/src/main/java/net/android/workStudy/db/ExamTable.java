package net.android.workStudy.db;


import com.google.gson.Gson;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "tb_exam")
public class ExamTable {

    @DatabaseField(generatedId = true)
    private int id;

    /**
     * content :  I'm afraid this painting is not by Picasso. It's only a copy and so it's _____.
     * A : priceless
     * B : invaluable
     * C :  unworthy
     * D : worthless
     * answers : D
     * analysis :
     */
    @DatabaseField
    private String content;
    @DatabaseField
    private String A;
    @DatabaseField
    private String B;
    @DatabaseField
    private String C;
    @DatabaseField
    private String D;
    @DatabaseField
    private String answers;
    @DatabaseField
    private String analysis;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getA() {
        return A;
    }

    public void setA(String A) {
        this.A = A;
    }

    public String getB() {
        return B;
    }

    public void setB(String B) {
        this.B = B;
    }

    public String getC() {
        return C;
    }

    public void setC(String C) {
        this.C = C;
    }

    public String getD() {
        return D;
    }

    public void setD(String D) {
        this.D = D;
    }

    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }

    public String getAnalysis() {
        return analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }
}
