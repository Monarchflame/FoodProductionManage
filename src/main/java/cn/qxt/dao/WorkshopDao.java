package cn.qxt.dao;

import cn.qxt.pojo.Workshop;
import cn.qxt.pojo.WorkshopExample;
import java.util.List;

public interface WorkshopDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Workshop record);

    int insertSelective(Workshop record);

    List<Workshop> selectByExample(WorkshopExample example);

    Workshop selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Workshop record);

    int updateByPrimaryKey(Workshop record);
}