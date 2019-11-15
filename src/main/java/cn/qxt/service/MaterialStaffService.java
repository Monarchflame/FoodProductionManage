package cn.qxt.service;

import cn.qxt.pojo.Material;
import cn.qxt.pojo.MaterialInventory;
import cn.qxt.pojo.MaterialStaff;
import cn.qxt.pojo.MaterialStaffExample;

import java.util.List;
import java.util.Map;

public interface MaterialStaffService {
    int deleteByPrimaryKey(String id);

    int insert(MaterialStaff record);

    int insertSelective(MaterialStaff record);

    List<MaterialStaff> selectByExample(MaterialStaffExample example);

    MaterialStaff selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MaterialStaff record);

    int updateByPrimaryKey(MaterialStaff record);

    List<Material> selectAllMaterial();

    Map<String, Object> selectMaterialInfoById(Integer id);

    Map<String, Object> selectMaterialInventoryInfoById(Integer id);

    Map<String, Object> selectMaterialInventoryInfoByMaterialId(Integer id);

    void destroy(int inventoryId);

    /**
     * 原材料入库，同时添加出入库记录
     * @param materialInventory 原材料库存信息
     */
    void addInventory(MaterialInventory materialInventory);
}
