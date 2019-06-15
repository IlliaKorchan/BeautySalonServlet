package controller.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Filter for changing language
 */
public class LanguageFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(final ServletRequest servletRequest,final ServletResponse servletResponse, final FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String language = (String) request.getSession().getAttribute("language");

//        if(Objects.nonNull(language)) {
//            request.getSession().setAttribute("language", language.equals("uk") ? "en" : "uk");
//        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
