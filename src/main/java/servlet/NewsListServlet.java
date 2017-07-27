package servlet;

import dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * Created by 唐国翔 on 2017/7/27 0027.
 */
@WebServlet(name = "NewsListServlet",urlPatterns = "/newsList")
public class NewsListServlet extends HttpServlet {

    private UserDao userDao;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        userDao = new UserDao();
        String userList = userDao.userList();

        System.out.println(userList);
        PrintWriter writer = response.getWriter();
        writer.write(userList);
        writer.flush();
        writer.close();
        /*request.setAttribute("userList",userList);
        request.getRequestDispatcher("/table.jsp").forward(request,response);*/
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
