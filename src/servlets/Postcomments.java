package servlets;

import dbutil.DBUtil;
import entities.Comments;
import entities.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/postcomments")
public class Postcomments extends HttpServlet {
    DBUtil dbUtil;

    public Postcomments() {
        super();
        dbUtil = new DBUtil();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF8");
        String title = request.getParameter("title");
        String content = request.getParameter("content");



        String id_Goods = request.getParameter("idGoodsC");

        System.out.println(id_Goods+ "----------------------------------->>>>>>>>S");
        Long idGoods = Long.valueOf(id_Goods);
        System.out.println(idGoods+ "----------------------------------->>>>>>>>L");

        Users u = (Users) request.getSession().getAttribute("login");
        Date date = new Date();
        SimpleDateFormat dateFormat = null;

        dateFormat = new SimpleDateFormat("HH:mm:ss");
        String currentTimeS = dateFormat.format(date);
        dateFormat = new SimpleDateFormat("dd MMMM y");
        String currentDateS = dateFormat.format(date);

        System.out.println(title);
        System.out.println(content);
        System.out.println(u.getId());
        System.out.println(currentDateS);
        System.out.println(currentTimeS);
        System.out.println(u.getName());

        dbUtil.addNewComment(new Comments(null , idGoods, title, content, currentDateS, currentTimeS, u.getName(), u.getSurname()));
        response.sendRedirect("opengoods");

    }


}