package cn.qxt.controller;

import cn.qxt.pojo.Plan;
import cn.qxt.service.WorkshopStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/admin/leader/workshop/leader")
public class WorkshopStaffController {
    @Autowired
    private WorkshopStaffService workshopStaffService;
    /**
     * 返回员工中心界面
     * @return String
     */
    @RequestMapping("")
    public String workshopStaffView()
    {
        return "admin/leader/workshop/staff";
    }

    /**
     * 返回生产计划界面
     * @return String
     */
    @GetMapping(value = "/plan-list")
    public String planListView()
    {
        return "admin/leader/workshop/plan-list";
    }

    /**
     * 返回管理员工界面
     * @return String
     */
    @GetMapping(value = "/staff-list")
    public String staffListView()
    {
        return "admin/leader/workshop/staff-list";
    }

    /**
     * 返回所有的计划详细信息
     * @return Maps {planList}
     */
    @ResponseBody
    @PostMapping(value = "/planInfoList")
    public Map<String, Object> PlanInfoList()
    {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("planList",workshopStaffService.selectAllPlanInfo());
        return map;
    }

    @ResponseBody
    @PostMapping(value = "/staffInfoList")
    public Map<String, Object> StaffInfoList()
    {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("staffList",workshopStaffService.selectAllStaffInfo());
        return map;
    }

    @GetMapping(value = "/productionPlanInfo")
    public String materialInfo(String planId, HttpSession session)
    {
        if (planId!=null)
        {
            Map planInfo = workshopStaffService.selectPlanInfoByPlanId(Integer.valueOf(planId));
            session.setAttribute("planInfo",planInfo);
            return "admin/leader/workshop/productionPlanInfo";
        }
        return "admin/leader/workshop/productionPlanInfo";
    }

    /**
     * 执行计划
     * @param planId 计划ID
     * @param finish_time 预计完成时间
     * @return Map{ret}
     */
    @ResponseBody
    @PostMapping(value = "/executePlan")
    public Map<String, Object> ExecutePlan(String planId,String finish_time)
    {
        Map<String,Object> map = new HashMap<String, Object>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");//定义一个formate
        int ret = 0;
        try {
            Date date = simpleDateFormat.parse(finish_time);//将formate型转化成Date数据类型
            workshopStaffService.executePlan(Integer.parseInt(planId),date);
            ret = 1;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        map.put("ret",ret);
        return map;
    }
}
