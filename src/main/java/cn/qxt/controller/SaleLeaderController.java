package cn.qxt.controller;

import cn.qxt.pojo.Order;
import cn.qxt.service.SaleStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/admin/leader/sale/leader")
public class SaleLeaderController {
    @Autowired
    private SaleStaffService saleStaffService;
    /**
     * 返回员工中心界面
     * @return String
     */
    @RequestMapping("")
    public String workshopStaffView()
    {
        return "admin/leader/sale/leader";
    }

    /**
     * 返回管理员工界面
     * @return String
     */
    @GetMapping(value = "/staff-list")
    public String staffListView()
    {
        return "admin/leader/sale/staff-list";
    }
    /*
    管理员功能
     */
    @ResponseBody
    @PostMapping(value = "/allStaffInfo")
    public Map<String, Object> AllStaffInfo()
    {
        Map<String,Object> map = new HashMap<String, Object>();
        List<Map> staffInfoList = saleStaffService.selectAllStaffInfo();
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
            Map staffInfo = saleStaffService.selectStaffInfoById(staffId);
            session.setAttribute("staffInfo",staffInfo);
            return "admin/leader/sale/staffInfo";
        }
        return "admin/leader/sale/staffInfo";
    }

    @ResponseBody
    @PostMapping(value = "/changeSalary")
    public Map<String, Object> ChangeSalary(String staffId,String newSalary)
    {
        Map<String,Object> map = new HashMap<String, Object>();
        int ret = saleStaffService.ChangeSalary(staffId,Integer.parseInt(newSalary));
        map.put("ret",ret);
        return map;
    }

    @ResponseBody
    @PostMapping(value = "/changePosition")
    public Map<String, Object> ChangePosition(String staffId,String newPosition)
    {
        Map<String,Object> map = new HashMap<String, Object>();
        int ret = saleStaffService.ChangePosition(staffId,newPosition);
        map.put("ret",ret);
        return map;
    }

    /**
     * 在员工中心界面显示订单信息
     * @return Maps
     */
    @ResponseBody
    @PostMapping(value = "/orderInfo")
    public Map OrderInfo()
    {
        return saleStaffService.selectOrders();
    }

    /**
     * 查看待处理的订单
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/order-list")
    public String order(HttpServletRequest request, HttpServletResponse response)
    {
        return "admin/leader/ceo/order-list";
    }

    /**
     * 界面显示订单信息
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/orderInfoList")
    public Map orderList()
    {
        List<Order> readyProcessOrderList = saleStaffService.selectAllReadyProcessOrder();
        List<Map<String, Object>> readyProcessOrders = new ArrayList<Map<String, Object>>();
        for (Order order:readyProcessOrderList) {
            readyProcessOrders.add(saleStaffService.selectOrderInfoByOrderId(order.getId()));
        }

        List<Order> readyDeliverOrderList = saleStaffService.selectAllReadyDeliverOrder();
        List<Map<String, Object>> readyDeliverOrders = new ArrayList<Map<String, Object>>();
        for (Order order:readyDeliverOrderList) {
            readyDeliverOrders.add(saleStaffService.selectOrderInfoByOrderId(order.getId()));
        }

        List<Order> inProductionOrderList = saleStaffService.selectAllinProductionOrder();
        List<Map<String, Object>> inProductionOrders = new ArrayList<Map<String, Object>>();
        for (Order order:inProductionOrderList) {
            inProductionOrders.add(saleStaffService.selectOrderInfoByOrderId(order.getId()));
        }

        Map<String,Object>map = new HashMap<String, Object>();
        map.put("readyConfirmOrderInfo",readyProcessOrders);
        map.put("readyDeliverOrderInfo",readyDeliverOrders);
        map.put("inProductionOrderInfo",inProductionOrders);
        return map;
    }
}
