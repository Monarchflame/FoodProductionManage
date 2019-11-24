package cn.qxt.controller;

import cn.qxt.pojo.Plan;
import cn.qxt.service.PlanStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/admin/leader/plan/leader")
public class PlanLeaderController {
    @Autowired
    private PlanStaffService planStaffService;
    /**
     * 返回员工中心界面
     * @return String
     */
    @RequestMapping("")
    public String workshopStaffView()
    {
        return "admin/leader/plan/leader";
    }

    /**
     * 返回管理员工界面
     * @return String
     */
    @GetMapping(value = "/staff-list")
    public String staffListView()
    {
        return "admin/leader/plan/staff-list";
    }
    /*
    管理员功能
     */
    @ResponseBody
    @PostMapping(value = "/allStaffInfo")
    public Map<String, Object> AllStaffInfo()
    {
        Map<String,Object> map = new HashMap<String, Object>();
        List<Map> staffInfoList = planStaffService.selectAllStaffInfo();
        //把管理员的信息剔除
        Iterator<Map> iterator = staffInfoList.iterator();
        while(iterator.hasNext()){
            Map staffInfo = iterator.next();
            if (staffInfo.get("position").toString().equals("管理员")) {
                iterator.remove();
            }
        }
        map.put("staffInfoList",staffInfoList);
        return map;
    }

    @GetMapping(value = "/staffInfo")
    public String staffInfo(String staffId, HttpSession session)
    {
        if (staffId!=null)
        {
            Map staffInfo = planStaffService.selectStaffInfoById(staffId);
            session.setAttribute("staffInfo",staffInfo);
            return "admin/leader/plan/staffInfo";
        }
        return "admin/leader/plan/staffInfo";
    }

    @ResponseBody
    @PostMapping(value = "/changeSalary")
    public Map<String, Object> ChangeSalary(String staffId,String newSalary)
    {
        Map<String,Object> map = new HashMap<String, Object>();
        int ret = planStaffService.ChangeSalary(staffId,Integer.parseInt(newSalary));
        map.put("ret",ret);
        return map;
    }

    @ResponseBody
    @PostMapping(value = "/changePosition")
    public Map<String, Object> ChangePosition(String staffId,String newPosition)
    {
        Map<String,Object> map = new HashMap<String, Object>();
        int ret = planStaffService.ChangePosition(staffId,newPosition);
        map.put("ret",ret);
        return map;
    }

    @RequestMapping("plan-list")
    public String planListView()
    {
        return "admin/leader/plan/plan-list";
    }
    /**
     * 返回计划详细信息
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/planList")
    public Map PlanList()
    {
        List<Plan> readyProcessPlanList = planStaffService.selectAllReadyProcessPlan();
        List<Map<String, Object>> readyProcessPlans = new ArrayList<Map<String, Object>>();
        for (Plan Plan:readyProcessPlanList) {
            readyProcessPlans.add(planStaffService.selectPlanInfoByPlanId(Plan.getId()));
        }

        List<Plan> readyDeliverPlanList = planStaffService.selectAllinProductionPlan();
        List<Map<String, Object>> readyDeliverPlans = new ArrayList<Map<String, Object>>();
        for (Plan Plan:readyDeliverPlanList) {
            readyDeliverPlans.add(planStaffService.selectPlanInfoByPlanId(Plan.getId()));
        }

        List<Plan> inProductionPlanList = planStaffService.selectAllCompletedPlan();
        List<Map<String, Object>> inProductionPlans = new ArrayList<Map<String, Object>>();
        for (Plan Plan:inProductionPlanList) {
            inProductionPlans.add(planStaffService.selectPlanInfoByPlanId(Plan.getId()));
        }

        Map<String,Object>map = new HashMap<String, Object>();
        map.put("readyProcessPlanList",readyProcessPlans);
        map.put("inProductionPlanList",readyDeliverPlans);
        map.put("completedPlanList",inProductionPlans);
        return map;
    }

    /**
     * 在员工中心界面显示生产计划信息
     * @return Maps
     */
    @ResponseBody
    @PostMapping(value = "/planInfoList")
    public Map<String, Object> PlanInfo()
    {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("planList",planStaffService.selectAllProductPlan());
        map.put("readyProcessRequirementList",planStaffService.selectReadyProcessRequirement());
        return map;
    }
}
