package cn.qxt.service;

import cn.qxt.pojo.Plan;
import cn.qxt.pojo.PlanStaff;
import cn.qxt.pojo.PlanStaffExample;
import cn.qxt.pojo.ProductRequirement;

import java.util.List;
import java.util.Map;

public interface PlanStaffService {
    int deleteByPrimaryKey(String id);

    int insert(PlanStaff record);

    int insertSelective(PlanStaff record);

    List<PlanStaff> selectByExample(PlanStaffExample example);

    PlanStaff selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PlanStaff record);

    int updateByPrimaryKey(PlanStaff record);

    /**
     * 返回所有的生产计划信息
     * @return ArrayList
     */
    List<Plan> selectAllProductPlan();

    List<ProductRequirement> selectReadyProcessRequirement();

    List<Map> selectReadyProcessRequirementInfo();

    /**
     * 查找库存
     * @param product_id 产品ID
     * @return 产品对应的库存
     */
    int selectAllRepertoryByProductId(Integer product_id);

    /**
     * 生成一个新计划，即创建计划对应的一系列操作
     * @param plan Plan
     * @param requirementIdList 对应的需求ID数组
     * @return 0为失败，1为成功
     */
    void drawUpPlan(Plan plan, String[] requirementIdList);

    /**
     * 查找所有待执行的计划
     * @return 计划列表
     */
    List<Plan> selectAllReadyProcessPlan();
    /**
     * 查找所有执行中的计划
     * @return 计划列表
     */
    List<Plan> selectAllinProductionPlan();
    /**
     * 查找所有已完成的计划
     * @return 计划列表
     */
    List<Plan> selectAllCompletedPlan();
    /**
     * 返回生产计划的详细信息
     * @param id 计划ID
     * @return 生产计划的详细信息
     */
    Map<String, Object> selectPlanInfoByPlanId(Integer id);

    /**
     * 取消生产计划
     * @param planId 生产计划ID
     * @return 1：成功，0：失败
     */
    int cancelPlan(int planId);
}
