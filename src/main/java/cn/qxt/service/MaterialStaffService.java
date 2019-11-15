package cn.qxt.service;

import cn.qxt.pojo.*;

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

    List<Map<String, Object>> selectMaterialInventoryInfoByMaterialId(Integer id);

    List<Map> selectAllMaterialInventoryInfo();

    void destroy(int inventoryId);

    /**
     * 原材料入库，同时添加出入库记录
     * @param materialInventory 原材料库存信息
     */
    void addInventory(MaterialInventory materialInventory);

    /**
     * 查找入库记录
     * @return 入库记录List
     */
    List<MaterialRecord> selectInRecord();

    List<MaterialRecord> selectOutRecord();

    List<MaterialRecord> selectDestroyRecord();

    Map<String, Object> selectRecordInfoById(Integer id);

}
