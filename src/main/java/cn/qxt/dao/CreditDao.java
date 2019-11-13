package cn.qxt.dao;

import cn.qxt.pojo.Credit;
import cn.qxt.pojo.CreditExample;
import java.util.List;

public interface CreditDao {
    int deleteByPrimaryKey(Integer grade);

    int insert(Credit record);

    int insertSelective(Credit record);

    List<Credit> selectByExample(CreditExample example);

    Credit selectByPrimaryKey(Integer grade);

    int updateByPrimaryKeySelective(Credit record);

    int updateByPrimaryKey(Credit record);
}