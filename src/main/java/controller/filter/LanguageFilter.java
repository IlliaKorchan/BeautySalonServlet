package controller.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static string.containers.StringContainer.LOCALE_EN;
import static string.containers.StringContainer.LOCALE_UKR;

/**
 * Filter for changing language
 */
public class LanguageFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse, final FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String language = (String) request.getSession().getAttribute("language");

        request.getSession().setAttribute("language", language.equals(LOCALE_UKR) ? LOCALE_EN : LOCALE_UKR);

        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
