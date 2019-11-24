package cn.qxt.interceptor;

import cn.qxt.pojo.*;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {
    /**
     * 在拦截点执行前拦截，如果返回true则不执行拦截点后的操作（拦截成功）返回false则不执行拦截
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @return
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        // 在拦截点执行前拦截，如果返回true则不执行拦截点后的操作（拦截成功）返回false则不执行拦截
        HttpSession session = httpServletRequest.getSession();
        String url = httpServletRequest.getRequestURI();//请求地址
        //根据身份拦截用户
        String[] strings = url.split("/");
//        int i=0;
//        for (String s:strings) {
//            System.out.println(i+" "+s);
//            i++;
//        }
        if(strings.length < 2)
        {
            return false;
        }
        if (strings[2].equalsIgnoreCase("staff"))//员工登录
        {
            if (strings[3].equalsIgnoreCase("sale"))//销售部
            {
                if(strings[4].equalsIgnoreCase("staff"))//员工
                {
                    if (session.getAttribute("LOGIN_USER")!=null && session.getAttribute("LOGIN_USER").getClass().equals(SaleStaff.class))
                    {
                        return true;
                    }
                    else {
                        System.out.println("拦截员工");
                        // 拦截后进入登录页面
                        //httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+"/admin");
                        httpServletResponse.sendRedirect("/admin");
                        return false;
                    }
                }
                else if (strings[4].equalsIgnoreCase("manager"))
                {
                    return false;
                }
            }
            else if (strings[3].equalsIgnoreCase("finance"))//财务部
            {
                if(strings[4].equalsIgnoreCase("staff"))//员工
                {
                    if (session.getAttribute("LOGIN_USER")!=null && session.getAttribute("LOGIN_USER").getClass().equals(FinancialStaff.class))
                    {
                        return true;
                    }
                    else {
                        System.out.println("拦截员工");
                        httpServletResponse.sendRedirect("/admin");
                        return false;
                    }
                }
                else if (strings[4].equalsIgnoreCase("manager"))
                {
                    return false;
                }
            }
            else if (strings[3].equalsIgnoreCase("product"))//成品库
            {
                if(strings[4].equalsIgnoreCase("staff"))//员工
                {
                    if (session.getAttribute("LOGIN_USER")!=null && session.getAttribute("LOGIN_USER").getClass().equals(ProductStaff.class))
                    {
                        return true;
                    }
                    else {
                        System.out.println("拦截员工");
                        httpServletResponse.sendRedirect("/admin");
                        return false;
                    }
                }
                else if (strings[4].equalsIgnoreCase("manager"))
                {
                    return false;
                }
            }
            else if (strings[3].equalsIgnoreCase("material"))//原材料库
            {
                if(strings[4].equalsIgnoreCase("staff"))//员工
                {
                    if (session.getAttribute("LOGIN_USER")!=null && session.getAttribute("LOGIN_USER").getClass().equals(MaterialStaff.class))
                    {
                        return true;
                    }
                    else {
                        System.out.println("拦截员工");
                        httpServletResponse.sendRedirect("/admin");
                        return false;
                    }
                }
                else if (strings[4].equalsIgnoreCase("manager"))
                {
                    return false;
                }
            }
            else if (strings[3].equalsIgnoreCase("plan"))//生产计划科
            {
                if(strings[4].equalsIgnoreCase("staff"))//员工
                {
                    if (session.getAttribute("LOGIN_USER")!=null && session.getAttribute("LOGIN_USER").getClass().equals(PlanStaff.class))
                    {
                        return true;
                    }
                    else {
                        System.out.println("拦截员工");
                        httpServletResponse.sendRedirect("/admin");
                        return false;
                    }
                }
                else if (strings[4].equalsIgnoreCase("manager"))
                {
                    return false;
                }
            }
            else if (strings[3].equalsIgnoreCase("workshop"))//生产车间
            {
                if(strings[4].equalsIgnoreCase("staff"))//员工
                {
                    if (session.getAttribute("LOGIN_USER")!=null && session.getAttribute("LOGIN_USER").getClass().equals(WorkshopStaff.class))
                    {
                        return true;
                    }
                    else {
                        System.out.println("拦截员工");
                        httpServletResponse.sendRedirect("/admin");
                        return false;
                    }
                }
                else if (strings[4].equalsIgnoreCase("manager"))
                {
                    return false;
                }
            }
        }
        else if (strings[2].equalsIgnoreCase("leader"))//领导登录
        {
            if (strings[3].equalsIgnoreCase("sale"))
            {
                if(strings[4].equalsIgnoreCase("leader"))//领导
                {
                    if (session.getAttribute("LOGIN_USER")!=null && session.getAttribute("LOGIN_USER").getClass().equals(SaleStaff.class))
                    {
                        return true;
                    }
                    else {
                        System.out.println("拦截领导");
                        httpServletResponse.sendRedirect("/admin");
                        return false;
                    }
                }
            }
            if (strings[3].equalsIgnoreCase("finance"))
            {
                if(strings[4].equalsIgnoreCase("leader"))//领导
                {
                    if (session.getAttribute("LOGIN_USER")!=null && session.getAttribute("LOGIN_USER").getClass().equals(FinancialStaff.class))
                    {
                        return true;
                    }
                    else {
                        System.out.println("拦截领导");
                        httpServletResponse.sendRedirect("/admin");
                        return false;
                    }
                }
            }
            if (strings[3].equalsIgnoreCase("product"))
            {
                if(strings[4].equalsIgnoreCase("leader"))//领导
                {
                    if (session.getAttribute("LOGIN_USER")!=null && session.getAttribute("LOGIN_USER").getClass().equals(ProductStaff.class))
                    {
                        return true;
                    }
                    else {
                        System.out.println("拦截领导");
                        httpServletResponse.sendRedirect("/admin");
                        return false;
                    }
                }
            }
            if (strings[3].equalsIgnoreCase("material"))
            {
                if(strings[4].equalsIgnoreCase("leader"))//领导
                {
                    if (session.getAttribute("LOGIN_USER")!=null && session.getAttribute("LOGIN_USER").getClass().equals(MaterialStaff.class))
                    {
                        return true;
                    }
                    else {
                        System.out.println("拦截领导");
                        httpServletResponse.sendRedirect("/admin");
                        return false;
                    }
                }
            }
            if (strings[3].equalsIgnoreCase("plan"))
            {
                if(strings[4].equalsIgnoreCase("leader"))//领导
                {
                    if (session.getAttribute("LOGIN_USER")!=null && session.getAttribute("LOGIN_USER").getClass().equals(PlanStaff.class))
                    {
                        return true;
                    }
                    else {
                        System.out.println("拦截领导");
                        httpServletResponse.sendRedirect("/admin");
                        return false;
                    }
                }
            }
            if (strings[3].equalsIgnoreCase("workshop"))//生产车间
            {
                if(strings[4].equalsIgnoreCase("leader"))//领导
                {
                    if (session.getAttribute("LOGIN_USER")!=null && session.getAttribute("LOGIN_USER").getClass().equals(WorkshopStaff.class))
                    {
                        return true;
                    }
                    else {
                        System.out.println("拦截领导");
                        httpServletResponse.sendRedirect("/admin");
                        return false;
                    }
                }
            }
        }
        else if (strings[2].equalsIgnoreCase("ceo"))//ceo登录
        {
            if (session.getAttribute("LOGIN_USER")!=null && session.getAttribute("LOGIN_USER").getClass().equals(CEO.class))
            {
                return true;
            }
            else {
                System.out.println("拦截CEO");
                httpServletResponse.sendRedirect("/admin");
                return false;
            }
        }
        else if (strings[1].equalsIgnoreCase("user"))//客户登录
        {
            if (session.getAttribute("LOGIN_USER")!=null && session.getAttribute("LOGIN_USER").getClass().equals(Client.class))
            {
                return true;
            }
            else {
                System.out.println("拦截客户");
                // 拦截后进入主页
                httpServletResponse.sendRedirect("/user/login");
                return false;
            }
        }
        return false;
    }

    /**
     * 在处理过程中，执行拦截
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param modelAndView
     * @throws Exception
     */
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 执行完毕，返回前拦截
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param e
     * @throws Exception
     */
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
