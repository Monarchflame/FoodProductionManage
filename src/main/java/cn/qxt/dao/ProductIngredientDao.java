package cn.qxt.dao;

import cn.qxt.pojo.ProductIngredient;
import cn.qxt.pojo.ProductIngredientExample;
import cn.qxt.pojo.ProductIngredientKey;
import java.util.List;
import java.util.Map;

public interface ProductIngredientDao {
    int deleteByPrimaryKey(ProductIngredientKey key);

    int insert(ProductIngredient record);

    int insertSelective(ProductIngredient record);

    List<ProductIngredient> selectByExample(ProductIngredientExample example);

    ProductIngredient selectByPrimaryKey(ProductIngredientKey key);

    int updateByPrimaryKeySelective(ProductIngredient record);

    int updateByPrimaryKey(ProductIngredient record);

    List<Map> ingredientInfoByProductId(Integer productId);
}