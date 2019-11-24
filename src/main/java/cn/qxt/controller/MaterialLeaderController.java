package cn.qxt.controller;

import cn.qxt.pojo.MaterialRecord;
import cn.qxt.service.MaterialStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/admin/leader/material/leader")
public class MaterialLeaderController {
    @Autowired
    private MaterialStaffService materialStaffService;

    /**
     * 返回员工中心界面
     * @return String
     */
    @RequestMapping("")
    public String workshopStaffView()
    {
        return "admin/leader/material/leader";
    }

    /**
     * 返回管理员工界面
     * @return String
     */
    @GetMapping(value = "/staff-list")
    public String staffListView()
    {
        return "admin/leader/material/staff-list";
    }
    
    /*
    管理员功能
     */
    @ResponseBody
    @PostMapping(value = "/allStaffInfo")
    public Map<String, Object> AllStaffInfo()
    {
        Map<String,Object> map = new HashMap<String, Object>();
        List<Map> staffInfoList = materialStaffService.selectAllStaffInfo();
        //把管理员的信息剔除
        Iterator<Map> iterator = staffInfoList.iterator();
        while(iterator.hasNext()){
            Map staffInfo = iterator.next();
            if (staffInfo.get("position").toString().equals("管理员")) {
                iterator.remove();
            }
        }
        map.put("staffInfoList",staffInfoList);
        return map;
    }

    @GetMapping(value = "/staffInfo")
    public String staffInfo(String staffId, HttpSession session)
    {
        if (staffId!=null)
        {
            Map staffInfo = materialStaffService.selectStaffInfoById(staffId);
            session.setAttribute("staffInfo",staffInfo);
            return "admin/leader/material/staffInfo";
        }
        return "admin/leader/material/staffInfo";
    }

    @ResponseBody
    @PostMapping(value = "/changeSalary")
    public Map<String, Object> ChangeSalary(String staffId,String newSalary)
    {
        Map<String,Object> map = new HashMap<String, Object>();
        int ret = materialStaffService.ChangeSalary(staffId,Integer.parseInt(newSalary));
        map.put("ret",ret);
        return map;
    }

    @ResponseBody
    @PostMapping(value = "/changePosition")
    public Map<String, Object> ChangePosition(String staffId,String newPosition)
    {
        Map<String,Object> map = new HashMap<String, Object>();
        int ret = materialStaffService.ChangePosition(staffId,newPosition);
        map.put("ret",ret);
        return map;
    }

    @RequestMapping("/material-list")
    public String materialList()
    {
        return "admin/leader/material/material-list";
    }

    @GetMapping(value = "/material-record")
    public String materialRecordView()
    {
        return "admin/leader/material/material-record";
    }
    
    /**
     * 返回原材料库存详细信息
     * @return 一个List，包含需求信息： material.id as material_id, name, material_inventory.quantity as quantity, expiration_time, material_record.create_time, material_inventory.id as inventory_id
     */
    @ResponseBody
    @PostMapping(value = "/materialInfoList")
    public Map<String, Object> materialInfoList(HttpServletRequest request)
    {
        String materialID = request.getParameter("materialID").toString();
        Map<String,Object> map = new HashMap<String, Object>();
        List <Map> materialInfoList = new ArrayList<Map>();
        if (materialID.equals(""))
        {
            materialInfoList = materialStaffService.selectAllMaterialInventoryInfo();
        }
        else
        {
            materialInfoList = materialStaffService.selectMaterialInventoryInfoByMaterialId(Integer.valueOf(materialID));
        }
        map.put("materialInfoList",materialInfoList);
        return map;
    }
    /**
     * 查找原材料的详细信息
     * @param inventory_id 原材料库存id
     * @return materialInfo:原材料的详细信息
     */
    @GetMapping(value = "/materialInfo")
    public String materialInfo(String inventory_id, HttpSession session)
    {
        if (inventory_id!=null)
        {
            Map materialInfo = materialStaffService.selectMaterialInventoryInfoById(Integer.valueOf(inventory_id));
            session.setAttribute("materialInfo",materialInfo);
            return "admin/leader/material/materialInfo";
        }
        return "admin/leader/material/materialInfo";
    }

    /**
     * 返回所有的记录
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/materialRecordInfo")
    public Map<String, Object> materialRecordInfo()
    {
        List<MaterialRecord> inRecord = materialStaffService.selectInRecord();
        List<Map<String, Object>> inRecordInfoList = new ArrayList<Map<String, Object>>();
        for (MaterialRecord materialRecord: inRecord) {
            inRecordInfoList.add(materialStaffService.selectRecordInfoById(materialRecord.getId()));
        }

        List<MaterialRecord> outRecord = materialStaffService.selectOutRecord();
        List<Map<String, Object>> outRecordInfoList = new ArrayList<Map<String, Object>>();
        for (MaterialRecord materialRecord: outRecord) {
            outRecordInfoList.add(materialStaffService.selectRecordInfoById(materialRecord.getId()));
        }

        List<MaterialRecord> destroyRecord = materialStaffService.selectDestroyRecord();
        List<Map<String, Object>> destroyRecordInfoList = new ArrayList<Map<String, Object>>();
        for (MaterialRecord materialRecord: destroyRecord) {
            destroyRecordInfoList.add(materialStaffService.selectRecordInfoById(materialRecord.getId()));
        }

        Map<String,Object> map = new HashMap<String, Object>();
        map.put("inRecordInfoList",inRecordInfoList);
        map.put("outRecordInfoList",outRecordInfoList);
        map.put("destroyRecordInfoList",destroyRecordInfoList);
        return map;
    }
}
