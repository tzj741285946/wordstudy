package net.android.workStudy.db;

import android.content.Context;

import com.blankj.utilcode.util.SPUtils;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;

public class AnswerDao {
    private Context context;
    // ORMLite提供的DAO类对象，第一个泛型是要操作的数据表映射成的实体类；第二个泛型是这个实体类中ID的数据类型
    private Dao<AnswerTable,Integer> dao;

    public AnswerDao(Context context) {
        this.context = context;
        try {
            this.dao = DataBaseHelper.getInstance(context).getDao(AnswerTable.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insert(AnswerTable table){
        try {
            dao.create(table);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 删除user表中的一条数据
    public void delete(AnswerTable data) {
        try {
            dao.delete(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 修改user表中的一条数据
    public void update(AnswerTable data) {
        try {
            dao.update(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 查询user表中的所有数据
    public List<AnswerTable> selectAll() {
        List<AnswerTable> users = null;
        try {
            users = dao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    // 根据ID取出用户信息
    public AnswerTable queryById(int id) {
        AnswerTable user = null;
        try {
            user = dao.queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    // 根据ID取出用户信息
    public AnswerTable queryByAnswer(int eid,int score_id) {
        AnswerTable user = null;
        try {
            QueryBuilder builder= dao.queryBuilder();
            builder.setWhere(builder.where().eq("eid",eid).and().eq("score_id",score_id));
            user = (AnswerTable) builder.queryForFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }


    public AnswerTable getAnswer(int eid) {
        AnswerTable table = null;
        try {
            table = (AnswerTable) dao.queryBuilder().where().eq("eid", eid).queryForFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return table;
    }

}
