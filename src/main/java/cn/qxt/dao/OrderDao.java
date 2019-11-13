package cn.qxt.dao;

import cn.qxt.pojo.Order;
import cn.qxt.pojo.OrderExample;
import java.util.List;
import java.util.Map;

public interface OrderDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    List<Order> selectByExample(OrderExample example);

    Order selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    List<Map<String,Object>> selectOrderInfo();

    Map<String, Object> selectOrderInfoByOrderId(Integer id);
}