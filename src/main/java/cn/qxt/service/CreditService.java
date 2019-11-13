package cn.qxt.service;

import cn.qxt.pojo.CreditExample;

import java.util.List;

public interface CreditService {
    int deleteByPrimaryKey(Integer grade);

    int insert(cn.qxt.pojo.Credit record);

    int insertSelective(cn.qxt.pojo.Credit record);

    List<cn.qxt.pojo.Credit> selectByExample(CreditExample example);

    cn.qxt.pojo.Credit selectByPrimaryKey(Integer grade);

    int updateByPrimaryKeySelective(cn.qxt.pojo.Credit record);

    int updateByPrimaryKey(cn.qxt.pojo.Credit record);
}
