package controller.servlet;

import controller.command.*;
import controller.command.account.processing.*;
import controller.command.schedule.AdminMasterSchedule;
import controller.command.schedule.ClientMasterSchedule;
import controller.command.schedule.MasterSchedule;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static string.containers.StringContainer.INDEX_PAGE;

/**
 * Main servlet, for processing all requests
 */
public class MainServlet extends HttpServlet {
    private Map<String, Command> commands;

    /**
     * Init method, that initialize servlet object and fills commands map
     */
    @Override
    public void init() {
        commands = new HashMap<>();
        commands.put("login", new Login());
        commands.put("logout", new Logout());
        commands.put("menu", new Menu());
        commands.put("registration", new Registration());
        commands.put("client-appointments", new ClientAppointments());
        commands.put("delete-account", new DeleteAccount());
        commands.put("change-password", new ChangePassword());
        commands.put("change-password-commit", new ChangePasswordCommit());
        commands.put("procedures", new Procedures());
        commands.put("client-reviews", new ClientReviews());
        commands.put("make-appointment", new MakeAppointment());
        commands.put("master-schedule", new MasterSchedule());
        commands.put("admin-reviews", new AdminReviews());
        commands.put("client-master-schedule", new ClientMasterSchedule());
        commands.put("admin-master-schedule", new AdminMasterSchedule());
        commands.put("make-review", new MakeReview());
    }

    /**
     * DoGet method, that fetches URI from request, split it and send request to appropriate implementation of
     * interface Command
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String path = req.getRequestURI();
        path = path.replaceAll(".*/" , "");
        path = path.replaceAll("\\?*" , "");
        Command command = commands.getOrDefault(path , (r)-> INDEX_PAGE);
        String page = command.execute(req);
        req.getRequestDispatcher(page).forward(req,resp);
    }

    /**
     * DoPost method, that redirects any request to the DoGet method
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
