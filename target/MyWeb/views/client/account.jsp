<%@ page import="cn.qxt.pojo.Client" %><%--
  Created by IntelliJ IDEA.
  User: 10703
  Date: 2019/9/22
  Time: 15:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% Client client=(Client) request.getSession().getAttribute("LOGIN_USER"); %>

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
                            <a href=""><i class="icon icon-lg">account_box</i>&nbsp;账户信息</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/user/myorder"><i class="icon icon-lg">event_note</i>&nbsp;我的订单</a>
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
            <h1 class="content-heading">我的账户</h1>
        </div>
    </div>
    <div class="container">
        <section class="content-inner margin-top-no">
            <div class="row">

                <div class="col-xx-12 col-sm-6">
                    <div class="card margin-bottom-no">
                        <div class="card-main">
                            <div class="card-inner">
                                <div class="cardbtn-edit">
                                    <div class="card-heading">账号登录密码修改</div>
                                    <button class="btn btn-flat" id="password-update"><span class="icon">check</span>&nbsp;
                                    </button>
                                </div>
                                <div class="form-group form-group-label">
                                    <label class="floating-label" for="oldpassword">当前密码</label>
                                    <input class="form-control maxwidth-edit" id="oldpassword" type="password">
                                </div>
                                <div class="form-group form-group-label">
                                    <label class="floating-label" for="password">新密码</label>
                                    <input class="form-control maxwidth-edit" id="password" type="password">
                                </div>
                                <div class="form-group form-group-label">
                                    <label class="floating-label" for="repassword">确认新密码</label>
                                    <input class="form-control maxwidth-edit" id="repassword" type="password">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="card margin-bottom-no">
                        <div class="card-main">
                            <div class="card-inner">
                                <div class="cardbtn-edit">
                                    <div class="card-heading">地址修改</div>
                                    <button class="btn btn-flat" id="address-update"><span class="icon">check</span>&nbsp;
                                    </button>
                                </div>
                                <p>当前地址：
                                    <code><% out.print(client.getAddress());%></code>
                                </p>
                                <div class="form-group form-group-label">
                                    <label class="floating-label" for="address">新地址</label>
                                    <input class="form-control maxwidth-edit" id="address" type="text">
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-xx-12 col-sm-6">
                    <div class="card margin-bottom-no">
                        <div class="card-main">
                            <div class="card-inner">
                                <div class="card-inner">
                                    <div class="cardbtn-edit">
                                        <div class="card-heading">手机号修改</div>
                                        <button class="btn btn-flat" id="phone-update"><span class="icon">check</span>&nbsp;
                                        </button>
                                    </div>
                                    <p>当前手机号：
                                        <code><% out.print(client.getPhone());%></code>
                                    </p>

                                    <div class="form-group form-group-label">
                                        <label class="floating-label" for="phone">在这输入手机号账号</label>
                                        <input class="form-control maxwidth-edit" id="phone" type="text">
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
<div aria-hidden="true" class="modal modal-va-middle fade" id="result" role="dialog" tabindex="-1">
    <div class="modal-dialog modal-xs">
        <div class="modal-content">
            <div class="modal-inner">
                <p class="h5 margin-top-sm text-black-hint" id="msg"></p>
            </div>
            <div class="modal-footer">
                <p class="text-right">
                    <button class="btn btn-flat btn-brand-accent waves-attach" data-dismiss="modal" type="button" id="result_ok">知道了
                    </button>
                </p>
            </div>
        </div>
    </div>
</div>

    <script src="https://cdn.jsdelivr.net/npm/jquery@2.2.1" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/theme/js/project.min.js" type="4text/javascript"></script>
    <!--让汉字跳起来-->
    <script src="${pageContext.request.contextPath}/theme/js/base.min.js" type="text/javascript"></script>
</body>
</html>

<!--修改密码-->
<script type="text/javascript">
    $(document).ready(function () {
        $("#password-update").click(function () {
            $.ajax({
                type: "POST",
                url: "${pageContext.request.contextPath}/user/alterpassword",
                dataType: "json",
                data: {
                    id:<% out.print(client.getId());%>,
                    oldpassword: $("#oldpassword").val(),
                    password: $("#password").val(),
                    repassword: $("#repassword").val()
                },
                success: function(data){
                    $("#result").modal();
                    document.getElementById('msg').innerHTML = data.msg;
                    window.location.reload();//刷新
                },
                error: function(jqXHR){
                    alert("错误")
                }
            })
        });
        $("#address-update").click(function () {
            $.ajax({
                type: "POST",
                url: "${pageContext.request.contextPath}/user/alteraddress",
                dataType: "json",
                data: {
                    id:<% out.print(client.getId());%>,
                    address: $("#address").val(),
                },
                success: function(data){
                    $("#result").modal();
                    document.getElementById('msg').innerHTML = data.msg;
                    window.location.reload();//刷新
                },
                error: function(jqXHR){
                    alert("错误")
                }
            })
        });
        $("#phone-update").click(function () {
            $.ajax({
                type: "POST",
                url: "${pageContext.request.contextPath}/user/alterphone",
                dataType: "json",
                data: {
                    id:<% out.print(client.getId());%>,
                    phone: $("#phone").val(),
                },
                success: function(data){
                    $("#result").modal();
                    document.getElementById('msg').innerHTML = data.msg;
                    window.location.reload();//刷新
                },
                error: function(jqXHR){
                    alert("错误")
                }
            })
        })
    })
</script>