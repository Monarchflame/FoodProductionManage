package cn.qxt.controller;

import cn.qxt.pojo.*;
import cn.qxt.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/ceo")
public class CeoController {
    @Autowired
    private SaleStaffService saleStaffService;
    @Autowired
    private FinancialStaffService financialStaffService;
    @Autowired
    private MaterialStaffService materialStaffService;
    @Autowired
    private PlanStaffService planStaffService;
    @Autowired
    private ProductStaffService productStaffService;
    @Autowired
    private WorkshopStaffService workshopStaffService;

    @RequestMapping("")
    public String ceoView()
    {
        return "admin/leader/ceo/index";
    }

    /*
    财务部
     */
    /**
     * 返回查看账单界面
     * @return String
     */
    @GetMapping("/bill")
    public String billView()
    {
        return "admin/leader/ceo/bill";
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
            return "admin/leader/ceo/financeInfo";
        }
        return "admin/leader/ceo/financeInfo";
    }
    /*
    原材料有关
     */
    @RequestMapping("/material-list")
    public String materialList()
    {
        return "admin/leader/ceo/material-list";
    }

    @GetMapping(value = "/material-record")
    public String materialRecordView()
    {
        return "admin/leader/ceo/material-record";
    }

    /**
     * 返回原材料库存详细信息
     * @return 一个List，包含需求信息： material.id as material_id, name, material_inventory.quantity as quantity, expiration_time, material_record.create_time, material_inventory.id as inventory_id
     */
    @ResponseBody
    @PostMapping(value = "/materialInfoList")
    public Map<String, Object> materialInfoList(HttpServletRequest request)
    {
        String materialID = request.getParameter("materialID").toString();
        Map<String,Object> map = new HashMap<String, Object>();
        List <Map> materialInfoList = new ArrayList<Map>();
        if (materialID.equals(""))
        {
            materialInfoList = materialStaffService.selectAllMaterialInventoryInfo();
        }
        else
        {
            materialInfoList = materialStaffService.selectMaterialInventoryInfoByMaterialId(Integer.valueOf(materialID));
        }
        map.put("materialInfoList",materialInfoList);
        return map;
    }
    /**
     * 查找原材料的详细信息
     * @param inventory_id 原材料库存id
     * @return materialInfo:原材料的详细信息
     */
    @GetMapping(value = "/materialInfo")
    public String materialInfo(String inventory_id, HttpSession session)
    {
        if (inventory_id!=null)
        {
            Map materialInfo = materialStaffService.selectMaterialInventoryInfoById(Integer.valueOf(inventory_id));
            session.setAttribute("materialInfo",materialInfo);
            return "admin/leader/ceo/materialInfo";
        }
        return "admin/leader/ceo/materialInfo";
    }
    
    /**
     * 返回所有的记录
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/materialRecordInfo")
    public Map<String, Object> materialRecordInfo()
    {
        List<MaterialRecord> inRecord = materialStaffService.selectInRecord();
        List<Map<String, Object>> inRecordInfoList = new ArrayList<Map<String, Object>>();
        for (MaterialRecord materialRecord: inRecord) {
            inRecordInfoList.add(materialStaffService.selectRecordInfoById(materialRecord.getId()));
        }

        List<MaterialRecord> outRecord = materialStaffService.selectOutRecord();
        List<Map<String, Object>> outRecordInfoList = new ArrayList<Map<String, Object>>();
        for (MaterialRecord materialRecord: outRecord) {
            outRecordInfoList.add(materialStaffService.selectRecordInfoById(materialRecord.getId()));
        }

        List<MaterialRecord> destroyRecord = materialStaffService.selectDestroyRecord();
        List<Map<String, Object>> destroyRecordInfoList = new ArrayList<Map<String, Object>>();
        for (MaterialRecord materialRecord: destroyRecord) {
            destroyRecordInfoList.add(materialStaffService.selectRecordInfoById(materialRecord.getId()));
        }

        Map<String,Object> map = new HashMap<String, Object>();
        map.put("inRecordInfoList",inRecordInfoList);
        map.put("outRecordInfoList",outRecordInfoList);
        map.put("destroyRecordInfoList",destroyRecordInfoList);
        return map;
    }
    /*
    生产计划
     */
    @RequestMapping("plan-list")
    public String planListView()
    {
        return "admin/leader/ceo/plan-list";
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
    /*
    成品
     */
    @RequestMapping("/product-list")
    public String productList()
    {
        return "admin/leader/ceo/product-list";
    }

    @GetMapping(value = "/product-record")
    public String productRecordView()
    {
        return "admin/leader/ceo/product-record";
    }

    /**
     * 返回货物库存详细信息
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/goodsInfoList")
    public Map<String, Object> goodsInfoList(HttpServletRequest request)
    {
        String productID = request.getParameter("productID").toString();
        Map<String,Object> map = new HashMap<String, Object>();
        List<Map> goodsInfoList = new ArrayList<Map>();
        if (productID.equals(""))
        {
            goodsInfoList = productStaffService.selectAllGoodsInfo();
        }
        else
        {
            goodsInfoList = productStaffService.selectGoodsInfoByProductId(Integer.valueOf(productID));
        }
        map.put("goodsInfoList",goodsInfoList);
        return map;
    }

    /**
     * 查找货物的详细信息
     * @param goods_id 货物id
     * @return 界面路径
     */
    @GetMapping(value = "/goodsInfo")
    public String goodsInfo(String goods_id, HttpSession session)
    {
        if (goods_id!=null)
        {
            Map goodsInfo = productStaffService.selectGoodsInfoById(Integer.valueOf(goods_id));
            session.setAttribute("goodsInfo",goodsInfo);
            return "admin/leader/ceo/goodsInfo";
        }
        return "admin/leader/ceo/goodsInfo";
    }
    
    /**
     * 返回所有的记录
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/goodsRecordInfo")
    public Map<String, Object> goodsRecordInfo()
    {
        List<GoodsRecord> inRecord = productStaffService.selectInRecord();
        List<Map<String, Object>> inRecordInfoList = new ArrayList<Map<String, Object>>();
        for (GoodsRecord goodsRecord: inRecord) {
            inRecordInfoList.add(productStaffService.selectRecordInfoById(goodsRecord.getId()));
        }

        List<GoodsRecord> outRecord = productStaffService.selectOutRecord();
        List<Map<String, Object>> outRecordInfoList = new ArrayList<Map<String, Object>>();
        for (GoodsRecord goodsRecord: outRecord) {
            outRecordInfoList.add(productStaffService.selectRecordInfoById(goodsRecord.getId()));
        }

        List<GoodsRecord> destroyRecord = productStaffService.selectDestroyRecord();
        List<Map<String, Object>> destroyRecordInfoList = new ArrayList<Map<String, Object>>();
        for (GoodsRecord goodsRecord: destroyRecord) {
            destroyRecordInfoList.add(productStaffService.selectRecordInfoById(goodsRecord.getId()));
        }

        Map<String,Object> map = new HashMap<String, Object>();
        map.put("inRecordInfoList",inRecordInfoList);
        map.put("outRecordInfoList",outRecordInfoList);
        map.put("destroyRecordInfoList",destroyRecordInfoList);
        return map;
    }
    /*
    销售
     */
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
