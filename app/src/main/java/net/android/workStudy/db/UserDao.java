package net.android.workStudy.db;

import android.content.Context;
import android.util.Log;


import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;

public class UserDao {
    private Context context;
    // ORMLite提供的DAO类对象，第一个泛型是要操作的数据表映射成的实体类；第二个泛型是这个实体类中ID的数据类型
    private Dao<UserInfo,Integer> dao;

    public UserDao(Context context) {
        this.context = context;
        try {
            this.dao = DataBaseHelper.getInstance(context).getDao(UserInfo.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insert(UserInfo userInfo){
        try {
            dao.create(userInfo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 删除user表中的一条数据
    public void delete(UserInfo data) {
        try {
            dao.delete(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 修改user表中的一条数据
    public void update(UserInfo data) {
        try {
            dao.update(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 查询user表中的所有数据
    public List<UserInfo> selectAll() {
        List<UserInfo> users = null;
        try {
            users = dao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    // 根据ID取出用户信息
    public UserInfo queryById(int id) {
        UserInfo user = null;
        try {
            user = dao.queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    // 根据ID取出用户信息
    public UserInfo queryByUserInfo(String userName,String passWord) {
        UserInfo user = null;
        try {
            QueryBuilder builder= dao.queryBuilder();
            builder.setWhere(builder.where().eq("userName",userName).and().eq("passWord",passWord));
            user = (UserInfo) builder.queryForFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }


    public List<UserInfo> queryAllUserInfo() {
        List<UserInfo> user = null;
        try {
            Log.i("TAG",   dao.queryForAll().size()+"");
            dao.queryForAll();
            user = dao.queryBuilder().where().ne("character", 1).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }


}
