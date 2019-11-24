package cn.qxt.service;

import cn.qxt.dao.GoodsDao;
import cn.qxt.dao.PlanDao;
import cn.qxt.dao.PlanStaffDao;
import cn.qxt.dao.ProductRequirementDao;
import cn.qxt.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PlanStaffServiceImpl implements PlanStaffService {
    @Autowired
    PlanDao planDao;
    @Autowired
    PlanStaffDao planStaffDao;
    @Autowired
    ProductRequirementDao productRequirementDao;
    @Autowired
    GoodsDao goodsDao;

    public int deleteByPrimaryKey(String id) {
        return planStaffDao.deleteByPrimaryKey(id);
    }

    public int insert(PlanStaff record) {
        return planStaffDao.insert(record);
    }

    public int insertSelective(PlanStaff record) {
        return planStaffDao.insertSelective(record);
    }

    public List<PlanStaff> selectByExample(PlanStaffExample example) {
        return planStaffDao.selectByExample(example);
    }

    public PlanStaff selectByPrimaryKey(String id) {
        return planStaffDao.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(PlanStaff record) {
        return planStaffDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(PlanStaff record) {
        return planStaffDao.updateByPrimaryKey(record);
    }

    public List<Plan> selectAllProductPlan() {

        return planDao.selectByExample(new PlanExample());
    }

    public List<ProductRequirement> selectReadyProcessRequirement() {
        ProductRequirementExample productRequirementExample = new ProductRequirementExample();
        productRequirementExample.or().andStatusEqualTo("未确认");
        return productRequirementDao.selectByExample(productRequirementExample);
    }

    public List<Map> selectReadyProcessRequirementInfo() {
        return productRequirementDao.selectReadyProcessRequirementInfo();
    }
    public int selectAllRepertoryByProductId(Integer product_id)
    {
        return goodsDao.selectAllRepertoryByProductId(product_id);
    }

    /**
     * 事务，下计划，同时修改需求
     * @param plan Plan
     * @param requirementIdList 对应的需求ID数组
     */
    @Transactional(propagation= Propagation.REQUIRED,rollbackForClassName="Exception")
    public void drawUpPlan(Plan plan, String[] requirementIdList) {
        for (String id: requirementIdList) {
            ProductRequirement productRequirement = productRequirementDao.selectByPrimaryKey(Integer.valueOf(id));
            productRequirement.setStatus("已确认");
            productRequirementDao.updateByPrimaryKeySelective(productRequirement);
        }
        planDao.insertSelective(plan);
    }

    public List<Plan> selectAllReadyProcessPlan() {
        PlanExample planExample = new PlanExample();
        planExample.or().andStatusEqualTo("未执行");
        return planDao.selectByExample(planExample);
    }

    public List<Plan> selectAllinProductionPlan() {
        PlanExample planExample = new PlanExample();
        planExample.or().andStatusEqualTo("执行中");
        return planDao.selectByExample(planExample);
    }

    public List<Plan> selectAllCompletedPlan() {
        PlanExample planExample = new PlanExample();
        planExample.or().andStatusEqualTo("已完成");
        return planDao.selectByExample(planExample);
    }

    public Map<String, Object> selectPlanInfoByPlanId(Integer id) {
        return planDao.selectPlanInfoByPlanId(id);
    }

    public int cancelPlan(int planId)
    {
        Plan plan = planDao.selectByPrimaryKey(planId);
        plan.setStatus("已取消");
        return planDao.updateByPrimaryKeySelective(plan);
    }
    /*
管理员有关功能
 */
    @Override
    public List<Map> selectAllStaffInfo() {
        List<PlanStaff> planStaffList = planStaffDao.selectByExample(new PlanStaffExample());
        List<Map> allStaffInfo = new ArrayList<Map>();
        //依次查询详细信息
        for (PlanStaff planStaff: planStaffList)
        {
            allStaffInfo.add(planStaffDao.selectStaffInfoById(planStaff.getId()));
        }
        return allStaffInfo;
    }

    @Override
    public Map selectStaffInfoById(String staffId)
    {
        return planStaffDao.selectStaffInfoById(staffId);
    }

    @Override
    public int ChangeSalary(String staffId, int newSalary)
    {
        PlanStaff planStaff = planStaffDao.selectByPrimaryKey(staffId);
        planStaff.setSalary(newSalary);
        return planStaffDao.updateByPrimaryKeySelective(planStaff);
    }
    @Override
    public int ChangePosition(String staffId, String newPosition)
    {
        PlanStaff planStaff = planStaffDao.selectByPrimaryKey(staffId);
        planStaff.setPosition(newPosition);
        return planStaffDao.updateByPrimaryKeySelective(planStaff);
    }
}
