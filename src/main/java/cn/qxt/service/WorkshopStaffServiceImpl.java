package cn.qxt.service;

import cn.qxt.dao.PlanDao;
import cn.qxt.dao.WorkshopStaffDao;
import cn.qxt.pojo.Plan;
import cn.qxt.pojo.PlanExample;
import cn.qxt.pojo.WorkshopStaff;
import cn.qxt.pojo.WorkshopStaffExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkshopStaffServiceImpl implements WorkshopStaffService{
    @Autowired
    WorkshopStaffDao workshopStaffDao;
    @Autowired
    PlanDao planDao;

    public int deleteByPrimaryKey(String id) {
        return workshopStaffDao.deleteByPrimaryKey(id);
    }

    public int insert(WorkshopStaff record) {
        return workshopStaffDao.insert(record);
    }

    public int insertSelective(WorkshopStaff record) {
        return workshopStaffDao.insertSelective(record);
    }

    public List<WorkshopStaff> selectByExample(WorkshopStaffExample example) {
        return workshopStaffDao.selectByExample(example);
    }

    public WorkshopStaff selectByPrimaryKey(String id) {
        return workshopStaffDao.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(WorkshopStaff record) {
        return workshopStaffDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(WorkshopStaff record) {
        return workshopStaffDao.updateByPrimaryKey(record);
    }

    public List<Plan> selectAllProductPlan() {
        return planDao.selectByExample(new PlanExample());
    }
}
