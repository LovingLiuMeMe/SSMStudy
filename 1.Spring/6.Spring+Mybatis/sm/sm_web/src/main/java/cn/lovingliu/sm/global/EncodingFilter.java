package cn.lovingliu.sm.global;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {
    private String encoding = "UTF-8";
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 过滤器初始化
        if(filterConfig.getInitParameter("ENCODING")!=null)
            encoding = filterConfig.getInitParameter("ENCODING");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 设置用户请求和响应的编码
        request.setCharacterEncoding(encoding);
        response.setCharacterEncoding(encoding);
        // 编码处理以后 放行请求
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {
        encoding = null;
    }
}
