package cn.qxt.controller;

import cn.qxt.pojo.GoodsRecord;
import cn.qxt.service.ProductStaffService;
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
@RequestMapping("/admin/leader/product/leader")
public class ProductLeaderController {
    @Autowired
    private ProductStaffService productStaffService;
    /**
     * 返回员工中心界面
     * @return String
     */
    @RequestMapping("")
    public String workshopStaffView()
    {
        return "admin/leader/product/leader";
    }

    /**
     * 返回管理员工界面
     * @return String
     */
    @GetMapping(value = "/staff-list")
    public String staffListView()
    {
        return "admin/leader/product/staff-list";
    }
    /*
    管理员功能
     */
    @ResponseBody
    @PostMapping(value = "/allStaffInfo")
    public Map<String, Object> AllStaffInfo()
    {
        Map<String,Object> map = new HashMap<String, Object>();
        List<Map> staffInfoList = productStaffService.selectAllStaffInfo();
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
            Map staffInfo = productStaffService.selectStaffInfoById(staffId);
            session.setAttribute("staffInfo",staffInfo);
            return "admin/leader/product/staffInfo";
        }
        return "admin/leader/product/staffInfo";
    }

    @ResponseBody
    @PostMapping(value = "/changeSalary")
    public Map<String, Object> ChangeSalary(String staffId,String newSalary)
    {
        Map<String,Object> map = new HashMap<String, Object>();
        int ret = productStaffService.ChangeSalary(staffId,Integer.parseInt(newSalary));
        map.put("ret",ret);
        return map;
    }

    @ResponseBody
    @PostMapping(value = "/changePosition")
    public Map<String, Object> ChangePosition(String staffId,String newPosition)
    {
        Map<String,Object> map = new HashMap<String, Object>();
        int ret = productStaffService.ChangePosition(staffId,newPosition);
        map.put("ret",ret);
        return map;
    }
    
    @RequestMapping("/product-list")
    public String productList()
    {
        return "admin/leader/product/product-list";
    }

    @GetMapping(value = "/product-record")
    public String productRecordView()
    {
        return "admin/leader/product/product-record";
    }

    /**
     * 返回货物库存详细信息
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/goodsInfoList")
    public Map<String, Object> goodsInfoList(HttpServletRequest request)
    {
        String productID = request.getParameter("productID").toString();
        Map<String,Object> map = new HashMap<String, Object>();
        List<Map> goodsInfoList = new ArrayList<Map>();
        if (productID.equals(""))
        {
            goodsInfoList = productStaffService.selectAllGoodsInfo();
        }
        else
        {
            goodsInfoList = productStaffService.selectGoodsInfoByProductId(Integer.valueOf(productID));
        }
        map.put("goodsInfoList",goodsInfoList);
        return map;
    }

    /**
     * 查找货物的详细信息
     * @param goods_id 货物id
     * @return 界面路径
     */
    @GetMapping(value = "/goodsInfo")
    public String goodsInfo(String goods_id, HttpSession session)
    {
        if (goods_id!=null)
        {
            Map goodsInfo = productStaffService.selectGoodsInfoById(Integer.valueOf(goods_id));
            session.setAttribute("goodsInfo",goodsInfo);
            return "admin/leader/product/goodsInfo";
        }
        return "admin/leader/product/goodsInfo";
    }

    /**
     * 返回所有的记录
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/goodsRecordInfo")
    public Map<String, Object> goodsRecordInfo()
    {
        List<GoodsRecord> inRecord = productStaffService.selectInRecord();
        List<Map<String, Object>> inRecordInfoList = new ArrayList<Map<String, Object>>();
        for (GoodsRecord goodsRecord: inRecord) {
            inRecordInfoList.add(productStaffService.selectRecordInfoById(goodsRecord.getId()));
        }

        List<GoodsRecord> outRecord = productStaffService.selectOutRecord();
        List<Map<String, Object>> outRecordInfoList = new ArrayList<Map<String, Object>>();
        for (GoodsRecord goodsRecord: outRecord) {
            outRecordInfoList.add(productStaffService.selectRecordInfoById(goodsRecord.getId()));
        }

        List<GoodsRecord> destroyRecord = productStaffService.selectDestroyRecord();
        List<Map<String, Object>> destroyRecordInfoList = new ArrayList<Map<String, Object>>();
        for (GoodsRecord goodsRecord: destroyRecord) {
            destroyRecordInfoList.add(productStaffService.selectRecordInfoById(goodsRecord.getId()));
        }

        Map<String,Object> map = new HashMap<String, Object>();
        map.put("inRecordInfoList",inRecordInfoList);
        map.put("outRecordInfoList",outRecordInfoList);
        map.put("destroyRecordInfoList",destroyRecordInfoList);
        return map;
    }
}
