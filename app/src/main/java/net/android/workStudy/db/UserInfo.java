package net.android.workStudy.db;


import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "tb_user")
public class UserInfo {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField
    private String sex ;

    @DatabaseField
    private int  age ;

    @DatabaseField
    private String  phone ;

    @DatabaseField
    private String username;

    @DatabaseField
    private String password;

    @DatabaseField
    private String avatar;


    //用户信息集合

    /**
     * 这里需要注意的是：属性类型只能是ForeignCollection<T>或者Collection<T>
     * 如果需要懒加载（延迟加载）可以在@ForeignCollectionField加上参数eager=false
     * 这个属性也就说明一个部门对应着多个用户
     */
    @ForeignCollectionField(eager = true) // 必须
    private ForeignCollection<ScoreTable> scorce;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public ForeignCollection<ScoreTable> getScorce() {
        return scorce;
    }

    public void setScorce(ForeignCollection<ScoreTable> scorce) {
        this.scorce = scorce;
    }
}
