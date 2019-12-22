package cn.qxt.controller;

import cn.qxt.pojo.*;
import cn.qxt.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

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
    @Autowired
    private ClientService clientService;
    @Autowired
    private CreditService creditService;
    @Autowired
    private OrderService orderService;

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
    /**
     * 返回确认退货单界面
     * @return String
     */
    @RequestMapping("/returnOrder")
    public String confirmOrderView()
    {
        return "admin/leader/ceo/returnOrder";
    }

    /**
     * 返回查看账单界面
     * @return String
     */
    @RequestMapping("/bill")
    public String billView()
    {
        return "admin/leader/ceo/bill";
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
    @PostMapping(value = "/returnOrder")
    public Map goodsReturnOrderList()
    {
        List<Map<String, Object>> goodsReturnOrderList = financialStaffService.selectReadyProcessGoodsReturnOrderInfo();
        Map<String,Object>map = new HashMap<String, Object>();
        map.put("goodsReturnOrderList",goodsReturnOrderList);
        return map;
    }

    /**
     * 返回确认退货单界面
     * @return String
     */
    @RequestMapping("/finance_returnOrder")
    public String finance_returnOrderView()
    {
        return "admin/leader/ceo/finance_returnOrder";
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
            return "admin/leader/ceo/financeInfo";
        }
        return "admin/leader/ceo/financeInfo";
    }
    /*
    原材料
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

    @RequestMapping("/addMaterial")
    public String addMaterialView()
    {
        return "admin/leader/ceo/addMaterial";
    }

    @RequestMapping("/inMaterial")
    public String materialInView()
    {
        return "admin/leader/ceo/materialIn";
    }

    @RequestMapping("/outMaterial")
    public String materialOutView()
    {
        return "admin/leader/ceo/materialOut";
    }

    @GetMapping(value = "/buyMaterial")
    public String buyView()
    {
        return "admin/leader/ceo/buyMaterial";
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
     * 销毁过期原材料
     * @param request HttpServletRequest
     * @return Map,存放消息
     */
    @ResponseBody
    @PostMapping(value = "/destruction")
    public Map<String, Object> destruction(HttpServletRequest request)
    {
        String inventoryId = request.getParameter("inventoryId").toString();
        Map<String,Object> map = new HashMap<String, Object>();
        int ret = 0;
        try {
            materialStaffService.destroy(Integer.valueOf(inventoryId));
            ret = 1;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        map.put("ret",ret);
        return map;
    }

    /**
     * 添加原材料种类
     * @param request
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/addMaterial")
    public Map<String, Object> andMaterial(HttpServletRequest request)
    {
        String name = request.getParameter("name").toString();
        String shelf_time = request.getParameter("shelf_time").toString();
        Material material = new Material();
        material.setName(name);
        material.setShelf_life(Integer.valueOf(shelf_time));

        Map<String,Object> map = new HashMap<String, Object>();
        int ret = materialStaffService.andMaterial(material);
        map.put("ret",ret);
        return map;
    }

    /**
     * 返回所有的材料信息
     * @return materialList:放置着所有原材料信息
     */
    @ResponseBody
    @PostMapping(value = "/allMaterialInfoList")
    public Map<String, Object> allMaterialInfoList()
    {
        Map<String,Object> map = new HashMap<String, Object>();
        List<Material> materialList = materialStaffService.selectAllMaterial();
        map.put("materialList",materialList);
        return map;
    }

    /**
     * 入库
     * @return ret:1为成功，0为失败。
     */
    @ResponseBody
    @PostMapping(value = "/addInventory")
    public Map<String, Object> addInventory(HttpServletRequest request)
    {
        String materialId = request.getParameter("materialId").toString();
        String quantity = request.getParameter("quantity").toString();
        String shelf_life = request.getParameter("shelf_life").toString();
        MaterialInventory materialInventory = new MaterialInventory();
        materialInventory.setMaterial_id(Integer.valueOf(materialId));
        materialInventory.setQuantity(Integer.valueOf(quantity));
        //计算过期时间
        Date expiration_time = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(expiration_time);//设置起时间
        cal.add(Calendar.DATE, Integer.parseInt(shelf_life));
        expiration_time = cal.getTime();
        materialInventory.setExpiration_time(expiration_time);

        Map<String,Object> map = new HashMap<String, Object>();
        int ret = 0;
        try {
            materialStaffService.addInventory(materialInventory);
            ret = 1;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        map.put("ret",ret);
        return map;
    }

    /**
     * 返回原材料库存信息
     * @param material_idList
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/classify")
    public Map<String, Object> classify(String[] material_idList)
    {
        //查找库存
        Map<String,Object> map = new HashMap<String, Object>();
        Map<String,Object> quantityMap = new HashMap<String, Object>();
        for (String material_id : material_idList)
        {
            int quantity = materialStaffService.selectAllRepertoryByMaterialId(Integer.valueOf(material_id));
            quantityMap.put(material_id, quantity);
        }
        map.put("quantityMap",quantityMap);
        return map;
    }

    /**
     * 返回需求详细信息
     * @return 一个List，包含需求信息： id:产品ID，name:产品名，quantity:数量，create_time:创建时间
     */
    @ResponseBody
    @PostMapping(value = "/requirementInfoList")
    public Map<String, Object> requirementInfoList(String materialID)
    {
        Map<String,Object> map = new HashMap<String, Object>();

        List <Map> requirementInfoList = new ArrayList<Map>();
        if (materialID.equals(""))
        {
            requirementInfoList = materialStaffService.selectReadyProcessRequirementInfo();
        }
        else
        {
            requirementInfoList = materialStaffService.selectReadyProcessRequirementInfoByMaterialID(Integer.valueOf(materialID));
        }
        map.put("requirementInfoList",requirementInfoList);
        return map;
    }

    /**
     * 出库
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/inventoryOut")
    public Map<String, Object> inventoryOut(String material_id, String requirement, Integer[] requirementIdList)
    {
        int ret = 0;
        try {
            //调用事务
            materialStaffService.inventoryOut(Integer.valueOf(material_id), Integer.valueOf(requirement), requirementIdList);
            ret = 1;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("ret",ret);
        return map;
    }

//    @GetMapping(value = "/materialRecordInfo")
//    public String materialRecordInfo(String material_record_id, HttpSession session)
//    {
//        if (material_record_id!=null)
//        {
//            Map materialRecordInfo = materialStaffService.selectRecordInfoById(Integer.valueOf(material_record_id));
//            session.setAttribute("materialRecordInfo",materialRecordInfo);
//            return "admin/staff/material/materialRecordInfo";
//        }
//        return "admin/staff/material/materialRecordInfo";
//    }

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
        return "admin/leader/ceo/drawUpPlan";
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

    @GetMapping(value = "/addProduct")
    public String addProduct() { return "admin/leader/ceo/addProduct"; }

    @RequestMapping("/inProduct")
    public String productInView()
    {
        return "admin/leader/ceo/productIn";
    }

    @GetMapping(value = "/product_returnOrder")
    public String returnView()
    {
        return "admin/leader/ceo/product_returnOrder";
    }

    @RequestMapping("/outProduct")
    public String productOutView()
    {
        return "admin/leader/ceo/productOut";
    }


    /**
     * 销毁
     * @param goodsId 货物ID
     * @return ret 0:失败，1：成功
     */
    @ResponseBody
    @PostMapping(value = "/destroy")
    public Map<String, Object> destroy(String goodsId)
    {
        Map<String,Object> map = new HashMap<String, Object>();
        int ret = 0;
        try {
            productStaffService.destroy(Integer.valueOf(goodsId));
            ret = 1;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        map.put("ret",ret);
        return map;
    }

    /**
     * 返回所有成品信息
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/allGoodsInfoList")
    public Map<String, Object> allGoodsInfoList()
    {
        Map<String,Object> map = new HashMap<String, Object>();
        List<Product> productList = productStaffService.selectAllProduct();
        map.put("productList",productList);
        return map;
    }

    /**
     * 添加产品种类
     * @param request HttpServletRequest
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/addProduct")
    public Map<String, Object> addProduct(HttpServletRequest request)
    {
        String name = request.getParameter("name").toString();
        String shelf_time = request.getParameter("shelf_time").toString();
        String unit_price = request.getParameter("unit_price").toString();
        String specification = request.getParameter("specification").toString();
        Product product = new Product();
        product.setName(name);
        product.setShelf_life(Integer.valueOf(shelf_time));
        product.setSpecification(specification);
        product.setUnit_price(Double.valueOf(unit_price));

        Map<String,Object> map = new HashMap<String, Object>();
        int ret = productStaffService.addProduct(product);
        map.put("ret",ret);
        return map;
    }

    /**
     * 发货界面显示订单信息
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/readyDeliverOrderList")
    public Map readyDeliverOrderList()
    {
        List<Order> readyDeliverOrderList = productStaffService.selectAllReadyDeliverOrder();
        List<Map<String, Object>> readyDeliverOrders = new ArrayList<Map<String, Object>>();
        for (Order order:readyDeliverOrderList) {
            readyDeliverOrders.add(productStaffService.selectOrderInfoByOrderId(order.getId()));
        }

        Map<String,Object>map = new HashMap<String, Object>();
        map.put("readyDeliverOrderInfo",readyDeliverOrders);
        return map;
    }

    /**
     * 返回待发货订单信息
     * @param id String
     * @param session HttpSession
     * @return String
     */
    @GetMapping(value = "/readyDeliverOrderInfo")
    public String readyDeliverOrder(String id, HttpSession session)
    {
        if (id!=null)
        {
            Map orderInfo = saleStaffService.selectOrderInfoByOrderId(Integer.valueOf(id));
            session.setAttribute("readyDeliverOrder",orderInfo);
            return "admin/staff/sale/readyDeliverOrderInfo";
        }
        return "admin/staff/sale/readyDeliverOrderInfo";
    }

    /**
     * 返回安排生产的订单信息
     * @param id 订单ID
     * @param session HttpSession
     * @return 界面路径
     */
    @GetMapping(value = "/inProductionOrderInfo")
    public String inProductionOrderInfo(String id, HttpSession session)
    {
        if (id!=null && !id.equals(""))
        {
            Map orderInfo = saleStaffService.selectOrderInfoByOrderId(Integer.valueOf(id));
            session.setAttribute("readyDeliverOrder",orderInfo);
            return "admin/leader/ceo/readyDeliverOrderInfo";
        }
        return "admin/leader/ceo/readyDeliverOrderInfo";
    }

    /**
     * 返回待退货订单信息
     * @param goodsReturnOrderId 退货单ID
     * @param session HttpSession
     * @return 界面路径
     */
    @GetMapping(value = "/inReturnOrderList")
    public String inReturnOrderList(String goodsReturnOrderId, HttpSession session)
    {
        if (goodsReturnOrderId!=null && !goodsReturnOrderId.equals(""))
        {
            Map<String, Object> inReturnOrderInfo = productStaffService.selectGoodsReturnOrderInfoByPrimaryKey(Integer.valueOf(goodsReturnOrderId));
            session.setAttribute("inReturnOrderInfo",inReturnOrderInfo);
            return "admin/leader/ceo/inReturnOrderInfo";
        }
        return "admin/leader/ceo/inReturnOrderInfo";
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
     * 返回待处理的订单信息
     * @param id
     * @param session
     * @return
     */
    @GetMapping(value = "/processOrderInfo")
    public String processOrderView(String id, HttpSession session)
    {
        if (id!=null)
        {
            Map orderInfo = saleStaffService.selectOrderInfoByOrderId(Integer.valueOf(id));
            session.setAttribute("processOrder",orderInfo);
            return "admin/leader/ceo/processOrderInfo";
        }
        return "admin/leader/ceo/processOrderInfo";
    }

    /**
     * 显示申请退货界面
     * @return String
     */
    @GetMapping(value = "/returnOrder")
    public String returnOrderView()
    {
        return "admin/leader/ceo/returnOrder";
    }

    /**
     * 显示待确认退货单
     * @return Map
     */
    @ResponseBody
    @PostMapping(value = "/returnOrderList")
    public Map returnOrderList()
    {
        //获取所有待确认的退货单详细信息
        List<Map<String, Object>> goodsReturnOrderList = saleStaffService.selectReadyConfirmGoodsReturnOrderInfo();
        Map<String,Object>map = new HashMap<String, Object>();
        map.put("goodsReturnOrderList",goodsReturnOrderList);
        return map;
    }

    /**
     * 返回待退货订单信息
     * @param goodsReturnOrderId
     * @param session
     * @return
     */
    @GetMapping(value = "/returnOrderInfo")
    public String returnOrderInfo(String goodsReturnOrderId, HttpSession session)
    {
        if (goodsReturnOrderId!=null)
        {
            Map<String, Object> goodsReturnOrderInfo = saleStaffService.selectGoodsReturnOrderInfoByPrimaryKey(Integer.valueOf(goodsReturnOrderId));
            session.setAttribute("goodsReturnOrderInfo",goodsReturnOrderInfo);
            return "admin/leader/ceo/returnOrderInfo";
        }
        return "admin/leader/ceo/returnOrderInfo";
    }

    /**
     * 生成生产要求，返回给生产计划科
     * @param request HttpServletRequest
     * @return 返回成功或失败的消息
     */
    @ResponseBody
    @PostMapping(value = "/deliverOrder")
    public Map deliverOrder(HttpServletRequest request)
    {
        String orderId = request.getParameter("orderId");
        Order order = orderService.selectByPrimaryKey(Integer.valueOf(orderId));
        int result = saleStaffService.deliverOrder(order);
        Map<String,Object>map = new HashMap<String, Object>();
        map.put("ret",result);
        if (result == 1)
            map.put("msg","操作成功");
        else
            map.put("msg","操作失败");
        return map;
    }

    /**
     * 安排发货，设置订单状态为：待发货
     * @param request HttpServletRequest
     * @return Map
     */
    @ResponseBody
    @PostMapping(value = "/deliverGoods")
    public Map deliverGoods(HttpServletRequest request)
    {
        String orderId = request.getParameter("orderId");
        Order order = orderService.selectByPrimaryKey(Integer.valueOf(orderId));
        order.setStatus("待发货");
        int result = orderService.updateByPrimaryKey(order);
        Map<String,Object>map = new HashMap<String, Object>();
        map.put("ret",result);
        if (result == 1)
            map.put("msg","操作成功");
        else
            map.put("msg","操作失败");
        return map;
    }

    /**
     * 同意取消订单
     * @param id String
     * @return Map
     */
    @ResponseBody
    @PostMapping(value = "/agreeCancelOrder")
    public Map agreeCancelOrder(String id)
    {
        int result = saleStaffService.agreeCancelOrder(Integer.valueOf(id));
        Map<String,Object>map = new HashMap<String, Object>();
        map.put("ret",result);
        if (result == 1)
            map.put("msg","操作成功");
        else
            map.put("msg","操作失败");
        return map;
    }

    /**
     * 拒绝取消订单
     * @param id String
     * @return Map
     */
    @ResponseBody
    @PostMapping(value = "/disagreeCancelOrder")
    public Map disagreeCancelOrder(String id)
    {
        int result = saleStaffService.disagreeCancelOrder(Integer.valueOf(id));
        Map<String,Object>map = new HashMap<String, Object>();
        map.put("ret",result);
        if (result == 1)
            map.put("msg","操作成功");
        else
            map.put("msg","操作失败");
        return map;
    }

    /*
    以下是关于销售策略
     */
    /**
     * 查看销售策略
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/strategy")
    public String strategyView(HttpServletRequest request, HttpServletResponse response)
    {
        return "admin/leader/ceo/strategy";
    }
    @RequestMapping("/client-list")
    public String listClient(HttpServletRequest request, HttpServletResponse response)
    {
        ClientExample clientExample = new ClientExample();
        List<Client> clients = clientService.selectByExample(clientExample);
        request.setAttribute("clients", clients);
        return "admin/leader/ceo/client-list";
    }


    /**
     * 返回生产计划界面
     * @return String
     */
    @GetMapping(value = "/workshop-plan-list")
    public String workshopPlanListView()
    {
        return "admin/leader/ceo/workshop-plan-list";
    }

    /**
     * 返回管理员工界面
     * @return String
     */
    @GetMapping(value = "/workshop-staff-list")
    public String workshopStaffListView()
    {
        return "admin/leader/ceo/workshop-staff-list";
    }

    @GetMapping(value = "/productionPlanInfo")
    public String productionPlanInfo(String planId, HttpSession session)
    {
        if (planId!=null)
        {
            Map planInfo = workshopStaffService.selectPlanInfoByPlanId(Integer.valueOf(planId));
            session.setAttribute("planInfo",planInfo);
            return "admin/leader/ceo/productionPlanInfo";
        }
        return "admin/leader/ceo/productionPlanInfo";
    }

    @GetMapping(value = "/inProductionPlanInfo")
    public String inProductionPlanInfo(String planId, HttpSession session)
    {
        if (planId!=null)
        {
            Map planInfo = workshopStaffService.selectPlanInfoByPlanId(Integer.valueOf(planId));
            session.setAttribute("planInfo",planInfo);
            return "admin/leader/ceo/inProductionPlanInfo";
        }
        return "admin/leader/ceo/inProductionPlanInfo";
    }

    /**
     * 返回待退货订单信息
     * @param goodsReturnOrderId
     * @param session
     * @return
     */
    @GetMapping(value = "/product_inReturnOrderInfo")
    public String product_inReturnOrderInfo(String goodsReturnOrderId, HttpSession session)
    {
        if (goodsReturnOrderId!=null)
        {
            Map<String, Object> goodsReturnOrderInfo = saleStaffService.selectGoodsReturnOrderInfoByPrimaryKey(Integer.valueOf(goodsReturnOrderId));
            session.setAttribute("goodsReturnOrderInfo",goodsReturnOrderInfo);
            return "admin/leader/ceo/product_returnOrderInfo";
        }
        return "admin/leader/ceo/product_returnOrderInfo";
    }
}
