package cn.qxt.service;

import cn.qxt.dao.*;
import cn.qxt.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SaleStaffServiceImpl implements SaleStaffService{
    @Autowired
    private SaleStaffDao saleStaffDao;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private ClientDao clientDao;
    @Autowired
    private GoodsReturnOrderDao goodsReturnOrderDao;
    @Autowired
    private ClientMessageDao clientMessageDao;
    @Autowired
    private ProductRequirementDao productRequirementDao;

    public int deleteByPrimaryKey(String id) {
        return saleStaffDao.deleteByPrimaryKey(id);
    }

    public int insert(SaleStaff record) {
        return saleStaffDao.insert(record);
    }

    public int insertSelective(SaleStaff record) {
        return saleStaffDao.insertSelective(record);
    }

    public List<SaleStaff> selectByExample(SaleStaffExample example) {
        return saleStaffDao.selectByExample(example);
    }

    public SaleStaff selectByPrimaryKey(String id) {
        return saleStaffDao.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(SaleStaff record) {
        return saleStaffDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(SaleStaff record) {
        return 0;
    }

    public List<Order> selectAllReadyProcessOrder() {
        OrderExample orderExample = new OrderExample();
        List<String>list = new ArrayList<String>();
        list.add("已付全款");
        list.add("已付定金");
        orderExample.or().andStatusIn(list);
        List<Order> readyProcessOrders = orderDao.selectByExample(orderExample);
        for (int i=0; i<readyProcessOrders.size(); i++) {
            Order order = readyProcessOrders.get(i);
            List<Map<String, Object>> stringObjectMap = goodsReturnOrderDao.selectGoodsReturnOrderInfoByOrderId(order.getId());
            //只要有退货单，一定是申请取消订单的
            //如果请求没有被拒绝，移出待处理订单列表
            if (stringObjectMap.size() !=0 && stringObjectMap.get(0).get("status") != "已拒绝") {
                readyProcessOrders.remove(i);
                i--;
            }
        }
        return readyProcessOrders;
    }

    public List<Order> selectAllReadyDeliverOrder() {
        OrderExample orderExample = new OrderExample();
        orderExample.or().andStatusEqualTo("待发货");
        return orderDao.selectByExample(orderExample);
    }

    public List<Order> selectAllinProductionOrder() {
        OrderExample orderExample = new OrderExample();
        orderExample.or().andStatusEqualTo("生产中");
        return orderDao.selectByExample(orderExample);
    }

    /*
    可以把它变成事务
     */
    public int deliverOrder(Order order) {
        ProductRequirement productRequirement = new ProductRequirement();
        productRequirement.setProduct_id(order.getProduct_id());
        productRequirement.setQuantity(order.getQuantity());
        order.setStatus("生产中");
        int result = orderDao.updateByPrimaryKey(order);
        return result * productRequirementDao.insertSelective(productRequirement);
    }

    public Map<String,Object> selectOrders() {
        OrderExample orderExample = new OrderExample();

        List<Order> readyConfirmOrders = selectAllReadyProcessOrder();

        orderExample.or().andStatusEqualTo("生产中");
        List<Order> inProductionOrders = orderDao.selectByExample(orderExample);
        orderExample.clear();
        orderExample.or().andStatusEqualTo("待发货");
        List<Order> readyDeliverOrders = orderDao.selectByExample(orderExample);

        GoodsReturnOrderExample goodsReturnOrderExample = new GoodsReturnOrderExample();
        goodsReturnOrderExample.or().andStatusEqualTo("待确认");
        List<GoodsReturnOrder> goodsReturnOrders = goodsReturnOrderDao.selectByExample(goodsReturnOrderExample);

        Map<String,Object> map = new HashMap<String, Object>();
        map.put("readyConfirmOrders",readyConfirmOrders);
        map.put("inProductionOrders",inProductionOrders);
        map.put("readyDeliverOrders",readyDeliverOrders);
        map.put("goodsReturnOrders",goodsReturnOrders);
        return map;
    }

    public List<Map<String,Object>> selectOrderInfo()
    {
        return orderDao.selectOrderInfo();
    }

    public Map<String, Object> selectOrderInfoByOrderId(Integer id) {
        return orderDao.selectOrderInfoByOrderId(id);
    }

    public List<GoodsReturnOrder> selectAllGoodsReturnOrder() {
        GoodsReturnOrderExample goodsReturnOrderExample = new GoodsReturnOrderExample();
        goodsReturnOrderExample.or().andStatusEqualTo("待确认");
        return goodsReturnOrderDao.selectByExample(goodsReturnOrderExample);
    }

    public List<Map<String, Object>> selectGoodsReturnOrderInfoByOrderId(Integer orderId) {
        return goodsReturnOrderDao.selectGoodsReturnOrderInfoByOrderId(orderId);
    }

    public List<Map<String, Object>> selectReadyConfirmGoodsReturnOrderInfo() {
        GoodsReturnOrderExample goodsReturnOrderExample = new GoodsReturnOrderExample();
        goodsReturnOrderExample.or().andStatusEqualTo("待确认");
        List<GoodsReturnOrder> goodsReturnOrderList = goodsReturnOrderDao.selectByExample(goodsReturnOrderExample);
        List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
        for (GoodsReturnOrder goodsReturnOrder: goodsReturnOrderList ) {
            returnList.add(goodsReturnOrderDao.selectGoodsReturnOrderInfoByPrimaryKey(goodsReturnOrder.getId()));
        }
        return returnList;
    }

    public Map<String, Object> selectGoodsReturnOrderInfoByPrimaryKey(Integer id) {
        return goodsReturnOrderDao.selectGoodsReturnOrderInfoByPrimaryKey(id);
    }

    public int agreeCancelOrder(Integer id) {
        GoodsReturnOrder goodsReturnOrder = goodsReturnOrderDao.selectByPrimaryKey(id);
        goodsReturnOrder.setStatus("已同意");
        Order order = orderDao.selectByPrimaryKey(goodsReturnOrder.getOrder_id());
        Client client = clientDao.selectByPrimaryKey(order.getClient_id());
        ClientMessage clientMessage = new ClientMessage();
        clientMessage.setClient_id(client.getId());
        clientMessage.setMessage("您的订单编号为 "+order.getId()+" 的订单取消申请已成功");
        int result = clientMessageDao.insertSelective(clientMessage);
        return result*goodsReturnOrderDao.updateByPrimaryKey(goodsReturnOrder);
    }

    public int disagreeCancelOrder(Integer id) {
        GoodsReturnOrder goodsReturnOrder = goodsReturnOrderDao.selectByPrimaryKey(id);
        goodsReturnOrder.setStatus("已拒绝");
        Order order = orderDao.selectByPrimaryKey(goodsReturnOrder.getOrder_id());
        Client client = clientDao.selectByPrimaryKey(order.getClient_id());
        ClientMessage clientMessage = new ClientMessage();
        clientMessage.setClient_id(client.getId());
        clientMessage.setMessage("您的订单编号为 "+order.getId()+" 的取消申请失败");
        int result = clientMessageDao.insertSelective(clientMessage);
        return result*goodsReturnOrderDao.updateByPrimaryKey(goodsReturnOrder);
    }

}
