package cn.qxt.controller;

import cn.qxt.service.WorkshopStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

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
        return "admin/staff/workshop/staff";
    }

    /**
     * 返回生产计划界面
     * @return String
     */
    @GetMapping(value = "/plan-list")
    public String planListView()
    {
        return "admin/staff/workshop/plan-list";
    }

    /**
     * 在员工中心界面显示生产计划信息
     * @return Maps
     */
    @ResponseBody
    @PostMapping(value = "/planInfoList")
    public Map<String, Object> PlanInfoList()
    {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("planList",workshopStaffService.selectAllProductPlan());
        return map;
    }
}
