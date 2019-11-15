package cn.qxt.dao;

import cn.qxt.pojo.MaterialRecord;
import cn.qxt.pojo.MaterialRecordExample;
import java.util.List;
import java.util.Map;

public interface MaterialRecordDao {
    int deleteByPrimaryKey(Integer id);

    int insert(MaterialRecord record);

    int insertSelective(MaterialRecord record);

    List<MaterialRecord> selectByExample(MaterialRecordExample example);

    MaterialRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MaterialRecord record);

    int updateByPrimaryKey(MaterialRecord record);

    Map<String, Object> selectRecordInfoById(Integer id);
}