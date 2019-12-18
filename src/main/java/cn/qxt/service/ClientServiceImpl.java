package cn.qxt.service;

import cn.qxt.dao.*;
import cn.qxt.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClientServiceImpl implements ClientService{
    @Autowired
    ClientDao clientDao;
    @Autowired
    CreditDao creditDao;
    @Autowired
    OrderDao orderDao;
    @Autowired
    GoodsReturnOrderDao goodsReturnOrderDao;
    @Autowired
    ClientMessageDao clientMessageDao;

    public int deleteByPrimaryKey(String id) {
       return clientDao.deleteByPrimaryKey(id);
    }

    public int insert(Client record) {
        return clientDao.insert(record);
    }

    public int insertSelective(Client record) {
        return clientDao.insertSelective(record);
    }

    public List<Client> selectByExample(ClientExample example) {
        return clientDao.selectByExample(example);
    }

    public Client selectByPrimaryKey(String id) {
        return clientDao.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(Client record) {
        return clientDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(Client record) {
        return clientDao.updateByPrimaryKey(record);
    }

    public Credit selectCreditByClientId(String id) {
        return creditDao.selectByPrimaryKey(clientDao.selectByPrimaryKey(id).getCreditrating());
    }
    public List<Credit> selectAllCredit()
    {
        return creditDao.selectByExample(new CreditExample());
    }

    public List<Order> selectMyOrdersByClientId(String id) {
        OrderExample orderExample = new OrderExample();
        orderExample.or().andClient_idEqualTo(id);
        return orderDao.selectByExample(orderExample);
    }

    public Map cancelOrder(Integer orderId)
    {
        Map<String,Object> map = new HashMap<String, Object>();
        List<Map<String,Object>> goodsReturnOrderInfo = goodsReturnOrderDao.selectGoodsReturnOrderInfoByOrderId(orderId);
        if (goodsReturnOrderInfo.size() == 0)
        {
            GoodsReturnOrder goodsReturnOrder = new GoodsReturnOrder();
            goodsReturnOrder.setOrder_id(orderId);
            goodsReturnOrder.setType("取消订单");
            int result = goodsReturnOrderDao.insertSelective(goodsReturnOrder);
            if (result == 1)
                map.put("msg","申请成功");
            else
                map.put("msg","申请失败");
        }
        //若不为空只有可能是申请取消订单
        else
        {
            map.put("msg","您已申请过退货");
        }
        return map;
    }

    @Transactional(propagation= Propagation.REQUIRED,rollbackForClassName="Exception")
    public Map returnGoods(Integer orderId)
    {
        Map<String,Object> map = new HashMap<String, Object>();
        Order order = orderDao.selectByPrimaryKey(orderId);
        List<Map<String,Object>> goodsReturnOrderInfoList = goodsReturnOrderDao.selectGoodsReturnOrderInfoByOrderId(orderId);
        //没有申请过取消订单或是退货
        if (goodsReturnOrderInfoList.size() == 0)
        {
            GoodsReturnOrder goodsReturnOrder = new GoodsReturnOrder();
            goodsReturnOrder.setOrder_id(orderId);
            goodsReturnOrder.setType("申请退货");
            int result = goodsReturnOrderDao.insertSelective(goodsReturnOrder);
            if (result == 1)
                map.put("msg","申请成功");
            else
                map.put("msg","申请失败");
            return map;
        }
        else
        {
            //有一个可能是申请取消订单，可能是申请退货
            if (goodsReturnOrderInfoList.size() == 1 )
            {
                //是申请取消订单,申请取消订单被拒绝
                if (goodsReturnOrderInfoList.get(0).get("type").equals("已拒绝"))
                {
                    //申请退货
                    GoodsReturnOrder goodsReturnOrder = new GoodsReturnOrder();
                    goodsReturnOrder.setOrder_id(orderId);
                    goodsReturnOrder.setType("申请退货");
                    int result = goodsReturnOrderDao.insertSelective(goodsReturnOrder);
                    if (result == 1)
                        map.put("msg","申请成功");
                    else
                        map.put("msg","申请失败");
                    return map;
                }
                //是申请退货、要么在审批。要么已结束
                else
                {
                    map.put("msg","您已申请过退货");
                    return map;
                }
            }
            if (goodsReturnOrderInfoList.size() == 2)
            {
                map.put("msg","您已申请过退货");
                return map;
            }
        }
        return map;
    }


    public List selectGoodsReturnOrderByOrderId(Integer orderId) {
        GoodsReturnOrderExample goodsReturnOrderExample = new GoodsReturnOrderExample();
        goodsReturnOrderExample.or().andOrder_idEqualTo(orderId);
        System.out.println(goodsReturnOrderDao.selectByExample(goodsReturnOrderExample));
        return goodsReturnOrderDao.selectByExample(goodsReturnOrderExample);
    }

    public List selectMessageByClientId(String clientId) {
        ClientMessageExample clientMessageExample = new ClientMessageExample();
        clientMessageExample.or().andClient_idEqualTo(clientId);
        return clientMessageDao.selectByExample(clientMessageExample);
    }

    @Transactional(propagation= Propagation.REQUIRED,rollbackForClassName="Exception")
    public void receive(Integer orderId)
    {
        Map<String,Object> map = new HashMap<String, Object>();
        Order order = orderDao.selectByPrimaryKey(orderId);
        //定金为0
        if (order.getDeposit() == 0)
        {
            order.setStatus("已完成");
        }
        else
        {
            order.setStatus("待付尾款");
        }
        orderDao.updateByPrimaryKeySelective(order);
    }

    @Transactional(propagation= Propagation.REQUIRED,rollbackForClassName="Exception")
    public void payTheBalance(Integer orderId)
    {
        Map<String,Object> map = new HashMap<String, Object>();
        Order order = orderDao.selectByPrimaryKey(orderId);
        Double deposit = order.getDeposit();
        Double total_payment = order.getTotal_payment();
        //扣款
        String client_id = order.getClient_id();
        Client client = clientDao.selectByPrimaryKey(client_id);
        client.setAccountbalance(client.getAccountbalance()-(total_payment - deposit));
        clientDao.updateByPrimaryKeySelective(client);
    }

    public List<Order> selectAllOrder(String clientId)
    {
        OrderExample orderExample = new OrderExample();
        orderExample.or().andClient_idEqualTo(clientId);
        return orderDao.selectByExample(orderExample);
    }

    public Map<String, Object> selectOrderInfoByOrderId(Integer id)
    {
        return orderDao.selectOrderInfoByOrderId(id);
    }

}
