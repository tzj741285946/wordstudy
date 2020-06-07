package net.android.workStudy.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DataBaseHelper extends OrmLiteSqliteOpenHelper {
    //单例模式
    private static DataBaseHelper dataBaseHelper;
    // 存储APP中所有的DAO对象的Map集合
    private Map<String, Dao> daos = new HashMap<>();

    private DataBaseHelper(Context context) {
        super(context, "demand.db", null, 1);
    }

    public static DataBaseHelper getInstance(Context context) {
        if (dataBaseHelper == null) {
            synchronized (DataBaseHelper.class) {
                if (dataBaseHelper == null) {
                    dataBaseHelper = new DataBaseHelper(context);
                }
            }
        }
        return dataBaseHelper;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, UserInfo.class);
            TableUtils.createTable(connectionSource, AnswerTable.class);
            TableUtils.createTable(connectionSource, ExamTable.class);
            TableUtils.createTable(connectionSource, StudyTable.class);
            TableUtils.createTable(connectionSource, ScoreTable.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {
        try {
            TableUtils.dropTable(connectionSource, UserInfo.class, true);
            TableUtils.dropTable(connectionSource, AnswerTable.class, true);
            TableUtils.dropTable(connectionSource, ExamTable.class, true);
            TableUtils.dropTable(connectionSource, StudyTable.class, true);
            TableUtils.dropTable(connectionSource, ScoreTable.class, true);
            onCreate(sqLiteDatabase, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public synchronized <D extends Dao<T, ?>, T> D getDao(Class<T> clazz) throws SQLException {
        Dao dao = null;
        String className = clazz.getSimpleName();
        if (daos.containsKey(className)) {
            dao = daos.get(className);
        }
        if (dao == null) {
            dao = super.getDao(clazz);
            daos.put(className, dao);
        }
        return (D) dao;
    }

    // 释放资源
    @Override
    public void close() {
        super.close();
        for (String key : daos.keySet()) {
            Dao dao = daos.get(key);
            dao = null;
        }
    }

}
