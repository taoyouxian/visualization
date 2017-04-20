package edu.ruc.charts.utils.db;

import edu.ruc.charts.utils.common.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @version V1.0
 * @Package: edu.ruc.charts.utils.db
 * @ClassName: DBUtil
 * @Description: 数据库操作工具类
 * @author: Tao
 * @date: Create in 2017-04-19 14:23
 **/
public class DBUtil {

    private static Logger log = LoggerFactory.getLogger(DBUtil.class);

    public static Connection getConnection() {
        try {
            return ConnectionFactory.getInstance().makeConnection();
        } catch (Exception ex) {
            log.error("SQL Server connect error! errmsg:{}", ex);
            return null;
        }
    }

    public static ResultSet Select(String SQL) {
        Connection conn = getConnection();
        Statement statement = null;
        ResultSet rs = null;
        try {
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            rs = statement.executeQuery(SQL);
        } catch (Exception e) {
            log.error("Select from sql server error! errmsg:{}", e);
        }
        return rs;
    }

    public static boolean Execute(String SQL) {
        boolean flag = false;
        Connection conn = getConnection();
        Statement statement = null;
        try {
            statement = conn.createStatement();
            statement.execute(SQL);
            flag = true;
        } catch (Exception e) {
            log.error("Execute sql error! errmsg:{}", e);
        } finally {
            try {
                statement.close();
                conn.close();
            } catch (SQLException e) {
                log.error("Close the Statement or Connection error! errmsg:{}", e);
            }
        }
        return flag;
    }

}
