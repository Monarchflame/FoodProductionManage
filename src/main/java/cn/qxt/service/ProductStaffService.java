package cn.qxt.service;

import cn.qxt.pojo.*;

import java.util.List;
import java.util.Map;

public interface ProductStaffService {
    int deleteByPrimaryKey(String id);

    int insert(ProductStaff record);

    int insertSelective(ProductStaff record);

    List<ProductStaff> selectByExample(ProductStaffExample example);

    ProductStaff selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ProductStaff record);

    int updateByPrimaryKey(ProductStaff record);

    List<Map> selectAllGoodsInfo();

    List<Map> selectGoodsInfoByProductId(Integer productID);

    List<Product> selectAllProduct();

    void destroy(int goodsId);

    void addGoods(Goods goods, Integer plan_id);

    Map<String, Object> selectOrderInfoByOrderId(Integer id);

    List<Order> selectAllReadyDeliverOrder();

    List<Map<String, Object>> selectAllinReturnOrder();

    Map<String, Object> selectGoodsReturnOrderInfoByPrimaryKey(Integer valueOf);

    List<GoodsRecord> selectInRecord();

    List<GoodsRecord> selectOutRecord();

    List<GoodsRecord> selectDestroyRecord();

    Map<String, Object> selectRecordInfoById(Integer id);

    int addProduct(Product product);

    Map selectGoodsInfoById(Integer valueOf);

    void ship(int orderId);

    int selectAllRepertoryByProductId(Integer product_id);

    List<Map> selectAllStaffInfo();

    Map selectStaffInfoById(String staffId);

    int ChangeSalary(String staffId, int parseInt);

    int ChangePosition(String staffId, String newPosition);

    List<Map> IngredientInfoByProductId(Integer productId);

    List<Material> selectAllMaterial();

    int agreeReturnOrder(Integer id);

    int disagreeReturnOrder(Integer id);
}
