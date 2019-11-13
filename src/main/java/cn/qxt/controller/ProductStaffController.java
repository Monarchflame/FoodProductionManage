package cn.qxt.controller;

import cn.qxt.service.ProductStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/staff/product/staff")
public class ProductStaffController {
    @Autowired
    private ProductStaffService productStaffService;

    /**
     * 返回员工中心界面
     * @return 路径
     */
    @RequestMapping("")
    public String productStaffView()
    {
        return "admin/staff/product/staff";
    }
}
