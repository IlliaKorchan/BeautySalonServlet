package controller;

import controller.servlet.MainServlet;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.*;
import static string.containers.StringContainer.LOGIN_PAGE;

public class MainServletTest {
    private static final String PATH = LOGIN_PAGE;
    private static final String URI = "/login";
    private static MainServlet mainServlet;

    @BeforeClass
    public static void init() {
        mainServlet = new MainServlet();
        mainServlet.init();
    }

    @Test
    public void doGetTest() throws ServletException, IOException {

        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);

        when(request.getRequestDispatcher(PATH)).thenReturn(requestDispatcher);
        when(request.getRequestURI()).thenReturn(URI);

        mainServlet.doGet(request, response);

        verify(request, times(1)).getRequestDispatcher(PATH);
        verify(requestDispatcher).forward(request, response);

    }
}

