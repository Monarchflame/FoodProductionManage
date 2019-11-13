package cn.qxt.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements  Filter {

    public void destroy() {
        // TODO Auto-generated method stub

    }

    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
            throws IOException, ServletException {
        // TODO Auto-generated method stub
        HttpServletRequest request = (HttpServletRequest)arg0;
        HttpServletResponse response = (HttpServletResponse)arg1;
        HttpSession session = request.getSession();

        if(session.getAttribute("LOGIN_USER")==null
                && request.getRequestURI().indexOf("/admin") == -1){
            // 没有登录
            response.sendRedirect(request.getContextPath()+"/admin");
        }else{
            // 已经登录，继续请求下一级资源（继续访问）
            arg2.doFilter(arg0, arg1);
        }

    }
    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub

    }

}