package cn.lovingliu.global;

import javax.servlet.*;
import java.io.IOException;

public class EncodeFilter implements Filter {
    private String ENCODING = "UTF-8";
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        if(filterConfig.getInitParameter("ENCODING")!=null)
            ENCODING = filterConfig.getInitParameter("ENCODING");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding(ENCODING);
        response.setCharacterEncoding(ENCODING);
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {
        ENCODING = null;
    }
}
