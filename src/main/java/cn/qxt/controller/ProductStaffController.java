package cn.qxt.controller;

import cn.qxt.pojo.*;
import cn.qxt.service.OrderService;
import cn.qxt.service.ProductStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/admin/staff/product/staff")
public class ProductStaffController {
    @Autowired
    private ProductStaffService productStaffService;
    @Autowired
    private OrderService orderService;

    /**
     * 返回员工中心界面
     * @return 路径
     */
    @RequestMapping("")
    public String productStaffView()
    {
        return "admin/staff/product/staff";
    }

    @GetMapping(value = "/add")
    public String addProduct() { return "admin/staff/product/addProduct"; }

    @RequestMapping("/product-list")
    public String productList()
    {
        return "admin/staff/product/product-list";
    }

    @RequestMapping("/in")
    public String productInView()
    {
        return "admin/staff/product/productIn";
    }

    @GetMapping(value = "/return")
    public String returnView()
    {
        return "admin/staff/product/returnOrder";
    }

    @RequestMapping("/out")
    public String productOutView()
    {
        return "admin/staff/product/productOut";
    }

    @GetMapping(value = "/record")
    public String recordView()
    {
        return "admin/staff/product/record";
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
            return "admin/staff/product/goodsInfo";
        }
        return "admin/staff/product/goodsInfo";
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
    public Map<String, Object> andMaterial(HttpServletRequest request)
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
     * 入库
     * @return ret:1为成功，0为失败。
     */
    @ResponseBody
    @PostMapping(value = "/addGoods")
    public Map<String, Object> addInventory(HttpServletRequest request)
    {
        String productId = request.getParameter("productId").toString();
        String quantity = request.getParameter("quantity").toString();
        String shelf_life = request.getParameter("shelf_life").toString();
        Goods goods = new Goods();
        goods.setProduct_id(Integer.valueOf(productId));
        goods.setQuantity(Integer.valueOf(quantity));
        //计算过期时间
        Date expiration_time = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(expiration_time);//设置起时间
        cal.add(Calendar.DATE, Integer.parseInt(shelf_life));
        expiration_time = cal.getTime();
        goods.setExpiration_time(expiration_time);

        Map<String,Object> map = new HashMap<String, Object>();
        int ret = 0;
        try {
            productStaffService.addGoods(goods);
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
     * 退货界面显示订单信息
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/inReturnOrderList")
    public Map inReturnOrderList()
    {
        List<Map<String, Object>> inReturnOrderList = productStaffService.selectAllinReturnOrder();

        Map<String,Object>map = new HashMap<String, Object>();
        map.put("inReturnOrderInfo",inReturnOrderList);
        return map;
    }

    /**
     * 返回待发货订单信息
     * @param orderId 订单ID
     * @param session HttpSession
     * @return 界面路径
     */
    @GetMapping(value = "/readyDeliverOrderInfo")
    public String readyDeliverOrderInfo(String orderId, HttpSession session)
    {
        if (orderId!=null && !orderId.equals(""))
        {
            Map orderInfo = productStaffService.selectOrderInfoByOrderId(Integer.valueOf(orderId));
            session.setAttribute("readyDeliverOrderInfo",orderInfo);
            return "admin/staff/product/readyDeliverOrderInfo";
        }
        return "admin/staff/product/readyDeliverOrderInfo";
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
            return "admin/staff/product/inReturnOrderInfo";
        }
        return "admin/staff/product/inReturnOrderInfo";
    }

    /**
     * 安排发货
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/ship")
    public Map ship(String orderId)
    {
        Map<String,Object> map = new HashMap<String, Object>();

        Order order = orderService.selectByPrimaryKey(Integer.valueOf(orderId));
        int repertory = productStaffService.selectAllRepertoryByProductId(order.getProduct_id());
        int ret = 0;
        if (repertory < order.getQuantity())//库存不足
        {
            map.put("ret",ret);
            map.put("msg","库存不足");
            return map;
        }

        try {
            productStaffService.ship(Integer.parseInt(orderId));
            ret = 1;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        map.put("ret",ret);
        if (ret == 1)
            map.put("msg","发货成功");
        return map;
    }
    /**
     * 返回所有的记录
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/allRecordInfo")
    public Map<String, Object> allRecordInfo()
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

    @GetMapping(value = "ingredient")
    public String ingredientView()
    {
        return "admin/staff/product/ingredient";
    }
    /**
     * 返回成品配方详细信息
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/ingredientInfo")
    public Map<String, Object> ingredientInfo(String productId)
    {
        List<Map> ingredientInfo = productStaffService.IngredientInfoByProductId(Integer.valueOf(productId));
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("ingredientInfo",ingredientInfo);
        return map;
    }

    @ResponseBody
    @PostMapping(value = "/allMaterialList")
    public Map<String, Object> materialInfoList()
    {
        Map<String,Object> map = new HashMap<String, Object>();

        List<Material> materialList = productStaffService.selectAllMaterial();
        map.put("materialList",materialList);
        return map;
    }
}
