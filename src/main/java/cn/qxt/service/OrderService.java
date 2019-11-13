package cn.qxt.service;

import cn.qxt.pojo.Order;
import cn.qxt.pojo.OrderExample;

import java.util.List;

public interface OrderService {
    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    List<Order> selectByExample(OrderExample example);

    Order selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
}
