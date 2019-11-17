package cn.deali.utils;

import cn.deali.utils.sqlite.SQLiteHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Database {
    private final static Logger logger = LoggerFactory.getLogger(Database.class);
    private static SQLiteHelper db;
    public static boolean eraseData = false;


    public static SQLiteHelper getInstance() {
        if (db != null)
            return db;

        try {
            db = new SQLiteHelper("test.db");
            return db;
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return null;
        }
    }

    public static boolean init() {
        logger.info("初始化数据库！");
        db = getInstance();
        try {
            if (eraseData) {
                db.executeUpdate("drop table if exists user;");
                db.executeUpdate("create table user(username varchar(20), password varchar(20));");
                logger.info("创建数据表");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return false;
        }
    }
}
