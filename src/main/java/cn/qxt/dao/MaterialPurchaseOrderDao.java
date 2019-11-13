package cn.qxt.dao;

import cn.qxt.pojo.MaterialPurchaseOrder;
import cn.qxt.pojo.MaterialPurchaseOrderExample;
import java.util.List;

public interface MaterialPurchaseOrderDao {
    int deleteByPrimaryKey(Integer id);

    int insert(MaterialPurchaseOrder record);

    int insertSelective(MaterialPurchaseOrder record);

    List<MaterialPurchaseOrder> selectByExample(MaterialPurchaseOrderExample example);

    MaterialPurchaseOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MaterialPurchaseOrder record);

    int updateByPrimaryKey(MaterialPurchaseOrder record);
}