<%@ page import="cn.qxt.pojo.Client" %><%--
  Created by IntelliJ IDEA.
  User: 10703
  Date: 2019/9/24
  Time: 20:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% Client client=(Client) request.getSession().getAttribute("LOGIN_USER"); %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html lang="zh-cn">
<head>
    <meta charset="UTF-8">
    <meta content="IE=edge" http-equiv="X-UA-Compatible">
    <meta content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no, width=device-width" name="viewport">
    <meta name="theme-color" content="#4285f4">
    <title>潮汐</title>

    <link href="${pageContext.request.contextPath}/theme/css/base.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/theme/css/project.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/theme/css/user.css">

    <script src="https://cdn.jsdelivr.net/npm/jquery@3.2.1" type="text/javascript"></script>
    <script src="https://cdn.jsdelivr.net/gh/davidshimjs/qrcodejs@gh-pages/qrcode.min.js" type="text/javascript"></script>
</head>
<body class="page-orange">
<header class="header header-orange header-transparent header-waterfall ui-header">
    <ul class="nav nav-list pull-left">
        <div>
            <a data-toggle="menu" href="#ui_menu">
                <span class="icon icon-lg text-white">menu</span>
            </a>
        </div>
    </ul>
    <ul class="nav nav-list pull-right">
        <div class="dropdown margin-right">
            <a class="dropdown-toggle padding-left-no padding-right-no" data-toggle="dropdown">
                <span class="access-hide">MonarchFlame</span>
                <span class="icon icon-cd margin-right">account_circle</span>
            </a>
            <ul class="dropdown-menu dropdown-menu-right">
                <li>
                    <a class="waves-attach" href="${pageContext.request.contextPath}/user"><span class="icon icon-lg margin-right">account_box</span>用户中心</a>
                </li>
                <li>
                    <a class="padding-right-cd waves-attach" href="/"><span class="icon icon-lg margin-right">exit_to_app</span>登出</a>
                </li>
            </ul>
        </div>
    </ul>
</header>
<nav aria-hidden="true" class="menu menu-left nav-drawer nav-drawer-md" id="ui_menu" tabindex="-1">
    <div class="menu-scroll">
        <div class="menu-content">

            <a class="menu-logo" href="/"><i class="icon icon-lg">language</i>&nbsp首页</a>
            <ul class="nav">
                <li>
                    <a class="waves-attach" data-toggle="collapse" href="#ui_menu_me">我的</a>
                    <ul class="menu-collapse collapse in" id="ui_menu_me">
                        <li>
                            <a href="${pageContext.request.contextPath}/user"><i class="icon icon-lg">account_balance_wallet</i>&nbsp;用户中心</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/user/account"><i class="icon icon-lg">account_box</i>&nbsp;账户信息</a>
                        </li>
                        <li>
                            <a href=""><i class="icon icon-lg">event_note</i>&nbsp;我的订单</a>
                        </li>
                    </ul>

                    <a class="waves-attach" data-toggle="collapse" href="#ui_menu_sale">业务</a>
                    <ul class="menu-collapse collapse in" id="ui_menu_sale">
                        <li>
                            <a href="${pageContext.request.contextPath}/user/shop"><i class="icon icon-lg">shopping_basket</i>&nbsp;商城</a>
                        </li>
                    </ul>
            </ul>
        </div>
    </div>
</nav>

<main class="content">
    <div class="content-header ui-content-header">
        <div class="container">
            <h1 class="content-heading">我的订单</h1>
        </div>
    </div>
    <div class="container">
        <section class="content-inner margin-top-no">
            <div class="ui-card-wrap">
                <div class="row">
                    <div class="col-lg-12 col-sm-12 nodelist">
                        <div class="node-cardgroup" style="display: grid">
                            <div class="nodetitle">
                                <a class="waves-effect waves-button" data-toggle="collapse" href="#cardgroup0" aria-expanded="true" aria-controls="cardgroup0">
                                    <span>未完成订单</span><i class="material-icons">expand_more</i>
                                </a>
                            </div>
                            <div class="card-row collapse in" id="cardgroup0">
                            </div>
                            <div class="nodetitle">
                                <a class="waves-effect waves-button" data-toggle="collapse" href="#cardgroup1" aria-expanded="true" aria-controls="cardgroup1">
                                    <span>已完成</span><i class="material-icons">expand_more</i>
                                </a>
                            </div>
                            <div class="card-row collapse in" id="cardgroup1">
                            </div>
                            <div class="nodetitle">
                                <a class="waves-effect waves-button" data-toggle="collapse" href="#cardgroup2" aria-expanded="true" aria-controls="cardgroup1">
                                    <span>取消中订单</span><i class="material-icons">expand_more</i>
                                </a>
                            </div>
                            <div class="card-row collapse in" id="cardgroup2">
                            </div>
                        </div>
                    </div>

                    <div aria-hidden="true" class="modal modal-va-middle fade" id="result" role="dialog" tabindex="-1">
                        <div class="modal-dialog modal-xs">
                            <div class="modal-content">
                                <div class="modal-inner">
                                    <p class="h5 margin-top-sm text-black-hint" id="msg"></p>
                                </div>
                                <div class="modal-footer">
                                    <p class="text-right">
                                        <button class="btn btn-flat btn-brand-accent waves-attach" data-dismiss="modal" type="button" id="result_ok" onclick="location.reload()">知道了
                                        </button>
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div aria-hidden="true" class="modal modal-va-middle fade" id="orderinfo" role="dialog" tabindex="-1">
                        <div class="modal-dialog modal-full">
                            <div class="modal-content">
                                <iframe class="iframe-seamless" title="Modal with iFrame" id="infoifram"></iframe>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </section>
    </div>
</main>
<script src="https://cdn.jsdelivr.net/npm/jquery@2.2.1" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/theme/js/base.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/theme/js/project.min.js" type="4text/javascript"></script>
<%--<script src="https://cdn.jsdelivr.net/npm/clipboard@1.5.16/dist/clipboard.min.js" type="text/javascript"></script>--%>
</body>
</html>
<script type="text/javascript">
    var goodsReturnOrdernumber = 0 ;
    var goodsReturnOrderstatus = "";
    var goodsReturnOrderstatus2="";
    $(document).ready(function(){
        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/user/getmyorder",
            dataType: "json",
            data: {
                clientId:'<% out.print(client.getId());%>',
            },
            //查询商品及客户信息返回相应数据
            success: function(data) {
                var list = eval(data.myOrders);
                var order_notcomplete = [];
                var order_completed = [];
                var order_cancel = [];
                var notcomliete = 1;
                var completed = 1;
                var canceling = 1;
                for(var i = 0;i < list.length;i++) {
                    //myorder不是order类型
                    var myorder = list[i];
                    //找对应的退货单
                    $.ajax({
                        type: "POST",
                        url: "${pageContext.request.contextPath}/user/findGoodsReturnOrder",
                        async: false,
                        dataType: "json",
                        data: {
                            orderId:myorder.orderId,
                        },
                        success: function(data) {
                            var goodsReturnOrderList = eval(data.goodsReturnOrderList);
                            if (goodsReturnOrderList!= null)
                            {
                                goodsReturnOrdernumber = goodsReturnOrderList.length;
                                goodsReturnOrderstatus = goodsReturnOrderList[0].status;
                                //第二次申请，即收到货后申请
                                if (goodsReturnOrderList.length === 2)
                                {
                                    goodsReturnOrderstatus2 = goodsReturnOrderList[1].status;
                                }
                            }
                        },
                        error: (jqXHR) => {
                            $("#result").modal();
                            document.getElementById('msg').innerHTML = `发生了错误`;
                        }
                    });
                    var status = myorder.status;
                    //已完成
                    if (status === "已完成" || status === "已取消")  {
                        order_completed.push('<div class="node-card node-flex" cardindex="' + completed + '" onclick="orderInfo(' + myorder.orderId + ')">' +
                            '<div class="nodemain">' +
                            '<div class="nodehead node-flex">' +
                            '<div class="nodename">' + myorder.productName + '</div>' +
                            '</div>' +
                            '<div class="nodemiddle node-flex">' +
                            '<div class="onlinemember node-flex">' +
                            '<i class="material-icons node-icon">location_on</i><span>地址</span>' +
                            '</div>' +
                            '<div class="nodetype">' + myorder.address + '</div>' +
                            '</div>' +
                            '<div class="nodeinfo node-flex">' +
                            '<div class="nodetraffic node-flex">' +
                            '<i class="material-icons node-icon">attach_money</i><span>付款</span>' +
                            '</div>' +
                            '<div class="nodetype">' + myorder.money + '</div>' +
                            '<div class="nodecheck node-flex">' +
                            '<i class="material-icons node-icon">archive</i><span>数量</span>' +
                            '</div>' +
                            '<div class="nodetype">' + myorder.quantity + '</div>' +
                            '<div class="nodeband node-flex">' +
                            '<i class="material-icons node-icon">flash_on</i><span>状态</span>' +
                            '</div>' +
                            '<div class="nodetype">' + myorder.status + '</div>' +
                            '</div></div></div>'
                        );
                        completed++;
                    }
                    //取消中
                    else if ((status === "已付定金" || status === "已付全款") && goodsReturnOrdernumber === 1 && goodsReturnOrderstatus !== "已拒绝" && goodsReturnOrderstatus !== "已成功" )
                    {
                        order_cancel.push('<div class="node-card node-flex" cardindex="' + canceling + '" onclick="orderInfo(' + myorder.orderId + ')">' +
                            '<div class="nodemain">' +
                            '<div class="nodehead node-flex">' +
                            '<div class="nodename">' + myorder.productName + '</div>' +
                            '</div>' +
                            '<div class="nodemiddle node-flex">' +
                            '<div class="onlinemember node-flex">' +
                            '<i class="material-icons node-icon">location_on</i><span>地址</span>' +
                            '</div>' +
                            '<div class="nodetype">' + myorder.address + '</div>' +
                            '</div>' +
                            '<div class="nodeinfo node-flex">' +
                            '<div class="nodetraffic node-flex">' +
                            '<i class="material-icons node-icon">attach_money</i><span>付款</span>' +
                            '</div>' +
                            '<div class="nodetype">' + myorder.money + '</div>' +
                            '<div class="nodecheck node-flex">' +
                            '<i class="material-icons node-icon">archive</i><span>数量</span>' +
                            '</div>' +
                            '<div class="nodetype">' + myorder.quantity + '</div>' +
                            '<div class="nodeband node-flex">' +
                            '<i class="material-icons node-icon">flash_on</i><span>状态</span>' +
                            '</div>' +
                            '<div class="nodetype">' + myorder.status + '</div>' +
                            '</div></div></div>'
                        );
                        canceling++
                    }
                    //退货中
                    else if ((status === "已完成" || status === "待付尾款") && goodsReturnOrdernumber === 2 && goodsReturnOrderstatus2 !== "已拒绝" && goodsReturnOrderstatus2 !== "已成功")
                    {
                        order_cancel.push('<div class="node-card node-flex" cardindex="' + canceling + '" onclick="orderInfo(' + myorder.orderId + ')">' +
                            '<div class="nodemain">' +
                            '<div class="nodehead node-flex">' +
                            '<div class="nodename">' + myorder.productName + '</div>' +
                            '</div>' +
                            '<div class="nodemiddle node-flex">' +
                            '<div class="onlinemember node-flex">' +
                            '<i class="material-icons node-icon">location_on</i><span>地址</span>' +
                            '</div>' +
                            '<div class="nodetype">' + myorder.address + '</div>' +
                            '</div>' +
                            '<div class="nodeinfo node-flex">' +
                            '<div class="nodetraffic node-flex">' +
                            '<i class="material-icons node-icon">attach_money</i><span>付款</span>' +
                            '</div>' +
                            '<div class="nodetype">' + myorder.money + '</div>' +
                            '<div class="nodecheck node-flex">' +
                            '<i class="material-icons node-icon">archive</i><span>数量</span>' +
                            '</div>' +
                            '<div class="nodetype">' + myorder.quantity + '</div>' +
                            '<div class="nodeband node-flex">' +
                            '<i class="material-icons node-icon">flash_on</i><span>状态</span>' +
                            '</div>' +
                            '<div class="nodetype">' + myorder.status + '</div>' +
                            '</div></div></div>'
                        );
                        canceling++
                    }
                    else {
                        order_notcomplete.push('<div class="node-card node-flex" cardindex="' + notcomliete + '" onclick="orderInfo(' + myorder.orderId + ')">' +
                            '<div class="nodemain">' +
                            '<div class="nodehead node-flex">' +
                            '<div class="nodename">' + myorder.productName + '</div>' +
                            '</div>' +
                            '<div class="nodemiddle node-flex">' +
                            '<div class="onlinemember node-flex">' +
                            '<i class="material-icons node-icon">location_on</i><span>地址</span>' +
                            '</div>' +
                            '<div class="nodetype">' + myorder.address + '</div>' +
                            '</div>' +
                            '<div class="nodeinfo node-flex">' +
                            '<div class="nodetraffic node-flex">' +
                            '<i class="material-icons node-icon">attach_money</i><span>付款</span>' +
                            '</div>' +
                            '<div class="nodetype">' + myorder.money + '</div>' +
                            '<div class="nodecheck node-flex">' +
                            '<i class="material-icons node-icon">archive</i><span>数量</span>' +
                            '</div>' +
                            '<div class="nodetype">' + myorder.quantity + '</div>' +
                            '<div class="nodeband node-flex">' +
                            '<i class="material-icons node-icon">flash_on</i><span>状态</span>' +
                            '</div>' +
                            '<div class="nodetype">' + myorder.status + '</div>' +
                            '</div></div></div>'
                        );
                        notcomliete++
                    }
                }
                $('#cardgroup0').html(order_notcomplete.join(''));
                $('#cardgroup1').html(order_completed.join(''));
                $('#cardgroup2').html(order_cancel.join(''));
            },
            error: function(){
                $("#result").modal();
                document.getElementById('msg').innerHTML = `发生了错误`;
            }
        })
    });
    //点击订单 弹出信息
    function orderInfo(id) {
        document.getElementById('infoifram').src = '/user/orderinfo?id=' + id;
        $("#orderinfo").modal();
    }
</script>
