package cn.qxt.service;

import cn.qxt.dao.ProductStaffDao;
import cn.qxt.pojo.ProductStaff;
import cn.qxt.pojo.ProductStaffExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductStaffServiceImpl implements ProductStaffService{
    @Autowired
    private ProductStaffDao productStaffDao;

    public int deleteByPrimaryKey(String id) {
        return productStaffDao.deleteByPrimaryKey(id);
    }

    public int insert(ProductStaff record) {
        return productStaffDao.insert(record);
    }

    public int insertSelective(ProductStaff record) {
        return productStaffDao.insertSelective(record);
    }

    public List<ProductStaff> selectByExample(ProductStaffExample example) {
        return productStaffDao.selectByExample(example);
    }

    public ProductStaff selectByPrimaryKey(String id) {
        return productStaffDao.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(ProductStaff record) {
        return productStaffDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(ProductStaff record) {
        return productStaffDao.updateByPrimaryKey(record);
    }
}
