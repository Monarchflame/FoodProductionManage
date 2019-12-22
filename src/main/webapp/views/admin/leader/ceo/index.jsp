<%--
  Created by IntelliJ IDEA.
  User: 10703
  Date: 2019/11/21
  Time: 14:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-cn">
<head>
    <meta charset="UTF-8">
    <meta content="IE=edge" http-equiv="X-UA-Compatible">
    <meta content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no, width=device-width" name="viewport">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/labellogo.jpg" type="image/x-icon">
    <meta name="theme-color" content="#4285f4">
    <title>CEO界面</title>

    <link href="${pageContext.request.contextPath}/theme/css/base.min.css" rel="stylesheet">
    <!--小图片-->
    <link href="${pageContext.request.contextPath}/theme/css/project.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Material+Icons" rel="stylesheet">
    <!--柱状图样式-->
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
                    <a class="waves-attach" href=""><span class="icon icon-lg margin-right">account_box</span>CEO中心</a>
                </li>
                <li>
                    <a class="padding-right-cd waves-attach" href="${pageContext.request.contextPath}/admin"><span class="icon icon-lg margin-right">exit_to_app</span>登出</a>
                </li>
            </ul>
        </div>
    </ul>
</header>

<nav aria-hidden="true" class="menu menu-left nav-drawer nav-drawer-md" id="ui_menu" tabindex="-1">
    <div class="menu-scroll">
        <div class="menu-content">

            <a class="menu-logo" href="${pageContext.request.contextPath}/admin"><i class="icon icon-lg">language</i>&nbsp后台</a>
            <ul class="nav">
                <li>
                    <a class="waves-attach" data-toggle="collapse" href="#ui_menu_me">我的</a>
                    <ul class="menu-collapse collapse in" id="ui_menu_me">
                        <li>
                            <a href="${pageContext.request.contextPath}/admin/ceo"><i class="icon icon-lg">account_balance_wallet</i>&nbsp;CEO中心</a>
                        </li>
                    </ul>

                    <a class="waves-attach" data-toggle="collapse" href="#ui_menu_sale">销售部</a>
                    <ul class="menu-collapse collapse in" id="ui_menu_sale">
                        <li>
                            <a href="${pageContext.request.contextPath}/admin/ceo/order-list"><i class="icon icon-lg">account_balance_wallet</i>&nbsp;查看订单列表</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/admin/ceo/client-list"><i class="icon icon-lg">account_box</i>&nbsp;管理客户信息</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/admin/ceo/strategy"><i class="icon icon-lg">announcement</i>&nbsp;管理销售策略</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/admin/ceo/returnOrder"><i class="icon icon-lg">sync_problem</i>&nbsp;处理退货</a>
                        </li>
                    </ul>

                    <a class="waves-attach" data-toggle="collapse" href="#ui_menu_finance">财务部</a>
                    <ul class="menu-collapse collapse in" id="ui_menu_finance">
                        <li>
                            <a href="${pageContext.request.contextPath}/admin/ceo/bill"><i class="icon icon-lg">account_balance_wallet</i>&nbsp;查看账单列表</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/admin/ceo/finance_returnOrder"><i class="icon icon-lg">account_box</i>&nbsp;确认退货单</a>
                        </li>
                    </ul>

                    <a class="waves-attach" data-toggle="collapse" href="#ui_menu_material">原材料库</a>
                    <ul class="menu-collapse collapse in" id="ui_menu_material">
                        <li>
                            <a href="${pageContext.request.contextPath}/admin/ceo/material-list"><i class="icon icon-lg">account_balance_wallet</i>&nbsp;查看原材料列表</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/admin/ceo/addMaterial"><i class="icon icon-lg">add</i>&nbsp;添加原材料类型</a>
                        </li>

                        <li>
                            <a href="${pageContext.request.contextPath}/admin/ceo/inMaterial"><i class="icon icon-lg">add_box</i>&nbsp;原材料入库</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/admin/ceo/outMaterial"><i class="icon icon-lg">announcement</i>&nbsp;原材料出库</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/admin/ceo/material-record"><i class="icon icon-lg">account_balance_wallet</i>&nbsp;查看原材料记录</a>
                        </li>
                    </ul>

                    <a class="waves-attach" data-toggle="collapse" href="#ui_menu_plan">生产计划科</a>
                    <ul class="menu-collapse collapse in" id="ui_menu_plan">
                        <li>
                            <a href="${pageContext.request.contextPath}/admin/ceo/drawUpPlan"><i class="icon icon-lg">account_box</i>&nbsp;制定生产计划</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/admin/ceo/plan-list"><i class="icon icon-lg">account_balance_wallet</i>&nbsp;查看生产计划列表</a>
                        </li>
                    </ul>

                    <a class="waves-attach" data-toggle="collapse" href="#ui_menu_product">成品库</a>
                    <ul class="menu-collapse collapse in" id="ui_menu_product">
                        <li>
                            <a href="${pageContext.request.contextPath}/admin/ceo/product-list"><i class="icon icon-lg">account_balance_wallet</i>&nbsp;查看成品列表</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/admin/ceo/addProduct"><i class="icon icon-lg">add</i>&nbsp;添加产品类型</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/admin/ceo/inProduct"><i class="icon icon-lg">add_box</i>&nbsp;货物入库</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/admin/ceo/outProduct"><i class="icon icon-lg">announcement</i>&nbsp;货物出库</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/admin/ceo/product_returnOrder"><i class="icon icon-lg">announcement</i>&nbsp;处理退货</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/admin/ceo/product-record"><i class="icon icon-lg">account_balance_wallet</i>&nbsp;查看成品记录</a>
                        </li>
                    </ul>

                    <a class="waves-attach" data-toggle="collapse" href="#ui_menu_workshop">生产车间</a>
                    <ul class="menu-collapse collapse in" id="ui_menu_workshop">
                        <li>
                            <a href="${pageContext.request.contextPath}/admin/ceo/workshop-plan-list"><i class="icon icon-lg">account_box</i>&nbsp;查看生产计划</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/admin/ceo/workshop-staff-list"><i class="icon icon-lg">account_box</i>&nbsp;查看员工</a>
                        </li>
                    </ul>
            </ul>
        </div>
    </div>
</nav>

<main class="content">
    <div class="content-header ui-content-header">
        <div class="container">
            <h1 class="content-heading">CEO中心</h1>
        </div>
    </div>
    <div class="container">
        <section class="content-inner margin-top-no">
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
                                            <span class="traffic-info">待确认订单</span>
                                            <code class="card-tag tag-green" id="readyConfirmOrders">0</code>
                                        </div>
                                    </div>
                                </div>
                                <div class="progressbar">
                                    <div class="before"></div>
                                    <div id="readyDeliverOrders-bar" class="bar ard color2" style="width:calc(0%);">
                                        <span></span>
                                    </div>
                                    <div class="label-flex">
                                        <div class="label la-top">
                                            <div class="bar ard color2"><span></span></div>
                                            <span class="traffic-info">待发货订单</span>
                                            <code class="card-tag tag-orange" id="readyDeliverOrders">0</code>
                                        </div>
                                    </div>
                                </div>
                                <div class="progressbar">
                                    <div class="before"></div>
                                    <div id="goodsReturnOrders-bar" class="bar remain color3" style="width:calc(0%);">
                                        <span></span>
                                    </div>
                                    <div class="label-flex">
                                        <div class="label la-top">
                                            <div class="bar ard color3"><span></span></div>
                                            <span class="traffic-info">待处理退货订单</span>
                                            <code class="card-tag tag-red" id="goodsReturnOrders">0</code>
                                        </div>
                                    </div>
                                </div>
                                <div class="progressbar">
                                    <div class="before"></div>
                                    <div id="inProductionOrders-bar" class="bar ard color4" style="width:calc(0%);">
                                        <span></span>
                                    </div>
                                    <div class="label-flex">
                                        <div class="label la-top">
                                            <div class="bar ard color4"><span></span></div>
                                            <span class="traffic-info">生产中的订单</span>
                                            <code class="card-tag tag-blue" id="inProductionOrders">0</code>
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
                                <dl id="repertory" class="dl-horizontal">

                                </dl>
                            </div>
                        </div>
                    </div>
                    <div class="card">
                        <div class="card-main">
                            <div class="card-inner">
                                <div class="progressbar">
                                    <div class="before"></div>
                                    <div id="readyProcessRequirementList-bar" class="bar tuse color" style="width:calc(0%);"></div>
                                    <div class="label-flex">
                                        <div class="label la-top">
                                            <div class="bar ard color"></div>
                                            <span class="traffic-info">待确认生产要求</span>
                                            <code class="card-tag tag-green" id="readyProcessRequirementList">0</code>
                                        </div>
                                    </div>
                                </div>
                                <div class="progressbar">
                                    <div class="before"></div>
                                    <div id="readyProductionPlan-bar" class="bar ard color2" style="width:calc(0%);">
                                        <span></span>
                                    </div>
                                    <div class="label-flex">
                                        <div class="label la-top">
                                            <div class="bar ard color2"><span></span></div>
                                            <span class="traffic-info">未执行生产计划</span>
                                            <code class="card-tag tag-orange" id="readyProductionPlan">0</code>
                                        </div>
                                    </div>
                                </div>
                                <div class="progressbar">
                                    <div class="before"></div>
                                    <div id="inProductionPlan-bar" class="bar ard color3" style="width:calc(0%);">
                                        <span></span>
                                    </div>
                                    <div class="label-flex">
                                        <div class="label la-top">
                                            <div class="bar ard color2"><span></span></div>
                                            <span class="traffic-info">执行中生产计划</span>
                                            <code class="card-tag tag-orange" id="inProductionPlan">0</code>
                                        </div>
                                    </div>
                                </div>
                                <div class="progressbar">
                                    <div class="before"></div>
                                    <div id="productionCompletedPlan-bar" class="bar ard color4" style="width:calc(0%);">
                                        <span></span>
                                    </div>
                                    <div class="label-flex">
                                        <div class="label la-top">
                                            <div class="bar ard color2"><span></span></div>
                                            <span class="traffic-info">已执行生产计划</span>
                                            <code class="card-tag tag-orange" id="productionCompletedPlan">0</code>
                                        </div>
                                    </div>
                                </div>
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
                </div>
            </div>
        </section>
    </div>
</main>
<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.17.0" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/theme/js/base.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/theme/js/project.min.js" type="text/javascript"></script>
</body>
</html>
<script>
    $(document).ready(function () {
        //填入订单信息
        {
            $.ajax({
                type: "POST",
                url: "${pageContext.request.contextPath}/admin/staff/sale/staff/orderInfo",
                dataType: "json",
                traditional: true,
                data: {
                },
                success: function(data){
                    var readyConfirmOrders = data.readyConfirmOrders;
                    var readyDeliverOrders = data.readyDeliverOrders;
                    var inProductionOrders = data.inProductionOrders;
                    var goodsReturnOrders = data.goodsReturnOrders;
                    if (readyConfirmOrders != null)
                    {
                        document.getElementById('readyConfirmOrders').innerHTML = readyConfirmOrders.length;
                        document.getElementById('readyConfirmOrders-bar').style.setProperty('width','calc('+readyConfirmOrders.length+'%)');
                    }
                    if (readyDeliverOrders != null)
                    {
                        document.getElementById('readyDeliverOrders').innerHTML = readyDeliverOrders.length;
                        document.getElementById('readyDeliverOrders-bar').style.setProperty('width','calc('+readyDeliverOrders.length+'%)');
                    }
                    if (goodsReturnOrders != null)
                    {
                        document.getElementById('goodsReturnOrders').innerHTML = goodsReturnOrders.length;
                        document.getElementById('goodsReturnOrders-bar').style.setProperty('width','calc('+goodsReturnOrders.length+'%)');
                    }
                    if (inProductionOrders != null)
                    {
                        document.getElementById('inProductionOrders').innerHTML = inProductionOrders.length;
                        document.getElementById('inProductionOrders-bar').style.setProperty('width','calc('+inProductionOrders.length+'%)');
                    }
                },
                error: function() {
                    $("#result").modal();
                    document.getElementById('msg').innerHTML = `发生了错误`;
                }
            });
        }
        //填入要求生产计划信息
        {
            $.ajax({
                type: "POST",
                url: "${pageContext.request.contextPath}/admin/staff/plan/staff/productPlanList",
                dataType: "json",
                traditional: true,
                data: {
                },
                success: function(data){
                    var planList = data.planList;
                    var readyProcessRequirementList = data.readyProcessRequirementList;
                    var readyProductionPlan = 0;
                    var inProductionPlan = 0;
                    var productionCompletedPlan = 0;
                    if(planList != null)
                    {
                        for (var i=0; i<planList.length; i++)
                        {
                            if (planList[i].status === "未执行")
                            {
                                readyProductionPlan++;
                            }
                            else if (planList[i].status === "执行中")
                            {
                                inProductionPlan++;
                            }
                            else if (planList[i].status === "已执行")
                            {
                                productionCompletedPlan++;
                            }
                        }
                        document.getElementById('readyProductionPlan').innerHTML = readyProductionPlan;
                        document.getElementById('readyProductionPlan-bar').style.setProperty('width','calc('+readyProductionPlan+'%)');

                        document.getElementById('inProductionPlan').innerHTML = inProductionPlan;
                        document.getElementById('inProductionPlan-bar').style.setProperty('width','calc('+inProductionPlan+'%)');

                        document.getElementById('productionCompletedPlan').innerHTML = productionCompletedPlan;
                        document.getElementById('productionCompletedPlan-bar').style.setProperty('width','calc('+productionCompletedPlan+'%)');
                    }

                    if (readyProcessRequirementList != null)
                    {
                        document.getElementById('readyProcessRequirementList').innerHTML = readyProcessRequirementList.length;
                        document.getElementById('readyProcessRequirementList-bar').style.setProperty('width','calc('+readyProcessRequirementList.length+'%)');
                    }
                },
                error: function() {
                    $("#result").modal();
                    document.getElementById('msg').innerHTML = `发生了错误`;
                }
            });
            //查找库存
            {
                $.ajax({
                    type: "POST",
                    url: "${pageContext.request.contextPath}/admin/staff/sale/staff/repertory",
                    dataType: "json",
                    data: {
                    },
                    success: function(data){
                        let productInfoList = data.productInfoList;
                        let html=[];
                        for (let i=0;i<productInfoList.length;i++)
                        {
                            let productInfo = productInfoList[i];
                            html.push("<dt>"+ productInfo.product.name +"</dt>\n" +
                                "                                    <i class=\"icon icon-md\">widgets</i>\n" +
                                "                                    <span class=\"label-account-expire\">库存</span>\n" +
                                "                                    <code><span id=\"days-account-expire\">"+productInfo.quantity+"</span></code>"
                            )
                        }
                        $('#repertory').html(html.join(''));
                    },
                    error: function() {
                        $("#result").modal();
                        document.getElementById('msg').innerHTML = `发生了错误`;
                    }
                });
            }
        }

    })
</script>