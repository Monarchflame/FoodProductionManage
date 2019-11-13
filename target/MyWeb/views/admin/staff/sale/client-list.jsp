<%--
  Created by IntelliJ IDEA.
  User: 10703
  Date: 2019/9/18
  Time: 15:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html lang="zh-cn">
<head>
    <meta charset="UTF-8">
    <meta content="IE=edge" http-equiv="X-UA-Compatible">
    <meta content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no, width=device-width" name="viewport">
    <meta name="theme-color" content="#4285f4">
    <link rel="shortcut icon" href="../../../../images/labellogo.jpg" type="image/x-icon">
    <title>潮汐</title>

    <link href="../../../../theme/css/base.min.css" rel="stylesheet">
    <link href="../../../../theme/css/project.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Material+Icons" rel="stylesheet">
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
                            <a href="/admin/staff/sale/staff"><i class="icon icon-lg">account_balance_wallet</i>&nbsp;员工中心</a>
                        </li>
                        <li>
                            <a href="/admin/account"><i class="icon icon-lg">account_box</i>&nbsp;账户信息</a>
                        </li>
                    </ul>

                    <a class="waves-attach" data-toggle="collapse" href="#ui_menu_sale">销售部</a>
                    <ul class="menu-collapse collapse in" id="ui_menu_sale">
                        <li>
                            <a href=" "><i class="icon icon-lg">account_box</i>&nbsp;管理客户信息</a>
                        </li>
                        <li>
                            <a href="/admin/staff/sale/staff/strategy"><i class="icon icon-lg">announcement</i>&nbsp;管理销售策略</a>
                        </li>
                        <li>
                            <a href="/admin/staff/sale/staff/order"><i class="icon icon-lg">account_balance_wallet</i>&nbsp;接受订货</a>
                        </li>
                        <li>
                            <a href="/admin/staff/sale/staff/returnOrder"><i class="icon icon-lg">sync_problem</i>&nbsp;处理退货</a>
                        </li>
                    </ul>
            </ul>
        </div>
    </div>
</nav>

<main class="content">
    <div class="content-header ui-content-header">
        <div class="container">
            <h1 class="content-heading">客户账户</h1>
        </div>
    </div>
    <div class="container">
        <section class="content-inner margin-top-no">
            <div class="row">

                <div class="col-lg-12 col-md-12">
                    <div class="card margin-bottom-no">
                        <div class="card-main">
                            <div class="card-inner">
                                <div class="cardbtn-edit">
                                    <div class="card-heading">查找</div>
                                    <button class="btn btn-flat" id="findClient"><span class="icon">check</span>&nbsp;
                                    </button>
                                </div>
                                <div class="form-group form-group-label">
                                    <label class="floating-label" for="clientName">客户名</label>
                                    <input class="form-control maxwidth-edit"  autocomplete="off"  name="clientName" id="clientName">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-lg-12 col-md-12">
                    <div class="card margin-bottom-no">
                        <div class="card-main">
                            <div class="card-inner">
                                <div class="card-table">
                                    <div class="table-responsive table-user">
                                        <table class="table table-fixed" id="clientTable">
                                            <tbody>
                                            <tr>
<%--                                                <th><input type="checkbox" lay-filter="checkall" name="" lay-skin="primary"></th>--%>
                                                <th>ID</th>
                                                <th>客户名</th>
                                                <th>类别</th>
                                                <th>信用等级</th>
                                                <th>余额</th>
                                                <th>地址</th>
                                            </tr>
                                            <c:forEach items="${clients}" var="c" varStatus="status">
                                                <tr onclick="javascript:edit('${c.id}');">
<%--                                                    <td><input type="checkbox" lay-filter="checkall" name="" lay-skin="primary"></td>--%>
                                                    <td>${c.id}</td>
                                                    <td>${c.name}</td>
                                                    <td>${c.type}</td>
                                                    <td>${c.creditrating}</td>
                                                    <td>${c.accountbalance}</td>
                                                    <td>${c.address}</td>
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div aria-hidden="true" class="modal modal-va-middle fade" id="clientinfo" role="dialog" tabindex="-1" style="display: none;position: center;">
                    <div class="modal-dialog modal-full">
                        <div class="modal-content">
                            <iframe class="iframe-seamless" title="Modal with iFrame" id="infoifram" src="/admin/staff/sale/staff/clientInfo"></iframe>
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
<script src="https://cdn.jsdelivr.net/npm/shake.js@1.2.2/shake.min.js" type="text/javascript"></script>

</body>
</html>

<%--根据客户名查找客户--%>
<script type="text/javascript">
    $(document).ready(function () {
        $("#findClient").click(function () {
            $.ajax({
                type: "POST",
                url: "/admin/staff/sale/staff/findClient",
                dataType: "json",
                data: {
                    name: $("#clientName").val()
                },
                success:function (data) {
                    var table = [];
                    var list = eval(data);//解析json
                    for(var i = 0;i < list.length;i++){//循环遍历数据
                        var clientInfo = list[i];
                        table.push('<tr>' +
                            '<th>ID</th>' +
                            '<th>客户名</th>' +
                            '<th>类别</th>' +
                            '<th>信用等级</th>' +
                            '<th>余额</th>' +
                            '<th>地址</th>' +
                            '</tr>');
                        table.push('<tr onclick="javascript:edit();"><td>' + clientInfo.id + '</td><td>' + clientInfo.name + '</td><td>' + clientInfo.type + '</td><td>'
                            + clientInfo.creditrating + '</td><td>' + clientInfo.accountbalance + '</td><td>' + clientInfo.address + '</td></tr>')
                    }
                    $('#clientTable').html(table.join(''))
                },
                error: function(){
                    alert('error');
                }
            })
        });
    });
    edit = function(id) {
        document.getElementById('infoifram').src = "/admin/staff/sale/staff/clientInfo?id="+id;
        $("#clientinfo").modal();
    }
</script>
