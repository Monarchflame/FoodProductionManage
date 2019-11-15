package cn.qxt.service;

import cn.qxt.dao.MaterialDao;
import cn.qxt.dao.MaterialInventoryDao;
import cn.qxt.dao.MaterialRecordDao;
import cn.qxt.dao.MaterialStaffDao;
import cn.qxt.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
    public List<Map<String, Object>> selectMaterialInventoryInfoByMaterialId(Integer id) {
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
}
