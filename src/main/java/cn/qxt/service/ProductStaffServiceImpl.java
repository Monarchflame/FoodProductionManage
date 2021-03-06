package cn.qxt.service;

import cn.qxt.dao.*;
import cn.qxt.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ProductStaffServiceImpl implements ProductStaffService{
    @Autowired
    private ProductStaffDao productStaffDao;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private GoodsDao goodsDao;
    @Autowired
    private GoodsRecordDao goodsRecordDao;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private GoodsReturnOrderDao goodsReturnOrderDao;
    @Autowired
    private ProductIngredientDao productIngredientDao;
    @Autowired
    private MaterialDao materialDao;

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

    public List<Map> selectAllGoodsInfo()
    {
        return goodsDao.selectAllGoodsInfo();
    }

    public List<Map> selectGoodsInfoByProductId(Integer productID)
    {
        return goodsDao.selectGoodsInfoByProductId(productID);
    }

    public List<Product> selectAllProduct() {
        return productDao.selectByExample(new ProductExample());
    }

    /**
     * 入库事务
     * @param goods 货物
     * @param plan_id
     */
    @Transactional(propagation= Propagation.REQUIRED,rollbackForClassName="Exception")
    public void addGoods(Goods goods, Integer plan_id) {
        goodsDao.insertSelective(goods);
        GoodsRecord goodsRecord = new GoodsRecord();
        //Mapper中设置insertSelective方法，可以得到goods的主键
        goodsRecord.setGoods_id(goods.getId());
        goodsRecord.setProduct_id(goods.getProduct_id());
        goodsRecord.setQuantity(goods.getQuantity());
        goodsRecord.setType("入库");
        goodsRecord.setExternal_key(plan_id);
        goodsRecordDao.insertSelective(goodsRecord);
    }

    public Map<String, Object> selectOrderInfoByOrderId(Integer id) {
        return orderDao.selectOrderInfoByOrderId(id);
    }

    public List<Order> selectAllReadyDeliverOrder() {
        OrderExample orderExample = new OrderExample();
        orderExample.or().andStatusEqualTo("待发货");
        return orderDao.selectByExample(orderExample);
    }

    public List<Map<String, Object>> selectAllinReturnOrder() {
        GoodsReturnOrderExample goodsReturnOrderExample = new GoodsReturnOrderExample();
        goodsReturnOrderExample.or().andStatusEqualTo("待收货");
        List<GoodsReturnOrder> goodsReturnOrderList = goodsReturnOrderDao.selectByExample(goodsReturnOrderExample);
        List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
        for (GoodsReturnOrder goodsReturnOrder: goodsReturnOrderList ) {
            returnList.add(goodsReturnOrderDao.selectGoodsReturnOrderInfoByPrimaryKey(goodsReturnOrder.getId()));
        }
        return returnList;
    }

    public Map<String, Object> selectGoodsReturnOrderInfoByPrimaryKey(Integer valueOf) {
        return goodsReturnOrderDao.selectGoodsReturnOrderInfoByPrimaryKey(valueOf);
    }

    @Transactional(propagation= Propagation.REQUIRED,rollbackForClassName="Exception")
    public void destroy(int goodsId)
    {
        Goods goods = goodsDao.selectByPrimaryKey(goodsId);
        GoodsRecord goodsRecord = new GoodsRecord();
        goodsRecord.setGoods_id(goods.getId());
        goodsRecord.setProduct_id(goods.getProduct_id());
        goodsRecord.setQuantity(goods.getQuantity());
        goodsRecord.setType("销毁");
        goodsRecordDao.insertSelective(goodsRecord);
        goodsDao.deleteByPrimaryKey(goodsId);
    }
    public List<GoodsRecord> selectInRecord()
    {
        GoodsRecordExample example = new GoodsRecordExample();
        example.or().andTypeEqualTo("入库");
        return goodsRecordDao.selectByExample(example);
    }
    public List<GoodsRecord> selectOutRecord()
    {
        GoodsRecordExample example = new GoodsRecordExample();
        example.or().andTypeEqualTo("出库");
        return goodsRecordDao.selectByExample(example);
    }
    public List<GoodsRecord> selectDestroyRecord()
    {
        GoodsRecordExample example = new GoodsRecordExample();
        example.or().andTypeEqualTo("销毁");
        return goodsRecordDao.selectByExample(example);
    }
    public Map<String, Object> selectRecordInfoById(Integer id)
    {
        return goodsRecordDao.selectRecordInfoById(id);
    }
    public int addProduct(Product product)
    {
        return productDao.insertSelective(product);
    }

    public Map selectGoodsInfoById(Integer goods_id) {
        return goodsDao.selectGoodsInfoById(goods_id);
    }

    /**
     * 出库
     * @param orderId
     */
    @Transactional(propagation= Propagation.REQUIRED,rollbackForClassName="Exception")
    public void ship(int orderId) {
        Order order = orderDao.selectByPrimaryKey(orderId);
        Integer requirement = order.getQuantity();
        Integer product_id = order.getProduct_id();

        //选择所有库存，按入库时间排列，快过期的在前面
        List<Map> goodsInfoList = goodsDao.selectGoodsInfoByProductId(product_id);
        //从前向后，依次减少库存，减完就把库存删掉，换下一个
        for (Map goods : goodsInfoList)
        {
            //检查不能有过期货物
            Date expiration_time = (Date) goods.get("expiration_time");
            Date now_time = new Date();
            if (expiration_time.getTime() < now_time.getTime())//过期
            {
                continue;
            }
            //查找库存
            int quantity = Integer.parseInt(goods.get("quantity").toString());
            int goods_id = Integer.parseInt(goods.get("goods_id").toString());
            if (requirement >= quantity)
            {
                //扣完
                insertOutRecord(orderId, goods_id, null);
            }
            else
            {
                insertOutRecord(orderId, goods_id, requirement);
                break;
            }
        }
        //将订单状态设为已发货
        order.setStatus("已发货");
        orderDao.updateByPrimaryKeySelective(order);
    }

    private void insertOutRecord(Integer orderId, Integer goods_id, Integer quantity)
    {
        Goods goods = goodsDao.selectByPrimaryKey(goods_id);

        //插入记录
        GoodsRecord record = new GoodsRecord();
        record.setGoods_id(goods_id);
        record.setProduct_id(goods.getProduct_id());
        record.setType("出库");
        record.setExternal_key(orderId);
        if (quantity == null)//全扣完
        {
            record.setQuantity(goods.getQuantity());
            //删除库存
            destroy(goods_id);
        }
        else
        {
            record.setQuantity(quantity);
            //扣除库存
            goods.setQuantity(goods.getQuantity() - quantity);
            goodsDao.updateByPrimaryKeySelective(goods);
        }
        goodsRecordDao.insertSelective(record);
    }

    public int selectAllRepertoryByProductId(Integer product_id)
    {
        return goodsDao.selectAllRepertoryByProductId(product_id);
    }
    /*
管理员有关功能
 */
    @Override
    public List<Map> selectAllStaffInfo() {
        List<ProductStaff> productStaffList = productStaffDao.selectByExample(new ProductStaffExample());
        List<Map> allStaffInfo = new ArrayList<Map>();
        //依次查询详细信息
        for (ProductStaff productStaff: productStaffList)
        {
            allStaffInfo.add(productStaffDao.selectStaffInfoById(productStaff.getId()));
        }
        return allStaffInfo;
    }

    @Override
    public Map selectStaffInfoById(String staffId)
    {
        return productStaffDao.selectStaffInfoById(staffId);
    }

    @Override
    public int ChangeSalary(String staffId, int newSalary)
    {
        ProductStaff productStaff = productStaffDao.selectByPrimaryKey(staffId);
        productStaff.setSalary(newSalary);
        return productStaffDao.updateByPrimaryKeySelective(productStaff);
    }
    @Override
    public int ChangePosition(String staffId, String newPosition)
    {
        ProductStaff productStaff = productStaffDao.selectByPrimaryKey(staffId);
        productStaff.setPosition(newPosition);
        return productStaffDao.updateByPrimaryKeySelective(productStaff);
}

    @Override
    public List<Map> IngredientInfoByProductId(Integer productId) {
        return productIngredientDao.ingredientInfoByProductId(productId);
    }

    @Override
    public List<Material> selectAllMaterial() {
        return materialDao.selectByExample(new MaterialExample());
    }

    @Override
    public int agreeReturnOrder(Integer id) {
        GoodsReturnOrder goodsReturnOrder = goodsReturnOrderDao.selectByPrimaryKey(id);
        goodsReturnOrder.setStatus("已收货");
        return goodsReturnOrderDao.updateByPrimaryKey(goodsReturnOrder);
    }

    public int disagreeReturnOrder(Integer id) {
        GoodsReturnOrder goodsReturnOrder = goodsReturnOrderDao.selectByPrimaryKey(id);
        goodsReturnOrder.setStatus("已拒绝");
        return goodsReturnOrderDao.updateByPrimaryKey(goodsReturnOrder);
    }
}
