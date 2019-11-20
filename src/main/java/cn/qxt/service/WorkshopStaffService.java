package cn.qxt.service;

import cn.qxt.pojo.Plan;
import cn.qxt.pojo.WorkshopStaff;
import cn.qxt.pojo.WorkshopStaffExample;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface WorkshopStaffService {
    int deleteByPrimaryKey(String id);

    int insert(WorkshopStaff record);

    int insertSelective(WorkshopStaff record);

    List<WorkshopStaff> selectByExample(WorkshopStaffExample example);

    WorkshopStaff selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(WorkshopStaff record);

    int updateByPrimaryKey(WorkshopStaff record);

    Map selectPlanInfoByPlanId(Integer planId);

    List<Map>  selectAllPlanInfo();

    List<Map> selectAllStaffInfo();

    void executePlan(Integer planId, Date finish_time);

    int completePlan(int planId);

    Map selectStaffInfoById(String staffId);

    int ChangeSalary(String staffId, int newSalary);

    int ChangePosition(String staffId, String newPosition);
}
