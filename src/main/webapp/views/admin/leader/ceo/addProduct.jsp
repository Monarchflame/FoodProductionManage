<%--
  Created by IntelliJ IDEA.
  User: 10703
  Date: 2019/11/17
  Time: 16:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta content="IE=edge" http-equiv="X-UA-Compatible">
    <meta content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no, width=device-width" name="viewport">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/labellogo.jpg" type="image/x-icon">

    <meta name="theme-color" content="#4285f4">
    <title>原材料入库</title>

    <link href="${pageContext.request.contextPath}/theme/css/base.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/theme/css/project.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/theme/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/theme/css/user.css">

    <script type="text/javascript" src="${pageContext.request.contextPath}/theme/js/jQuery-2.1.4.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/theme/js/jquery.tablesorter.js"></script>

    <script src="https://cdn.jsdelivr.net/gh/davidshimjs/qrcodejs@gh-pages/qrcode.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/theme/js/base.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/theme/js/project.min.js" type="4text/javascript"></script>

</head>

<body class="page-orange" style="background-image: url(${pageContext.request.contextPath}/images/abackground.jpg)" >
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
                            <a href="${pageContext.request.contextPath}/admin/ceo/buyMaterial"><i class="icon icon-lg">attach_money</i>&nbsp;购入原材料</a>
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
    <div class="authpage auth-reg">
        <div class="container" style="width: 50%; height: 50%;" >
            <section>
                <div class="auth-main auth-row">
                    <div class="rowtocol">
                        <div class="auth-row">
                            <div class="form-group-label auth-row">
                                <label class="floating-label" for="name">名称</label>
                                <input class="form-control maxwidth-auth" id="name" name="name" type="text" maxlength="32">
                            </div>
                        </div>
                    </div>
                    <div class="rowtocol">
                        <div class="auth-row">
                            <div class="form-group-label auth-row">
                                <label class="floating-label" for="unit_price">单价</label>
                                <input class="form-control maxwidth-auth" id="unit_price" name="unit_price" type="text" maxlength="32">
                            </div>
                        </div>
                    </div>
                    <div class="rowtocol">
                        <div class="auth-row">
                            <div class="form-group-label auth-row">
                                <label class="floating-label" for="shelf_time">保质期</label>
                                <input class="form-control maxwidth-auth" id="shelf_time" name="shelf_time" type="text">
                            </div>
                        </div>
                    </div>
                    <div class="rowtocol">
                        <div class="auth-row">
                            <div class="form-group-label auth-row">
                                <label class="floating-label" for="specification">规格</label>
                                <input class="form-control maxwidth-auth" id="specification" name="specification" type="text" maxlength="32">
                            </div>
                        </div>
                    </div>

                    <div class="rowtocol">
                        <div class="btn-auth auth-row">
                            <button id="andMaterial" type="submit" class="btn-reg btn btn-block btn-brand waves-attach waves-light">确认添加
                            </button>
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
            </section>
        </div>
    </div>
</main>
</body>
</html>
<script>
    $(document).ready(function(){
        $("#andMaterial").click(function () {
            let name = $("#name").val();
            let unit_price = $("#unit_price").val();
            let shelf_time = $("#shelf_time").val();
            let specification = $("#specification").val();

            if (name === undefined || shelf_time === undefined || unit_price === undefined || specification === undefined)
            {
                $("#result").modal();
                document.getElementById('msg').innerHTML = '信息不完整';
            }
            else
            {
                $.ajax({
                    type: "POST",
                    url: "${pageContext.request.contextPath}/admin/staff/product/staff/addProduct",
                    dataType: "json",
                    data: {
                        name:name,
                        unit_price:unit_price,
                        shelf_time:shelf_time,
                        specification:specification,
                    },
                    success: function(data){
                        if (data.ret === 1) {
                            $("#result").modal();
                            document.getElementById('msg').innerHTML = '添加成功';
                            window.setTimeout("location.href='/admin/staff/product/staff/in'", 1200);
                        } else {
                            $("#result").modal();
                            document.getElementById('msg').innerHTML = '添加失败';
                        }
                    },
                    error: function() {
                        $("#result").modal();
                        document.getElementById('msg').innerHTML = `发生了错误`;
                    }
                })
            }
        });
    })
</script>
