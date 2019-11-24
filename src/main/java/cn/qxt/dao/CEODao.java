package cn.qxt.dao;

import cn.qxt.pojo.CEO;
import cn.qxt.pojo.CEOExample;
import java.util.List;

public interface CEODao {
    int deleteByPrimaryKey(String id);

    int insert(CEO record);

    int insertSelective(CEO record);

    List<CEO> selectByExample(CEOExample example);

    CEO selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CEO record);

    int updateByPrimaryKey(CEO record);
}