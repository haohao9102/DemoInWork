package com.example.administrator.helloworld;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrator.helloworld.bean.User;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/23.
 */

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String TABLE_NAME = "sqlite_test.db";

    private static final int DATABASE_VERSION = 3;

    private static DatabaseHelper instance;
   // private Dao<User,Integer> userDao;

    private Map<String,Dao> maps = new HashMap<>();

    public DatabaseHelper(Context context) {
        super(context,TABLE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTableIfNotExists(connectionSource,User.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {
        try {
            TableUtils.dropTable(connectionSource,User.class,true);
            //onCreate(sqLiteDatabase,connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    /**
     * 获取单列helper
     * @param context
     * @return
     */
    public static synchronized DatabaseHelper getHelper(Context context){
        if (instance == null){
            synchronized (DatabaseHelper.class){
                if (instance == null){
                    instance = new DatabaseHelper(context);
                }
            }
        }
        return instance;
    }

  /*  *//**
     * 获取userDao
     * @return
     * @throws SQLException
     *//*
    public Dao<User,Integer> getUserDao() throws SQLException {
        if (userDao == null){
            userDao = getDao(User.class);
        }
        return userDao;
    }
*/
    /**
     * 获取数据库的访问对象
     * @param cls
     * @return
     * @throws SQLException
     */
    public synchronized Dao getDao(Class cls) throws SQLException {
        Dao dao = null;
        String clsName = cls.getSimpleName();
        if (maps.containsKey(clsName)){
            dao = maps.get(clsName);
        }else{
            dao = super.getDao(cls);
            maps.put(clsName,dao);
        }
        return dao;
    }

    /**
     * 释放资源
     */
    @Override
    public void close() {
        super.close();
        for (String key :
                maps.keySet()) {
            Dao dao = maps.get(key);
            dao = null;
        }
      //  userDao = null;
    }
}
