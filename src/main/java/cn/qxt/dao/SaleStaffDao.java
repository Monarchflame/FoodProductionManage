package cn.qxt.dao;

import cn.qxt.pojo.SaleStaff;
import cn.qxt.pojo.SaleStaffExample;
import java.util.List;

public interface SaleStaffDao {
    int deleteByPrimaryKey(String id);

    int insert(SaleStaff record);

    int insertSelective(SaleStaff record);

    List<SaleStaff> selectByExample(SaleStaffExample example);

    SaleStaff selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SaleStaff record);

    int updateByPrimaryKey(SaleStaff record);
}