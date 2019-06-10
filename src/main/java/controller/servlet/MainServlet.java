package controller.servlet;

import controller.command.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainServlet extends HttpServlet {
    private Map<String, Command> commands;

    @Override
    public void init() {
        commands = new HashMap<>();
        commands.put("login", new Login());
        commands.put("logout", new Logout());
        commands.put("registration", new Registration());
        commands.put("client-appointments", new ClientAppointments());
        commands.put("delete-account", new DeleteAccount());
        commands.put("change-password", new ChangePassword());
        commands.put("change-password-commit", new ChangePasswordCommit());
        commands.put("client-procedures", new Procedures());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String path = req.getRequestURI();
        path = path.replaceAll(".*/" , "");
        path = path.replaceAll("\\?*" , "");
        Command command = commands.getOrDefault(path , (r)->"/WEB-INF/view/index.jsp");
        String page = command.execute(req);

        req.getRequestDispatcher(page).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
