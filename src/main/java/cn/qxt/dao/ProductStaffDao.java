package cn.qxt.dao;

import cn.qxt.pojo.ProductStaff;
import cn.qxt.pojo.ProductStaffExample;
import java.util.List;
import java.util.Map;

public interface ProductStaffDao {
    int deleteByPrimaryKey(String id);

    int insert(ProductStaff record);

    int insertSelective(ProductStaff record);

    List<ProductStaff> selectByExample(ProductStaffExample example);

    ProductStaff selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ProductStaff record);

    int updateByPrimaryKey(ProductStaff record);

    Map selectStaffInfoById(String id);
}