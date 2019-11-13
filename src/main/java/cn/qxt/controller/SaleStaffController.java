package cn.qxt.controller;

import cn.qxt.pojo.*;
import cn.qxt.service.ClientService;
import cn.qxt.service.CreditService;
import cn.qxt.service.OrderService;
import cn.qxt.service.SaleStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/staff/sale/staff")
public class SaleStaffController {
    @Autowired
    private SaleStaffService saleStaffService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private CreditService creditService;
    @Autowired
    private OrderService orderService;
    /*
    有关员工
     */
    /**
     * 返回员工中心界面
     * @return String
     */
    @RequestMapping("")
    public String saleStaffView()
    {
        return "admin/staff/sale/staff";
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
     * 查看客户
     * @param request
     * @param response
     * @return String
     */
    @RequestMapping("/client-list")
    public String listClient(HttpServletRequest request, HttpServletResponse response)
    {
        ClientExample clientExample = new ClientExample();
        List<Client> clients = clientService.selectByExample(clientExample);
        request.setAttribute("clients", clients);
        return "admin/staff/sale/client-list";
    }
    @ResponseBody
    @RequestMapping(value = "/findClient",method = RequestMethod.POST)
    public List<Client> findClient(HttpServletRequest request, HttpServletResponse response)
    {
        String clientName = request.getParameter("name").toString();
        ClientExample clientExample = new ClientExample();
        clientExample.or().andNameEqualTo(clientName);
        return clientService.selectByExample(clientExample);
        //JSONObject object =JSONObject.fromObject(clients);
    }
    @RequestMapping("/clientInfo")
    public String editClient(String id,HttpServletRequest request)
    {
        if (id!=null)
        {
            ClientExample clientExample = new ClientExample();
            clientExample.or().andIdEqualTo(id);
            System.out.println(clientService.selectByExample(clientExample).get(0).getName());
            request.setAttribute("client", clientService.selectByExample(clientExample));
            return "admin/staff/sale/clientInfo";
        }
        return "admin/staff/sale/clientInfo";
    }
    @RequestMapping(value = "/updateClient",method = RequestMethod.POST)
    public void updateClient(HttpServletRequest request)
    {
        String clientId = request.getParameter("id").toString();
        String clientName = request.getParameter("name").toString();
        String clientType = request.getParameter("type").toString();
        int clientCreditrating = Integer.parseInt(request.getParameter("creditrating").toString());
        String clientAddress = request.getParameter("address").toString();

        Client client = new Client();
        client = clientService.selectByPrimaryKey(clientId);

        client.setId(clientId);
        client.setName(clientName);
        client.setType(clientType);
        client.setCreditrating(clientCreditrating);
        client.setAddress(clientAddress);

        clientService.updateByPrimaryKey(client);
    }
    /*
    有关订单
     */
    /**
     * 查看待处理的订单
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/order")
    public String order(HttpServletRequest request, HttpServletResponse response)
    {
        return "admin/staff/sale/order";
    }

    /**
     * 界面显示订单信息
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/orderList")
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
            return "admin/staff/sale/processOrderInfo";
        }
        return "admin/staff/sale/processOrderInfo";
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
     * 显示申请退货界面
     * @return String
     */
    @GetMapping(value = "/returnOrder")
    public String returnOrderView()
    {
        return "admin/staff/sale/returnOrder";
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
            return "admin/staff/sale/returnOrderInfo";
        }
        return "admin/staff/sale/returnOrderInfo";
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
        System.out.println(orderId);
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
        return "admin/staff/sale/strategy";
    }

    /**
     * 返回所有的信用等级信息
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getCredit",method = RequestMethod.POST)
    public Map getCredit(HttpServletRequest request, HttpServletResponse response)
    {
        List<Credit> credits = creditService.selectByExample(new CreditExample());
        Map<String,Object>map = new HashMap<String, Object>();
        map.put("credits",credits);
        return map;
    }

    /**
     * 更新折扣
     * @param discountList
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateDiscount",method = RequestMethod.POST)
    public Map updateDiscount(String[] discountList)
    {
        List<Credit> credits = creditService.selectByExample(new CreditExample());
        int result = 1;
        for (int i=0; i<5; i++)
        {
            Credit credit = credits.get(i);
            credit.setDiscount(Short.valueOf(discountList[i]));
            //有一次失败result就会变为零
            result = creditService.updateByPrimaryKey(credit) * result;
        }
        Map<String,Object>map = new HashMap<String, Object>();
        map.put("ret",result);
        if (result == 1)
            map.put("msg","修改成功");
        else
            map.put("msg","修改失败");
        return map;
    }

    /**
     * 更新预付款比例
     * @param ratioList
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateRatio",method = RequestMethod.POST)
    public Map updateRatio(String[] ratioList)
    {
        List<Credit> credits = creditService.selectByExample(new CreditExample());
        int result = 1;
        for (int i=0; i<5; i++)
        {
            Credit credit = credits.get(i);
            credit.setRatio(Short.valueOf(ratioList[i]));
            //有一次失败result就会变为零
            result = creditService.updateByPrimaryKey(credit) * result;
        }
        Map<String,Object>map = new HashMap<String, Object>();
        map.put("ret",result);
        if (result == 1)
            map.put("msg","修改成功");
        else
            map.put("msg","修改失败");
        return map;
    }
}
