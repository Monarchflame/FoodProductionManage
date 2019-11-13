package cn.qxt.dao;

import cn.qxt.pojo.PlanStaff;
import cn.qxt.pojo.PlanStaffExample;
import java.util.List;

public interface PlanStaffDao {
    int deleteByPrimaryKey(String id);

    int insert(PlanStaff record);

    int insertSelective(PlanStaff record);

    List<PlanStaff> selectByExample(PlanStaffExample example);

    PlanStaff selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PlanStaff record);

    int updateByPrimaryKey(PlanStaff record);
}