package cn.qxt.service;

import cn.qxt.pojo.WorkshopStaff;
import cn.qxt.pojo.WorkshopStaffExample;

import java.util.List;

public interface WorkshopStaffService {
    int deleteByPrimaryKey(String id);

    int insert(WorkshopStaff record);

    int insertSelective(WorkshopStaff record);

    List<WorkshopStaff> selectByExample(WorkshopStaffExample example);

    WorkshopStaff selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(WorkshopStaff record);

    int updateByPrimaryKey(WorkshopStaff record);
}
