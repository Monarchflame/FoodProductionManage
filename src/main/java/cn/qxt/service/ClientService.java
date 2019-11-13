package cn.qxt.service;

import cn.qxt.pojo.*;

import java.util.List;
import java.util.Map;

public interface ClientService {
    int deleteByPrimaryKey(String id);

    int insert(Client record);

    int insertSelective(Client record);

    List<Client> selectByExample(ClientExample example);

    Client selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Client record);

    int updateByPrimaryKey(Client record);

    Credit selectCreditByClientId(String id);

    List<Credit> selectAllCredit();

    List<Order> selectMyOrdersByClientId(String id);

    Map cancelOrder(Integer orderId);

    Map returnGoods(Integer orderId);

    List selectGoodsReturnOrderByOrderId(Integer orderId);

    /**
     * 返回客户对应的信息列表
     * @param clientId String
     * @return List
     */
    List selectMessageByClientId(String clientId);
}
