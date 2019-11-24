package cn.qxt.controller;

import cn.qxt.pojo.Finance;
import cn.qxt.service.FinancialStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/leader/finance/leader")
public class FinancialLeaderController {
    @Autowired
    private FinancialStaffService financialStaffService;

    /**
     * 返回员工中心界面
     * @return String
     */
    @RequestMapping("")
    public String workshopStaffView()
    {
        return "admin/leader/finance/leader";
    }

    /**
     * 返回管理员工界面
     * @return String
     */
    @GetMapping(value = "/staff-list")
    public String staffListView()
    {
        return "admin/leader/finance/staff-list";
    }

    /*
    管理员功能
     */
    @ResponseBody
    @PostMapping(value = "/allStaffInfo")
    public Map<String, Object> AllStaffInfo()
    {
        Map<String,Object> map = new HashMap<String, Object>();
        List<Map> staffInfoList = financialStaffService.selectAllStaffInfo();
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
            Map staffInfo = financialStaffService.selectStaffInfoById(staffId);
            session.setAttribute("staffInfo",staffInfo);
            return "admin/leader/finance/staffInfo";
        }
        return "admin/leader/finance/staffInfo";
    }

    @ResponseBody
    @PostMapping(value = "/changeSalary")
    public Map<String, Object> ChangeSalary(String staffId,String newSalary)
    {
        Map<String,Object> map = new HashMap<String, Object>();
        int ret = financialStaffService.ChangeSalary(staffId,Integer.parseInt(newSalary));
        map.put("ret",ret);
        return map;
    }

    @ResponseBody
    @PostMapping(value = "/changePosition")
    public Map<String, Object> ChangePosition(String staffId,String newPosition)
    {
        Map<String,Object> map = new HashMap<String, Object>();
        int ret = financialStaffService.ChangePosition(staffId,newPosition);
        map.put("ret",ret);
        return map;
    }
    /**
     * 返回查看账单界面
     * @return String
     */
    @GetMapping("/bill")
    public String billView()
    {
        return "admin/leader/finance/bill";
    }

    /**
     * 返回账单
     * @return Map中放着账单列表：billList
     */
    @ResponseBody
    @PostMapping(value = "/bill")
    public Map bill()
    {
        Map<String,Object>map = new HashMap<String, Object>();
        List<Finance> financeList = financialStaffService.selectAllFinance();
        map.put("financeList",financeList);
        return map;
    }
}
