package cn.qxt.dao;

import cn.qxt.pojo.Client;
import cn.qxt.pojo.ClientExample;
import java.util.List;

public interface ClientDao
{
    int deleteByPrimaryKey(String id);

    int insert(Client record);

    int insertSelective(Client record);

    List<Client> selectByExample(ClientExample example);

    Client selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Client record);

    int updateByPrimaryKey(Client record);
}