package cn.qxt.dao;

import cn.qxt.pojo.ShipOrder;
import cn.qxt.pojo.ShipOrderExample;
import java.util.List;

public interface ShipOrderDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ShipOrder record);

    int insertSelective(ShipOrder record);

    List<ShipOrder> selectByExample(ShipOrderExample example);

    ShipOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ShipOrder record);

    int updateByPrimaryKey(ShipOrder record);
}