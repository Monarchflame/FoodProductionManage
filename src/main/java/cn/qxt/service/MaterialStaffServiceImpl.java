package cn.qxt.service;

import cn.qxt.dao.MaterialDao;
import cn.qxt.dao.MaterialInventoryDao;
import cn.qxt.dao.MaterialStaffDao;
import cn.qxt.pojo.Material;
import cn.qxt.pojo.MaterialExample;
import cn.qxt.pojo.MaterialStaff;
import cn.qxt.pojo.MaterialStaffExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Map<String, Object> selectMaterialInventoryInfoByMaterialId(Integer id) {
        return materialInventoryDao.selectMaterialInventoryInfoByMaterialId(id);
    }

    public int destroy(int inventoryId)
    {
        return materialInventoryDao.deleteByPrimaryKey(inventoryId);
    }
}
