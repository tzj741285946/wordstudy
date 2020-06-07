package net.android.workStudy.db;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;

public class StudyDao {
    private Context context;
    // ORMLite提供的DAO类对象，第一个泛型是要操作的数据表映射成的实体类；第二个泛型是这个实体类中ID的数据类型
    private Dao<StudyTable,Integer> dao;

    public StudyDao(Context context) {
        this.context = context;
        try {
            this.dao = DataBaseHelper.getInstance(context).getDao(StudyTable.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insert(StudyTable userInfo){
        try {
            dao.create(userInfo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 删除user表中的一条数据
    public void delete(StudyTable data) {
        try {
            dao.delete(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 修改user表中的一条数据
    public void update(StudyTable data) {
        try {
            dao.update(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 查询user表中的所有数据
    public List<StudyTable> selectAll() {
        List<StudyTable> users = null;
        try {
            users = dao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    // 根据ID取出用户信息
    public StudyTable queryById(int id) {
        StudyTable user = null;
        try {
            user = dao.queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    // 根据ID取出用户信息
    public StudyTable queryByUserInfo(String name,String address) {
        StudyTable study = null;
        try {
            QueryBuilder builder= dao.queryBuilder();
            builder.setWhere(builder.where().eq("name",name).and().eq("videoPath",address));
            study = (StudyTable) builder.queryForFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return study;
    }


}
