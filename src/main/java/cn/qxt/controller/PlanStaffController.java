package cn.qxt.controller;

import cn.qxt.pojo.Plan;
import cn.qxt.pojo.PlanStaff;
import cn.qxt.service.PlanStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/admin/staff/plan/staff")
public class PlanStaffController {
    @Autowired
    private PlanStaffService planStaffService;

    /**
     * 返回员工中心界面
     * @return String
     */
    @RequestMapping("")
    public String planStaffView()
    {
        return "admin/staff/plan/staff";
    }

    /**
     * 在员工中心界面显示生产计划信息
     * @return Maps
     */
    @ResponseBody
    @PostMapping(value = "/productPlanList")
    public Map<String, Object> PlanInfo()
    {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("planList",planStaffService.selectAllProductPlan());
        map.put("readyProcessRequirementList",planStaffService.selectReadyProcessRequirement());
        return map;
    }

    /*
    制定生产计划
     */
    @RequestMapping("/drawUpPlan")
    public String drawUpPlanView()
    {
        return "admin/staff/plan/drawUpPlan";
    }

    /**
     * 返回需求详细信息
     * @return 一个List，包含需求信息： id:产品ID，name:产品名，quantity:数量，create_time:创建时间
     */
    @ResponseBody
    @PostMapping(value = "/requirementInfoList")
    public Map<String, Object> requirementInfoList()
    {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("requirementInfoList",planStaffService.selectReadyProcessRequirementInfo());
        return map;
    }

    /**
     * 返回产品库存信息
     * @param product_idList
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/classify")
    public Map<String, Object> classify(String[] product_idList)
    {
        //查找库存
        Map<String,Object> map = new HashMap<String, Object>();
        Map<String,Object> quantityMap = new HashMap<String, Object>();
        for (String product_id : product_idList)
        {
            int quantity = planStaffService.selectAllRepertoryByProductId(Integer.valueOf(product_id));
            quantityMap.put(product_id, quantity);
        }
        map.put("quantityMap",quantityMap);
        return map;
    }

    /**
     * 新建一个生产计划
     * @param
     * @return 结果res，为1则是成功，为0则是失败
     */
    @ResponseBody
    @PostMapping(value = "/newPlan")
    public Map<String, Object> newPlan(String product_id, String requirement, String[] requirementIdList)
    {
        Plan plan = new Plan();
        plan.setProduct_id(Integer.valueOf(product_id));
        plan.setQuantity(Integer.valueOf(requirement));
        int res = 0;
        try {
            //调用事务
            planStaffService.drawUpPlan(plan, requirementIdList);
            res = 1;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("res",res);
        return map;
    }

    @RequestMapping("plan-list")
    public String planListView()
    {
        return "admin/staff/plan/plan-list";
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
     * 返回未执行生产计划详细信息
     * @param id 生产计划id
     * @param session 生产计划放在session中
     * @return 界面
     */
    @GetMapping(value = "/processPlanInfo")
    public String processOrderView(String id, HttpSession session)
    {
        if (id!=null)
        {
            Map planInfo = planStaffService.selectPlanInfoByPlanId(Integer.valueOf(id));
            session.setAttribute("processPlan",planInfo);
            return "admin/staff/plan/processPlanInfo";
        }
        return "admin/staff/plan/processPlanInfo";
    }

    /**
     * 取消生产计划
     * @return 消息，放在Map中
     */
    @ResponseBody
    @PostMapping(value = "/cancelPlan")
    public Map cancelPlan(String planId)
    {
        int ret = planStaffService.cancelPlan(Integer.parseInt(planId));
        Map<String,Object>map = new HashMap<String, Object>();
        map.put("ret", ret);
        return map;
    }
}
