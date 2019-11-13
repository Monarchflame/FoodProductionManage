package cn.qxt.dao;

import cn.qxt.pojo.ClientMessage;
import cn.qxt.pojo.ClientMessageExample;
import cn.qxt.pojo.ClientMessageKey;
import java.util.List;

public interface ClientMessageDao {
    int deleteByPrimaryKey(ClientMessageKey key);

    int insert(ClientMessage record);

    int insertSelective(ClientMessage record);

    List<ClientMessage> selectByExample(ClientMessageExample example);

    ClientMessage selectByPrimaryKey(ClientMessageKey key);

    int updateByPrimaryKeySelective(ClientMessage record);

    int updateByPrimaryKey(ClientMessage record);
}