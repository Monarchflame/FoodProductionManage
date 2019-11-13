package cn.qxt.dao;

import cn.qxt.pojo.Plan;
import cn.qxt.pojo.PlanExample;
import java.util.List;
import java.util.Map;

public interface PlanDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Plan record);

    int insertSelective(Plan record);

    List<Plan> selectByExample(PlanExample example);

    Plan selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Plan record);

    int updateByPrimaryKey(Plan record);

    Map<String,Object> selectPlanInfoByPlanId(Integer id);
}