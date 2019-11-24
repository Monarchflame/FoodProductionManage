package cn.qxt.service;

import cn.qxt.pojo.*;

import java.util.List;
import java.util.Map;

public interface SaleStaffService {
    int deleteByPrimaryKey(String id);

    int insert(SaleStaff record);

    int insertSelective(SaleStaff record);

    List<SaleStaff> selectByExample(SaleStaffExample example);

    SaleStaff selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SaleStaff record);

    int updateByPrimaryKey(SaleStaff record);

    /**
     * 返回所有待处理订单
     * @return List
     */
    List<Order> selectAllReadyProcessOrder();

    /**
     * 返回所有待发货订单
     * @return List
     */
    List<Order> selectAllReadyDeliverOrder();

    /**
     * 返回所有生产中订单
     * @return List
     */
    //List<Order> selectAllInProductionOrder();

    List<Order> selectAllinProductionOrder();

    /**
     * 生产生产要求
     * @param order 订单
     * @return 1就是成功 0就是失败
     */
    int deliverOrder(Order order);

    /**
     * 返回所有订单、退货单列表
     * @return Map
     */
    Map<String, Object> selectOrders();

    /**
     * 返回所有的订单详细信息
     * @return List
     */
    List<Map<String,Object>> selectOrderInfo();

    /**
     * 根据订单id查询订单详细信息
     * @param id Integer
     * @return Map
     */
    Map<String,Object> selectOrderInfoByOrderId(Integer id);

    /**
     * 返回所有的未处理退货单
     * @return List
     */
    List<GoodsReturnOrder> selectAllGoodsReturnOrder();

    /**
     * 返回退货单详细信息
     * @param orderId Integer
     * @return Map
     */
    List<Map<String, Object>> selectGoodsReturnOrderInfoByOrderId(Integer orderId);

    /**
     * 返回待确认的退货单详细信息
     * @return List
     */
    List<Map<String,Object>> selectReadyConfirmGoodsReturnOrderInfo();

    Map<String,Object> selectGoodsReturnOrderInfoByPrimaryKey(Integer id);

    /**
     * 同意取消订单
     * @param id String
     * @return int
     */
    int agreeCancelOrder(Integer id);

    /**
     * 拒绝取消订单
     * @param id String
     * @return int
     */
    int disagreeCancelOrder(Integer id);

    List<Map> selectAllStaffInfo();

    Map selectStaffInfoById(String staffId);

    int ChangeSalary(String staffId, int parseInt);

    int ChangePosition(String staffId, String newPosition);
}
