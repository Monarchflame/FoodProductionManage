package cn.qxt.dao;

import cn.qxt.pojo.Goods;
import cn.qxt.pojo.GoodsExample;
import java.util.List;
import java.util.Map;

public interface GoodsDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Goods record);

    int insertSelective(Goods record);

    List<Goods> selectByExample(GoodsExample example);

    Goods selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);

    int selectAllRepertoryByProductId(Integer product_id);

    List<Map> selectAllGoodsInfo();

    List<Map> selectGoodsInfoByProductId(Integer productID);

    Map selectGoodsInfoById(Integer goods_id);
}