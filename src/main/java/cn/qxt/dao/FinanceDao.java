package cn.qxt.dao;

import cn.qxt.pojo.Finance;
import cn.qxt.pojo.FinanceExample;
import java.util.List;
import java.util.Map;

public interface FinanceDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Finance record);

    int insertSelective(Finance record);

    List<Finance> selectByExample(FinanceExample example);

    Finance selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Finance record);

    int updateByPrimaryKey(Finance record);

    Map<String,Object> selectFinanceInfoByPrimaryKey_Client(Integer financeId);

    Map<String,Object> selectFinanceInfoByPrimaryKey_Material(Integer financeId);
}