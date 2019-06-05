package controller.servlet;

import controller.command.Command;
import controller.command.Login;
import model.dao.DaoFactory;
import model.entities.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainServlet extends HttpServlet {
    private Map<String, Command> commands;
    @Override
    public void init() throws ServletException {
        commands = new HashMap<>();
        commands.put("login", new Login());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        String path = req.getRequestURI();
        path = path.replaceAll(".*/api/" , "");
        path = path.replaceAll("\\?*" , "");
        Command command = commands.getOrDefault(path , (r)->"/WEB-INF/view/index.jsp");
        String page = command.execute(req);
        req.getRequestDispatcher(page).forward(req,resp);

//        List<User> users = DaoFactory.getInstance().createUserDao().findAll();
//        req.setAttribute("users", users );
//        req.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(req, resp);
    }

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);
//    }
}
