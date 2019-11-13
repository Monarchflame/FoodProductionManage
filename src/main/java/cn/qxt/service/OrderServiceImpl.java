package cn.qxt.service;

import cn.qxt.dao.OrderDao;
import cn.qxt.pojo.Order;
import cn.qxt.pojo.OrderExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;
    public int deleteByPrimaryKey(Integer id) {
        return orderDao.deleteByPrimaryKey(id);
    }

    public int insert(Order record) {
        return orderDao.insert(record);
    }

    public int insertSelective(Order record) {
        return orderDao.insertSelective(record);
    }

    public List<Order> selectByExample(OrderExample example) {
        return orderDao.selectByExample(example);
    }

    public Order selectByPrimaryKey(Integer id) {
        return orderDao.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(Order record) {
        return orderDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(Order record) {
        return orderDao.updateByPrimaryKey(record);
    }
}
