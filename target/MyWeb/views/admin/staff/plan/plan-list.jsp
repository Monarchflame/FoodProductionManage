<%--
  Created by IntelliJ IDEA.
  User: 10703
  Date: 2019/11/9
  Time: 16:48
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
                            <a href="/admin/staff/plan/staff"><i class="icon icon-lg">account_balance_wallet</i>&nbsp;员工中心</a>
                        </li>
                        <li>
                            <a href="/admin/account"><i class="icon icon-lg">account_box</i>&nbsp;账户信息</a>
                        </li>
                    </ul>

                    <a class="waves-attach" data-toggle="collapse" href="#ui_menu_sale">销售部</a>
                    <ul class="menu-collapse collapse in" id="ui_menu_sale">
                        <li>
                            <a href="/admin/staff/plan/staff/drawUpPlan"><i class="icon icon-lg">account_box</i>&nbsp;制定生产计划</a>
                        </li>
                        <li>
                            <a href=""><i class="icon icon-lg">announcement</i>&nbsp;查看生产计划</a>
                        </li>
                    </ul>
            </ul>
        </div>
    </div>
</nav>

<main class="content">
    <div class="content-header ui-content-header">
        <div class="container">
            <h1 class="content-heading">生产计划</h1>
        </div>
    </div>
    <div class="container">
        <section class="content-inner margin-top-no">
            <div class="ui-card-wrap">
                <div class="row">
                    <div class="col-lg-12 col-sm-12 nodelist">
                        <div class="node-cardgroup" style="display: grid">
                            <div class="nodetitle">
                                <a class="waves-effect waves-button" data-toggle="collapse" href="#plangroup0" aria-expanded="true" aria-controls="cardgroup0">
                                    <span>待执行计划</span><i class="material-icons">expand_more</i>
                                </a>
                            </div>
                            <div class="card-row collapse in" id="plangroup0">
                            </div>

                            <div class="nodetitle">
                                <a class="waves-effect waves-button" data-toggle="collapse" href="#plangroup1" aria-expanded="true" aria-controls="cardgroup1">
                                    <span>生产中计划</span><i class="material-icons">expand_more</i>
                                </a>
                            </div>
                            <div class="card-row collapse in" id="plangroup1">
                            </div>

                            <div class="nodetitle">
                                <a class="waves-effect waves-button" data-toggle="collapse" href="#plangroup2" aria-expanded="true" aria-controls="cardgroup2">
                                    <span>已完成计划</span><i class="material-icons">expand_more</i>
                                </a>
                            </div>
                            <div class="card-row collapse in" id="plangroup2">
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

                    <div aria-hidden="true" class="modal modal-va-middle fade" id="planinfo" role="dialog" tabindex="-1">
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
<script>
    $(document).ready(function(){
        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/admin/staff/plan/staff/planList",
            dataType: "json",
            data: {
            },
            //查询生产计划信息返回相应数据
            success: function(data) {
                var readyProcessPlanList = eval(data.readyProcessPlanList);
                var inProductionPlanList = eval(data.inProductionPlanList);
                var completedPlanList = eval(data.completedPlanList);

                var readyProcessPlanHTML = [];
                var inProductionPlanHTML = [];
                var completedPlanHTML = [];
                //未执行计划
                for(let i = 0;i < readyProcessPlanList.length;i++) {
                    let plan = readyProcessPlanList[i];
                    readyProcessPlanHTML.push('<div class="node-card node-flex" onclick="processPlan(' + plan.plan_id + ')">' +
                        '<div class="nodemain">' +
                        '<div class="nodehead node-flex">' +
                        '<div class="nodename">' +'产品编号：'+ plan.product_id + '</div>' +
                        '</div>' +
                        '<div class="nodemiddle node-flex">' +
                        '<div class="onlinemember node-flex">' +
                        '<i class="material-icons node-icon">location_on</i><span>预计完成时间</span>' +
                        '</div>' +
                        '<div class="nodetype">' + (new Date(plan.finish_time)).toLocaleString() + '</div>' +
                        '</div>' +
                        '<div class="nodeinfo node-flex">' +
                        '<div class="nodetraffic node-flex">' +
                        '<i class="material-icons node-icon">person</i><span>产品名</span>' +
                        '</div>' +
                        '<div class="nodetype">' + plan.product_name + '</div>' +
                        '<div class="nodecheck node-flex">' +
                        '<i class="material-icons node-icon">archive</i><span>数量</span>' +
                        '</div>' +
                        '<div class="nodetype">' + plan.quantity + '</div>' +
                        '<div class="nodeband node-flex">' +
                        '<i class="material-icons node-icon">flash_on</i><span>状态</span>' +
                        '</div>' +
                        '<div class="nodetype">' + plan.status + '</div>' +
                        '</div></div></div>')
                }
                //执行中计划
                for(let i = 0;i < inProductionPlanList.length;i++) {
                    let plan = inProductionPlanList[i];
                    inProductionPlanHTML.push('<div class="node-card node-flex" onclick="readyDeliverPlan(' + plan.plan_id + ')">' +
                        '<div class="nodemain">' +
                        '<div class="nodehead node-flex">' +
                        '<div class="nodename">' +'产品编号：'+ plan.product_id + '</div>' +
                        '</div>' +
                        '<div class="nodemiddle node-flex">' +
                        '<div class="onlinemember node-flex">' +
                        '<i class="material-icons node-icon">location_on</i><span>预计完成时间</span>' +
                        '</div>' +
                        '<div class="nodetype">' + (new Date(plan.finish_time)).toLocaleString() + '</div>' +
                        '</div>' +
                        '<div class="nodeinfo node-flex">' +
                        '<div class="nodetraffic node-flex">' +
                        '<i class="material-icons node-icon">person</i><span>产品名</span>' +
                        '</div>' +
                        '<div class="nodetype">' + plan.product_name + '</div>' +
                        '<div class="nodecheck node-flex">' +
                        '<i class="material-icons node-icon">archive</i><span>数量</span>' +
                        '</div>' +
                        '<div class="nodetype">' + plan.quantity + '</div>' +
                        '<div class="nodeband node-flex">' +
                        '<i class="material-icons node-icon">flash_on</i><span>状态</span>' +
                        '</div>' +
                        '<div class="nodetype">' + plan.status + '</div>' +
                        '</div></div></div>')
                }
                //执行完成计划
                for(let i = 0;i < completedPlanList.length;i++) {
                    let plan = completedPlanList[i];
                    completedPlanHTML.push('<div class="node-card node-flex" onclick="inProductionPlan(' + plan.plan_id + ')">' +
                        '<div class="nodemain">' +
                        '<div class="nodehead node-flex">' +
                        '<div class="nodename">' +'产品编号：'+ plan.product_id + '</div>' +
                        '</div>' +
                        '<div class="nodemiddle node-flex">' +
                        '<div class="onlinemember node-flex">' +
                        '<i class="material-icons node-icon">location_on</i><span>预计完成时间</span>' +
                        '</div>' +
                        '<div class="nodetype">' + (new Date(plan.finish_time)).toLocaleString() + '</div>' +
                        '</div>' +
                        '<div class="nodeinfo node-flex">' +
                        '<div class="nodetraffic node-flex">' +
                        '<i class="material-icons node-icon">person</i><span>产品名</span>' +
                        '</div>' +
                        '<div class="nodetype">' + plan.product_name + '</div>' +
                        '<div class="nodecheck node-flex">' +
                        '<i class="material-icons node-icon">archive</i><span>数量</span>' +
                        '</div>' +
                        '<div class="nodetype">' + plan.quantity + '</div>' +
                        '<div class="nodeband node-flex">' +
                        '<i class="material-icons node-icon">flash_on</i><span>状态</span>' +
                        '</div>' +
                        '<div class="nodetype">' + plan.status + '</div>' +
                        '</div></div></div>')
                }

                $('#plangroup0').html(readyProcessPlanHTML.join(''));
                $('#plangroup1').html(inProductionPlanHTML.join(''));
                $('#plangroup2').html(completedPlanHTML.join(''));
            },
            error: function(){
                $("#result").modal();
                document.getElementById('msg').innerHTML = `发生了错误`;
            }
        });
        processPlan = function (id) {
            document.getElementById('infoifram').src = '/admin/staff/plan/staff/processPlanInfo?id=' + id;
            $("#planinfo").modal();
        }
    })
</script>
