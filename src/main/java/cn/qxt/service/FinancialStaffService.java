package cn.qxt.service;

import cn.qxt.pojo.Finance;
import cn.qxt.pojo.FinancialStaff;
import cn.qxt.pojo.FinancialStaffExample;
import cn.qxt.pojo.GoodsReturnOrder;

import java.util.List;
import java.util.Map;

public interface FinancialStaffService {
    int deleteByPrimaryKey(String id);

    int insert(FinancialStaff record);

    int insertSelective(FinancialStaff record);

    List<FinancialStaff> selectByExample(FinancialStaffExample example);

    FinancialStaff selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FinancialStaff record);

    int updateByPrimaryKey(FinancialStaff record);

    List<GoodsReturnOrder> selectAllGoodsReturnOrder();

    List<Map<String, Object>>  selectGoodsReturnOrderInfoByOrderId(Integer orderId);

    List<Map<String, Object>> selectReadyProcessGoodsReturnOrderInfo();

    int agreeRefund(Integer goodsReturnOrderId);

    void disagreeRefund(Integer goodsReturnOrderId);

    Map<String,Object> selectGoodsReturnOrderInfoByPrimaryKey(Integer id);

    Finance selectFinanceByPrimaryKey(Integer id);

    List<Finance> selectAllFinance();

    Map<String,Object> selectFinanceInfoByPrimaryKey_Client(Integer financeId);

    Map<String,Object> selectFinanceInfoByPrimaryKey_Material(Integer financeId);

    List<Map> selectAllStaffInfo();

    Map selectStaffInfoById(String staffId);

    int ChangeSalary(String staffId, int parseInt);

    int ChangePosition(String staffId, String newPosition);
}
