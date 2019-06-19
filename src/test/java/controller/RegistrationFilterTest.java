package controller;

import controller.filter.RegistrationFilter;
import org.junit.Test;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.mockito.Mockito.*;
import static string.containers.StringContainer.LOGIN_PAGE;

public class RegistrationFilterTest {
    @Test
    public void registrationTest() {
        String nameUkr = "Ілля";
        String nameEn = "Illia";
        String surnameUkr = "Корчан";
        String surnameEn = "Korchan";
        String login = "IlliaCore";
        String password = "12345678";
        String gender = "Male";
        String email = "wrongdata";

        final RegistrationFilter registrationFilter = mock(RegistrationFilter.class);
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        final FilterChain filterChain = mock(FilterChain.class);

        when(request.getParameter("userNameUkr")).thenReturn(nameUkr);
        when(request.getParameter("userNameEn")).thenReturn(nameEn);
        when(request.getParameter("userSurnameUkr")).thenReturn(surnameUkr);
        when(request.getParameter("userSurnameEn")).thenReturn(surnameEn);
        when(request.getParameter("userLogin")).thenReturn(login);
        when(request.getParameter("userPassword")).thenReturn(password);
        when(request.getParameter("userGender")).thenReturn(gender);
        when(request.getParameter("userEmail")).thenReturn(email);

        try {
            registrationFilter.doFilter(request, response, filterChain);
//            verify(request, times(1)).getRequestDispatcher(LOGIN_PAGE);
            verify(filterChain).doFilter(request, response);
        } catch (IOException | ServletException e) {
            e.printStackTrace();
        }
    }
}
