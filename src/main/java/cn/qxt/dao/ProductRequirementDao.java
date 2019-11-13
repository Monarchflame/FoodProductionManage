package cn.qxt.dao;

import cn.qxt.pojo.ProductRequirement;
import cn.qxt.pojo.ProductRequirementExample;
import java.util.List;
import java.util.Map;

public interface ProductRequirementDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductRequirement record);

    int insertSelective(ProductRequirement record);

    List<ProductRequirement> selectByExample(ProductRequirementExample example);

    ProductRequirement selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductRequirement record);

    int updateByPrimaryKey(ProductRequirement record);

    List<Map> selectReadyProcessRequirementInfo();
}