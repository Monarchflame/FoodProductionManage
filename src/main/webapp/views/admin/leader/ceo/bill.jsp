<%--
  Created by IntelliJ IDEA.
  User: 10703
  Date: 2019/10/7
  Time: 16:49
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
    <title>员工界面</title>

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
                    <a class="waves-attach" href="/admin/sale/staff"><span class="icon icon-lg margin-right">account_box</span>员工中心</a>
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
                            <a href=""><i class="icon icon-lg">account_balance_wallet</i>&nbsp;CEO中心</a>
                        </li>
                        <li>
                            <a href="/admin/account"><i class="icon icon-lg">account_box</i>&nbsp;账户信息</a>
                        </li>
                    </ul>

                    <a class="waves-attach" data-toggle="collapse" href="#ui_menu_sale">销售部</a>
                    <ul class="menu-collapse collapse in" id="ui_menu_sale">
                        <li>
                            <a href="/admin/ceo/order-list"><i class="icon icon-lg">account_balance_wallet</i>&nbsp;查看订单列表</a>
                        </li>
                    </ul>

                    <a class="waves-attach" data-toggle="collapse" href="#ui_menu_finance">财务部</a>
                    <ul class="menu-collapse collapse in" id="ui_menu_finance">
                        <li>
                            <a href="/admin/ceo/bill"><i class="icon icon-lg">account_balance_wallet</i>&nbsp;查看账单列表</a>
                        </li>
                    </ul>

                    <a class="waves-attach" data-toggle="collapse" href="#ui_menu_material">原材料库</a>
                    <ul class="menu-collapse collapse in" id="ui_menu_material">
                        <li>
                            <a href="/admin/ceo/material-list"><i class="icon icon-lg">account_balance_wallet</i>&nbsp;查看原材料列表</a>
                        </li>
                        <li>
                            <a href="/admin/ceo/material-record"><i class="icon icon-lg">account_balance_wallet</i>&nbsp;查看原材料记录</a>
                        </li>
                    </ul>

                    <a class="waves-attach" data-toggle="collapse" href="#ui_menu_plan">生产计划科</a>
                    <ul class="menu-collapse collapse in" id="ui_menu_plan">
                        <li>
                            <a href="/admin/ceo/plan-list"><i class="icon icon-lg">account_balance_wallet</i>&nbsp;查看生产计划列表</a>
                        </li>
                    </ul>

                    <a class="waves-attach" data-toggle="collapse" href="#ui_menu_product">成品库</a>
                    <ul class="menu-collapse collapse in" id="ui_menu_product">
                        <li>
                            <a href="/admin/ceo/product-list"><i class="icon icon-lg">account_balance_wallet</i>&nbsp;查看成品列表</a>
                        </li>
                        <li>
                            <a href="/admin/ceo/product-record"><i class="icon icon-lg">account_balance_wallet</i>&nbsp;查看成品记录</a>
                        </li>
                    </ul>
            </ul>
        </div>
    </div>
</nav>

<main class="content">
    <div class="content-header ui-content-header">
        <div class="container">
            <h1 class="content-heading">查看账单</h1>
        </div>
    </div>
    <div class="container">
        <section class="content-inner margin-top-no">
            <div class="ui-card-wrap">
                <div class="row">
                    <div class="col-lg-12 col-sm-12 nodelist">
                        <div class="node-cardgroup" style="display: grid">
                            <div class="nodetitle">
                                <a class="waves-effect waves-button" data-toggle="collapse" href="#ordergroup0" aria-expanded="true" aria-controls="cardgroup0">
                                    <span>该月账单</span><i class="material-icons">expand_more</i>
                                </a>
                            </div>
                            <div class="card-row collapse in" id="ordergroup0">
                            </div>

                            <div class="nodetitle">
                                <a class="waves-effect waves-button" data-toggle="collapse" href="#ordergroup1" aria-expanded="true" aria-controls="cardgroup1">
                                    <span>往期账单</span><i class="material-icons">expand_more</i>
                                </a>
                            </div>
                            <div class="card-row collapse in" id="ordergroup1">
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

<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.17.0" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/theme/js/base.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/theme/js/project.min.js" type="text/javascript"></script>
</body>
</html>
<script type="text/javascript">
    $(document).ready(function(){
        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/admin/ceo/bill",
            dataType: "json",
            data: {
            },
            //查询商品及客户信息返回相应数据
            success: function(data) {
                var financeList = eval(data.financeList);
                var financeHTML = [];
                var financeHTML2 = [];
                for(let i = 0;i < financeList.length;i++) {
                    let finance = financeList[i];
                    var create_time = new Date(finance.create_time); // 获取时间戳
                    var month = create_time.getMonth() + 1; //得到日期月份
                    var date = new Date(); //得到当前日期原始模式
                    var nowmonth = date.getMonth() + 1;//得到当前月份
                    create_time = create_time.toLocaleString();
                    if (month === nowmonth)
                        financeHTML.push('<div class="node-card node-flex" onclick="financeInfo(' + finance.id + ')">' +
                            '<div class="nodemain">' +
                            '<div class="nodehead node-flex">' +
                            '<div class="nodename">' +'账单编号：'+ finance.id + '</div>' +
                            '</div>' +
                            '<div class="nodemiddle node-flex">' +
                            '<div class="onlinemember node-flex">' +
                            '<i class="material-icons node-icon">grade</i><span> ' + finance.type + '</span>' +
                            '</div>' +
                            '<div class="nodetype"></div>' +
                            '</div>' +
                            '<div class="nodeinfo node-flex">' +
                            '<div class="nodetraffic node-flex">' +
                            '<i class="material-icons node-icon">attach_money</i><span>交易金额</span>' +
                            '</div>' +
                            '<div class="nodetype">' + finance.money + '</div>' +
                            '<div class="nodecheck node-flex">' +
                            '<i class="material-icons node-icon">archive</i><span>交易时间</span>' +
                            '</div>' +
                            '<div class="nodetype">' + create_time + '</div>' +
                            '</div></div></div>'
                        );
                    else
                        financeHTML2.push('<div class="node-card node-flex" onclick="financeInfo(' + finance.id + ')">' +
                            '<div class="nodemain">' +
                            '<div class="nodehead node-flex">' +
                            '<div class="nodename">' +'账单编号：'+ finance.id + '</div>' +
                            '</div>' +
                            '<div class="nodemiddle node-flex">' +
                            '<div class="onlinemember node-flex">' +
                            '<i class="material-icons node-icon">grade</i><span> ' + finance.type + '</span>' +
                            '</div>' +
                            '<div class="nodetype"></div>' +
                            '</div>' +
                            '<div class="nodeinfo node-flex">' +
                            '<div class="nodetraffic node-flex">' +
                            '<i class="material-icons node-icon">attach_money</i><span>交易金额</span>' +
                            '</div>' +
                            '<div class="nodetype">' + finance.money + '</div>' +
                            '<div class="nodecheck node-flex">' +
                            '<i class="material-icons node-icon">archive</i><span>交易时间</span>' +
                            '</div>' +
                            '<div class="nodetype">' + create_time + '</div>' +
                            '</div></div></div>'
                        );
                }
                $('#ordergroup0').html(financeHTML.join(''));
                $('#ordergroup1').html(financeHTML2.join(''));
            },
            error: function(){
                $("#result").modal();
                document.getElementById('msg').innerHTML = `发生了错误`;
            }
        })
    });
    //查看账单详细信息
    function financeInfo(id) {
        document.getElementById('infoifram').src = '/admin/ceo/financeInfo?id=' + id;
        $("#orderinfo").modal();
    }
</script>