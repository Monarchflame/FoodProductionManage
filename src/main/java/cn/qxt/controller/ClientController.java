package cn.qxt.controller;

import cn.qxt.pojo.*;
import cn.qxt.service.ClientService;
import cn.qxt.service.OrderService;
import cn.qxt.service.ProductService;

import cn.qxt.service.RegisterService;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;
@Controller
@RequestMapping(value = "/user")
public class ClientController {
    @Autowired
    private ClientService clientService;
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private RegisterService registerService;

    /**
     * 返回用户中心
     * @param request
     * @return
     */
    @RequestMapping("")
    public String userView(HttpServletRequest request)
    {
        List<Credit> credits = clientService.selectAllCredit();
        request.setAttribute("credits",credits);
        return "client/user";
    }
    @ResponseBody
    @PostMapping("/test")
    public Map test()
    {
        return new HashMap<String, Object>();
    }
    /**
     * 返回客户的消息列表
     * @param request HttpServletRequest
     * @return Map
     */
    @ResponseBody
    @PostMapping("/findMessage")
    public Map findMessage(HttpServletRequest request)
    {
        String clientId = request.getParameter("clientId").toString();
        List messageList = clientService.selectMessageByClientId(clientId);
        Map<String,Object>map = new HashMap<String, Object>();
        //返回的是对象数组不是字符串数组
        map.put("messageList",messageList);
        return map;
    }
    /*
    以下有关登录注册
     */
    /**
     * 返回登录界面
     * @return String
     */
    @GetMapping("/login")
    public String loginView()
    {
        return "client/login";
    }

    /**
     * 自动填写账号密码
     * @param request HttpServletRequest
     * @return Map
     */
    @ResponseBody
    @PostMapping("/getCookie")
    public Map getCookie(HttpServletRequest request)
    {
        Map<String,Object>map = new HashMap<String, Object>();
        Cookie[] cookies = request.getCookies();
        if (null == cookies) {
            return null;
        }
        else {
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("client_email")){
                    map.put("email", cookie.getValue());
                }
                if (cookie.getName().equals("client_password")) {
                    map.put("password", cookie.getValue());
                }
            }
            if (map.size() == 0)
                return null;
            return map;
        }
    }

    /**
     * 返回及设置登录信息
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @param session HttpSession
     * @return Map
     */
    @ResponseBody
    @PostMapping("/login")
    public Map login(HttpServletRequest request, HttpServletResponse response ,HttpSession session)
    {
        String email = request.getParameter("email").toString();
        String password = request.getParameter("password").toString();
        String remember_me = "";
        if (request.getParameter("remember_me") != null)
            remember_me = request.getParameter("remember_me").toString();

        if (remember_me.equalsIgnoreCase("week"))
        {
            //创建Cookie对象，Cookie中有两个参数:Cookie名，要保存的数据
            //只设置最大存活期，暂时不设置可访问域名和路径
            Cookie cookie1 = new Cookie("client_email",email);
            //存活期一周
            cookie1.setMaxAge(60 * 60 * 24 * 7);
            Cookie cookie2 = new Cookie("client_password",password);
            cookie2.setMaxAge(60 * 60 * 24 * 7);

            response.addCookie(cookie1);
            response.addCookie(cookie2);
        }
        else
        {
            Cookie[] cookies = request.getCookies();//这样便可以获取一个cookie数组
            if (null == cookies) {
                System.out.println("没有cookie");
            } else {
                for(Cookie cookie : cookies){
                    if(cookie.getName().equals("client_email") || cookie.getName().equals("client_password")){
                        cookie.setValue(null);
                        cookie.setMaxAge(0);//立即销毁cookie
                    }
                }
            }
        }

        Map<String,Object>map = new HashMap<String, Object>();
        Client client = clientService.selectByPrimaryKey(email);
        if (client == null)
        {
            session.setAttribute("LOGIN_USER", null);
            map.put("msg","查无此人");
            return map;
        }
        if (client.getPassword().equalsIgnoreCase(password))
        {
            session.setAttribute("LOGIN_USER", client);
            map.put("msg","登录成功");
            map.put("ret",1);
            map.put("user",client);
            return map;
        }
        else
        {
            session.setAttribute("LOGIN_USER", null);
            map.put("msg","密码错误");
            return map;
        }
    }

    /**
     * 向指定邮箱发送验证码
     * @param request HttpServletRequest
     * @param session HttpSession
     * @return Map
     */
    @ResponseBody
    @PostMapping("/verificationCode")
    public Map getVerificationCode(HttpServletRequest request, HttpSession session)
    {
        String email = request.getParameter("email");
        System.out.println(email);
        String code = registerService.generateVerificationCode();
        boolean b = registerService.sendEmail(email, code);
        Map<String,Object>map = new HashMap<String, Object>();
        if (b)
        {
            Map<String,Object> verificationCode = new HashMap<String, Object>();
            verificationCode.put(email,code);
            session.setAttribute("verificationCode", verificationCode);
            map.put("msg","验证码已发送，注意接收");
        }
        else
            map.put("msg","验证码发送失败");
        return map;
    }

    /**
     * 返回注册界面
     * @return
     */
    @GetMapping("/register")
    public String registerView()
    {
        return "client/register";
    }

    /**
     * 返回及设置注册信息
     * @param request
     * @param session
     * @return
     */
    @ResponseBody
    @PostMapping("/register")
    public Map register(HttpServletRequest request, HttpSession session)
    {
        String account = request.getParameter("account").toString();
        String password = request.getParameter("password").toString();
        String name = request.getParameter("name").toString();
        String repassword = request.getParameter("repassword").toString();
        String phone = request.getParameter("phone").toString();
        String type = request.getParameter("type").toString();
        String address = request.getParameter("address").toString();
        String emailcode = request.getParameter("emailcode").toString();

        //得到系统给它发送的验证码
        String verificationCode = String.valueOf(((Map) session.getAttribute("verificationCode") ).get(account));

        Map<String,Object>map = new HashMap<String, Object>();
        if (account.equalsIgnoreCase("")||password.equalsIgnoreCase("")||name.equalsIgnoreCase("") || phone.equalsIgnoreCase("")
                ||repassword.equalsIgnoreCase("")||address.equalsIgnoreCase("")||verificationCode.equalsIgnoreCase(""))
        {
            map.put("msg","请输入完整信息");
            return map;
        }
        else if (!password.equalsIgnoreCase(repassword))
        {
            map.put("msg","密码与重复密码不一致");
            return map;
        }
        else if (!verificationCode.equalsIgnoreCase(emailcode))
        {
            map.put("msg","验证码错误");
            return map;
        }
        else
        {
            Client client = new Client();
            client.setId(account);
            client.setName(name);
            client.setPhone(phone);
            client.setType(type);
            client.setCreditrating(1);
            client.setAccountbalance(0.0);
            client.setPassword(password);
            client.setAddress(address);
            int result = clientService.insertSelective(client);
            if (result==1)
            {
                map.put("msg","注册成功");
                map.put("ret",result);
            }
            else
                map.put("msg","注册失败");
            return map;
        }
    }

    /**
     * 查看服务条款
     * @return
     */
    @RequestMapping("/tos")
    public String tosView()
    {
        return "client/tos";
    }
    /*
      以下是对客户信息的处理
     */
    @RequestMapping("/account")
    public String accountView()
    {
        return "client/account";
    }

    /**
     * 修改密码
     * @param request
     * @param response
     * @param session
     * @return
     */
    @ResponseBody
    @PostMapping("/alterpassword")
    public Map alterPassword(HttpServletRequest request, HttpServletResponse response, HttpSession session)
    {
        String id = request.getParameter("id").toString();
        String oldpassword = request.getParameter("oldpassword").toString();
        String password = request.getParameter("password").toString();
        String repassword = request.getParameter("repassword").toString();
        Map<String,Object>map = new HashMap<String, Object>();
        if (!password.equalsIgnoreCase(repassword))
        {
            map.put("msg","新密码与重复密码不符");
            return map;
        }
        Client client = clientService.selectByPrimaryKey(id);
        if (!client.getPassword().equalsIgnoreCase(oldpassword))
        {
            map.put("msg","原密码输入错误");
            return map;
        }
        else
        {
            client.setPassword(password);//设置新密码
            int result = clientService.updateByPrimaryKey(client);
            if (result==1)
            {
                map.put("msg","修改成功");
                session.setAttribute("LOGIN_USER",client);
                map.put("user",client);
            }
            else
                map.put("msg","修改失败");
            return map;
        }
    }

    /**
     * 修改地址
     * @param request
     * @param response
     * @param session
     * @return
     */
    @ResponseBody
    @PostMapping("/alteraddress")
    public Map alterAddress(HttpServletRequest request, HttpServletResponse response, HttpSession session)
    {
        String id = request.getParameter("id").toString();
        String address = request.getParameter("address").toString();
        Map<String,Object>map = new HashMap<String, Object>();
        if (address.equalsIgnoreCase(""))
        {
            map.put("msg","地址不能为空");
            return map;
        }
        Client client = clientService.selectByPrimaryKey(id);
        client.setAddress(address);//设置新密码
        int result = clientService.updateByPrimaryKey(client);
        if (result==1)
        {
            map.put("msg","修改成功");
            session.setAttribute("LOGIN_USER",client);
            map.put("user",client);
        }
        else
            map.put("msg","修改失败");
        return map;
    }

    /**
     * 修改手机号
     * @param request
     * @param response
     * @param session
     * @return
     */
    @ResponseBody
    @PostMapping("/alterphone")
    public Map alterPhone(HttpServletRequest request, HttpServletResponse response, HttpSession session)
    {
        String id = request.getParameter("id").toString();
        String phone = request.getParameter("phone").toString();
        Map<String,Object>map = new HashMap<String, Object>();
        if (phone.equalsIgnoreCase(""))
        {
            map.put("msg","电话号不能为空");
            return map;
        }
        if (phone.length()!=11)
        {
            map.put("msg","电话号格式错误");
            return map;
        }
        Client client = clientService.selectByPrimaryKey(id);
        client.setPhone(phone);//设置新密码
        int result = clientService.updateByPrimaryKey(client);
        if (result==1)
        {
            map.put("msg","修改成功");
            session.setAttribute("LOGIN_USER",client);
            map.put("user",client);
        }
        else
            map.put("msg","修改失败");
        return map;
    }

    /*
    以下是关于商店的处理
     */
    @GetMapping(value = "/shop")
    public String shopView()
    {
        System.out.println("捕捉！！！");
        return "client/shop";
    }

    /**
     * 根据信用等级不同，改变售价
     * @param request
     * @param response
     * @param session
     * @return
     */
    @ResponseBody
    @PostMapping("/changeprice")
    public Map changePrice(HttpServletRequest request, HttpServletResponse response, HttpSession session)
    {
        List<Product> products = productService.selectByExample(new ProductExample());
        Client client = (Client) session.getAttribute("LOGIN_USER");
        Credit credit = clientService.selectCreditByClientId(client.getId());
        Map<String,Object>map = new HashMap<String, Object>();
        map.put("discount",credit.getDiscount());
        map.put("products",products);
        return map;
    }

    /**
     * 客户点击购买，返回相应信息
     * @param request
     * @param response
     * @param session
     * @return
     */
    @ResponseBody
    @PostMapping("/chooseproduct")
    public Map chooseProduct(HttpServletRequest request, HttpServletResponse response, HttpSession session)
    {
        String productId = request.getParameter("productId").toString();
        Product product = productService.selectByPrimaryKey(Integer.valueOf(productId));
        Client client = (Client) session.getAttribute("LOGIN_USER");
        //根据销售策略得到最低付款比例
        Credit credit = clientService.selectCreditByClientId(client.getId());

        if (product!=null)
        {
            Map<String,Object>map = new HashMap<String, Object>();
            map.put("product_name",product.getName());
            map.put("unit_price",product.getUnit_price());
            map.put("discount",credit.getDiscount());
            map.put("ratio",credit.getRatio());
            map.put("ret",1);
            //作为全局变量
            session.setAttribute("product",product);
            return map;
        }
        return null;
    }

    /**
     * 客户下单处理函数
     * @param request
     * @param response
     * @param session
     * @return
     */
    @ResponseBody
    @PostMapping("/buy")
    public Map buy(HttpServletRequest request, HttpServletResponse response, HttpSession session)
    {
        String productId = request.getParameter("productId").toString();
        Product product = productService.selectByPrimaryKey(Integer.valueOf(productId));
        String pay_deposit = request.getParameter("pay_deposit").toString();
        String pay_full = request.getParameter("pay_full").toString();
        String money = request.getParameter("money").toString();
        String quantity = request.getParameter("quantity").toString();
        Client client = (Client)session.getAttribute("LOGIN_USER");
        //生成一个order，状态为已付定金或已付全款
        Order order = new Order();
        order.setClient_id(client.getId());
        order.setQuantity(Integer.valueOf(quantity));
        order.setProduct_id(Integer.valueOf(productId));
        //这是应付总金额，如果定金列不为null的话，它减去定金就是尾款
        order.setTotal_payment(Double.valueOf(money));
        //新建金额流水项
        Finance finance = new Finance();

        if (pay_deposit.equalsIgnoreCase("1") && pay_full.equalsIgnoreCase("0"))//付定金
        {
            order.setStatus("已付定金");
            //得到最低定金
            String min_advance_payment = request.getParameter("min_advance_payment").toString();
            //设置定金
            order.setDeposit(Double.valueOf(min_advance_payment));
            finance.setType("定金");
        }
        else if (pay_deposit.equalsIgnoreCase("0") && pay_full.equalsIgnoreCase("1"))
        {
            order.setStatus("已付全款");
        }
        Map<String,Object>map = new HashMap<String, Object>();
        //扣款
        if (order.getDeposit()!=null)//付的定金
            client.setAccountbalance(client.getAccountbalance()-order.getDeposit());
        else
            client.setAccountbalance(client.getAccountbalance()-order.getTotal_payment());

        //插入订单，更新客户信息
        int result = 0;
        try {
            deductingMoney(order,client);
            result = 1;
        }
        catch (Exception e)
        {
            if (order.getDeposit()!=null)//付的定金
                client.setAccountbalance(client.getAccountbalance()+order.getDeposit());
            else
                client.setAccountbalance(client.getAccountbalance()+order.getTotal_payment());
        }

        if (result == 1)
        {
            map.put("msg","下单成功");
        }
        else
        {
            map.put("msg","购买失败");
        }
        return map;
    }

    /**
     * MySQL事务：扣钱，生成金额流水
     * @param order Order
     * @param client Client
     */
    @Transactional(propagation= Propagation.REQUIRED,rollbackForClassName="Exception")
    public void deductingMoney(Order order,Client client)
    {
        //插入订单
        orderService.insertSelective(order);
        //更新客户信息
        clientService.updateByPrimaryKeySelective(client);
    }

    /*
    以下是关于订单
     */
    @GetMapping("/myorder")
    public String myOrderView()
    {
        return "client/myorder";
    }

    /**
     * 界面显示我的订单信息
     * @return noCompletedOrderInfoList、completedOrderInfoList、cancelingOrderInfoList
     */
    @ResponseBody
    @PostMapping(value = "/orderList")
    public Map orderList(HttpServletRequest request)
    {
        String clientId = request.getParameter("clientId");
        List<Order> orderList = clientService.selectAllOrder(clientId);

        List<Map<String, Object>> noCompletedOrderInfoList = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> completedOrderInfoList = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> cancelingOrderInfoList = new ArrayList<Map<String, Object>>();

        for (Order order:orderList) {
            //有退货单
            //Map中有一个goodsReturnOrderList
            Map goodsReturnOrderMap = findGoodsReturnOrder(order.getId() + "");
            List<GoodsReturnOrder> goodsReturnOrderList = (List<GoodsReturnOrder>)goodsReturnOrderMap.get("goodsReturnOrderList");
            if (goodsReturnOrderList.size()>0 && !order.getStatus().equals("已取消"))
            {
                //被拒绝
                if (goodsReturnOrderList.size() == 1 && !goodsReturnOrderList.get(0).getStatus().equals("已拒绝"))
                {
                    cancelingOrderInfoList.add(clientService.selectOrderInfoByOrderId(order.getId()));
                }
                else if (goodsReturnOrderList.size() == 2 && !goodsReturnOrderList.get(1).getStatus().equals("已拒绝"))
                {
                    cancelingOrderInfoList.add(clientService.selectOrderInfoByOrderId(order.getId()));
                }
            }
            else if (order.getStatus().equals("已完成") || order.getStatus().equals("已取消"))
                completedOrderInfoList.add(clientService.selectOrderInfoByOrderId(order.getId()));
            else
                noCompletedOrderInfoList.add(clientService.selectOrderInfoByOrderId(order.getId()));
        }

        Map<String,Object>map = new HashMap<String, Object>();
        map.put("noCompletedOrderInfoList",noCompletedOrderInfoList);
        map.put("completedOrderInfoList",completedOrderInfoList);
        map.put("cancelingOrderInfoList",cancelingOrderInfoList);
        return map;
    }

    /**
     * 返回订单信息
     * @param id String
     * @param session HttpSession
     * @return String
     */
    @RequestMapping("/orderinfo")
    public String orderInfoView(String id ,HttpSession session)
    {
        if (id!=null)
        {
            Order order = orderService.selectByPrimaryKey(Integer.valueOf(id));
            MyOrder myOrder = new MyOrder();
            myOrder.setOrderId(order.getId());
            myOrder.setProductName(productService.selectByPrimaryKey(order.getProduct_id()).getName());
            myOrder.setQuantity(order.getQuantity());
            myOrder.setStatus(order.getStatus());
            //只交了定金，没交尾款
            if (order.getDeposit() != 0 && !myOrder.getStatus().equalsIgnoreCase("已完成"))
                myOrder.setMoney(order.getDeposit());
            else
                myOrder.setMoney(order.getTotal_payment());

            String type = "";
            Map goodsReturnOrderMap = findGoodsReturnOrder(order.getId() + "");
            List<GoodsReturnOrder> goodsReturnOrderList = (List<GoodsReturnOrder>)goodsReturnOrderMap.get("goodsReturnOrderList");
            if (goodsReturnOrderList.size()>0 && !order.getStatus().equals("已取消"))
            {
                //被拒绝
                if (goodsReturnOrderList.size() == 1 && !goodsReturnOrderList.get(0).getStatus().equals("已拒绝"))
                {
                    type = "取消中";
                }
                else if (goodsReturnOrderList.size() == 2 && !goodsReturnOrderList.get(1).getStatus().equals("已拒绝"))
                {
                    type = "取消中";
                }
            }

            session.setAttribute("myOrder",myOrder);
            session.setAttribute("type",type);
            return "client/orderinfo";
        }
        return "client/orderinfo";
    }

    /**
     * 申请取消订单
     * @param request HttpServletRequest
     * @return Map
     */
    @ResponseBody
    @PostMapping("/cancelorder")
    public Map cancelOrder(HttpServletRequest request)
    {
        String orderId = request.getParameter("orderId").toString();
        return clientService.cancelOrder(Integer.valueOf(orderId));
    }

    /**
     * 申请退货（已收到货）
     * @param request HttpServletRequest
     * @return Map
     */
    @ResponseBody
    @PostMapping("/returngoods")
    public Map returnGoods(HttpServletRequest request)
    {
        String orderId = request.getParameter("orderId");
        Map map = new HashMap<String, Object>();
        try {
            map = clientService.returnGoods(Integer.valueOf(orderId));
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return map;
    }

    /**
     * 查找对应的退货单
     * @param orderId  它还要在该文件中被调用，所以参数设为String
     * @return Map
     */
    @ResponseBody
    @PostMapping("/findGoodsReturnOrder")
    public Map findGoodsReturnOrder(String orderId)
    {
        List<GoodsReturnOrder> goodsReturnOrderList = clientService.selectGoodsReturnOrderByOrderId(Integer.valueOf(orderId));
        Map<String,Object>map = new HashMap<String, Object>();
        map.put("goodsReturnOrderList",goodsReturnOrderList);
        return map;
    }

    /**
     * 确认收货
     * @param request
     * @return
     */
    @ResponseBody
    @PostMapping("/receive")
    public Map receive(HttpServletRequest request)
    {
        String orderId = request.getParameter("orderId");
        int ret = 0;
        try {
            clientService.receive(Integer.valueOf(orderId));
            ret = 1;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("ret",ret);

        return map;
    }

    /**
     * 支付尾款
     * @param request
     * @return
     */
    @ResponseBody
    @PostMapping("/payTheBalance")
    public Map payTheBalance(HttpServletRequest request)
    {
        String orderId = request.getParameter("orderId");
        int ret = 0;
        try {
            clientService.payTheBalance(Integer.valueOf(orderId));
            ret = 1;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("ret",ret);

        return map;
    }

}