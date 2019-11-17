<%@ page import="cn.qxt.pojo.MaterialStaff" %><%--
Created by IntelliJ IDEA.
User: 10703
Date: 2019/11/10
Time: 15:35
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% MaterialStaff materialStaff=(MaterialStaff)request.getSession().getAttribute("LOGIN_USER"); %>
<html lang="zh-cn">
<head>
    <meta charset="UTF-8">
    <meta content="IE=edge" http-equiv="X-UA-Compatible">
    <meta content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no, width=device-width" name="viewport">
    <link rel="shortcut icon" href="../../../../images/labellogo.jpg" type="image/x-icon">
    <meta name="theme-color" content="#4285f4">
    <title>员工界面</title>

    <link href="../../../../theme/css/base.min.css" rel="stylesheet">
    <!--小图片-->
    <link href="../../../../theme/css/project.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Material+Icons" rel="stylesheet">
    <!--柱状图样式-->
    <link rel="stylesheet" href="../../../../theme/css/user.css">

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
                    <a class="waves-attach" href=""><span class="icon icon-lg margin-right">account_box</span>员工中心</a>
                </li>
                <li>
                    <a class="padding-right-cd waves-attach" href="/admin"><span class="icon icon-lg margin-right">exit_to_app</span>登出</a>
                </li>
            </ul>
        </div>
    </ul>
</header>
<nav aria-hidden="true" class="menu menu-left nav-drawer nav-drawer-md" id="ui_menu" tabindex="-1">
    <div class="menu-scroll">
        <div class="menu-content">

            <a class="menu-logo" href="/admin"><i class="icon icon-lg">language</i>&nbsp后台</a>
            <ul class="nav">
                <li>
                    <a class="waves-attach" data-toggle="collapse" href="#ui_menu_me">我的</a>
                    <ul class="menu-collapse collapse in" id="ui_menu_me">
                        <li>
                            <a href=""><i class="icon icon-lg">account_balance_wallet</i>&nbsp;员工中心</a>
                        </li>
                        <li>
                            <a href="/admin/account"><i class="icon icon-lg">account_box</i>&nbsp;账户信息</a>
                        </li>
                    </ul>

                    <a class="waves-attach" data-toggle="collapse" href="#ui_menu_material">原材料库</a>
                    <ul class="menu-collapse collapse in" id="ui_menu_material">
                        <li>
                            <a href="/admin/staff/material/staff/material-list"><i class="icon icon-lg">account_box</i>&nbsp;查看原材料库存</a>
                        </li>
                        <li>
                            <a href="/admin/staff/material/staff/add"><i class="icon icon-lg">add</i>&nbsp;添加原材料类型</a>
                        </li>
                        <li>
                            <a href="/admin/staff/material/staff/buy"><i class="icon icon-lg">attach_money</i>&nbsp;购入原材料</a>
                        </li>
                        <li>
                            <a href="/admin/staff/material/staff/in"><i class="icon icon-lg">add_box</i>&nbsp;原材料入库</a>
                        </li>
                        <li>
                            <a href="/admin/staff/material/staff/out"><i class="icon icon-lg">announcement</i>&nbsp;原材料出库</a>
                        </li>
                        <li>
                            <a href="/admin/staff/material/staff/record"><i class="icon icon-lg">account_balance_wallet</i>&nbsp;查看出入库记录</a>
                        </li>
                    </ul>
            </ul>
        </div>
    </div>
</nav>
<main class="content">
    <div class="content-header ui-content-header">
        <div class="container">
            <h1 class="content-heading">员工中心</h1>
        </div>
    </div>
    <div class="container">
        <section class="content-inner margin-top-no">
            <div class="ui-card-wrap">
                <div class="col-xx-12 col-xs-6 col-lg-3">
                    <div class="card user-info">
                        <div class="user-info-main">
                            <div class="nodemain">
                                <div class="nodehead node-flex">
                                    <div class="nodename">操作人员</div>
                                </div>
                                <div class="nodemiddle node-flex">
                                    <div class="nodetype">
                                        <% out.print(materialStaff.getName());%>
                                    </div>
                                </div>
                            </div>
                            <div class="nodestatus">
                                <div class="infocolor-red">
                                    <i class="icon icon-md t4-text">person</i>
                                </div>
                            </div>
                        </div>
                        <div class="user-info-bottom">
                            <div class="nodeinfo node-flex">
                                <span></span>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-xx-12 col-xs-6 col-lg-3">
                    <div class="card user-info">
                        <div class="user-info-main">
                            <div class="nodemain">
                                <div class="nodehead node-flex">
                                    <div class="nodename">部门</div>
                                </div>
                                <div class="nodemiddle node-flex">
                                    <div class="nodetype">
                                        原材料库
                                    </div>
                                </div>
                            </div>
                            <div class="nodestatus">
                                <div class="infocolor-green">
                                    <i class="icon icon-md">account_balance</i>
                                </div>
                            </div>
                        </div>
                        <div class="user-info-bottom">
                            <div class="nodeinfo node-flex">
                                <span></span>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-xx-12 col-xs-6 col-lg-3">
                    <div class="card user-info">
                        <div class="user-info-main">
                            <div class="nodemain">
                                <div class="nodehead node-flex">
                                    <div class="nodename">职务</div>
                                </div>
                                <div class="nodemiddle node-flex">
                                    <div class="nodetype">
                                        <% out.print(materialStaff.getPosition());%>
                                    </div>
                                </div>
                            </div>
                            <div class="nodestatus">
                                <div class="infocolor-yellow">
                                    <i class="icon icon-md">assignment_ind</i>
                                </div>
                            </div>
                        </div>
                        <div class="user-info-bottom">
                            <div class="nodeinfo node-flex">
                                <span></span>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-xx-12 col-xs-6 col-lg-3">
                    <div class="card user-info">
                        <div class="user-info-main">
                            <div class="nodemain">
                                <div class="nodehead node-flex">
                                    <div class="nodename">请输入</div>
                                </div>
                                <div class="nodemiddle node-flex">
                                    <div class="nodetype">
                                        请输入
                                    </div>
                                </div>
                            </div>
                            <div class="nodestatus">
                                <div class="infocolor-blue">
                                    <i class="icon icon-md">settings_input_component</i>
                                </div>
                            </div>
                        </div>
                        <div class="user-info-bottom">
                            <div class="nodeinfo node-flex">
                                <span></span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="ui-card-wrap">
                <div class="col-xx-12 col-sm-4">
                    <div class="card">
                        <div class="card-main">
                            <div class="card-inner">
                                <div class="progressbar">
                                    <div class="before"></div>
                                    <div id="readyConfirmOrders-bar" class="bar tuse color" style="width:calc(0%);"></div>
                                    <div class="label-flex">
                                        <div class="label la-top">
                                            <div class="bar ard color"></div>
                                            <span class="traffic-info">整个啥？</span>
                                            <code class="card-tag tag-green" id="readyConfirmOrders">0</code>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                    <div class="card">
                        <div class="card-main">
                            <div class="card-inner margin-bottom-no">
                                <p class="card-heading"><i class="icon icon-md">account_circle</i>&nbsp;货物存量</p>
                                <dl class="dl-horizontal">
                                    <dt>面包</dt>
                                    <i class="icon icon-md">widgets</i>
                                    <span class="label-level-expire">剩余</span>
                                    <code><span id="days-level-expire">100</span></code>
                                    <span class="label-level-expire">吨</span>

                                    <dt>果汁</dt>
                                    <i class="icon icon-md">widgets</i>
                                    <span class="label-account-expire">剩余</span>
                                    <code><span id="days-account-expire">43</span></code>
                                    <span class="label-account-expire">吨</span>
                                </dl>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-xx-12 col-sm-8">
                    <div class="card">
                        <div class="card-main">
                            <div class="card-inner margin-bottom-no">
                                <p class="card-heading"><i class="icon icon-md">notifications_active</i>&nbsp;消息栏</p>
                                <hr />
                                <p>公告1</p>
                                <p>公告2</p>
                                <p>公告3</p>
                                <br />
                            </div>
                        </div>
                    </div>
                    <div class="card">
                        <div class="card-main">
                            <div class="card-inner margin-bottom-no">
                                <p class="card-heading"><i class="icon icon-md">notifications_active</i>&nbsp;温馨提示</p>
                                <p>1. 内容1</p>
                                <p>2. 内容2</p>
                                <p>3. 内容3</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>
</main>
<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.17.0" type="text/javascript"></script>
<script src="../../../../theme/js/base.min.js" type="text/javascript"></script>
<script src="../../../../theme/js/project.min.js" type="text/javascript"></script>
</body>
</html>
<%--<script>--%>
<%--    $(document).ready(function () {--%>
<%--        //填入订单信息--%>
<%--        {--%>
<%--            $.ajax({--%>
<%--                type: "POST",--%>
<%--                url: "/admin/staff/material/staff/orderInfo",--%>
<%--                dataType: "json",--%>
<%--                traditional: true,--%>
<%--                data: {--%>
<%--                },--%>
<%--                success: function(data){--%>
<%--                    var readyConfirmOrders = data.readyConfirmOrders;--%>
<%--                    var readyDeliverOrders = data.readyDeliverOrders;--%>
<%--                    var inProductionOrders = data.inProductionOrders;--%>
<%--                    var goodsReturnOrders = data.goodsReturnOrders;--%>
<%--                    if (readyConfirmOrders != null)--%>
<%--                    {--%>
<%--                        document.getElementById('readyConfirmOrders').innerHTML = readyConfirmOrders.length;--%>
<%--                        document.getElementById('readyConfirmOrders-bar').style.setProperty('width','calc('+readyConfirmOrders.length+'%)');--%>
<%--                    }--%>
<%--                    if (readyDeliverOrders != null)--%>
<%--                    {--%>
<%--                        document.getElementById('readyDeliverOrders').innerHTML = readyDeliverOrders.length;--%>
<%--                        document.getElementById('readyDeliverOrders-bar').style.setProperty('width','calc('+readyDeliverOrders.length+'%)');--%>
<%--                    }--%>
<%--                    if (goodsReturnOrders != null)--%>
<%--                    {--%>
<%--                        document.getElementById('goodsReturnOrders').innerHTML = goodsReturnOrders.length;--%>
<%--                        document.getElementById('goodsReturnOrders-bar').style.setProperty('width','calc('+goodsReturnOrders.length+'%)');--%>
<%--                    }--%>
<%--                    if (inProductionOrders != null)--%>
<%--                    {--%>
<%--                        document.getElementById('inProductionOrders').innerHTML = inProductionOrders.length;--%>
<%--                        document.getElementById('inProductionOrders-bar').style.setProperty('width','calc('+inProductionOrders.length+'%)');--%>
<%--                    }--%>
<%--                },--%>
<%--                error: (jqXHR) => {--%>
<%--                    $("#result").modal();--%>
<%--                    document.getElementById('msg').innerHTML = `发生了错误`;--%>
<%--                }--%>
<%--            });--%>
<%--        }--%>
<%--    })--%>
<%--</script>--%>
