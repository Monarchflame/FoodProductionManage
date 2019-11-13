package cn.qxt.dao;

import cn.qxt.pojo.GoodsReturnOrder;
import cn.qxt.pojo.GoodsReturnOrderExample;
import java.util.List;
import java.util.Map;

public interface GoodsReturnOrderDao {
    int deleteByPrimaryKey(Integer id);

    int insert(GoodsReturnOrder record);

    int insertSelective(GoodsReturnOrder record);

    List<GoodsReturnOrder> selectByExample(GoodsReturnOrderExample example);

    GoodsReturnOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GoodsReturnOrder record);

    int updateByPrimaryKey(GoodsReturnOrder record);

    List<Map<String,Object>> selectGoodsReturnOrderInfoByOrderId(Integer orderId);

    List<Map<String,Object>> selectGoodsReturnOrderInfo();

    Map<String,Object> selectGoodsReturnOrderInfoByPrimaryKey(Integer id);

}