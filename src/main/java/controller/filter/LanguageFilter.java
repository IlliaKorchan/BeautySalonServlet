package controller.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static string.containers.StringContainer.*;

/**
 * Filter for changing language
 */
public class LanguageFilter implements Filter {
    private static final Logger LOGGER = Logger.getLogger(LanguageFilter.class.getSimpleName());

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(final ServletRequest servletRequest,
                         final ServletResponse servletResponse,
                         final FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String language = (String) request.getSession().getAttribute(LANGUAGE);

        request.getSession().setAttribute(LANGUAGE, language.equals(LOCALE_UKR) ? LOCALE_EN : LOCALE_UKR);

        LOGGER.info("Language changed to " + request.getSession().getAttribute(LANGUAGE));
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
