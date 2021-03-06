package cn.qxt.dao;

import cn.qxt.pojo.GoodsRecord;
import cn.qxt.pojo.GoodsRecordExample;
import java.util.List;
import java.util.Map;

public interface GoodsRecordDao {
    int deleteByPrimaryKey(Integer id);

    int insert(GoodsRecord record);

    int insertSelective(GoodsRecord record);

    List<GoodsRecord> selectByExample(GoodsRecordExample example);

    GoodsRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GoodsRecord record);

    int updateByPrimaryKey(GoodsRecord record);

    Map<String, Object> selectRecordInfoById(Integer id);
}