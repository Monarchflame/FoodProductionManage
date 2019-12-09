<%--
  Created by IntelliJ IDEA.
  User: 10703
  Date: 2019/9/23
  Time: 10:35
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
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/labellogo.jpg" type="image/x-icon">
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
                    <a class="padding-right-cd waves-attach" href="${pageContext.request.contextPath}/admin"><span class="icon icon-lg margin-right">exit_to_app</span>登出</a>
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
                            <a href="${pageContext.request.contextPath}/user/myorder"><i class="icon icon-lg">event_note</i>&nbsp;我的订单</a>
                        </li>
                    </ul>

                    <a class="waves-attach" data-toggle="collapse" href="#ui_menu_sale">业务</a>
                    <ul class="menu-collapse collapse in" id="ui_menu_sale">
                        <li>
                            <a href=""><i class="icon icon-lg">shopping_basket</i>&nbsp;商城</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/user/exchange"><i class="icon icon-lg">compare_arrows</i>&nbsp;换货</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/user/return"><i class="icon icon-lg">sync_problem</i>&nbsp;退货</a>
                        </li>
                    </ul>
            </ul>
        </div>
    </div>
</nav>

<main class="content">
    <div class="content-header ui-content-header">
        <div class="container">
            <h1 class="content-heading">购物车</h1>
        </div>
    </div>
    <div class="container">
        <section class="content-inner margin-top-no">
            <div class="row">
                <div class="col-lg-12 col-md-12">
                    <div class="card margin-bottom-no">
                        <div class="card-main">
                            <div class="card-inner">
                                <div class="card-table">
                                    <div class="table-responsive table-user">
                                        <table class="table table-fixed" id="clientTable">
                                            <tbody>
                                            <tr>
                                                <th><input type="checkbox" lay-filter="checkall" name="" lay-skin="primary" id="checkall"></th>
                                                <th>商品名</th>
                                                <th>单价</th>
                                                <th>数量</th>
                                                <th>金额</th>
                                            </tr>
                                            <c:forEach items="${clients}" var="c" varStatus="status">
                                                <tr>
                                                    <td><input type="checkbox" lay-filter="checkall" name="" lay-skin="primary" id="check"></td>
                                                    <td>${c.name}</td>
                                                    <td>${c.unitprice}</td>
                                                    <td>${c.number}</td>
                                                    <td>${c.accountbalance}</td>
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
            </div>
        </section>
    </div>
</main>
<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.17.0" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/theme/js/base.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/theme/js/project.min.js" type="text/javascript"></script>
<script src="https://cdn.jsdelivr.net/npm/shake.js@1.2.2/shake.min.js" type="text/javascript"></script>

</body>
</html>
