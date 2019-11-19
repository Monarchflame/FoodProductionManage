package cn.qxt.controller;

import cn.qxt.pojo.*;
import cn.qxt.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private SaleStaffService saleStaffService;
    @Autowired
    private FinancialStaffService financialStaffService;
    @Autowired
    private PlanStaffService planStaffService;
    @Autowired
    private ProductStaffService productStaffService;
    @Autowired
    private MaterialStaffService materialStaffService;
    @Autowired
    private WorkshopStaffService workshopStaffService;

    @RequestMapping(value = "",method = RequestMethod.GET)
    public String admin()
    {
        return "admin/admin";
    }

    /**
     * 登录
     * @param request
     * @param response
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping(value="",method= RequestMethod.POST)
    public Map login(HttpServletRequest request, HttpServletResponse response, HttpSession session)
    {
        String account = request.getParameter("account").toString();
        String password = request.getParameter("password").toString();
        String departmentCode = account.substring(4,7);//部门编码在工号4、5、6位
        Map<String,Object>map = new HashMap<String, Object>();
        System.out.println(account);
        //001为销售部
        if ("001".equals(departmentCode)) {
            SaleStaff saleStaff = saleStaffService.selectByPrimaryKey(account);
            if (password.equalsIgnoreCase(saleStaff.getPassword()))
            {
                if (!"管理员".equalsIgnoreCase(saleStaff.getPosition()))
                    return successLogin(session,saleStaff,"finance","staff");
                else
                    return successLogin(session,saleStaff,"finance","leader");
            }
            else
            {
                return failLogin(session);
            }
        }
        //002为财务部
        else if ("002".equals(departmentCode))
        {
            FinancialStaff financialStaff = financialStaffService.selectByPrimaryKey(account);
            if (password.equalsIgnoreCase(financialStaff.getPassword()))
            {
                if (!"管理员".equalsIgnoreCase(financialStaff.getPosition()))
                    return successLogin(session,financialStaff,"finance","staff");
                else
                    return successLogin(session,financialStaff,"finance","leader");
            }
            else
            {
                return failLogin(session);
            }
        }
        //003为成品库
        else if ("003".equals(departmentCode))
        {
            ProductStaff productStaff = productStaffService.selectByPrimaryKey(account);
            if (password.equalsIgnoreCase(productStaff.getPassword()))
            {
                if (!"管理员".equalsIgnoreCase(productStaff.getPosition()))
                    return successLogin(session,productStaff,"product","staff");
                else
                    return successLogin(session,productStaff,"product","leader");
            }
            else
            {
                return failLogin(session);
            }
        }
        //004是原材料库
        else if ("004".equals(departmentCode))
        {
            MaterialStaff materialStaff = materialStaffService.selectByPrimaryKey(account);
            if (password.equalsIgnoreCase(materialStaff.getPassword()))
            {
                if (!"管理员".equalsIgnoreCase(materialStaff.getPosition()))
                    return successLogin(session,materialStaff,"material","staff");
                else
                    return successLogin(session,materialStaff,"material","leader");
            }
            else
            {
                return failLogin(session);
            }
        }
        //005为生产计划科
        else if ("005".equals(departmentCode))
        {
            PlanStaff planStaff = planStaffService.selectByPrimaryKey(account);
            if (password.equalsIgnoreCase(planStaff.getPassword()))
            {
                if (!"管理员".equalsIgnoreCase(planStaff.getPosition()))
                    return successLogin(session,planStaff,"plan","staff");
                else
                    return successLogin(session,planStaff,"plan","leader");
            }
            else
            {
                return failLogin(session);
            }
        }
        //006为生产车间的领导
        else if ("006".equals(departmentCode))
        {
            WorkshopStaff workshopStaff = workshopStaffService.selectByPrimaryKey(account);
            if (password.equalsIgnoreCase(workshopStaff.getPassword()))
            {
                if (!"管理员".equalsIgnoreCase(workshopStaff.getPosition()))
                    return successLogin(session,workshopStaff,"workshop","staff");
                else
                    return successLogin(session,workshopStaff,"workshop","leader");
            }
            else
            {
                return failLogin(session);
            }
        }
        else
        {
            map.put("msg","帐号不规范");
            return map;
        }
    }

    /**
     * 成功匹配密码，登录
     * @param session HttpSession
     * @param object Object
     * @param department String
     * @param position String
     * @return Map
     */
    private Map successLogin(HttpSession session, Object object, String department, String position)
    {
        Map<String,Object>map = new HashMap<String, Object>();
        session.setAttribute("LOGIN_USER", object);
        map.put("msg","登录成功");
        map.put("ret",1);
        map.put("department",department);
        map.put("position",position);
        map.put("user",object);
        return map;
    }

    /**
     * 密码匹配失败
     * @param session HttpSession
     * @return Map
     */
    private Map failLogin(HttpSession session)
    {
        Map<String,Object>map = new HashMap<String, Object>();
        session.setAttribute("LOGIN_USER", null);
        map.put("msg","密码错误");
        return map;
    }
}
