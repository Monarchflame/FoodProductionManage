package cn.qxt.controller;

import cn.qxt.pojo.Material;
import cn.qxt.service.MaterialStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/staff/material/staff")
public class MaterialStaffController {
    @Autowired
    private MaterialStaffService materialStaffService;

    /**
     * 返回员工中心界面
     * @return 路径
     */
    @RequestMapping("")
    public String materialStaffView()
    {
        return "admin/staff/material/staff";
    }

    @RequestMapping("/material-list")
    public String materialList()
    {
        return "admin/staff/material/material-list";
    }

    /**
     * 返回原材料详细信息
     * @return 一个List，包含需求信息： id:产品ID，name:产品名，quantity:数量，create_time:入库时间，expiration_time：过期时间
     */
    @ResponseBody
    @PostMapping(value = "/materialInfoList")
    public Map<String, Object> materialInfoList()
    {
        Map<String,Object> map = new HashMap<String, Object>();
        List<Material> materialList = materialStaffService.selectAllMaterial();

        List <Map> materialInfoList = new ArrayList<Map>();
        for (Material material : materialList)
        {
            Map<String, Object> stringObjectMap = materialStaffService.selectMaterialInventoryInfoByMaterialId(material.getId());
            if (stringObjectMap != null)
                materialInfoList.add(stringObjectMap);
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
            return "admin/staff/material/materialInfo";
        }
        return "admin/staff/material/materialInfo";
    }

    /**
     * 销毁过期原材料
     * @param request
     * @return Map,存放消息
     */
    @ResponseBody
    @PostMapping(value = "/destruction")
    public Map<String, Object> destruction(HttpServletRequest request)
    {
        String inventoryId = request.getParameter("inventoryId").toString();
        Map<String,Object> map = new HashMap<String, Object>();

        int ret = materialStaffService.destroy(Integer.valueOf(inventoryId));
        map.put("ret",ret);
        return map;
    }
}
