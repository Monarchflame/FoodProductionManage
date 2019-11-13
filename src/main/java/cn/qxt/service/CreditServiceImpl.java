package cn.qxt.service;

import cn.qxt.dao.CreditDao;
import cn.qxt.pojo.Credit;
import cn.qxt.pojo.CreditExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CreditServiceImpl implements CreditService {
    @Autowired
    CreditDao creditDao;
    public int deleteByPrimaryKey(Integer id) {
        return creditDao.deleteByPrimaryKey(id);
    }

    public int insert(Credit record) {
        return creditDao.insert(record);
    }

    public int insertSelective(Credit record) {
        return creditDao.insertSelective(record);
    }

    public List<Credit> selectByExample(CreditExample example) {
        return creditDao.selectByExample(example);
    }

    public Credit selectByPrimaryKey(Integer id) {
        return creditDao.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(Credit record) {
        return creditDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(Credit record) {
        return creditDao.updateByPrimaryKey(record);
    }
}
