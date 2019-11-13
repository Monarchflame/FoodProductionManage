package cn.qxt.service;

import cn.qxt.dao.ProductDao;
import cn.qxt.pojo.Product;
import cn.qxt.pojo.ProductExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;
    public int deleteByPrimaryKey(Integer id) {
        return productDao.deleteByPrimaryKey(id);
    }

    public int insert(Product record) {
        return productDao.insert(record);
    }

    public int insertSelective(Product record) {
        return productDao.insertSelective(record);
    }

    public List<Product> selectByExample(ProductExample example) {
        return productDao.selectByExample(example);
    }

    public Product selectByPrimaryKey(Integer id) {
        return productDao.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(Product record) {
        return productDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(Product record) {
        return productDao.updateByPrimaryKey(record);
    }
}
