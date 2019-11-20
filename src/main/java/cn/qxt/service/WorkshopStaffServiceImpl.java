package cn.qxt.service;

import cn.qxt.dao.*;
import cn.qxt.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class WorkshopStaffServiceImpl implements WorkshopStaffService{
    @Autowired
    WorkshopStaffDao workshopStaffDao;
    @Autowired
    PlanDao planDao;
    @Autowired
    ProductDao productDao;
    @Autowired
    ProductIngredientDao productIngredientDao;
    @Autowired
    MaterialRequirementDao materialRequirementDao;

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

    public Map selectPlanInfoByPlanId(Integer planId)
    {
        return planDao.selectPlanInfoByPlanId(planId);
    }
    public List<Map> selectAllPlanInfo() {
        //选择所有的plan
        List<Plan> planList = planDao.selectByExample(new PlanExample());
        List<Map> allPlanInfo = new ArrayList<Map>();
        //依次查询详细信息
        for (Plan plan: planList)
        {
            allPlanInfo.add(planDao.selectPlanInfoByPlanId(plan.getId()));
        }
        return allPlanInfo;
    }

    public List<Map> selectAllStaffInfo()
    {
        //选择所有的plan
        List<WorkshopStaff> workshopStaffList = workshopStaffDao.selectByExample(new WorkshopStaffExample());
        List<Map> allStaffInfo = new ArrayList<Map>();
        //依次查询详细信息
        for (WorkshopStaff workshopStaff: workshopStaffList)
        {
            allStaffInfo.add(workshopStaffDao.selectStaffInfoById(workshopStaff.getId()));
        }
        return allStaffInfo;
    }

    @Transactional(propagation= Propagation.REQUIRED,rollbackForClassName="Exception")
    public void executePlan(Integer planId, Date finish_time)
    {
        Plan plan = planDao.selectByPrimaryKey(planId);
        plan.setFinish_time(finish_time);
        plan.setStatus("执行中");
        planDao.updateByPrimaryKeySelective(plan);

        //查找配方
        Integer product_id = plan.getProduct_id();
        ProductIngredientExample productIngredientExample = new ProductIngredientExample();
        productIngredientExample.or().andProduct_idEqualTo(product_id);
        List<ProductIngredient> productIngredientList = productIngredientDao.selectByExample(productIngredientExample);
        for (ProductIngredient productIngredient:productIngredientList)
        {
            //生成对应的原材料需求
            Integer material_id = productIngredient.getMaterial_id();
            Integer material_quantity = productIngredient.getMaterial_quantity();
            MaterialRequirement materialRequirement = new MaterialRequirement();
            materialRequirement.setMaterial_id(material_id);
            materialRequirement.setQuantity(material_quantity);
            materialRequirementDao.insertSelective(materialRequirement);
        }
    }

    public int completePlan(int planId)
    {
        Plan plan = planDao.selectByPrimaryKey(planId);
        plan.setStatus("已完成");
        plan.setFinish_time(new Date());
        return planDao.updateByPrimaryKeySelective(plan);
    }
}
