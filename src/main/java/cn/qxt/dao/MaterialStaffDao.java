package cn.qxt.dao;

import cn.qxt.pojo.MaterialStaff;
import cn.qxt.pojo.MaterialStaffExample;
import java.util.List;
import java.util.Map;

public interface MaterialStaffDao {
    int deleteByPrimaryKey(String id);

    int insert(MaterialStaff record);

    int insertSelective(MaterialStaff record);

    List<MaterialStaff> selectByExample(MaterialStaffExample example);

    MaterialStaff selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MaterialStaff record);

    int updateByPrimaryKey(MaterialStaff record);

    Map selectStaffInfoById(String id);
}