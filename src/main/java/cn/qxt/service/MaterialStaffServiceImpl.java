package cn.qxt.service;

import cn.qxt.dao.*;
import cn.qxt.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class MaterialStaffServiceImpl implements MaterialStaffService{
    @Autowired
    MaterialStaffDao materialStaffDao;
    @Autowired
    MaterialDao materialDao;
    @Autowired
    MaterialInventoryDao materialInventoryDao;
    @Autowired
    MaterialRecordDao materialRecordDao;
    @Autowired
    MaterialRequirementDao materialRequirementDao;
    @Autowired
    MaterialPurchaseOrderDao materialPurchaseOrderDao;

    public int deleteByPrimaryKey(String id) {
        return materialStaffDao.deleteByPrimaryKey(id);
    }

    public int insert(MaterialStaff record) {
        return materialStaffDao.insert(record);
    }

    public int insertSelective(MaterialStaff record) {
        return materialStaffDao.insertSelective(record);
    }

    public List<MaterialStaff> selectByExample(MaterialStaffExample example) {
        return materialStaffDao.selectByExample(example);
    }

    public MaterialStaff selectByPrimaryKey(String id) {
        return materialStaffDao.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(MaterialStaff record) {
        return materialStaffDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(MaterialStaff record) {
        return materialStaffDao.updateByPrimaryKey(record);
    }

    public List<Material> selectAllMaterial() {
        return materialDao.selectByExample(new MaterialExample());
    }

    public Map<String, Object> selectMaterialInfoById(Integer id) {
        return materialDao.selectMaterialInfoById(id);
    }

    public Map<String, Object> selectMaterialInventoryInfoById(Integer id) {
        return materialInventoryDao.selectMaterialInventoryInfoById(id);
    }
    public List<Map> selectMaterialInventoryInfoByMaterialId(Integer id) {
        return materialInventoryDao.selectMaterialInventoryInfoByMaterialId(id);
    }
    public List<Map> selectAllMaterialInventoryInfo()
    {
        return materialInventoryDao.selectAllMaterialInventoryInfo();
    }

    @Transactional(propagation= Propagation.REQUIRED,rollbackForClassName="Exception")
    public void destroy(int inventoryId)
    {
        MaterialInventory materialInventory = materialInventoryDao.selectByPrimaryKey(inventoryId);
        MaterialRecord materialRecord = new MaterialRecord();
        materialRecord.setMaterial_id(materialInventory.getMaterial_id());
        materialRecord.setQuantity(materialInventory.getQuantity());
        materialRecord.setType("销毁");
        materialRecordDao.insertSelective(materialRecord);
        materialInventoryDao.deleteByPrimaryKey(inventoryId);
    }

    @Transactional(propagation= Propagation.REQUIRED,rollbackForClassName="Exception")
    public void addInventory(MaterialInventory materialInventory)
    {
        materialInventoryDao.insertSelective(materialInventory);
        MaterialRecord materialRecord = new MaterialRecord();
        materialRecord.setMaterial_id(materialInventory.getMaterial_id());
        materialRecord.setQuantity(materialInventory.getQuantity());
        materialRecord.setType("入库");
        materialRecordDao.insertSelective(materialRecord);
    }

    public List<MaterialRecord> selectInRecord()
    {
        MaterialRecordExample example = new MaterialRecordExample();
        example.or().andTypeEqualTo("入库");
        return materialRecordDao.selectByExample(example);
    }
    public List<MaterialRecord> selectOutRecord()
    {
        MaterialRecordExample example = new MaterialRecordExample();
        example.or().andTypeEqualTo("出库");
        return materialRecordDao.selectByExample(example);
    }
    public List<MaterialRecord> selectDestroyRecord()
    {
        MaterialRecordExample example = new MaterialRecordExample();
        example.or().andTypeEqualTo("销毁");
        return materialRecordDao.selectByExample(example);
    }

    public Map<String, Object> selectRecordInfoById(Integer id)
    {
        return materialRecordDao.selectRecordInfoById(id);
    }

    public int andMaterial(Material material) {
        return materialDao.insertSelective(material);
    }

    public int selectAllRepertoryByMaterialId(Integer material_id)
    {
        return materialDao.selectAllRepertoryByMaterialId(material_id);
    }
    public List<Map> selectReadyProcessRequirementInfo()
    {
        return materialRequirementDao.selectReadyProcessRequirementInfo();
    }
    public List<Map> selectReadyProcessRequirementInfoByMaterialID(Integer material_id)
    {
        return materialRequirementDao.selectReadyProcessRequirementInfoByMaterialID(material_id);
    }
    /**
     * 出库，此时的库存一定是大于需求的
     * @param material_id
     * @param requirement
     * @param requirementIdList
     */
    @Transactional(propagation= Propagation.REQUIRED,rollbackForClassName="Exception")
    public void inventoryOut(Integer material_id, Integer requirement, Integer[] requirementIdList)
    {
        //选择所有库存，按入库时间排列，快过期的在前面
        List<Map> materialInventoryList = materialInventoryDao.selectMaterialInventoryInfoByMaterialId(material_id);
        //从前向后，依次减少库存，减完就把库存删掉，换下一个
        for (Map materialInventory : materialInventoryList)
        {
            //检查不能有过期货物
            Date expiration_time = (Date) materialInventory.get("expiration_time");
            Date now_time = new Date();
            if (expiration_time.getTime() < now_time.getTime())//过期
            {
                continue;
            }
            //查找库存
            int quantity = Integer.parseInt(materialInventory.get("quantity").toString());
            int inventory_id = Integer.parseInt(materialInventory.get("inventory_id").toString());
            if (requirement > quantity)
            {
                //扣完
                insertOutRecord(inventory_id, null);//全扣完
                //删除库存
                materialInventoryDao.deleteByPrimaryKey(inventory_id);
            }
            else
            {
                insertOutRecord(inventory_id, requirement);
                break;
            }
        }
        //将需求设为已确认
        for (int requirementId:requirementIdList)
        {
            MaterialRequirement materialRequirement = materialRequirementDao.selectByPrimaryKey(requirementId);
            materialRequirement.setStatus("已确认");
            materialRequirementDao.updateByPrimaryKeySelective(materialRequirement);
        }
    }

    private void insertOutRecord(Integer inventory_id, Integer quantity)
    {
        MaterialInventory materialInventory = materialInventoryDao.selectByPrimaryKey(inventory_id);

        //插入记录
        MaterialRecord record = new MaterialRecord();
        record.setMaterial_id(materialInventory.getMaterial_id());
        record.setType("出库");
        if (quantity == null)//全扣完
        {
            record.setQuantity(materialInventory.getQuantity());
        }
        else
        {
            record.setQuantity(quantity);
            //扣除库存
            materialInventory.setQuantity(materialInventory.getQuantity() - quantity);
        }
        record.setInventory_id(inventory_id);
        materialRecordDao.insertSelective(record);
        materialInventoryDao.updateByPrimaryKeySelective(materialInventory);
    }
    public int newMaterialPurchaseOrder(MaterialPurchaseOrder materialPurchaseOrder)
    {
        return materialPurchaseOrderDao.insertSelective(materialPurchaseOrder);
    }
}
