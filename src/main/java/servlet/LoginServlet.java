package servlet;

import util.AuthUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * Created by 唐国翔 on 2017/7/20 0020.
 */
@WebServlet(name = "LoginServlet",urlPatterns = "/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String backUrl = "http://1765qy5849.iask.in:19104/callBack";
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + AuthUtil.APPID+
                "&redirect_uri=" + URLEncoder.encode(backUrl)+
                "&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
        resp.sendRedirect(url);
    }
}
