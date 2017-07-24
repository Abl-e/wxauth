package util;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * Created by 唐国翔 on 2017/7/20 0020.
 */

/**
 * 数据库工具类
 */
public class DBUtil {
    private static Connection connection;
    public static Connection getConnection() throws IOException, ClassNotFoundException, SQLException {
        Properties properties = new Properties();
        properties.load(DBUtil.class.getClassLoader().getResourceAsStream("db.properties"));
        String driver = properties.getProperty("jdbc.driver");
        String url = properties.getProperty("jdbc.url");
        String username = properties.getProperty("jdbc.username");
        String password = properties.getProperty("jdbc.password");

        Class.forName(driver);

        connection = DriverManager.getConnection(url, username, password);

        return connection;
    }
    public static void closeAll(Connection connection, Statement statement,ResultSet resultSet) {
        try {
            if(resultSet!=null){
                resultSet.close();
            }
            if(statement!=null){
                statement.close();
            }
            if(connection!=null){
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
