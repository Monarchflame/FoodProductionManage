package cn.qxt.service;

import cn.qxt.pojo.Product;
import cn.qxt.pojo.ProductExample;

import java.util.List;

public interface ProductService {
    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    int insertSelective(Product record);

    List<Product> selectByExample(ProductExample example);

    Product selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);
}
