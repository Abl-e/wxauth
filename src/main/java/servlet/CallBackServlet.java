package servlet;

import com.alibaba.fastjson.JSONObject;
import util.AuthUtil;
import util.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by 唐国翔 on 2017/7/20 0020.
 */
@WebServlet(name = "callBackServlet",urlPatterns = "/callBack")
public class CallBackServlet extends HttpServlet {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" +
                AuthUtil.APPID+
                "&secret=" + AuthUtil.APPSECRET+
                "&code=" + code+
                "&grant_type=authorization_code";
        JSONObject json = AuthUtil.doGetJson(url);
        System.out.println(json);
        String openid = json.getString("openid");
        String token = json.getString("access_token");
        String infoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=" + token +
                "&openid=" + openid +
                "&lang=" +
                "zh_CN";
        JSONObject userInfo = AuthUtil.doGetJson(infoUrl);
        System.out.println(userInfo);
        req.setAttribute("userInfo",userInfo);
        req.getRequestDispatcher("/user.jsp").forward(req,resp);
    }


    public String getNickName(String openid){
        String nickName = "";
        String sql = "select nickname from user where openid=?";
        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,openid);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                nickName = resultSet.getString("nickname");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeAll(connection,preparedStatement,resultSet);
        }
        return nickName;
    }
}
