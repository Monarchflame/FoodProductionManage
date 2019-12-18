package cn.qxt.service;

import cn.qxt.dao.*;
import cn.qxt.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class FinancialStaffServiceImpl implements FinancialStaffService {
    @Autowired
    private FinancialStaffDao financialStaffDao;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private GoodsReturnOrderDao goodsReturnOrderDao;
    @Autowired
    private ClientDao clientDao;
    @Autowired
    private ClientMessageDao clientMessageDao;
    @Autowired
    private FinanceDao financeDao;

    public int deleteByPrimaryKey(String id) {
        return financialStaffDao.deleteByPrimaryKey(id);
    }

    public int insert(FinancialStaff record) {
        return financialStaffDao.insert(record);
    }

    public int insertSelective(FinancialStaff record) {
        return financialStaffDao.insertSelective(record);
    }

    public List<FinancialStaff> selectByExample(FinancialStaffExample example) {
        return financialStaffDao.selectByExample(example);
    }

    public FinancialStaff selectByPrimaryKey(String id) {
        return financialStaffDao.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(FinancialStaff record) {
        return financialStaffDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(FinancialStaff record) {
        return 0;
    }



    public List<GoodsReturnOrder> selectAllGoodsReturnOrder() {
        GoodsReturnOrderExample goodsReturnOrderExample = new GoodsReturnOrderExample();
        goodsReturnOrderExample.or().andStatusEqualTo("已同意");
        goodsReturnOrderExample.or().andTypeNotEqualTo("待收货");
        return goodsReturnOrderDao.selectByExample(goodsReturnOrderExample);
    }

    /**
     * 返回对应退货单列表
     * @param orderId
     * @return
     */
    public List<Map<String, Object>> selectGoodsReturnOrderInfoByOrderId(Integer orderId) {
        return goodsReturnOrderDao.selectGoodsReturnOrderInfoByOrderId(orderId);
    }

    public Map<String,Object> selectGoodsReturnOrderInfoByPrimaryKey(Integer id)
    {
        return goodsReturnOrderDao.selectGoodsReturnOrderInfoByPrimaryKey(id);
    }

    /**
     * 所有待退款的订单
     * @return
     */
    public List<Map<String, Object>> selectReadyProcessGoodsReturnOrderInfo() {
        GoodsReturnOrderExample goodsReturnOrderExample = new GoodsReturnOrderExample();
        goodsReturnOrderExample.or().andStatusEqualTo("已同意");
        List<GoodsReturnOrder> goodsReturnOrderList = goodsReturnOrderDao.selectByExample(goodsReturnOrderExample);
        List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
        for (GoodsReturnOrder goodsReturnOrder: goodsReturnOrderList ) {
            returnList.add(goodsReturnOrderDao.selectGoodsReturnOrderInfoByPrimaryKey(goodsReturnOrder.getId()));
        }
        return returnList;
    }

    /**
     * 同意退款事务
     * @param clientId String
     * @param money int
     */
    @Transactional(propagation= Propagation.REQUIRED,rollbackForClassName="Exception")
    public void returnMoney(String clientId, Double money, Integer orderID, Integer goodsReturnOrderId)
    {
        //客户余额增加
        Client client = clientDao.selectByPrimaryKey(clientId);
        client.setAccountbalance(client.getAccountbalance() + money);
        clientDao.updateByPrimaryKeySelective(client);
        //订单状态修改
        Order order = orderDao.selectByPrimaryKey(orderID);
        order.setStatus("已取消");
        orderDao.updateByPrimaryKeySelective(order);
        //添加金额流水记录
        Finance finance = new Finance();
        finance.setType("退款");
        finance.setMoney(-1 * money);
        finance.setOrder_id(orderID);
        finance.setUser_id(clientId);
        financeDao.insertSelective(finance);
        //增加客户通知信息
        ClientMessage clientMessage = new ClientMessage();
        clientMessage.setClient_id(client.getId());
        clientMessage.setMessage("您的订单编号为 "+order.getId()+" 的订单已退款，请查看");
        clientMessageDao.insertSelective(clientMessage);
        //退货单状态修改
        GoodsReturnOrder goodsReturnOrder = goodsReturnOrderDao.selectByPrimaryKey(goodsReturnOrderId);
        goodsReturnOrder.setStatus("已成功");
        goodsReturnOrderDao.updateByPrimaryKey(goodsReturnOrder);
    }

    /**
     * 同意退款方法
     * @param goodsReturnOrderId
     * @return
     */
    public int agreeRefund(Integer goodsReturnOrderId) {
        GoodsReturnOrder goodsReturnOrder = goodsReturnOrderDao.selectByPrimaryKey(goodsReturnOrderId);
        Order order = orderDao.selectByPrimaryKey(goodsReturnOrder.getOrder_id());
        Client client = clientDao.selectByPrimaryKey(order.getClient_id());
        String status = order.getStatus();
        int result = 0;
        if (status.equalsIgnoreCase("已付定金"))
        {
            //退钱
            try {
                returnMoney(client.getId(), order.getDeposit(), goodsReturnOrder.getOrder_id(), goodsReturnOrderId);
                result = 1;
            }
            catch (Exception e){
                e.printStackTrace();
            }

        }
        else if (status.equalsIgnoreCase("已付全款"))
        {
            //退钱
            try {
                returnMoney(client.getId(), order.getTotal_payment(), goodsReturnOrder.getOrder_id(), goodsReturnOrderId);
                result = 1;
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        else if (status.equalsIgnoreCase("已完成"))
        {
            try {
                returnMoney(client.getId(), order.getTotal_payment(), goodsReturnOrder.getOrder_id(), goodsReturnOrderId);
                result = 1;
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        else if (status.equalsIgnoreCase("待付尾款"))
        {
            try {
                returnMoney(client.getId(), order.getDeposit(), goodsReturnOrder.getOrder_id(), goodsReturnOrderId);
                result = 1;
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        return result;
    }

    @Transactional(propagation= Propagation.REQUIRED,rollbackForClassName="Exception")
    public void disagreeRefund(Integer goodsReturnOrderId)
    {
        GoodsReturnOrder goodsReturnOrder = goodsReturnOrderDao.selectByPrimaryKey(goodsReturnOrderId);
        Order order = orderDao.selectByPrimaryKey(goodsReturnOrder.getOrder_id());
        Client client = clientDao.selectByPrimaryKey(order.getClient_id());
        //修改退货单状态
        goodsReturnOrder.setStatus("已拒绝");
        goodsReturnOrderDao.updateByPrimaryKey(goodsReturnOrder);
        //增加客户通知信息
        ClientMessage clientMessage = new ClientMessage();
        clientMessage.setClient_id(client.getId());
        clientMessage.setMessage("您的订单编号为 "+order.getId()+" 的订单退货申请已被拒绝，有疑问请联系客服");
        clientMessageDao.insertSelective(clientMessage);

    }

    /**
     * 返回所有的金额流水记录
     * @return 金额流水列表
     */
    public List<Finance> selectAllFinance() {
        return financeDao.selectByExample(new FinanceExample());
    }

    public Finance selectFinanceByPrimaryKey(Integer id)
    {
        return financeDao.selectByPrimaryKey(id);
    }

    /**
     * 返回客户的金额流水详细信息
     * @param financeId
     * @return
     */
    public Map<String,Object> selectFinanceInfoByPrimaryKey_Client(Integer financeId)
    {
        return financeDao.selectFinanceInfoByPrimaryKey_Client(financeId);
    }

    public Map<String,Object> selectFinanceInfoByPrimaryKey_Material(Integer financeId)
    {
        return financeDao.selectFinanceInfoByPrimaryKey_Material(financeId);
    }

    /*
    管理员有关功能
     */
    @Override
    public List<Map> selectAllStaffInfo() {
        List<FinancialStaff> financialStaffList = financialStaffDao.selectByExample(new FinancialStaffExample());
        List<Map> allStaffInfo = new ArrayList<Map>();
        //依次查询详细信息
        for (FinancialStaff financialStaff: financialStaffList)
        {
            allStaffInfo.add(financialStaffDao.selectStaffInfoById(financialStaff.getId()));
        }
        return allStaffInfo;
    }
    
    @Override
    public Map selectStaffInfoById(String staffId)
    {
        return financialStaffDao.selectStaffInfoById(staffId);
    }
    
    @Override
    public int ChangeSalary(String staffId, int newSalary)
    {
        FinancialStaff financialStaff = financialStaffDao.selectByPrimaryKey(staffId);
        financialStaff.setSalary(newSalary);
        return financialStaffDao.updateByPrimaryKeySelective(financialStaff);
    }
    @Override
    public int ChangePosition(String staffId, String newPosition)
    {
        FinancialStaff financialStaff = financialStaffDao.selectByPrimaryKey(staffId);
        financialStaff.setPosition(newPosition);
        return financialStaffDao.updateByPrimaryKeySelective(financialStaff);
    }

}
