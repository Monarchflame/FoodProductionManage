package cn.qxt.dao;

import cn.qxt.pojo.Material;
import cn.qxt.pojo.MaterialExample;
import java.util.List;

public interface MaterialMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Material record);

    int insertSelective(Material record);

    List<Material> selectByExample(MaterialExample example);

    Material selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Material record);

    int updateByPrimaryKey(Material record);
}