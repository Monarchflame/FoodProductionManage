package cn.qxt.service;

import cn.qxt.dao.CEODao;
import cn.qxt.pojo.CEO;
import cn.qxt.pojo.CEOExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CEOServiceImpl implements CEOService{
    @Autowired
    private CEODao ceoDao;
    @Override
    public int deleteByPrimaryKey(String id) {
        return ceoDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(CEO record) {
        return ceoDao.insert(record);
    }

    @Override
    public int insertSelective(CEO record) {
        return ceoDao.insertSelective(record);
    }

    @Override
    public List<CEO> selectByExample(CEOExample example) {
        return ceoDao.selectByExample(example);
    }

    @Override
    public CEO selectByPrimaryKey(String id) {
        return ceoDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(CEO record) {
        return ceoDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CEO record) {
        return ceoDao.updateByPrimaryKey(record);
    }
}
