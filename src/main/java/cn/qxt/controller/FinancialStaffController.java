package cn.qxt.controller;

import cn.qxt.pojo.Finance;
import cn.qxt.pojo.GoodsReturnOrder;
import cn.qxt.service.FinancialStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/staff/finance/staff")
public class FinancialStaffController {
    @Autowired
    private FinancialStaffService financialStaffService;

    /**
     * 返回员工中心界面
     * @return String
     */
    @RequestMapping("")
    public String financialStaffView()
    {
        return "admin/staff/finance/staff";
    }

    /**
     * 返回确认退货单界面
     * @return String
     */
    @RequestMapping("/returnOrder")
    public String confirmOrderView()
    {
        return "admin/staff/finance/returnOrder";
    }

    /**
     * 返回查看账单界面
     * @return String
     */
    @RequestMapping("/bill")
    public String billView()
    {
        return "admin/staff/finance/bill";
    }

    /**
     * 返回待确认退货单列表
     * @return Map
     */
//    @ResponseBody
//    @PostMapping(value = "/returnOrderList")
//    public Map readyConfirmOrderList()
//    {
//        List<GoodsReturnOrder> goodsReturnOrderList = financialStaffService.selectAllGoodsReturnOrder();
//        Map<String,Object> map = new HashMap<String, Object>();
//        map.put("goodsReturnOrderList",goodsReturnOrderList);
//        return map;
//    }

    /**
     * 返回待处理退货单信息列表
     * @return Map
     */
    @ResponseBody
    @PostMapping(value = "/returnOrderList")
    public Map goodsReturnOrderList()
    {
        List<Map<String, Object>> goodsReturnOrderList = financialStaffService.selectReadyProcessGoodsReturnOrderInfo();
        Map<String,Object>map = new HashMap<String, Object>();
        map.put("goodsReturnOrderList",goodsReturnOrderList);
        return map;
    }

    /**
     * 返回退货单详细信息
     * @param id String
     * @param session HttpSession
     * @return String
     */
    @GetMapping(value = "/returnOrderInfo")
    public String returnOrderInfo(String id, HttpSession session) {
        if (id != null) {
            Map goodsReturnOrderInfo = financialStaffService.selectGoodsReturnOrderInfoByPrimaryKey(Integer.valueOf(id));
            session.setAttribute("goodsReturnOrderInfo", goodsReturnOrderInfo);
            return "admin/staff/finance/returnOrderInfo";
        }
        return "admin/staff/finance/returnOrderInfo";
    }

    /**
     * 同意退款
     * @param goodsReturnOrderId String
     * @return Map
     */
    @ResponseBody
    @PostMapping(value = "/agreeRefund")
    public Map agreeRefund(String goodsReturnOrderId)
    {
        int result = financialStaffService.agreeRefund(Integer.valueOf(goodsReturnOrderId));
        Map<String,Object>map = new HashMap<String, Object>();
        if (result == 1)
            map.put("msg","操作成功");
        else
            map.put("msg","操作失败");
        return map;
    }

    /**
     * 拒绝退款
     * @param goodsReturnOrderId String
     * @return Map
     */
    @ResponseBody
    @PostMapping(value = "/disagreeRefund")
    public Map disagreeRefund(String goodsReturnOrderId)
    {
        Map<String,Object>map = new HashMap<String, Object>();
        try
        {
            financialStaffService.disagreeRefund(Integer.valueOf(goodsReturnOrderId));
            map.put("msg","操作成功");
        }
        catch (Exception e)
        {
            map.put("msg","操作失败");
        }
        return map;
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

    @GetMapping(value = "/financeInfo")
    public String financeInfo(String id, HttpSession session) {
        if (id != null) {
            /**
             * 暂时只有客户和材料库员工会涉及金额流水的记录。等业务扩大，可以考虑在finance表中增加user_type列简化判断操作
             * 或者使用正则表达式。
             */
            //是邮箱，及客户
            Map<String, Object> financeInfo;
            Finance finance = financialStaffService.selectFinanceByPrimaryKey(Integer.valueOf(id));
            String user_id = finance.getUser_id();
            if (user_id.contains("@"))
            {
                financeInfo = financialStaffService.selectFinanceInfoByPrimaryKey_Client(Integer.valueOf(id));
            }
            else
            {
                financeInfo = financialStaffService.selectFinanceInfoByPrimaryKey_Material(Integer.valueOf(id));
            }
            session.setAttribute("financeInfo", financeInfo);
            return "admin/staff/finance/financeInfo";
        }
        return "admin/staff/finance/financeInfo";
    }



}
