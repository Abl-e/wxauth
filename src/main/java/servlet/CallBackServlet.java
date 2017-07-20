package servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import util.AuthUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by 唐国翔 on 2017/7/20 0020.
 */
@WebServlet(name = "callBackServlet",urlPatterns = "/callBack")
public class CallBackServlet extends HttpServlet {
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
}
