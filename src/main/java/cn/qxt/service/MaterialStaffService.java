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

    /**
     * 根据材料ID选择库存信息
     * @param id 材料ID
     * @return
     */
    List<Map> selectMaterialInventoryInfoByMaterialId(Integer id);

    /**
     * 选择所有库存信息
     * @return
     */
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

    int andMaterial(Material material);

    int selectAllRepertoryByMaterialId(Integer material_id);

    List<Map> selectReadyProcessRequirementInfo();

    List<Map> selectReadyProcessRequirementInfoByMaterialID(Integer material_id);

    /**
     * 出库函数
     * @param material_id
     * @param requirement
     * @param requirementIdList
     */
    void inventoryOut(Integer material_id, Integer requirement, Integer[] requirementIdList);

    int newMaterialPurchaseOrder(MaterialPurchaseOrder materialPurchaseOrder);
}
