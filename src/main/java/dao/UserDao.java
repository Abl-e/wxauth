package dao;

import com.alibaba.fastjson.JSON;
import domain.User;
import util.DBUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 唐国翔 on 2017/7/27 0027.
 */
public class UserDao {
    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    public String userList(){
        String sql = "select * from user";
        List<User> users = new ArrayList<>();
        String usersJson;
        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setAccount(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));
                user.setNickname(resultSet.getString(4));
                user.setOpenid(resultSet.getString(5));
                users.add(user);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        usersJson = JSON.toJSONString(users);
        return usersJson;
    }
}
