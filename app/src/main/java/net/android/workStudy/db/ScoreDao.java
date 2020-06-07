package net.android.workStudy.db;

import android.content.Context;

import com.blankj.utilcode.util.SPUtils;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;

public class ScoreDao {
    private Context context;
    // ORMLite提供的DAO类对象，第一个泛型是要操作的数据表映射成的实体类；第二个泛型是这个实体类中ID的数据类型
    private Dao<ScoreTable,Integer> dao;

    public ScoreDao(Context context) {
        this.context = context;
        try {
            this.dao = DataBaseHelper.getInstance(context).getDao(ScoreTable.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insert(ScoreTable table){
        try {
            dao.create(table);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 删除user表中的一条数据
    public void delete(ScoreTable data) {
        try {
            dao.delete(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 修改user表中的一条数据
    public void update(ScoreTable data) {
        try {
            dao.update(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 查询user表中的所有数据
    public List<ScoreTable> selectAll() {
        List<ScoreTable> users = null;
        try {
            users = dao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    // 根据ID取出用户信息
    public ScoreTable queryById(int id) {
        ScoreTable user = null;
        try {
            user = dao.queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    // 根据ID取出用户信息
    public List<ScoreTable> queryByScores() {
        List<ScoreTable> list = null;
        try {
            QueryBuilder builder= dao.queryBuilder();
            builder.setWhere(builder.where().eq("info_id",SPUtils.getInstance().getInt("id")));
            list = (List<ScoreTable>) builder.query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    public ScoreTable getScore() {
        ScoreTable table = null;
        try {
            table = (ScoreTable) dao.queryBuilder().where().eq("info_id", SPUtils.getInstance().getInt("id")).queryForFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return table;
    }

}
