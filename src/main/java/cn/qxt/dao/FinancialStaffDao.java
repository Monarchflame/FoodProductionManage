package cn.qxt.dao;

import cn.qxt.pojo.FinancialStaff;
import cn.qxt.pojo.FinancialStaffExample;
import java.util.List;
import java.util.Map;

public interface FinancialStaffDao {
    int deleteByPrimaryKey(String id);

    int insert(FinancialStaff record);

    int insertSelective(FinancialStaff record);

    List<FinancialStaff> selectByExample(FinancialStaffExample example);

    FinancialStaff selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FinancialStaff record);

    int updateByPrimaryKey(FinancialStaff record);

    Map selectStaffInfoById(String id);
}