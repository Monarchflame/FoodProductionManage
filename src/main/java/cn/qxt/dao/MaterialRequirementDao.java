package cn.qxt.dao;

import cn.qxt.pojo.MaterialRequirement;
import cn.qxt.pojo.MaterialRequirementExample;
import java.util.List;
import java.util.Map;

public interface MaterialRequirementDao {
    int deleteByPrimaryKey(Integer id);

    int insert(MaterialRequirement record);

    int insertSelective(MaterialRequirement record);

    List<MaterialRequirement> selectByExample(MaterialRequirementExample example);

    MaterialRequirement selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MaterialRequirement record);

    int updateByPrimaryKey(MaterialRequirement record);

    List<Map> selectReadyProcessRequirementInfo();

    List<Map> selectReadyProcessRequirementInfoByMaterialID(Integer material_id);
}