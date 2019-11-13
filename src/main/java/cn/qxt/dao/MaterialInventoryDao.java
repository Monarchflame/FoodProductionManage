package cn.qxt.dao;

import cn.qxt.pojo.MaterialInventory;
import cn.qxt.pojo.MaterialInventoryExample;
import java.util.List;
import java.util.Map;

public interface MaterialInventoryDao {
    int deleteByPrimaryKey(Integer id);

    int insert(MaterialInventory record);

    int insertSelective(MaterialInventory record);

    List<MaterialInventory> selectByExample(MaterialInventoryExample example);

    MaterialInventory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MaterialInventory record);

    int updateByPrimaryKey(MaterialInventory record);

    Map<String, Object> selectMaterialInventoryInfoById(Integer id);

    Map<String, Object> selectMaterialInventoryInfoByMaterialId(Integer id);
}