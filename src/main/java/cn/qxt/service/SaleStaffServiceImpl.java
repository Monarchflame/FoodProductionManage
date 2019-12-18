package cn.qxt.service;

import cn.qxt.dao.*;
import cn.qxt.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
    private CreditDao creditDao;
    @Autowired
    private GoodsReturnOrderDao goodsReturnOrderDao;
    @Autowired
    private ClientMessageDao clientMessageDao;
    @Autowired
    private ProductRequirementDao productRequirementDao;
    @Autowired
    private GoodsDao goodsDao;
    @Autowired
    private ProductDao productDao;

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

    public int agreeCancelOrder(Integer OrderId) {
        GoodsReturnOrder goodsReturnOrder = selectGoodsReturnOrder(OrderId);
        Order order = orderDao.selectByPrimaryKey(OrderId);

        goodsReturnOrder.setStatus("已同意");
        Client client = clientDao.selectByPrimaryKey(order.getClient_id());
        ClientMessage clientMessage = new ClientMessage();
        clientMessage.setClient_id(client.getId());
        clientMessage.setMessage("您的订单编号为 "+order.getId()+" 的订单取消申请已成功");
        int result = clientMessageDao.insertSelective(clientMessage);
        return result*goodsReturnOrderDao.updateByPrimaryKey(goodsReturnOrder);
    }

    public int disagreeCancelOrder(Integer OrderId) {
        GoodsReturnOrder goodsReturnOrder = selectGoodsReturnOrder(OrderId);
        Order order = orderDao.selectByPrimaryKey(OrderId);
        goodsReturnOrder.setStatus("已拒绝");
        Client client = clientDao.selectByPrimaryKey(order.getClient_id());
        ClientMessage clientMessage = new ClientMessage();
        clientMessage.setClient_id(client.getId());
        clientMessage.setMessage("您的订单编号为 "+order.getId()+" 的取消申请失败");
        int result = clientMessageDao.insertSelective(clientMessage);
        return result*goodsReturnOrderDao.updateByPrimaryKey(goodsReturnOrder);
    }
    @Override
    @Transactional(propagation= Propagation.REQUIRED,rollbackForClassName="Exception")
    public void agreeReturnGoods(Integer OrderId) {
        GoodsReturnOrder goodsReturnOrder = selectGoodsReturnOrder(OrderId);
        Order order = orderDao.selectByPrimaryKey(OrderId);
        goodsReturnOrder.setStatus("待收货");
        Client client = clientDao.selectByPrimaryKey(order.getClient_id());
        ClientMessage clientMessage = new ClientMessage();
        clientMessage.setClient_id(client.getId());
        clientMessage.setMessage("您的订单编号为 "+order.getId()+" 的退货申请已成功");
        clientMessageDao.insertSelective(clientMessage);
        goodsReturnOrderDao.updateByPrimaryKey(goodsReturnOrder);
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED,rollbackForClassName="Exception")
    public void disagreeReturnGoods(Integer OrderId) {
        GoodsReturnOrder goodsReturnOrder = selectGoodsReturnOrder(OrderId);
        Order order = orderDao.selectByPrimaryKey(OrderId);
        goodsReturnOrder.setStatus("已拒绝");
        Client client = clientDao.selectByPrimaryKey(order.getClient_id());
        ClientMessage clientMessage = new ClientMessage();
        clientMessage.setClient_id(client.getId());
        clientMessage.setMessage("您的订单编号为 "+order.getId()+" 的退货申请失败");
        clientMessageDao.insertSelective(clientMessage);
        goodsReturnOrderDao.updateByPrimaryKey(goodsReturnOrder);
    }

    private GoodsReturnOrder selectGoodsReturnOrder(Integer orderId)
    {
        GoodsReturnOrderExample goodsReturnOrderExample = new GoodsReturnOrderExample();
        List<String> list = new ArrayList<String>();
        list.add("已拒绝");
        list.add("已成功");
        goodsReturnOrderExample.or().andOrder_idEqualTo(orderId).andStatusNotIn(list);
        GoodsReturnOrder goodsReturnOrder;
        List<GoodsReturnOrder> goodsReturnOrderList = goodsReturnOrderDao.selectByExample(goodsReturnOrderExample);
        goodsReturnOrder = goodsReturnOrderList.get(0);
        return goodsReturnOrder;
    }
    /*
    管理员有关功能
     */
    @Override
    public List<Map> selectAllStaffInfo() {
        List<SaleStaff> saleStaffList = saleStaffDao.selectByExample(new SaleStaffExample());
        List<Map> allStaffInfo = new ArrayList<Map>();
        //依次查询详细信息
        for (SaleStaff saleStaff: saleStaffList)
        {
            allStaffInfo.add(saleStaffDao.selectStaffInfoById(saleStaff.getId()));
        }
        return allStaffInfo;
    }

    @Override
    public Map selectStaffInfoById(String staffId)
    {
        return saleStaffDao.selectStaffInfoById(staffId);
    }

    @Override
    public int ChangeSalary(String staffId, int newSalary)
    {
        SaleStaff saleStaff = saleStaffDao.selectByPrimaryKey(staffId);
        saleStaff.setSalary(newSalary);
        return saleStaffDao.updateByPrimaryKeySelective(saleStaff);
    }
    @Override
    public int ChangePosition(String staffId, String newPosition)
    {
        SaleStaff saleStaff = saleStaffDao.selectByPrimaryKey(staffId);
        saleStaff.setPosition(newPosition);
        return saleStaffDao.updateByPrimaryKeySelective(saleStaff);
    }

    @Transactional(propagation= Propagation.REQUIRED,rollbackForClassName="Exception")
    public void updateCredit()
    {
        List<Client> clientList = clientDao.selectByExample(new ClientExample());
        for (Client client:clientList)
        {
            double totalSpend = totalSpend(client.getId());
            List<Credit> creditList = creditDao.selectByExample(new CreditExample());
            Credit rightCredit = creditList.get(0);
            for (Credit credit:creditList)
            {
                Double spend = credit.getSpend();
                if (spend < totalSpend)
                    rightCredit=credit;
                else
                    break;
            }
            //rightCredit就是客户应当的等级
            client.setCreditrating(rightCredit.getGrade());
            clientDao.updateByPrimaryKeySelective(client);
        }
    }

    /**
     * 返回一个客户消费总金额
     * @param clientId 客户
     * @return double 消费金额
     */
    public double totalSpend(String clientId)
    {
        double result = 0;
        OrderExample orderExample = new OrderExample();
        orderExample.or().andClient_idEqualTo(clientId);
        List<Order> orderList = orderDao.selectByExample(orderExample);
        if (orderList.size() == 0)
            return result;
        else
        {
            for (Order order:orderList)
            {
                //剔除已取消的订单
                if (!order.getStatus().equals("已取消"))
                {
                    if (order.getStatus().equals("已完成"))
                        result += order.getTotal_payment();
                    else if (order.getDeposit() != 0)
                        result += order.getDeposit();
                    else
                        result += order.getTotal_payment();
                }
            }
            return result;
        }
    }

    /**
     * 返回货物信息及库存
     * @return [{product，quantity}]
     */
    @Override
    public List<Map> selectAllRepertory() {
        List<Map> result = new ArrayList<Map>();

        List<Product> products = productDao.selectByExample(new ProductExample());
        for (Product product:products)
        {
            Map<String, Object> map = new HashMap<String,Object>();
            map.put("product",product);
            Integer quantity = goodsDao.selectAllRepertoryByProductId(product.getId());

            map.put("quantity",quantity);
            result.add(map);
        }
        return result;
    }



}
