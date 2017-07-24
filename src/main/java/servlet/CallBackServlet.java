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
        /*System.out.println(userInfo);
        req.setAttribute("userInfo",userInfo);
        req.getRequestDispatcher("/user.jsp").forward(req,resp);*/
        String nickName = this.getNickName(openid);
        if(!nickName.equals("")){
            //绑定成功
            req.setAttribute("nickName",nickName);
            req.getRequestDispatcher("/success.jsp").forward(req,resp);
        }else{
            //未绑定
            req.setAttribute("openid",openid);
            req.setAttribute("nickname",userInfo.getString("nickname"));
            req.getRequestDispatcher("/login.jsp").forward(req,resp);
        }

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

    public int updateUser(String openid,String nickName,String account,String password){
        int result = 0;
        try {
            connection = DBUtil.getConnection();
            String sql = "update user set openid=?,nickname=? where account=? and password=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,openid);
            preparedStatement.setString(2,nickName);
            preparedStatement.setString(3,account);
            preparedStatement.setString(4,password);
            result = preparedStatement.executeUpdate();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String account = req.getParameter("account").trim();
        String password = req.getParameter("password").trim();
        String openid = req.getParameter("openid");
        String nickname = req.getParameter("nickname");
        int count = this.updateUser(openid,nickname,account, password);
        if(count>0){
            System.out.println("绑定成功");
        }else{
            System.out.println("绑定失败");
        }
    }
}
